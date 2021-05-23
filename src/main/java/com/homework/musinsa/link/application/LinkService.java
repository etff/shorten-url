package com.homework.musinsa.link.application;

import com.google.common.hash.Hashing;
import com.homework.musinsa.link.domain.Link;
import com.homework.musinsa.link.dto.LinkResponseDto;
import com.homework.musinsa.link.infra.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * 링크 정보를 다룬다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LinkService {
    public static final String NOT_VALID_LINK_KEY = "잘못된 링크 입력입니다";
    private final LinkRepository linkRepository;

    /**
     * 단축 URL을 생성한다./
     *
     * @param originalLink 원본 링크 정보
     * @return 단축 링크 정보
     */
    public String createShortUrl(final String originalLink) {
        final String id = Hashing.murmur3_32().hashString(originalLink, StandardCharsets.UTF_8).toString();
        Optional<Link> findLink = linkRepository.findById(id);
        if (findLink.isPresent()) {
            Link savedLink = linkRepository.save(findLink.get().updateCount());
            return savedLink.getKey();
        }
        final Link savedLink = linkRepository.save(Link.generateLink(id, originalLink));
        return savedLink.getKey();
    }

    /**
     * 원본 링크 정보를 가져온다.
     *
     * @param key 단축 정보 URL
     * @return 원본 링크 정보
     */
    public LinkResponseDto getOriginalLink(final String key) {
        if (key == null || key.isEmpty()) {
            throw new LinkNotFoundException(NOT_VALID_LINK_KEY);
        }
        final Link link = linkRepository.findById(key)
                .orElseThrow(() -> new LinkNotFoundException(NOT_VALID_LINK_KEY));
        return new LinkResponseDto(link.getOriginalLink());
    }
}
