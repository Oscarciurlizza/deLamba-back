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
import com.app.deLamba.module.product.dto.request.CreateAdditionalRequest;
import com.app.deLamba.module.product.dto.request.CreateDiscountRequest;
import com.app.deLamba.module.product.dto.request.CreateProductRequest;
import com.app.deLamba.module.product.model.Additional;
import com.app.deLamba.module.product.model.Discount;
import com.app.deLamba.module.product.model.Product;
import com.app.deLamba.module.product.service.AdditionalService;
import com.app.deLamba.module.product.service.DiscountService;
import com.app.deLamba.module.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final AdditionalService additionalService;
  private final DiscountService discountService;

  @PostMapping
  public ResponseEntity<ApiResponse<Product>> createProduct(
      @Valid @RequestBody CreateProductRequest request) {
    String userId = "temp-user-id";
    Product createdProduct = productService.createProduct(userId, request);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success("Product created successfully", createdProduct));
  }

  @PostMapping("/{productId}/additionals")
  public ResponseEntity<ApiResponse<Product>> addAdditional(
      @PathVariable String productId,
      @Valid @RequestBody CreateAdditionalRequest request) {
    Product updatedProduct = productService.addAdditional(productId, request);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success("Additional added successfully", updatedProduct));
  }

  @PostMapping("/{productId}/discounts")
  public ResponseEntity<ApiResponse<Product>> addDiscount(
      @PathVariable String productId,
      @Valid @RequestBody CreateDiscountRequest request) {
    Product updatedProduct = productService.addDiscount(productId, request);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success("Discount added successfully", updatedProduct));
  }

  @GetMapping("/{productId}/additionals")
  public ResponseEntity<ApiResponse<List<Additional>>> getProductAdditionals(
      @PathVariable String productId) {
    List<Additional> additionals = additionalService.getProductAdditionals(productId);
    return ResponseEntity.ok(
        ApiResponse.success("Additionals retrieved successfully", additionals));
  }

  @GetMapping("/{productId}/discounts")
  public ResponseEntity<ApiResponse<List<Discount>>> getProductDiscounts(
      @PathVariable String productId) {
    List<Discount> discounts = discountService.getActiveDiscounts(productId);
    return ResponseEntity.ok(
        ApiResponse.success("Active discounts retrieved successfully", discounts));
  }
}
