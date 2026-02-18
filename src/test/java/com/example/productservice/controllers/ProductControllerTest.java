package com.example.productservice.controllers;

import com.example.productservice.dtos.products.GetProductResponseDTO;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean(name = "dbProductService")
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Long> idcaptor;

    //testName_When_Then
    @Test
    public void testGetProductById_WhenValidIdIsPassed_ReturnsProductsSuccessfully() throws ProductNotFoundException {

        //Arrange
        Product product = new Product();
        product.setPrice(1000.00);
        product.setDescription("Best IPhone Ever");
        product.setTitle("Iphone 17 pro");
        product.setId(1L);

        when(productService.getProductById(any(Long.class))).thenReturn(product);

        //Act
        ResponseEntity<GetProductResponseDTO> responseEntity = productController.getProductById(1L);

        //Assert
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(1000.00, responseEntity.getBody().getProduct().getPrice());
        assertEquals("Best IPhone Ever",  responseEntity.getBody().getProduct().getDescription());
        assertEquals("Iphone 17 pro" , responseEntity.getBody().getProduct().getTitle());
//        assertEquals(1L , responseEntity.getBody().getProduct().getId());
        verify(productService).getProductById(idcaptor.capture());
        assertEquals(1L, idcaptor.getValue());

    }

    @Test
    public void testGetProductById_WhenInvalidIdIsPassed_ResultsInRuntimeException() throws ProductNotFoundException {

         RuntimeException ex= assertThrows(RuntimeException.class,() -> productController.getProductById(0L));

         assertEquals("Something went wrong",ex.getMessage());
    }
}
