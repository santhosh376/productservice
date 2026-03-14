package com.example.productservice.services.sortingService;

import com.example.productservice.models.Product;

import java.util.List;

public interface Sorter {

    List<Product> sort(List<Product> products);
}
