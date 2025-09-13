package com.cadt.blog.domain;

import com.cadt.blog.common.entity.BaseEntity;
import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_category_name", columnNames = "name"),
                @UniqueConstraint(name = "uk_category_slug", columnNames = "slug")
        },
        indexes = {
                @Index(name = "idx_category_slug", columnList = "slug")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 160)
    private String slug;

    @Column(columnDefinition = "text")
    private String description;

}
