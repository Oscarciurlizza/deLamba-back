package com.app.deLamba.module.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.deLamba.module.product.model.Additional;

@Repository
public interface AdditionalRepository extends MongoRepository<Additional, String> {
  // Buscar adicionales activos de un producto
  List<Additional> findByProductIdAndActiveTrue(String productId);

  // Buscar un adicional específico activo
  Optional<Additional> findByIdAndActiveTrueAndProductId(String id, String productId);

  // Verificar si un adicional existe y está activo
  boolean existsByIdAndActiveTrueAndProductId(String id, String productId);

  // Buscar por nombre para evitar duplicados
  boolean existsByNameAndProductIdAndActiveTrue(String name, String productId);

  // Buscar adicionales disponibles
  List<Additional> findByProductIdAndAvailableAndActiveTrue(String productId, boolean available);
}
