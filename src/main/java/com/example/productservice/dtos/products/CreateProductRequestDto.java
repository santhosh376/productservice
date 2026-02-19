package com.example.productservice.dtos.products;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)  //TestCase added code
public class CreateProductRequestDto {
       private Long id;
       private String title;
       private String description;
       //private double price;
       private Double price;
       private String imageUrl;
       private String categoryName;

       public Product toProduct(){
              Product product= new Product();
              product.setTitle(this.title);
              product.setDescription(this.description);
              product.setPrice(this.price);
              product.setImageUrl(this.imageUrl);

              Category category = new Category();
              category.setName(categoryName);
              product.setCategory(category);


              return product;
       }
}
