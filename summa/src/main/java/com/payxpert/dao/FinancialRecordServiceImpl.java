package com.payxpert.dao;

import com.payxpert.entity.FinancialRecord;
import com.payxpert.exception.FinancialRecordException;
import com.payxpert.util.DBConnUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordServiceImpl implements FinancialRecordService {
    
    private static final String CONNECTION_STRING = DBConnUtil.getConnectionString();

    @Override
    public void addFinancialRecord(int recordID, int employeeId, String recordDate, String description, BigDecimal amount, String recordType) {
        
        String insertQuery = "INSERT INTO FinancialRecord (RecordID, EmployeeID, RecordDate, Description, Amount, RecordType) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, recordID);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.setString(3, recordDate);
            preparedStatement.setString(4, description);
            preparedStatement.setBigDecimal(5, amount);
            preparedStatement.setString(6, recordType);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) {
        String query = "SELECT * FROM FinancialRecord WHERE RecordID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recordId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToFinancialRecord(resultSet);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        throw new FinancialRecordException("Financial record with ID " + recordId + " not found.");
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        String query = "SELECT * FROM FinancialRecord WHERE EmployeeID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                financialRecords.add(mapResultSetToFinancialRecord(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return financialRecords;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(String recordDate) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        String query = "SELECT * FROM FinancialRecord WHERE RecordDate = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recordDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                financialRecords.add(mapResultSetToFinancialRecord(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return financialRecords;
    }

   
    private FinancialRecord mapResultSetToFinancialRecord(ResultSet resultSet) throws SQLException {
        return new FinancialRecord(
                resultSet.getInt("RecordID"),
                resultSet.getInt("EmployeeID"),
                resultSet.getString("RecordDate"),
                resultSet.getString("Description"),
                resultSet.getBigDecimal("Amount"),
                resultSet.getString("RecordType")
        );
    }

    
    private void handleSQLException(SQLException e) {
        
        throw new RuntimeException("An error occurred while accessing the database: " + e.getMessage());
    }
}

