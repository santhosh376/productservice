package com.example.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //For Redis
@JsonInclude(JsonInclude.Include.NON_NULL)   //TestCase added code
public class Product extends BaseModel{
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
}

