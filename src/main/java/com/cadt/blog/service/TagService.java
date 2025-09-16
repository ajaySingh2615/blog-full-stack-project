package com.cadt.blog.service;

import com.cadt.blog.dto.request.TagCreateRequest;
import com.cadt.blog.dto.response.TagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    TagResponse create(TagCreateRequest req);

    void delete(Long id);

    Page<TagResponse> list(String search, Pageable pageable);
}
