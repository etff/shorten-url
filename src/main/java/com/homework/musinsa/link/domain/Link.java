package com.homework.musinsa.link.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash("link")
public class Link implements Serializable {
    @Id
    private final String key;
    private final String originalLink;

    @Builder
    public Link(final String key, final String originalLink) {
        this.key = key;
        this.originalLink = originalLink;
    }
}
