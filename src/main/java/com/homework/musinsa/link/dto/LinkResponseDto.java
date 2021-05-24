package com.homework.musinsa.link.dto;

import com.homework.musinsa.link.domain.Link;
import lombok.Getter;

/**
 * 링크 응답 정보.
 */
@Getter
public class LinkResponseDto {
    public static final String SLASH = "/";

    private String link;
    private int count;

    public LinkResponseDto(String link, int count) {
        this.link = link;
        this.count = count;
    }

    public LinkResponseDto(Link savedLink) {
        this.link = savedLink.getOriginalLink();
        this.count = savedLink.getCount();
    }

    public LinkResponseDto(Link savedLink, String baseUrl) {
        this.link = baseUrl + SLASH + savedLink.getKey();
        this.count = savedLink.getCount();
    }
}
