package com.example.productservice.services;

import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository  productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product createProduct(Product product) {
        Category toPutInProduct = getCategoryToBeInProduct(product);
        product.setCategory(toPutInProduct);
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product partialUpdateProduct(Long productId,Product product) throws ProductNotFoundException {

        Optional<Product> productToUpdateOptional = productRepository.findById(productId);
        if(productToUpdateOptional.isEmpty()){
            throw new ProductNotFoundException();
        }

        Product productToUpdate = productToUpdateOptional.get();
        if(product.getDescription() != null){
               productToUpdate.setDescription(product.getDescription());
        }

        if(product.getPrice() != null){
               productToUpdate.setPrice(product.getPrice());
        }

        if(product.getTitle() != null){
            productToUpdate.setTitle(product.getTitle());
        }

        if(product.getCategory() != null){
            Category toPutInProduct = getCategoryToBeInProduct(product);

            productToUpdate.setCategory(toPutInProduct);

        }
        return productRepository.save(productToUpdate);
    }

    private Category getCategoryToBeInProduct(Product product) {
        String categoryName = product.getCategory().getName();
        Optional<Category> category = categoryRepository.findByName(categoryName);  //null pointer exception handled here

        Category  toPutInProduct = null;
        if(category.isEmpty()){
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);
            toPutInProduct = toSaveCategory;
//            toPutInProduct = categoryRepository.save(toSaveCategory);
        }else{
            toPutInProduct = category.get();
        }
        return toPutInProduct;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productFound = productRepository.findById(id);
        if(productFound.isEmpty()){
            throw new ProductNotFoundException();
        }
        return productFound.get();
    }
}
