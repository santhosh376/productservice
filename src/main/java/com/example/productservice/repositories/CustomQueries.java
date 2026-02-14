package com.example.productservice.repositories;

public interface CustomQueries {
    String GET_PRODUCTS_WITH_SUBCATEGORY_NAME = "select * from Product p join category c on Product p where p.category_id = c.id  join SubCategory s where s.category_id = c.id";

}
