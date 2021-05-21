package com.homework.musinsa.link.application;

import com.homework.musinsa.link.domain.Link;
import com.homework.musinsa.link.dto.LinkResponseDto;
import com.homework.musinsa.link.infra.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * 링크 정보를 다룬다.
 */
@Service
@RequiredArgsConstructor
public class LinkService {
    public static final String NOT_VALID_LINK_KEY = "잘못된 링크 입력입니다";
    private final LinkRepository linkRepository;

    /**
     * 단축 URL을 생성한다./
     *
     * @param link 원본 링크 정보
     * @return 단축 링크 정보
     */
    public LinkResponseDto createShortUrl(final String link) {
        final String key = RandomStringUtils.randomAlphabetic(8);
        final Link savedLink = linkRepository.save(new Link(key, link));
        return new LinkResponseDto(savedLink.getKey());
    }

    /**
     * 원본 링크 정보를 가져온다.
     *
     * @param key 단축 정보 URL
     * @return 원본 링크 정보
     */
    public LinkResponseDto getOriginalLink(final String key) {
        final Link link = linkRepository.findById(key)
                .orElseThrow(() -> new LinkNotFoundException(NOT_VALID_LINK_KEY));
        return new LinkResponseDto(link.getOriginalLink());
    }
}
