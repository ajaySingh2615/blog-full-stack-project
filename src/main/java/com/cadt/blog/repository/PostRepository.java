package com.cadt.blog.repository;

import com.cadt.blog.common.enums.PostStatus;
import com.cadt.blog.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsBySlug(String slug);

    Optional<Post> findBySlugAndDeletedFalseAndStatus(String slug, PostStatus status);

    Optional<Post> findByIdAndDeletedFalse(Long id);

    Page<Post> findAllByDeletedFalseAndStatus(PostStatus status, Pageable pageable);
}
