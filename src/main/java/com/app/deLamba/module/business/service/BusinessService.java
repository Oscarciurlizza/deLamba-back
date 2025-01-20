package com.app.deLamba.module.business.service;

import org.springframework.stereotype.Service;

import com.app.deLamba.common.exception.BusinessException;
import com.app.deLamba.common.exception.ResourceNotFoundException;
import com.app.deLamba.module.business.dto.request.CreateBusinessRequest;
import com.app.deLamba.module.business.model.Business;
import com.app.deLamba.module.business.model.BusinessStatus;
import com.app.deLamba.module.business.repository.BusinessCategoryRepository;
import com.app.deLamba.module.business.repository.BusinessRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessService {
  private final BusinessRepository businessRepository;
  private final BusinessCategoryRepository businessCategoryRepository;

  public Business createBusiness(String userId, CreateBusinessRequest request) {
    if (businessRepository.existsByUserIdAndActiveTrue(userId)) {
      throw new BusinessException("User already has an active business");
    }

    businessCategoryRepository.findByIdAndActiveTrue(request.getBusinessCategoryId())
        .orElseThrow(() -> new ResourceNotFoundException("Business category not found"));

    // Crear el negocio
    Business business = new Business();
    business.setName(request.getName());
    business.setDescription(request.getDescription());
    business.setAddress(request.getAddress());
    business.setPhone(request.getPhone());
    business.setSchedule(request.getSchedule());
    business.setUserId(userId);
    business.setBusinessCategoryId(request.getBusinessCategoryId());
    business.setStatus(BusinessStatus.PENDING);

    return businessRepository.save(business);
  }
}
