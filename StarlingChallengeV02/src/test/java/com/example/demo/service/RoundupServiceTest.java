package com.example.demo.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.transaction.Transaction;

public class RoundupServiceTest {
	 public static final String SAVINGS_GOAL_UUID = "testUuid";
	    public static final BigDecimal ROUNDEDUP_AMOUNT = BigDecimal.TEN;
	    private RetrieveTransactionsService retrieveTransactionsService = mock(RetrieveTransactionsService.class);

	    private CreateSavingsGoalService createSavingsGoalService = mock(CreateSavingsGoalService.class);

	    private PutSavingsService putSavingsService = mock(PutSavingsService.class);

	    private RoundupCalculator roundupCalculator = mock(RoundupCalculator.class);

	    private RoundupService roundupService;

	    @BeforeEach
	    public void before() {
	        roundupService = new RoundupService(retrieveTransactionsService, createSavingsGoalService, putSavingsService, roundupCalculator);
	    }

	    @Test
	    public void shouldCreateSavings() {
	        Transaction transaction = mock(Transaction.class);
	        when(retrieveTransactionsService.getTransactions()).thenReturn(List.of(transaction));
	        when(roundupCalculator.calculate(List.of(transaction))).thenReturn(ROUNDEDUP_AMOUNT);
	        when(createSavingsGoalService.createSavingsGoal()).thenReturn(SAVINGS_GOAL_UUID);

	        roundupService.roundup();

	        verify(putSavingsService).putSavings(SAVINGS_GOAL_UUID, ROUNDEDUP_AMOUNT);
	    }
}
