package com.app.deLamba.module.product.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.app.deLamba.common.exception.BusinessException;
import com.app.deLamba.common.exception.ResourceNotFoundException;
import com.app.deLamba.module.business.model.Business;
import com.app.deLamba.module.business.repository.BusinessRepository;
import com.app.deLamba.module.product.dto.request.CreateAdditionalRequest;
import com.app.deLamba.module.product.dto.request.CreateDiscountRequest;
import com.app.deLamba.module.product.dto.request.CreateProductRequest;
import com.app.deLamba.module.product.model.Additional;
import com.app.deLamba.module.product.model.Discount;
import com.app.deLamba.module.product.model.Product;
import com.app.deLamba.module.product.model.ProductStatus;
import com.app.deLamba.module.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final BusinessRepository businessRepository;
  private final AdditionalService additionalService;
  private final DiscountService discountService;

  public Product createProduct(String userId, CreateProductRequest request) {
    Business business = businessRepository.findByIdAndActiveTrue(request.getBusinessId())
        .orElseThrow(() -> new ResourceNotFoundException("Business not found"));

    if (!business.getUserId().equals(userId)) {
      throw new BusinessException("You don't have permission to add products to this business");
    }

    // Crear el producto
    Product product = new Product();
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setImage(request.getImage());
    product.setBusinessId(request.getBusinessId());
    product.setProductCategoryId(request.getProductCategoryId());
    product.setStatus(ProductStatus.AVAILABLE);
    product.setAdditionalIds(new ArrayList<>());
    product.setDiscountIds(new ArrayList<>());

    return productRepository.save(product);
  }

  public Product addAdditional(String productId, CreateAdditionalRequest request) {
    Additional additional = additionalService.createAdditional(productId, request);
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    if (product.getAdditionalIds() == null) {
      product.setAdditionalIds(new ArrayList<>());
    }
    product.getAdditionalIds().add(additional.getId());

    return productRepository.save(product);
  }

  public Product addDiscount(String productId, CreateDiscountRequest request) {
    Discount discount = discountService.createDiscount(productId, request);
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    if (product.getDiscountIds() == null) {
      product.setDiscountIds(new ArrayList<>());
    }
    product.getDiscountIds().add(discount.getId());

    return productRepository.save(product);
  }
}
