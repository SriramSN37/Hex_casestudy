package com.payxpert.dao;

import com.payxpert.entity.Tax;
import java.math.BigDecimal;
import java.util.List;

public interface TaxService {
    void calculateTax(int taxId, int taxPercent);
    Tax getTaxById(int taxId);
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
    BigDecimal calTax(BigDecimal taxableIncome, BigDecimal taxPercent);
}
