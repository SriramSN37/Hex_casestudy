package com.payxpert.dao;

import com.payxpert.entity.Employee;
import com.payxpert.exception.EmployeeNotFoundException;
import com.payxpert.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    
    private static final String CONNECTION_STRING = DBConnUtil.getConnectionString();

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        String query = "SELECT * FROM Employee WHERE EmployeeID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEmployee(resultSet);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                employees.add(mapResultSetToEmployee(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return employees;
    }

    @Override
    public void addEmployee(Employee employeeData) {
        String query = "INSERT INTO Employee (EmployeeID, FirstName, LastName, DateOfBirth, Gender, Email, " +
                "PhoneNumber, Address, Position, JoiningDate, TerminationDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeData.getEmployeeID());
            preparedStatement.setString(2, employeeData.getFirstName());
            preparedStatement.setString(3, employeeData.getLastName());
            preparedStatement.setString(4, employeeData.getDateOfBirth());
            preparedStatement.setString(5, employeeData.getGender());
            preparedStatement.setString(6, employeeData.getEmail());
            preparedStatement.setString(7, employeeData.getPhoneNumber());
            preparedStatement.setString(8, employeeData.getAddress());
            preparedStatement.setString(9, employeeData.getPosition());
            preparedStatement.setString(10, employeeData.getJoiningDate());
            preparedStatement.setString(11, employeeData.getTerminationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void updateEmployee(Employee employeeData) {
        String query = "UPDATE Employee SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, " +
                "Email = ?, PhoneNumber = ?, Address = ?, Position = ?, JoiningDate = ?, TerminationDate = ? " +
                "WHERE EmployeeID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employeeData.getFirstName());
            preparedStatement.setString(2, employeeData.getLastName());
            preparedStatement.setString(3, employeeData.getDateOfBirth());
            preparedStatement.setString(4, employeeData.getGender());
            preparedStatement.setString(5, employeeData.getEmail());
            preparedStatement.setString(6, employeeData.getPhoneNumber());
            preparedStatement.setString(7, employeeData.getAddress());
            preparedStatement.setString(8, employeeData.getPosition());
            preparedStatement.setString(9, employeeData.getJoiningDate());
            preparedStatement.setString(10, employeeData.getTerminationDate());
            preparedStatement.setInt(11, employeeData.getEmployeeID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void removeEmployee(int employeeId) {
        String query = "DELETE FROM Employee WHERE EmployeeID = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    
    private Employee mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("EmployeeID"),
                resultSet.getString("FirstName"),
                resultSet.getString("LastName"),
                resultSet.getString("DateOfBirth"),
                resultSet.getString("Gender"),
                resultSet.getString("Email"),
                resultSet.getString("PhoneNumber"),
                resultSet.getString("Address"),
                resultSet.getString("Position"),
                resultSet.getString("JoiningDate"),
                resultSet.getString("TerminationDate")
        );
    }

    
    private void handleSQLException(SQLException e) {
        // Handle exception
        throw new EmployeeNotFoundException("Employee with ID " + e.getErrorCode() + " not found.");

    }
}

