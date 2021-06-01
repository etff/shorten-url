package com.example.tinyurl.link.domain;

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
    private final int count;

    @Builder
    public Link(final String key, final String originalLink, final int count) {
        this.key = key;
        this.originalLink = originalLink;
        this.count = count;
    }

    public static Link generateLink(String key, String originalLink) {
        return Link.builder().key(key)
                .originalLink(originalLink)
                .count(0)
                .build();
    }

    public Link updateCount() {
        return Link.builder().key(this.key)
                .originalLink(this.originalLink)
                .count(this.count + 1)
                .build();
    }
}
