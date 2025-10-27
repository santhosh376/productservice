package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

   Optional<Category> findByName(String name);
    //List<Category>findByIdGreaterThanAndNameGreaterThan(Long Id, String  name);
    Category save(Category category);
}
