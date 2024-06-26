package com.payxpert.entity;

import java.math.BigDecimal;

public class Tax {
    private int taxID;
    private int employeeID;
    private int taxYear;
    private BigDecimal taxableIncome;
    private BigDecimal taxAmount;

   
    public Tax(int taxID, int employeeID, int taxYear, BigDecimal taxableIncome, BigDecimal taxAmount) {
        this.taxID = taxID;
        this.employeeID = employeeID;
        this.taxYear = taxYear;
        this.taxableIncome = taxableIncome;
        this.taxAmount = taxAmount;
    }

    // Getters and setters
    public int getTaxID() {
        return taxID;
    }

    public void setTaxID(int taxID) {
        this.taxID = taxID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }

    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public String toString() {
        return "Tax ID: " + taxID + ", Employee ID: " + employeeID + ", Tax Year: " + taxYear + ", Taxable Income: " + taxableIncome
                + ", Tax Amount: " + taxAmount;
    }
}
