package com.achala.product_server.controller;

import com.achala.product_server.dto.ProductPurchaseRequest;
import com.achala.product_server.dto.ProductPurchaseResponseDto;
import com.achala.product_server.dto.ProductRequestDto;
import com.achala.product_server.dto.ProductResponseDto;
import com.achala.product_server.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequestDto));
    }

    @GetMapping("/get-products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponseDto>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> products) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.purchaseProducts(products));
    }
}
