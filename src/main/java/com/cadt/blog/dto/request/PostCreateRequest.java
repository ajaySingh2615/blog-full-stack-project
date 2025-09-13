package com.cadt.blog.dto.request;

import com.cadt.blog.common.enums.PostStatus;
import jakarta.validation.constraints.*;
import java.util.List;

public record PostCreateRequest(
        @NotBlank @Size(min = 3, max = 200) String title,
        @Size(max = 500) String excerpt,
        @NotBlank String content,
        @NotNull Long categoryId,
        @Size(max = 10) List<Long> tagIds,
        PostStatus status,                        // optional; default DRAFT
        @NotBlank @Size(max = 120) String authorName
) {}
