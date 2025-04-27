package com.example.Market;


import com.example.Market.Model.Product;
import com.example.Market.Repositories.ProductRepository;
import com.example.Market.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetProductById() {
        Product mockProduct = new Product(1L, "Мышь", "Современная мышь с быстрым откликом", 1500, "Техника", 400);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Product result = productService.getProductById(1L);

        assertEquals("Мышь", result.getName());
        assertEquals(1500, result.getPrice());
    }
    @Test
    void testUpdateProductSuccessfully() {
        Product original = new Product(1L, "Old Name", "Old Desc", 1000, "OldCat", 10);
        Product updated = new Product(null, "New Name", "New Desc", 1500, "NewCat", 20);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(original));
        Mockito.when(productRepository.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));

        Product result = productService.updateProduct(1L, updated);

        assertEquals("New Name", result.getName());
        assertEquals(1500, result.getPrice());
        assertEquals("NewCat", result.getCategory());
        assertEquals(20, result.getQuan());
        assertEquals("New Desc", result.getDescription());

        Mockito.verify(productRepository).save(original);
    }
    @Test
    void testDeleteProduct() {
        Long id = 1L;
        productService.deleteProduct(id);
        Mockito.verify(productRepository).deleteById(id);
    }
    @Test
    void testDecreaseQuantitySuccessfully() {
        Product product = new Product(1L, "Name", "Desc", 1000, "Cat", 10);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.decreaseQuantity(1L, 3);

        assertEquals(7, product.getQuan());
        Mockito.verify(productRepository).save(product);
    }

}
