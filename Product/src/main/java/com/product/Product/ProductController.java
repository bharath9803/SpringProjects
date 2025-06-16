package com.product.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/new")
    public ResponseEntity<ProductEntity> newProduct(@RequestBody ProductEntity product){
        ProductEntity products = service.addProduct(product);
        return  ResponseEntity.ok(products);
    }

    @GetMapping("/view")
    public ResponseEntity<List<ProductEntity>> viewAll(){
        return ResponseEntity.ok(service.viewProduct());
    }

    @GetMapping("/{productId}")
    public  ResponseEntity<Optional<ProductEntity>> getByProductId(@PathVariable Long productId){
        Optional<ProductEntity> product = service.ProductById(productId);
        return ResponseEntity.ok(product);
    }
}
