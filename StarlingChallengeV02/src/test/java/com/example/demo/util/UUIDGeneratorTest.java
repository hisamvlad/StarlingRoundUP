package com.example.demo.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UUIDGeneratorTest {
    private UUIDGenerator uuidGenerator = new UUIDGenerator();

    @Test
    public void shouldGenerateUUIDString() {
        String uuid = uuidGenerator.getUUID();

        assertNotNull(uuid);
    }

}
