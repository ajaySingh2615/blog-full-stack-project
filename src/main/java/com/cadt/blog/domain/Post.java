package com.cadt.blog.domain;

import com.cadt.blog.common.entity.BaseEntity;
import com.cadt.blog.common.enums.PostStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "posts",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_post_slug", columnNames = "slug")
        },
        indexes = {
                @Index(name = "idx_post_slug", columnList = "slug"),
                @Index(name = "idx_post_status_published_at", columnList = "status, published_at"),
                @Index(name = "idx_post_category", columnList = "category_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 200)
    private String slug;

    @Column(length = 500)
    private String excerpt;

    @Lob
    @Column(columnDefinition = "longtext", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private PostStatus status;

    @Column(name = "published_at")
    private Instant publishedAt;

    @Column(name = "author_name", length = 120, nullable = false)
    private String authorName;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_post_category"))
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_posttag_post")),
            inverseJoinColumns = @JoinColumn(name = "tag_id", foreignKey = @ForeignKey(name = "fk_posttag_tag")),
            uniqueConstraints = @UniqueConstraint(name = "uk_post_tag", columnNames = {"post_id", "tag_id"})
    )
    @Builder.Default
    private Set<Tag> tags = new LinkedHashSet<>();
}
