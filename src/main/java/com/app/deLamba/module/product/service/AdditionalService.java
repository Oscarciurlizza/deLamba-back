package com.app.deLamba.module.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.deLamba.common.exception.BusinessException;
import com.app.deLamba.common.exception.ResourceNotFoundException;
import com.app.deLamba.module.product.dto.request.CreateAdditionalRequest;
import com.app.deLamba.module.product.model.Additional;
import com.app.deLamba.module.product.repository.AdditionalRepository;
import com.app.deLamba.module.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdditionalService {
  private final AdditionalRepository additionalRepository;
  private final ProductRepository productRepository;

  public Additional createAdditional(String productId, CreateAdditionalRequest request) {
    if (!productRepository.existsById(productId)) {
      throw new ResourceNotFoundException("Product not found");
    }

    if (additionalRepository.existsByNameAndProductIdAndActiveTrue(
        request.getName().trim(), productId)) {
      throw new BusinessException(
          String.format("Additional '%s' already exists for this product", request.getName()));
    }

    Additional additional = new Additional();
    additional.setName(request.getName());
    additional.setPrice(request.getPrice());
    additional.setAvailable(request.getAvailable());
    additional.setProductId(productId);

    return additionalRepository.save(additional);
  }

  public List<Additional> getProductAdditionals(String productId) {
    return additionalRepository.findByProductIdAndActiveTrue(productId);
  }
}