package com.app.deLamba.module.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.deLamba.module.product.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
  List<Product> findByBusinessIdAndActiveTrue(String businessId);

  Optional<Product> findByIdAndBusinessIdAndActiveTrue(String id, String businessId);

  List<Product> findByBusinessIdAndProductCategoryIdAndActiveTrue(String businessId, String categoryId);

  boolean existsByIdAndBusinessIdAndActiveTrue(String id, String businessId);
}
