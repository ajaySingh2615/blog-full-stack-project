package com.cadt.blog.service;

import com.cadt.blog.dto.request.PostCreateRequest;
import com.cadt.blog.dto.request.PostUpdateRequest;
import com.cadt.blog.dto.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostResponse create(PostCreateRequest req);

    PostResponse update(Long id, PostUpdateRequest req);

    void delete(Long id);  // soft delete

    PostResponse publish(Long id);

    PostResponse getPublicBySlug(String slug);

    Page<PostResponse> listPublished(Pageable pageable);
}
