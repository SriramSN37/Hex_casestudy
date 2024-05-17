package com.payxpert.entity;

import java.math.BigDecimal;

public class Payroll {
    private int payrollID;
    private int employeeID;
    private String payPeriodStartDate;
    private String payPeriodEndDate;
    private BigDecimal basicSalary;
    private BigDecimal overtimePay;
    private BigDecimal deductions;
    private BigDecimal netSalary;

  
    public Payroll(int payrollID, int employeeID, String payPeriodStartDate, String payPeriodEndDate,
                   BigDecimal basicSalary, BigDecimal overtimePay, BigDecimal deductions, BigDecimal netSalary) {
        this.payrollID = payrollID;
        this.employeeID = employeeID;
        this.payPeriodStartDate = payPeriodStartDate;
        this.payPeriodEndDate = payPeriodEndDate;
        this.basicSalary = basicSalary;
        this.overtimePay = overtimePay;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    // Getters and setters
    public int getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(int payrollID) {
        this.payrollID = payrollID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public void setPayPeriodStartDate(String payPeriodStartDate) {
        this.payPeriodStartDate = payPeriodStartDate;
    }

    public String getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public void setPayPeriodEndDate(String payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(BigDecimal overtimePay) {
        this.overtimePay = overtimePay;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    @Override
    public String toString() {
        return "Payroll ID: " + payrollID + ", Employee ID: " + employeeID + ", Pay Period Start Date: " + payPeriodStartDate
                + ", Pay Period End Date: " + payPeriodEndDate + ", Basic Salary: " + basicSalary + ", Overtime Pay: " + overtimePay
                + ", Deductions: " + deductions + ", Net Salary: " + netSalary;
    }
}

