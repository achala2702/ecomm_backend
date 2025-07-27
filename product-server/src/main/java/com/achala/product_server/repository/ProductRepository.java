package com.achala.product_server.repository;

import com.achala.product_server.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    List<ProductModel> findAllByProductIdInOrderByProductId(List<Integer> productIds);
}