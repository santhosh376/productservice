package com.example.productservice.dtos.products;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;

    public static GetProductDto from(Product product) {

        GetProductDto getProductResponseDTO = new GetProductDto();
        getProductResponseDTO.setId(product.getId());
        getProductResponseDTO.setTitle(product.getTitle());
        getProductResponseDTO.setDescription(product.getDescription());
        getProductResponseDTO.setPrice(product.getPrice());
        getProductResponseDTO.setImageUrl(product.getImageUrl());

        return getProductResponseDTO;
    }
}
