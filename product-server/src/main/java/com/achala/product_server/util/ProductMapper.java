package com.achala.product_server.util;

import com.achala.product_server.dto.ProductRequestDto;
import com.achala.product_server.model.Category;
import com.achala.product_server.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    //map product request to product model
    public ProductModel maptoProductModel(ProductRequestDto productRequestDto, Category category) {
        return ProductModel.builder()
                .name(productRequestDto.name())
                .description(productRequestDto.description())
                .price(productRequestDto.price())
                .availableQuantity(productRequestDto.availableQuantity())
                .category(category)
                .build();
    }
}
