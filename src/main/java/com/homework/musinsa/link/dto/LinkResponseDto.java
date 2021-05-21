package com.homework.musinsa.link.dto;

import lombok.Getter;

/**
 * 링크 응답 정보.
 */
@Getter
public class LinkResponseDto {
    private final String link;

    public LinkResponseDto(String link) {
        this.link = link;
    }
}
