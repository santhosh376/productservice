package com.example.productservice.services;

import com.example.productservice.dtos.fakestore.FakeStoreCreateProductRequestDto;
import com.example.productservice.dtos.fakestore.FakeStoreCreateProductResponseDto;
import com.example.productservice.dtos.fakestore.FakeStoreGetProductResponseDto;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service("fakeStoreProductService")
//@Primary
public class ProductServiceFakestoreImpl implements ProductService {

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    public ProductServiceFakestoreImpl(RedisTemplate redisTemplate, RestTemplate restTemplate) {
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getCategory().getName());
        request.setTitle(product.getTitle());
        request.setDescription(product.getDescription());
        request.setImage(product.getImageUrl());
        request.setPrice(product.getPrice());

        FakeStoreCreateProductResponseDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreCreateProductResponseDto.class
        );
//        Product product1 = new Product();
//        product1.setId(response.getId());
//        product1.setTitle(response.getTitle());
//        product1.setDescription(response.getDescription());
//        product1.setPrice(response.getPrice());
//        product1.setImageUrl(response.getImage());
//        product1.setCategoryName(response.getCategory());

        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreGetProductResponseDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreGetProductResponseDto[].class
        );

        List<FakeStoreGetProductResponseDto> responseDtoList = Stream.of(response).toList();

        List<Product> products = new ArrayList<>();
        for (FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto : responseDtoList) {
            products.add(fakeStoreGetProductResponseDto.toProduct());
        }
        return products;
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) {
        FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreCreateProductRequestDto.fromProduct(product),
                FakeStoreGetProductResponseDto.class, productId
        );
        return fakeStoreGetProductResponseDto.toProduct();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //check is this product is available in REDIS or not
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);

        //Cache hit
        if (product != null) {
            return product;
        }


        //Call FakeStore to fetch the Product with given id. => HTTP Call.
        FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/{id}", FakeStoreGetProductResponseDto.class, id);
        if (fakeStoreGetProductResponseDto == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

        Product productx = fakeStoreGetProductResponseDto.toProduct();
        //Before returning the product store in redis
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, productx);
        return productx;
    }
}

//Product object
//   ↓ (Serialization)
//JSON / bytes
//   ↓
//Stored in Redis
//   ↓
//Retrieved from Redis
//   ↓ (Deserialization)
//Product object



