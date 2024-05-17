package com.payxpert.dao;

import com.payxpert.entity.Payroll;
import com.payxpert.exception.PayrollGenerationException;
import com.payxpert.util.DBConnUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollServiceImpl implements PayrollService {
    
    private static final String CONNECTION_STRING = DBConnUtil.getConnectionString();

    @Override
    public void generatePayroll(int employeeId, String startDate, String endDate, BigDecimal basicSalary, BigDecimal overtimePay, BigDecimal deductions) {
    
    BigDecimal totalSalary = basicSalary.add(overtimePay);
    BigDecimal netSalary = totalSalary.subtract(deductions);

    
    String insertQuery = "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
         PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setString(2, startDate);
        preparedStatement.setString(3, endDate);
        preparedStatement.setBigDecimal(4, basicSalary);
        preparedStatement.setBigDecimal(5, overtimePay);
        preparedStatement.setBigDecimal(6, deductions);
        preparedStatement.setBigDecimal(7, netSalary);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        handleSQLException(e);
    }
}
    public void generatePayrollsForMultipleEmployees(List<Payroll> payrolls) {
        for (Payroll payroll : payrolls) {
            generatePayroll(payroll.getEmployeeID(), payroll.getPayPeriodStartDate(), payroll.getPayPeriodEndDate(),
                    payroll.getBasicSalary(), payroll.getOvertimePay(), payroll.getDeductions());
        }
    }


    @Override
    public Payroll getPayrollById(int payrollId) {
        String query = "SELECT * FROM Payroll WHERE PayrollID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, payrollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPayroll(resultSet);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        throw new PayrollGenerationException("Payroll with ID " + payrollId + " not found.");
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> payrolls = new ArrayList<>();
        String query = "SELECT * FROM Payroll WHERE EmployeeID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payrolls.add(mapResultSetToPayroll(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return payrolls;
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(String startDate, String endDate) {
        List<Payroll> payrolls = new ArrayList<>();
        String query = "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payrolls.add(mapResultSetToPayroll(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return payrolls;
    }

    @Override
    public BigDecimal calculateGrossSalary(BigDecimal basicSalary, BigDecimal overtimePay) {
        return basicSalary.add(overtimePay);
    }

    @Override
    public BigDecimal calculateNetSalary(BigDecimal grossSalary, BigDecimal deductions) {
        return grossSalary.subtract(deductions);
    }

    
    private Payroll mapResultSetToPayroll(ResultSet resultSet) throws SQLException {
        return new Payroll(
                resultSet.getInt("PayrollID"),
                resultSet.getInt("EmployeeID"),
                resultSet.getString("PayPeriodStartDate"),
                resultSet.getString("PayPeriodEndDate"),
                resultSet.getBigDecimal("BasicSalary"),
                resultSet.getBigDecimal("OvertimePay"),
                resultSet.getBigDecimal("Deductions"),
                resultSet.getBigDecimal("NetSalary")
        );
    }

    
    private void handleSQLException(SQLException e) {
        
        throw new RuntimeException("An error occurred while accessing the database: " + e.getMessage());
    }


}
