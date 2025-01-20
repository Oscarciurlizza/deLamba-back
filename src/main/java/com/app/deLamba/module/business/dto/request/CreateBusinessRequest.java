package com.app.deLamba.module.business.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBusinessRequest {
  @NotBlank(message = "Name is required")
  @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
  private String name;

  @Size(max = 200, message = "Description cannot exceed 200 characters")
  private String description;

  @NotBlank(message = "Address is required")
  @Size(max = 100, message = "Address cannot exceed 100 characters")
  private String address;

  @NotBlank(message = "Phone is required")
  @Pattern(regexp = "^\\+?[0-9]{6,15}$", message = "Invalid phone number format")
  private String phone;

  @NotBlank(message = "Schedule is required")
  private String schedule;

  @NotNull(message = "Business category is required")
  private String businessCategoryId;
}
