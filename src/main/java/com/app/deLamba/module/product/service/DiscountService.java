package com.app.deLamba.module.product.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.deLamba.common.exception.BusinessException;
import com.app.deLamba.common.exception.ResourceNotFoundException;
import com.app.deLamba.module.product.dto.request.CreateDiscountRequest;
import com.app.deLamba.module.product.model.Discount;
import com.app.deLamba.module.product.model.DiscountType;
import com.app.deLamba.module.product.repository.DiscountRepository;
import com.app.deLamba.module.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscountService {
  private final DiscountRepository discountRepository;
  private final ProductRepository productRepository;

  public Discount createDiscount(String productId, CreateDiscountRequest request) {
    // Validar que el producto existe
    if (!productRepository.existsById(productId)) {
      throw new ResourceNotFoundException("Product not found");
    }

    // Validar fechas
    if (request.getEndDate().isBefore(request.getStartDate())) {
      throw new BusinessException("End date cannot be before start date");
    }
    if (request.getStartDate().isBefore(LocalDateTime.now())) {
      throw new BusinessException("Start date cannot be in the past");
    }

    // Validar descuento según tipo
    if (request.getType() == DiscountType.PERCENTAGE && request.getValue().compareTo(new BigDecimal("100")) > 0) {
      throw new BusinessException("Percentage discount cannot exceed 100%");
    }

    Discount discount = new Discount();
    discount.setPercentage(request.getValue());
    discount.setStartDate(request.getStartDate());
    discount.setEndDate(request.getEndDate());
    discount.setProductId(productId);
    discount.setType(request.getType());

    return discountRepository.save(discount);
  }

  public List<Discount> getActiveDiscounts(String productId) {
    LocalDateTime now = LocalDateTime.now();
    return discountRepository.findByProductIdAndStartDateBeforeAndEndDateAfterAndActiveTrue(
        productId, now, now);
  }
}
