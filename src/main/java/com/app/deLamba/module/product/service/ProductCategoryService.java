package com.app.deLamba.module.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.deLamba.common.exception.BusinessException;
import com.app.deLamba.common.exception.ResourceNotFoundException;
import com.app.deLamba.module.business.model.Business;
import com.app.deLamba.module.business.repository.BusinessRepository;
import com.app.deLamba.module.product.dto.request.CreateProductCategoryRequest;
import com.app.deLamba.module.product.model.ProductCategory;
import com.app.deLamba.module.product.repository.ProductCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
  private final ProductCategoryRepository productCategoryRepository;
  private final BusinessRepository businessRepository;

  public ProductCategory createCategory(String userId, CreateProductCategoryRequest request) {
    Business business = businessRepository.findByIdAndActiveTrue(request.getBusinessId())
        .orElseThrow(() -> new ResourceNotFoundException("Business not found"));

    if (!business.getUserId().equals(userId)) {
      throw new BusinessException("You don't have permission to create categories for this business");
    }

    if (productCategoryRepository.existsByNameAndBusinessIdAndActiveTrue(
        request.getName(), request.getBusinessId())) {
      throw new BusinessException("A category with this name already exists in this business");
    }

    ProductCategory category = new ProductCategory();
    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setBusinessId(request.getBusinessId());

    return productCategoryRepository.save(category);
  }

  public List<ProductCategory> getCategoriesByBusiness(String businessId) {
    if (!businessRepository.existsByIdAndActiveTrue(businessId)) {
      throw new ResourceNotFoundException("Business not found");
    }
    return productCategoryRepository.findByBusinessIdAndActiveTrue(businessId);
  }
}
