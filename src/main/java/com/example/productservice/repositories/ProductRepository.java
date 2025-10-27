package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    // Update is also done using save
    //If the product you try to save has an ID
    //JPA will see if the product with that ID exist:
    //if no -> Insert
    //If yes -> Update
    @Override
    Product save(Product p);

    @Override
    void delete(Product entity);

    List<Product>  findAll();

   Optional<Product> findById(Long id);

   List<Product>

    findAllByCategory_Subcategories_SurnameEquals(String subcategorySurname);
}
