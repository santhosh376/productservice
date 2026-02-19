package com.example.productservice.dtos.products;

import com.example.productservice.models.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)  //TestCase added code
public class CreateProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
//    private double price;
    private String imageUrl;

    public static CreateProductResponseDto fromProduct(Product product) {
        CreateProductResponseDto createProductResponseDto = new CreateProductResponseDto();
        createProductResponseDto.setId(product.getId());
        createProductResponseDto.setTitle(product.getTitle());
        createProductResponseDto.setDescription(product.getDescription());
        createProductResponseDto.setPrice(product.getPrice());
        createProductResponseDto.setImageUrl(product.getImageUrl());

        return createProductResponseDto;
    }
}
