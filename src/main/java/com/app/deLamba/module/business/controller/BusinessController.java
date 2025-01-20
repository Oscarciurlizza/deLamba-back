package com.app.deLamba.module.business.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.deLamba.common.dto.ApiResponse;
import com.app.deLamba.module.business.dto.request.CreateBusinessRequest;
import com.app.deLamba.module.business.model.Business;
import com.app.deLamba.module.business.service.BusinessService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/businesses")
@RequiredArgsConstructor
public class BusinessController {

  private final BusinessService businessService;

  @PostMapping
  public ResponseEntity<ApiResponse<Business>> createBusiness(
      @Valid @RequestBody CreateBusinessRequest request) {
    String userId = "temp-user-id";
    Business createdBusiness = businessService.createBusiness(userId, request);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponse.success("Business created successfully", createdBusiness));
  }
}