package com.example.demo.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.transaction.Transaction;
import com.example.demo.domain.transaction.TransactionResponse;
import com.example.demo.util.HttpHeadersGenerator;

@Service
public class RetrieveTransactionsService {
	private final RestTemplate restTemplate;

    private final HttpHeadersGenerator httpHeadersGenerator;

    public RetrieveTransactionsService(RestTemplate restTemplate, HttpHeadersGenerator httpHeadersGenerator) {
        this.restTemplate = restTemplate;
        this.httpHeadersGenerator = httpHeadersGenerator;
    }

    public List<Transaction> getTransactions() {

        HttpEntity<TransactionResponse> getEntity = getHttpEntity();

        TransactionResponse rateResponse =
                restTemplate.exchange("https://api-sandbox.starlingbank.com/api/v2/feed/account/ed5437b1-e6d5-436a-9dc4-b780296a2529/category/a3266fcf-f7e7-44e9-b872-8ede2d2d1b38",
                        HttpMethod.GET, getEntity, new ParameterizedTypeReference<TransactionResponse>() {
                        }).getBody();
        return rateResponse.getEmbeddedTransactions().getTransactions();

    }

    private HttpEntity<TransactionResponse> getHttpEntity() {
        HttpHeaders httpHeaders = httpHeadersGenerator.createHttpHeaders();
        return new HttpEntity<>(httpHeaders);
    }
}
