package com.achala.order_server.client;

import com.achala.order_server.dto.ProductPurchaseResponseDto;
import com.achala.order_server.dto.PurchaseItemRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVER")
public interface ProductServerClient {

    @PostMapping("/product/purchase")
    ResponseEntity<List<ProductPurchaseResponseDto>> purchaseProducts(@RequestBody List<PurchaseItemRequestDto> products);
}
