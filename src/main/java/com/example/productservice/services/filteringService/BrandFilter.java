package com.example.productservice.services.filteringService;

import com.example.productservice.models.Product;

import java.util.List;

public class BrandFilter implements Filter{

    @Override
    public List<Product> apply(List<Product> products, List<String> allowedValues) {
        return List.of();
    }
}
