package com.example.demo.service;


import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.example.demo.util.HttpHeadersGenerator;
import com.example.demo.util.UUIDGenerator;

public class PutSavingsServiceTest {
	public static final String TRANSFER_UUID = "transferUUID";
    public static final String SAVINGS_GOAL_UUID = "savingsGoalUid";
    public static final String ACCOUNT_UUID = "accountUid";

    private RestTemplate restTemplate = mock(RestTemplate.class);

    private HttpHeadersGenerator httpHeadersGenerator = mock(HttpHeadersGenerator.class);

    private UUIDGenerator uuidGenerator = mock(UUIDGenerator.class);

    private PutSavingsService putSavingsService;

    @BeforeEach
    public void before() {
        putSavingsService = new PutSavingsService(restTemplate, httpHeadersGenerator, uuidGenerator);
    }

    @Test
    public void shouldCreateSavingsService() {

        when(uuidGenerator.getUUID()).thenReturn(TRANSFER_UUID);
        Map<String, String> urlParams = new HashMap<String, String>();

        urlParams.put("savingsGoalUid", SAVINGS_GOAL_UUID);
        urlParams.put("transferUid", TRANSFER_UUID);
        urlParams.put("accountUid", ACCOUNT_UUID);

        putSavingsService.putSavings(SAVINGS_GOAL_UUID, BigDecimal.TEN);

        verify(restTemplate).exchange(eq("https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}/add-money/{transferUid}"),
                eq(HttpMethod.PUT), (HttpEntity<?>) any(HttpEntity.class), eq(String.class), eq(urlParams));

    }
}
