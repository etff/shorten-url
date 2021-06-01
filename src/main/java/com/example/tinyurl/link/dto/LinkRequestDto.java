package com.example.tinyurl.link.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

/**
 * 링크 요청 정보.
 */
@Getter
@NoArgsConstructor
public class LinkRequestDto {
    @URL
    private String link;

    public LinkRequestDto(String link) {
        this.link = link;
    }
}
