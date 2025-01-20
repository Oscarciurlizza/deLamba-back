package com.app.deLamba.module.business.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.deLamba.common.dto.ApiResponse;
import com.app.deLamba.module.business.dto.request.CreateBusinessCategoryRequest;
import com.app.deLamba.module.business.model.BusinessCategory;
import com.app.deLamba.module.business.service.BusinessCategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/business-categories")
@RequiredArgsConstructor
public class BusinessCategoryController {

  private final BusinessCategoryService businessCategoryService;

  @PostMapping
  public ResponseEntity<ApiResponse<BusinessCategory>> createCategory(
      @Valid @RequestBody CreateBusinessCategoryRequest request) {
    BusinessCategory createdCategory = businessCategoryService.createCategory(request);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success("Business category created successfully", createdCategory));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<BusinessCategory>>> getAllCategories() {
    List<BusinessCategory> categories = businessCategoryService.getAllCategories();
    return ResponseEntity.ok(ApiResponse.success("Categories retrieved successfully", categories));
  }
}
