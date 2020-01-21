package com.example.demo.service;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.savingsgoal.SavingsGoal;
import com.example.demo.util.HttpHeadersGenerator;
import com.example.demo.util.UUIDGenerator;

@Service
public class CreateSavingsGoalService {
	 private final RestTemplate restTemplate;

	    private final HttpHeadersGenerator httpHeadersGenerator;

	    private final UUIDGenerator uuidGenerator;

	    public CreateSavingsGoalService(RestTemplate restTemplate,

	                                    HttpHeadersGenerator httpHeadersGenerator, UUIDGenerator uuidGenerator) {
	        this.restTemplate = restTemplate;
	        this.httpHeadersGenerator = httpHeadersGenerator;
	        this.uuidGenerator = uuidGenerator;
	    }

	    public String createSavingsGoal() {

	        Map<String, String> urlParams = new HashMap<String, String>();
	        String uuid = uuidGenerator.getUUID();
	        urlParams.put("savingsGoalUid", uuid);
	        String accId = "ed5437b1-e6d5-436a-9dc4-b780296a2529";
	        urlParams.put("accountUid", accId);

	        HttpEntity<SavingsGoal> putEntity = getHttpEntity();

	        restTemplate.exchange("https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}",
	                HttpMethod.PUT, putEntity, Void.class, urlParams);

	        return uuid;

	    }

	    private HttpEntity<SavingsGoal> getHttpEntity() {

	        HttpHeaders httpHeaders = httpHeadersGenerator.createHttpHeaders();

	        SavingsGoal savingsGoal = new SavingsGoal();
	        savingsGoal.setName("Trip to Southampton Saving Goal");
	        savingsGoal.setCurrency(Currency.getInstance("GBP"));

	        return new HttpEntity<>(savingsGoal, httpHeaders);
	    }
}
