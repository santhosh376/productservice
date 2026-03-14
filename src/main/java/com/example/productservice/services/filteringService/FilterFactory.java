package com.example.productservice.services.filteringService;

public class FilterFactory {
    public static Filter getFilterFromKey(String key) {
        return switch (key) {
            case "brand" -> new BrandFilter();
            case "ram" -> new RAMFilter();
            case null, default -> null;
        };
    }
}
