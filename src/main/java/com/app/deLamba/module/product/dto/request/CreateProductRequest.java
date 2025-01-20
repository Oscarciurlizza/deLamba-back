package com.app.deLamba.module.product.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProductRequest {
  @NotBlank(message = "Name is required")
  @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
  private String name;

  @Size(max = 200, message = "Description cannot exceed 200 characters")
  private String description;

  @NotNull(message = "Price is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  @Digits(integer = 6, fraction = 2, message = "Price can have up to 6 digits and 2 decimals")
  private BigDecimal price;

  private String image;

  @NotBlank(message = "Business ID is required")
  private String businessId;

  @NotBlank(message = "Product Category ID is required")
  private String productCategoryId;
}
