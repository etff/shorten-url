package com.homework.musinsa.link.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 링크 요청 정보.
 */
@Getter
@NoArgsConstructor
public class LinkRequestDto {
    @NotBlank
    private String link;

    public LinkRequestDto(String link) {
        this.link = link;
    }
}
