package com.example.demo.domain.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponse {
	 @JsonProperty("_embedded")
	    private EmbeddedTransaction embeddedTransactions;

	    public EmbeddedTransaction getEmbeddedTransactions() {
	        return embeddedTransactions;
	    }

	    public void setEmbeddedTransactions(EmbeddedTransaction embeddedTransactions) {
	        this.embeddedTransactions = embeddedTransactions;
	    }
}
