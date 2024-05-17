package com.payxpert;

import com.payxpert.dao.PayrollServiceImpl;
import com.payxpert.entity.Payroll;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayrollServiceTest {

    private final PayrollServiceImpl payrollService = new PayrollServiceImpl();

    @Test
    public void testCalculateGrossSalaryForEmployee() {
        // Arrange
        BigDecimal basicSalary = new BigDecimal("5000.00");
        BigDecimal overtimePay = new BigDecimal("500.00");
        BigDecimal expectedGrossSalary = new BigDecimal("5500.00");

        // Act
        BigDecimal actualGrossSalary = payrollService.calculateGrossSalary(basicSalary, overtimePay);

        // Assert
        assertEquals(expectedGrossSalary, actualGrossSalary, "The calculated gross salary is incorrect.");
    }

    @Test
    public void testCalculateNetSalaryAfterDeductions() {
    // Arrange
    BigDecimal grossSalary = new BigDecimal("5500.00");
    BigDecimal deductions = new BigDecimal("500.00");
    BigDecimal expectedNetSalary = new BigDecimal("5000.00");

    // Act
    BigDecimal actualNetSalary = payrollService.calculateNetSalary(grossSalary, deductions);

    // Assert
    assertEquals(expectedNetSalary, actualNetSalary, "The calculated net salary is incorrect.");
}
    @Test
    public void testProcessPayrollForMultipleEmployees() {
        List<Payroll> payrolls = new ArrayList<>();

        Payroll payroll1 = new Payroll(1, 101, "2024-01-01", "2024-01-31", 
                new BigDecimal("3000.00"), new BigDecimal("200.00"), new BigDecimal("300.00"), null);
        Payroll payroll2 = new Payroll(2, 102, "2024-01-01", "2024-01-31", 
                new BigDecimal("3200.00"), new BigDecimal("150.00"), new BigDecimal("350.00"), null);
        Payroll payroll3 = new Payroll(3, 103, "2024-01-01", "2024-01-31", 
                new BigDecimal("2800.00"), new BigDecimal("300.00"), new BigDecimal("250.00"), null);

        payrolls.add(payroll1);
        payrolls.add(payroll2);
        payrolls.add(payroll3);

    }

}
