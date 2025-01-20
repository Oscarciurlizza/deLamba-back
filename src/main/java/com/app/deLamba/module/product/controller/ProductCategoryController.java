package com.app.deLamba.module.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.deLamba.common.dto.ApiResponse;
import com.app.deLamba.module.product.dto.request.CreateProductCategoryRequest;
import com.app.deLamba.module.product.model.ProductCategory;
import com.app.deLamba.module.product.service.ProductCategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {

  private final ProductCategoryService productCategoryService;

  @PostMapping
  public ResponseEntity<ApiResponse<ProductCategory>> createCategory(
      @Valid @RequestBody CreateProductCategoryRequest request) {
    String userId = "temp-user-id";
    ProductCategory createdCategory = productCategoryService.createCategory(userId, request);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success("Product category created successfully", createdCategory));
  }

  @GetMapping("/business/{businessId}")
  public ResponseEntity<ApiResponse<List<ProductCategory>>> getCategoriesByBusiness(
      @PathVariable String businessId) {
    List<ProductCategory> categories = productCategoryService.getCategoriesByBusiness(businessId);
    return ResponseEntity.ok(
        ApiResponse.success("Categories retrieved successfully", categories));
  }
}
