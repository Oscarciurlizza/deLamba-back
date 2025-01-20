package com.app.deLamba.common.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
  private Boolean success;
  private String message;
  private T data;
  private ApiError error;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timestamp;

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder()
        .success(true)
        .data(data)
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static <T> ApiResponse<T> success(String message, T data) {
    return ApiResponse.<T>builder()
        .success(true)
        .message(message)
        .data(data)
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static <T> ApiResponse<T> error(HttpStatus status, String message) {
    return ApiResponse.<T>builder()
        .success(false)
        .error(new ApiError(status.value(), message))
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static <T> ApiResponse<T> error(HttpStatus status, String message, String details) {
    return ApiResponse.<T>builder()
        .success(false)
        .error(new ApiError(status.value(), message, details))
        .timestamp(LocalDateTime.now())
        .build();
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ApiError {
    private Integer code;
    private String message;
    private String details;

    public ApiError(Integer code, String message) {
      this.code = code;
      this.message = message;
    }
  }
}
