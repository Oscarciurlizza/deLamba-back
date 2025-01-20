package com.app.deLamba.module.product.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.app.deLamba.module.product.model.DiscountType;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDiscountRequest {
  @NotNull(message = "Percentage/Amount is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than 0")
  @DecimalMax(value = "100.0", message = "Percentage cannot exceed 100%", groups = PercentageValidation.class)
  private BigDecimal value;

  @NotNull(message = "Start date is required")
  private LocalDateTime startDate;

  @NotNull(message = "End date is required")
  private LocalDateTime endDate;

  @NotNull(message = "Discount type is required")
  private DiscountType type;

  // Grupo de validación para porcentajes
  public interface PercentageValidation {
  }
}
