package com.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/PlaceOrder")
   public Mono<OrderResponseDTO>  placeOrder(@RequestBody OrderEntity order) {
       return service.placeOrder(order)
               .map(response -> ResponseEntity.ok(response).getBody());
   }

    @GetMapping("/viewOrder")
    public ResponseEntity<List<OrderResponseDTO>> viewOrder() {
        return ResponseEntity.ok(service.getAllOrderResponses());
    }

}
