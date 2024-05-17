package com.payxpert.dao;

import com.payxpert.entity.Tax;
import com.payxpert.exception.TaxCalculationException;
import com.payxpert.util.DBConnUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxServiceImpl implements TaxService {
    
    private static final String CONNECTION_STRING = DBConnUtil.getConnectionString();

    @Override
    public void calculateTax(int taxId, int taxPercent) {
    
    BigDecimal taxableIncome = fetchTaxableIncomeForTax(taxId);

    
    BigDecimal calculatedTaxAmount = taxableIncome.multiply(BigDecimal.valueOf(taxPercent)).divide(BigDecimal.valueOf(100));

    
    String updateQuery = "UPDATE Tax SET TaxAmount = ? WHERE TaxID = ?";
    try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
         PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
        preparedStatement.setBigDecimal(1, calculatedTaxAmount);
        preparedStatement.setInt(2, taxId);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected == 0) {
            throw new TaxCalculationException("Tax with ID " + taxId + " not found.");
        }
    } catch (SQLException e) {
        handleSQLException(e);
    }
}
 




    @Override
    public Tax getTaxById(int taxId) {
        String query = "SELECT * FROM Tax WHERE TaxID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, taxId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToTax(resultSet);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        throw new TaxCalculationException("Tax with ID " + taxId + " not found.");
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> taxes = new ArrayList<>();
        String query = "SELECT * FROM Tax WHERE EmployeeID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                taxes.add(mapResultSetToTax(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return taxes;
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> taxes = new ArrayList<>();
        String query = "SELECT * FROM Tax WHERE TaxYear = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, taxYear);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                taxes.add(mapResultSetToTax(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return taxes;
    }

    private BigDecimal fetchTaxableIncomeForTax(int taxId) {
    // Query the database to fetch the taxable income for the given tax ID
    String query = "SELECT TaxableIncome FROM Tax WHERE TaxID = ?";
    try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, taxId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getBigDecimal("TaxableIncome");
        } else {
            throw new TaxCalculationException("Tax with ID " + taxId + " not found.");
        }
    } catch (SQLException e) {
        handleSQLException(e);
    }
    
    return BigDecimal.ZERO;
}

    @Override
    public BigDecimal calTax(BigDecimal taxableIncome, BigDecimal taxPercent) {
        return taxableIncome.multiply(taxPercent).divide(BigDecimal.valueOf(100));
    }
    
    private Tax mapResultSetToTax(ResultSet resultSet) throws SQLException {
        return new Tax(
                resultSet.getInt("TaxID"),
                resultSet.getInt("EmployeeID"),
                resultSet.getInt("TaxYear"),
                resultSet.getBigDecimal("TaxableIncome"),
                resultSet.getBigDecimal("TaxAmount")
        );
    }

    
    private void handleSQLException(SQLException e) {
        
        throw new RuntimeException("An error occurred while accessing the database: " + e.getMessage());
    }
}
