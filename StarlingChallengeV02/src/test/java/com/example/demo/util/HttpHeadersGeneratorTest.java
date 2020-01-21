package com.example.demo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpHeadersGeneratorTest {

    @Test
    public void shouldCreateHttpEntity() {
        HttpHeadersGenerator httpHeadersGenerator = new HttpHeadersGenerator();

        HttpHeaders httpHeaders = httpHeadersGenerator.createHttpHeaders();

        assertEquals(httpHeaders.getAccept(), List.of(MediaType.APPLICATION_JSON));
        assertEquals(httpHeaders.getContentType(), MediaType.APPLICATION_JSON);
        assertNotNull(httpHeaders.get("Authorization"));
    }
}
