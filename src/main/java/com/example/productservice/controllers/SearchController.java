package com.example.productservice.controllers;

import com.example.productservice.dtos.products.GetProductDto;
import com.example.productservice.dtos.search.FilterDto;
import com.example.productservice.dtos.search.SearchResponseDto;
import com.example.productservice.dtos.search.SortingCriteria;
import com.example.productservice.models.Product;
import com.example.productservice.services.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {

     private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public SearchResponseDto search(@RequestParam("query") String query,
                                    @RequestParam("filters") List<FilterDto> filters,
                                    @RequestParam("sortBy") SortingCriteria sortingCriteria,
                                    @RequestParam("pageNumber") int pageNumber,  //If the user sends them → use them
                                    @RequestParam("pageSize") int pageSize) {    //If the user doesn't → use defaults
        SearchResponseDto response = new SearchResponseDto();
        Page<Product> productsPage =  searchService.search(
                query, filters, sortingCriteria,pageNumber, pageSize);

        List<GetProductDto> getProductDto = productsPage.getContent().stream()
                .map(GetProductDto::from)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(productsPage.getNumber(), productsPage.getSize(), productsPage.getSort());
        Page<GetProductDto> getProductDtoPage = new PageImpl<>(getProductDto, pageable, productsPage.getTotalElements());

        response.setProductsPage(getProductDtoPage);

        return response;

    }




}
