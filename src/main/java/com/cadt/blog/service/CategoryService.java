package com.cadt.blog.service;

import com.cadt.blog.dto.request.CategoryCreateRequest;
import com.cadt.blog.dto.request.CategoryUpdateRequest;
import com.cadt.blog.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryResponse create(CategoryCreateRequest req);

    CategoryResponse update(Long id, CategoryUpdateRequest request);

    void delete(Long id);  // will fall if in use (FK)

    CategoryResponse getBySlug(String slug);

    Page<CategoryResponse> list(Pageable pageable);
}
