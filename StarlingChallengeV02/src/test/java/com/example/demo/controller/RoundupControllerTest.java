package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.service.RoundupService;

public class RoundupControllerTest {
	public static final String TEST_UUID = "testUUID";
    private RoundupService roundupService = mock(RoundupService.class);

    RoundupController roundupController;

    @BeforeEach
    public void before() {
        roundupController = new RoundupController(roundupService);
    }

    @Test
    public void shouldCallRoundupService() {

        when(roundupService.roundup()).thenReturn(TEST_UUID);
        String roundup = roundupController.roundup();

        assertEquals(TEST_UUID, roundup);

    }
}
