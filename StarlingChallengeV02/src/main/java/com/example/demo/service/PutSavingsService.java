package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.addmoney.AddMoney;
import com.example.demo.domain.addmoney.Amount;
import com.example.demo.util.HttpHeadersGenerator;
import com.example.demo.util.UUIDGenerator;

@Service
public class PutSavingsService {
	private final RestTemplate restTemplate;

    private final HttpHeadersGenerator httpHeadersGenerator;

    private final UUIDGenerator uuidGenerator;

    public PutSavingsService(RestTemplate restTemplate, HttpHeadersGenerator httpHeadersGenerator, UUIDGenerator uuidGenerator) {
        this.restTemplate = restTemplate;
        this.httpHeadersGenerator = httpHeadersGenerator;
        this.uuidGenerator = uuidGenerator;
    }

    public void putSavings(String savingsGoalUUID, BigDecimal roundedupAmount) {

        Map<String, String> urlParams = new HashMap<String, String>();
        String uuid = uuidGenerator.getUUID();
        urlParams.put("savingsGoalUid", savingsGoalUUID);
        urlParams.put("transferUid", uuid);
        String accId = "ed5437b1-e6d5-436a-9dc4-b780296a2529";
        urlParams.put("accountUid", accId);

       HttpEntity<AddMoney> putEntity = getHttpEntity(roundedupAmount);

        restTemplate.exchange("https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}/add-money/{transferUid}",
                HttpMethod.PUT, putEntity, String.class, urlParams);
    }

    private HttpEntity<AddMoney> getHttpEntity(BigDecimal roundedupAmount) {

        HttpHeaders httpHeaders = httpHeadersGenerator.createHttpHeaders();

        Amount amount = new Amount();
        amount.setMinorUnits(roundedupAmount.movePointRight(2));
        amount.setCurrency(Currency.getInstance("GBP"));

        AddMoney addMoney = new AddMoney();
        addMoney.setAmount(amount);

        return new HttpEntity<>(addMoney, httpHeaders);
    }
}
