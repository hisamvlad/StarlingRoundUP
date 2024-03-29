package com.example.demo.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.example.demo.util.HttpHeadersGenerator;
import com.example.demo.util.UUIDGenerator;

public class CreateSavingsGoalServiceTest {
	public static final String TEST_UUID = "testUUid";
	public static final String TEST_ACCOUNT_UUID = "testAccountUUid";

    private RestTemplate restTemplate = mock(RestTemplate.class);

    private HttpHeadersGenerator httpHeadersGenerator = mock(HttpHeadersGenerator.class);

    private UUIDGenerator uuidGenerator = mock(UUIDGenerator.class);

    private CreateSavingsGoalService createSavingsGoalService;

    @BeforeEach
    public void before() {
      createSavingsGoalService = new CreateSavingsGoalService(restTemplate, httpHeadersGenerator, uuidGenerator);
    }

    @Test
    public void testCreateSavingsGoal() {

        when(uuidGenerator.getUUID()).thenReturn(TEST_UUID);
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("savingsGoalUid", TEST_UUID);
        urlParams.put("accountUid", TEST_ACCOUNT_UUID);

        String savingsGoal = createSavingsGoalService.createSavingsGoal();

        verify(restTemplate).exchange(eq("https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}"),
                eq(HttpMethod.PUT), (HttpEntity<?>) any(HttpEntity.class), eq(Void.class), eq(urlParams));

        assertSame(TEST_UUID, savingsGoal);

    }
}
