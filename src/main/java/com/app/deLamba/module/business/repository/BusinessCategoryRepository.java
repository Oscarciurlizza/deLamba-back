package com.app.deLamba.module.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.deLamba.module.business.model.BusinessCategory;

@Repository
public interface BusinessCategoryRepository extends MongoRepository<BusinessCategory, String> {
  Optional<BusinessCategory> findByIdAndActiveTrue(String id);

  List<BusinessCategory> findByActiveTrue();

  boolean existsByNameAndActiveTrue(String name);

  List<BusinessCategory> findByActiveTrueOrderByNameAsc();
}
