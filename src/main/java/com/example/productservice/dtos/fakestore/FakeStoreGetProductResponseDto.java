package com.example.productservice.dtos.fakestore;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreGetProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setImageUrl(this.getImage());

        Category category1 = new Category();
        category1.setName(category);
        product.setCategory(category1);
        return product;
    }

}
