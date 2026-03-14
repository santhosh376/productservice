package com.example.productservice.services;

import com.example.productservice.dtos.search.FilterDto;
import com.example.productservice.dtos.search.SortingCriteria;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.services.filteringService.FilterFactory;
import com.example.productservice.services.sortingService.SorterFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(String query,
                                List<FilterDto> filters,
                                SortingCriteria sortingCriteria,
                                int pageNumber,   // 1    // 2    //3
                                int pageSize) {   // 5    // 5 -> (pagesize * (pageNumber-1)) -> (pageNumber * pageSize) -1
        //                                        0 -> 4  //                        5  ->   9
        List<Product> products = productRepository.findByTitleContaining(query);
        for(FilterDto filterDto : filters ){
            products = FilterFactory.getFilterFromKey(filterDto.getKey())
                    .apply(products,filterDto.getValues());
        }

        products = SorterFactory.getSortByCriteria(sortingCriteria)
                .sort(products);

        List<Product> productsOnPage = new ArrayList<>();

        for(int i = pageSize * (pageNumber-1) ; i <= (pageNumber * pageSize) -1; i++){
            productsOnPage.add(products.get(i));
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize); // Adjust page number and size as needed
        return new PageImpl<>(productsOnPage, pageable, products.size());

    }
}



