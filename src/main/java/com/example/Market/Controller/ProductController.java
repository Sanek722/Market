package com.example.Market.Controller;

import com.example.Market.Model.Product;
import com.example.Market.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("/products")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_Admin')")
    public Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}/decrease")
    public void decreaseQuantity(@PathVariable Long id, @RequestParam int quantity) {
        productService.decreaseQuantity(id, quantity);
    }
}


