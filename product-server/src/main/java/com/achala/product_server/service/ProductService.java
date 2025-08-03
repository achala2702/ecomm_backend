package com.achala.product_server.service;

import com.achala.product_server.dto.ProductPurchaseRequest;
import com.achala.product_server.dto.ProductPurchaseResponseDto;
import com.achala.product_server.dto.ProductRequestDto;
import com.achala.product_server.dto.ProductResponseDto;
import com.achala.product_server.exception.InsufficientStockException;
import com.achala.product_server.exception.ProductNotFoundException;
import com.achala.product_server.model.Category;
import com.achala.product_server.model.ProductModel;
import com.achala.product_server.repository.CategoryRepository;
import com.achala.product_server.repository.ProductRepository;
import com.achala.product_server.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    //get all products
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::maptoProductResponse)
                .toList();
    }

    //get product by id
    public ProductResponseDto getProductById(Integer id) {
        ProductModel product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product with ID: " + id + " was not found."));
        return productMapper.maptoProductResponse(product);
    }

    //purchase products
    public List<ProductPurchaseResponseDto> purchaseProducts(List<ProductPurchaseRequest> products) {

        //getting the products from database with provided products ids
        List<Integer> productIds = products.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        List<ProductModel> storedProducts = productRepository.findAllByProductIdInOrderByProductId(productIds);

        if(productIds.size() != storedProducts.size()){
            throw new ProductNotFoundException("One or More Product missing in the database");
        }

        //checking the quantity of products
        List<ProductPurchaseRequest> sortedPurchaseRequests = products.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        //list to store all the purchase products that going to return
        List<ProductPurchaseResponseDto> purchasedProductsList = new ArrayList<>();
        //list to store all the products after quantity changed when user buying
        List<ProductModel> updatedProductList = new ArrayList<>();

        for(int i = 0; i<storedProducts.size(); i++) {
            ProductModel product = storedProducts.get(i);
            ProductPurchaseRequest request = sortedPurchaseRequests.get(i);

            if(product.getAvailableQuantity() < request.quantity()) {
                throw new InsufficientStockException("Insufficient stock quantity to fulfill the order.");
            }

            //saving the new quantity to product and add product to updated list
            Integer newAvailableQuantity = product.getAvailableQuantity() - request.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            updatedProductList.add(product);

            //adding the purchase product to purchased product list
            purchasedProductsList.add(productMapper.maptoProductPurchaseResponse(product, request.quantity()));
        }
        //updating the products in db
        productRepository.saveAll(updatedProductList);
        return purchasedProductsList;
    }
}
