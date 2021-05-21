package com.homework.musinsa.link.application;

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException() {
        super();
    }

    public LinkNotFoundException(String message) {
        super(message);
    }
}
