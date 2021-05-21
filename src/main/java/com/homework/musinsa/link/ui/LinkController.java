package com.homework.musinsa.link.ui;

import com.homework.musinsa.link.application.LinkService;
import com.homework.musinsa.link.dto.LinkRequestDto;
import com.homework.musinsa.link.dto.LinkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/link")
    public ResponseEntity<LinkResponseDto> createShortUrl(@RequestBody @Valid LinkRequestDto dto) {
        LinkResponseDto shortUrl = linkService.createShortUrl(dto.getLink());
        return ResponseEntity.created(URI.create("/" + shortUrl.getLink()))
                .body(shortUrl);
    }

    @GetMapping("{key}")
    public ResponseEntity<Object> getLink(@PathVariable String key) {
        final LinkResponseDto responseDto = linkService.getOriginalLink(key);
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                .header("Location", responseDto.getLink()).build();
    }
}
