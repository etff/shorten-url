package com.homework.musinsa.link.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinkTest {

    @Test
    void canCreate() {
        Link link = new Link("google", "https://www.google.com", 0);

        assertThat(link).isNotNull();
    }
}
