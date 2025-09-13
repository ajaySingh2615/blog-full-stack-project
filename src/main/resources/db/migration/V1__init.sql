-- Schema: blog backbone (MySQL 8+)
-- Engine/charset
SET NAMES utf8mb4;
SET time_zone = '+00:00';

CREATE TABLE IF NOT EXISTS categories (
                                          id BIGINT NOT NULL AUTO_INCREMENT,
                                          name VARCHAR(120) NOT NULL,
    slug VARCHAR(160) NOT NULL,
    description TEXT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uk_category_name UNIQUE (name),
    CONSTRAINT uk_category_slug UNIQUE (slug),
    INDEX idx_category_slug (slug)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS tags (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    name VARCHAR(120) NOT NULL,
    slug VARCHAR(160) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uk_tag_name UNIQUE (name),
    CONSTRAINT uk_tag_slug UNIQUE (slug),
    INDEX idx_tag_slug (slug)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS posts (
                                     id BIGINT NOT NULL AUTO_INCREMENT,
                                     title VARCHAR(200) NOT NULL,
    slug VARCHAR(180) NOT NULL,
    excerpt VARCHAR(500) NULL,
    content LONGTEXT NOT NULL,
    status VARCHAR(16) NOT NULL,                       -- DRAFT / PUBLISHED
    published_at TIMESTAMP NULL,
    author_name VARCHAR(120) NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uk_post_slug UNIQUE (slug),
    CONSTRAINT fk_post_category FOREIGN KEY (category_id) REFERENCES categories(id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    INDEX idx_post_slug (slug),
    INDEX idx_post_status_published_at (status, published_at),
    INDEX idx_post_category (category_id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS post_tags (
                                         post_id BIGINT NOT NULL,
                                         tag_id BIGINT NOT NULL,
                                         PRIMARY KEY (post_id, tag_id),
    CONSTRAINT fk_posttag_post FOREIGN KEY (post_id) REFERENCES posts(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_posttag_tag FOREIGN KEY (tag_id) REFERENCES tags(id) ON UPDATE CASCADE ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
