package com.example.productservice.dtos.search;

import com.example.productservice.dtos.products.GetProductDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class SearchResponseDto {

    private Page<GetProductDto> productsPage;
}
