package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.transaction.Transaction;

public class RoundupCalculatorTest {
	private RoundupCalculator roundupCalculator = new RoundupCalculator();

    @Test
    public void shouldReturnRoundupAmount() {
        Transaction transaction1 = mock(Transaction.class);
        when(transaction1.getAmount()).thenReturn(BigDecimal.valueOf(4.35));
        Transaction transaction2 = mock(Transaction.class);
        when(transaction2.getAmount()).thenReturn(BigDecimal.valueOf(5.20));
        Transaction transaction3 = mock(Transaction.class);
        when(transaction3.getAmount()).thenReturn(BigDecimal.valueOf(0.87));
        Transaction transaction4 = mock(Transaction.class);
        when(transaction4.getAmount()).thenReturn(BigDecimal.valueOf(-2.56));


        BigDecimal roundupValue = roundupCalculator.calculate(List.of(transaction1, transaction2, transaction3));

        assertEquals(BigDecimal.valueOf(1.58), roundupValue);
    }
}
