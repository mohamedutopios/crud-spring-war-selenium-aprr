package org.example.crudthymeleafselenium.service.impl;

import org.example.crudthymeleafselenium.model.Product;
import org.example.crudthymeleafselenium.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Product p1 = new Product(1L, "Prod1", 10);
        Product p2 = new Product(2L, "Prod2", 20);
        when(productRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Product> result = productService.findAll();

        assertThat(result).hasSize(2);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        Product p = new Product(1L, "Prod1", 10);
        when(productRepository.save(p)).thenReturn(p);

        Product result = productService.save(p);

        assertThat(result.getName()).isEqualTo("Prod1");
        verify(productRepository, times(1)).save(p);
    }

    @Test
    void testDeleteById() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindById() {
        Product p = new Product(1L, "Prod1", 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(p));

        Product result = productService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Prod1");
    }
}
