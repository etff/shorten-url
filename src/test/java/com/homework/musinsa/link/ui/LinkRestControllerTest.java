package com.homework.musinsa.link.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.musinsa.link.application.LinkNotFoundException;
import com.homework.musinsa.link.application.LinkService;
import com.homework.musinsa.link.dto.LinkRequestDto;
import com.homework.musinsa.link.dto.LinkResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("LinkController 클래스")
class LinkRestControllerTest {
    private static final String LINK_URL = "https://www.google.com";
    private static final String NOT_EXIST_LINK_URL = LINK_URL + "_NOT_EXIST";
    private static final String SHORT_URL = "fBXbFrVV";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LinkService linkService;

    @Autowired
    protected ObjectMapper objectMapper;

    @Nested
    @DisplayName("GET /link는")
    class Describe_getLink {
        @Nested
        @DisplayName("등록된 링크가 있으면")
        class Context_with_exist_link {
            private LinkResponseDto linkResponseDto;
            private String key;

            @BeforeEach
            void setUp() {
                linkResponseDto = new LinkResponseDto(LINK_URL, 0);
                key = SHORT_URL;

                given(linkService.getOriginalLink(key))
                        .willReturn(linkResponseDto);
            }

            @DisplayName("308 Redirect를 응답한다.")
            @Test
            void It_responds_redirect() throws Exception {
                mockMvc.perform(get("/{key}", key))
                        .andExpect(status().is3xxRedirection());
            }
        }

        @Nested
        @DisplayName("등록된 링크가 없으면")
        class Context_without_not_exist_link {
            @BeforeEach
            void setUp() {
                given(linkService.getOriginalLink(eq(NOT_EXIST_LINK_URL)))
                        .willThrow(new LinkNotFoundException(NOT_EXIST_LINK_URL));
            }

            @DisplayName("404 상태코드, Not Found 상태를 응답한다.")
            @Test
            void It_responds_not_found() throws Exception {
                mockMvc.perform(get("/{key}", NOT_EXIST_LINK_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        @DisplayName("POST /link 는")
        class Describe_createShortUrl {
            LinkRequestDto requestDto;
            LinkResponseDto responseDto;

            @Nested
            @DisplayName("등록할 링크가 주어지면")
            class Context_with_link {
                @BeforeEach
                void setUp() {
                    requestDto = new LinkRequestDto(LINK_URL);
                    responseDto = new LinkResponseDto(SHORT_URL, 0);
                    given(linkService.createShortUrl(anyString(), anyString()))
                            .willReturn(responseDto);
                }

                @DisplayName("201 Created 상태를 응답한다.")
                @Test
                void It_responds_short_url() throws Exception {
                    mockMvc.perform(post("/link")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(requestDto)))
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("link").exists());
                }
            }
        }
    }
}
