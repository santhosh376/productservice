package com.example.productservice.dtos.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)  //TestCase added code
public class GetAllProductsResponseDto {
    private List<GetProductDto> products;
}
