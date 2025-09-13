package com.cadt.blog.dto.response;

import java.time.Instant;

public record TagResponse(
        Long id, String name, String slug,
        Instant createdAt, Instant updatedAt
) {
}
