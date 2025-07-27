package com.achala.product_server.util;

import com.achala.product_server.dto.ProductPurchaseResponseDto;
import com.achala.product_server.dto.ProductRequestDto;
import com.achala.product_server.dto.ProductResponseDto;
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

    public ProductResponseDto maptoProductResponse(ProductModel productModel){
        return ProductResponseDto.builder()
                .productId(productModel.getProductId())
                .name(productModel.getName())
                .description(productModel.getDescription())
                .category(productModel.getDescription())
                .price(productModel.getPrice())
                .availableQuantity(productModel.getAvailableQuantity())
                .build();
    }

    public ProductPurchaseResponseDto maptoProductPurchaseResponse(ProductModel productModel, Integer quantity) {
        return ProductPurchaseResponseDto.builder()
                .productId(productModel.getProductId())
                .name(productModel.getName())
                .description(productModel.getDescription())
                .price(productModel.getPrice())
                .quantity(quantity)
                .build();
    }
}
