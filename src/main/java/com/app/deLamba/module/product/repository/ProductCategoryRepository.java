package com.app.deLamba.module.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.deLamba.module.product.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
  List<ProductCategory> findByBusinessIdAndActiveTrue(String businessId);

  Optional<ProductCategory> findByIdAndBusinessIdAndActiveTrue(String id, String businessId);

  boolean existsByNameAndBusinessIdAndActiveTrue(String name, String businessId);
}
