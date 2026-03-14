package com.example.productservice.services.filteringService;

import com.example.productservice.models.Product;

import java.util.List;

public interface Filter {

    List<Product> apply(List<Product> products,List<String>allowedValues);
}
