package com.cadt.blog.dto.request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record CategoryUpdateRequest(
        @NotBlank @Size(min = 2, max = 120) String name,
        @Size(max = 2000) String description
) {}
