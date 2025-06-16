package com.product.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository repo;

    public ProductEntity addProduct(ProductEntity product){
        return repo.save(product);
    }

    public List<ProductEntity> viewProduct(){
        return repo.findAll();
    }

    public Optional<ProductEntity> ProductById(long productId){
        return Optional.ofNullable(repo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found")));
    }
}
