package com.example.tinyurl.link.application;

import com.example.tinyurl.link.domain.Link;
import com.example.tinyurl.link.dto.LinkResponseDto;
import com.example.tinyurl.link.infra.LinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LinkServiceTest {
    private static final String ORIGINAL_LINK = "https://www.google.com";
    private static final String KEY = "0f32a2f0";

    private LinkRepository linkRepository = mock(LinkRepository.class);
    private LinkResponseDto linkResponseDto;

    private LinkService linkService;
    private Link link;

    @BeforeEach
    void setUp() {
        linkService = new LinkService(linkRepository);
        linkResponseDto = new LinkResponseDto("4170157c", 0);

        link = Link.generateLink(KEY, ORIGINAL_LINK);
    }

    @Test
    void createUrl() {
        // given
        final String baseUrl = "http://localhost";
        when(linkRepository.findById(anyString()))
                .thenReturn(Optional.ofNullable(null));
        when(linkRepository.save(any(Link.class)))
                .thenReturn(link);
        // when
        LinkResponseDto shortUrl = linkService.createShortUrl(ORIGINAL_LINK, baseUrl);

        // then
        assertThat(shortUrl.getLink()).isNotEqualTo(ORIGINAL_LINK);
    }

    @Test
    void findOriginalLink() {
        // given
        when(linkRepository.findById(anyString()))
                .thenReturn(Optional.of(link));
        // when
        LinkResponseDto actual = linkService.getOriginalLink(KEY);

        // then
        assertThat(actual.getLink()).isEqualTo(ORIGINAL_LINK);
    }
}
