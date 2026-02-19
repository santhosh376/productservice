package com.example.productservice.controllers;

import com.example.productservice.dtos.products.CreateProductDto;
import com.example.productservice.dtos.products.CreateProductRequestDto;
import com.example.productservice.dtos.products.GetAllProductsResponseDto;
import com.example.productservice.dtos.products.GetProductDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "dbProductService")
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;
    // object <-> json <-> string

    @Test
    public void TestGetAllProducts_RunsSuccessfully() throws Exception {

        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Iphone 16");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getAllProducts()).thenReturn(productList);

        // Expected DTO response
        GetProductDto dto = GetProductDto.from(product);

        GetAllProductsResponseDto responseDto =
                new GetAllProductsResponseDto();
        responseDto.setProducts(List.of(dto));

        //Act & Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    public void TestCreateProduct_RunsSuccessfully() throws Exception {

        //Arrange
        CreateProductRequestDto request = new CreateProductRequestDto();
        request.setId(2L);
        request.setTitle("MacBook Pro");

        Product product = new Product();
        product.setId(2L);
        product.setTitle("MacBook Pro");


        when(productService.createProduct(any(Product.class))).thenReturn(product);

        //Act & Assert
        mockMvc.perform(post("/products")
                .content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(request)));
    }
}
