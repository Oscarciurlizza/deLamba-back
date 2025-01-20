package com.app.deLamba.module.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProductCategoryRequest {
  @NotBlank(message = "Name is required")
  @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
  private String name;

  @Size(max = 200, message = "Description cannot exceed 200 characters")
  private String description;

  @NotBlank(message = "Business ID is required")
  private String businessId;
}
