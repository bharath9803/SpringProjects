package com.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo repo;

    @Autowired
    private WebClient.Builder webclient;

    public Mono<OrderResponseDTO> placeOrder(OrderEntity order) {
        return webclient.build()
                .get()
                .uri("http://localhost:8082/product/" + order.getProductId())
                .retrieve()
                .bodyToMono(ProductDTO.class)
                .map(productDTO -> {
                    // Save the order
                    repo.save(order);

                    // Build response
                    OrderResponseDTO response = new OrderResponseDTO();
                        response.setOrderId(order.getOrderId());
                    response.setProductId(order.getProductId());
                    response.setQuantity(order.getQuantity());
                    response.setProductName(productDTO.getProductName());
                    response.setProductPrice(productDTO.getProductPrice());
                    response.setTotalPrice(order.getQuantity() * productDTO.getProductPrice());

                    return response;
                });
    }

    public List<OrderResponseDTO> getAllOrderResponses() {
        List<OrderEntity> orders = repo.findAll();
        List<OrderResponseDTO> responses = new ArrayList<>();

        for (OrderEntity order : orders) {
            ProductDTO product = webclient.build()
                    .get()
                    .uri("http://localhost:8082/product/" + order.getProductId())
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block(); // blocking for simplicity

            OrderResponseDTO response = new OrderResponseDTO();
            response.setOrderId(order.getOrderId());
            response.setProductId(order.getProductId());
            response.setQuantity(order.getQuantity());
            response.setProductName(product.getProductName());
            response.setProductPrice(product.getProductPrice());
            response.setTotalPrice(order.getQuantity() * product.getProductPrice());

            responses.add(response);
        }

        return responses;
    }

}
