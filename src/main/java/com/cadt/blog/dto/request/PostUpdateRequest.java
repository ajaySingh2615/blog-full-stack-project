package com.cadt.blog.dto.request;

import com.cadt.blog.common.enums.PostStatus;
import jakarta.validation.constraints.Size;
import java.util.List;

public record PostUpdateRequest(
        @Size(min = 3, max = 200) String title,
        @Size(max = 500) String excerpt,
        String content,
        Long categoryId,
        @Size(max = 10) List<Long> tagIds,
        PostStatus status
) {}
