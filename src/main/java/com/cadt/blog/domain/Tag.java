package com.cadt.blog.domain;

import com.cadt.blog.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tags",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_tag_name", columnNames = "name"),
                @UniqueConstraint(name = "uk_tag_slug", columnNames = "slug")
        },
        indexes = {
                @Index(name = "idx_tag_slug", columnList = "slug")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag extends BaseEntity {

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 160)
    private String slug;
}
