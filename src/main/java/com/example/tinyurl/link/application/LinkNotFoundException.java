package com.example.tinyurl.link.application;

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException() {
        super();
    }

    public LinkNotFoundException(String message) {
        super(message);
    }
}
