package com.app.deLamba.module.product.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.deLamba.module.product.model.Discount;
import com.app.deLamba.module.product.model.DiscountType;

@Repository
public interface DiscountRepository extends MongoRepository<Discount, String> {
  // Buscar descuentos activos de un producto
  List<Discount> findByProductIdAndActiveTrue(String productId);

  // Buscar descuentos vigentes (por fecha)
  List<Discount> findByProductIdAndStartDateBeforeAndEndDateAfterAndActiveTrue(
      String productId,
      LocalDateTime currentDate,
      LocalDateTime sameCurrentDate);

  // Buscar un descuento específico activo
  Optional<Discount> findByIdAndActiveTrueAndProductId(String id, String productId);

  // Verificar si existe un descuento activo en un rango de fechas
  boolean existsByProductIdAndActiveTrueAndStartDateBeforeAndEndDateAfter(
      String productId,
      LocalDateTime startDate,
      LocalDateTime endDate);

  // Buscar descuentos que expiran pronto
  List<Discount> findByProductIdAndEndDateBetweenAndActiveTrue(
      String productId,
      LocalDateTime startDate,
      LocalDateTime endDate);

  // Buscar descuentos por tipo
  List<Discount> findByProductIdAndTypeAndActiveTrue(
      String productId,
      DiscountType type);
}
