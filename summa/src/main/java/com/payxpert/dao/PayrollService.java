package com.payxpert.dao;

import com.payxpert.entity.Payroll;

import java.math.BigDecimal;
import java.util.List;

public interface PayrollService {
    void generatePayroll(int employeeId, String startDate, String endDate, BigDecimal basicSalary, BigDecimal overtimePay, BigDecimal deductions);
    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(String startDate, String endDate);
    BigDecimal calculateGrossSalary(BigDecimal basicSalary, BigDecimal overtimePay);
    BigDecimal calculateNetSalary(BigDecimal grossSalary, BigDecimal deductions);
}

