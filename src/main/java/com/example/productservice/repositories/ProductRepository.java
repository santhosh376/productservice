package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
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


   //Mix of SQL queries syntax with JPA queries
   @Query("select p " +
           "from Product p " +
           "where p.category.subcategories = :categorySurname")
   List<Product> allProductWithSurname(@Param("categorySurname") String categorySurname);

   @Query("select p " +
           "from Product p " +
           "where p.id > : idGreaterthen")
   List<Product> getIdGreater(@Param("idGreaterthen") Long id);

   //Lowest level of API i.e., Sql raw queries
    @Query(
            value = CustomQueries.GET_PRODUCTS_WITH_SUBCATEGORY_NAME,
            nativeQuery = true)
    List<Product> rawQuery();

    //3 ways to query a database 1.Query methods 2.JPA Queries 3.Native queries yourself

}
