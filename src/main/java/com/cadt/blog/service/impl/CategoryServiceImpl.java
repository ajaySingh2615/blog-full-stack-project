package com.cadt.blog.service.impl;

import com.cadt.blog.domain.Category;
import com.cadt.blog.dto.request.CategoryCreateRequest;
import com.cadt.blog.dto.request.CategoryUpdateRequest;
import com.cadt.blog.dto.response.CategoryResponse;
import com.cadt.blog.repository.CategoryRepository;
import com.cadt.blog.service.CategoryService;
import com.cadt.blog.service.helpers.SlugService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;
    private final SlugService slugService;

    @Override
    public CategoryResponse create(CategoryCreateRequest req) {
        String slug = slugService.unique(req.name(), repo::existsBySlug);
        Category c = Category.builder()
                .name(req.name())
                .slug(slug)
                .description(req.description())
                .build();
        c = repo.save(c);
        return map(c);
    }

    @Override
    public CategoryResponse update(Long id, CategoryUpdateRequest request) {
        Category c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        if (!c.getName().equals(request.name())) {
            c.setName(request.name());
            c.setSlug(slugService.unique(request.name(),
                    s -> repo.existsBySlug(s) && !s.equals(c.getSlug())));
        }
        c.setDescription(request.description());
        return map(repo.save(c));
    }

    @Override
    public void delete(Long id) {
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Category is in use by posts");
        }
    }

    @Override
    public CategoryResponse getBySlug(String slug) {
        Category c = repo.findBySlug(slug).orElseThrow(() -> new IllegalArgumentException("category not found"));
        return map(c);
    }

    @Override
    public Page<CategoryResponse> list(Pageable pageable) {
        return repo.findAll(pageable).map(this::map);
    }

    private CategoryResponse map(Category c) {
        return new CategoryResponse(c.getId(), c.getName(), c.getSlug(),
                c.getDescription(), c.getCreatedAt(), c.getUpdatedAt());
    }
}
