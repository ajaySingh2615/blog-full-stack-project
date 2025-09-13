package com.cadt.blog.dto.response;

import com.cadt.blog.common.enums.PostStatus;
import com.cadt.blog.dto.response.common.CategorySummary;
import com.cadt.blog.dto.response.common.TagSummary;

import java.time.Instant;
import java.util.List;

public record PostResponse(
        Long id,
        String title,
        String slug,
        String excerpt,
        String content,
        PostStatus status,
        Instant publishedAt,
        String authorName,
        CategorySummary category,
        List<TagSummary> tags,
        Instant createdAt,
        Instant updatedAt
) {
}
