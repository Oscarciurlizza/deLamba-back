package com.app.deLamba.module.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.deLamba.module.business.model.Business;

@Repository
public interface BusinessRepository extends MongoRepository<Business, String> {
  boolean existsByIdAndActiveTrue(String id);

  boolean existsByUserIdAndActiveTrue(String userId);

  Optional<Business> findByIdAndActiveTrue(String id);

  List<Business> findByActiveTrue();

  Optional<Business> findByUserIdAndActiveTrue(String userId);
}