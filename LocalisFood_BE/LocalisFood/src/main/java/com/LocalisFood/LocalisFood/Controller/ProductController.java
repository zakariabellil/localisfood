package com.LocalisFood.LocalisFood.Controller;

import com.LocalisFood.LocalisFood.dto.ProductRequest;
import com.LocalisFood.LocalisFood.dto.ProductResponse;
import com.LocalisFood.LocalisFood.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/products/")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return status(HttpStatus.OK).body(productService.getProducts(id));
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<ProductResponse>> getProductsByUsername(String username) {
        return status(HttpStatus.OK).body(productService.getProductsByUsername(username));
    }
}
