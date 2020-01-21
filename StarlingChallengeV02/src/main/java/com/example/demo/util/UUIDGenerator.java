package com.example.demo.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UUIDGenerator {
	public String getUUID() {
        return UUID.randomUUID().toString();
    }
}
