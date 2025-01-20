package com.app.deLamba.module.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.deLamba.common.exception.BusinessException;
import com.app.deLamba.module.business.dto.request.CreateBusinessCategoryRequest;
import com.app.deLamba.module.business.model.BusinessCategory;
import com.app.deLamba.module.business.repository.BusinessCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessCategoryService {

  private final BusinessCategoryRepository businessCategoryRepository;

  public BusinessCategory createCategory(CreateBusinessCategoryRequest request) {
    if (businessCategoryRepository.existsByNameAndActiveTrue(request.getName())) {
      throw new BusinessException("A category with this name already exists");
    }

    BusinessCategory category = new BusinessCategory();
    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setActive(true);
    category.setIcon(request.getIcon());
    category.setSlug(request.getSlug());

    return businessCategoryRepository.save(category);
  }

  public List<BusinessCategory> getAllCategories() {
    return businessCategoryRepository.findByActiveTrueOrderByNameAsc();
  }
}
