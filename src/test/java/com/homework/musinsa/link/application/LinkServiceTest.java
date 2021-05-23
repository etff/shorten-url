package com.homework.musinsa.link.application;

import com.homework.musinsa.link.dto.LinkResponseDto;
import com.homework.musinsa.link.infra.LinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LinkServiceTest {

    @Autowired
    private LinkRepository linkRepository;

    private LinkService linkService;

    @BeforeEach
    void setUp() {
        linkService = new LinkService(linkRepository);
    }

    @Test
    void createUrl() {
        // given
        final String input = "https://www.google.com";

        // when
        String shortUrl = linkService.createShortUrl(input);
        // then
        assertThat(shortUrl.length()).isEqualTo(8);
    }

    @Test
    void findOriginalLink() {
        // given
        final String input = "https://www.google.com";
        String shortUrl = linkService.createShortUrl(input);

        // when
        LinkResponseDto actual = linkService.getOriginalLink(shortUrl);

        // then
        assertThat(actual.getLink()).isEqualTo(input);
    }
}
