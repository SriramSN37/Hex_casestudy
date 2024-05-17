package com.payxpert;

import com.payxpert.dao.TaxServiceImpl;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxServiceTest {

    private final TaxServiceImpl taxService = new TaxServiceImpl();

    @SuppressWarnings("deprecation")
    @Test
    public void testVerifyTaxCalculationForHighIncomeEmployee() {
        // Arrange
        BigDecimal taxableIncome = new BigDecimal("100000.00");
        BigDecimal taxPercent = new BigDecimal("30.00");
        BigDecimal expectedTaxAmount = new BigDecimal("30000.00");

        // Act
        BigDecimal actualTaxAmount = taxService.calTax(taxableIncome, taxPercent).setScale(2, BigDecimal.ROUND_HALF_UP);

        // Assert
        assertEquals(expectedTaxAmount, actualTaxAmount, "The calculated tax amount is incorrect for a high-income employee.");
    }
}

