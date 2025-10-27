package com.example.productservice.dtos.fakestore;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    public static FakeStoreCreateProductRequestDto fromProduct(Product product){
        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto = new FakeStoreCreateProductRequestDto();
        fakeStoreCreateProductRequestDto.title = product.getTitle();
        fakeStoreCreateProductRequestDto.price = product.getPrice();
        fakeStoreCreateProductRequestDto.description = product.getDescription();
        fakeStoreCreateProductRequestDto.image = product.getImageUrl();
        fakeStoreCreateProductRequestDto.category = product.getCategory().getName();

        return fakeStoreCreateProductRequestDto;
    }
}
