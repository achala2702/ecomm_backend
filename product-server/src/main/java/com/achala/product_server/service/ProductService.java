package com.achala.product_server.service;

import com.achala.product_server.dto.ProductRequestDto;
import com.achala.product_server.model.Category;
import com.achala.product_server.model.ProductModel;
import com.achala.product_server.repository.CategoryRepository;
import com.achala.product_server.repository.ProductRepository;
import com.achala.product_server.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public String addProduct(ProductRequestDto productRequestDto) {

        //needs to check whether category is in db if not create a new category and return it
        Category productCategory = categoryRepository.findByName(productRequestDto.category()).orElseGet(()-> categoryRepository.save(Category.builder()
                .name(productRequestDto.category())
                .build()));

        ProductModel product = productMapper.maptoProductModel(productRequestDto, productCategory);
        productRepository.save(product);

        return "Product added successfully";
    }
}
