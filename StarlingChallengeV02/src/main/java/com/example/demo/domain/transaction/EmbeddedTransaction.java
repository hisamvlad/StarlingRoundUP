package com.example.demo.domain.transaction;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddedTransaction {
	private List<Transaction> transaction;

    public List<Transaction> getTransactions() {
        return transaction;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transaction = transactions;
    }
}
