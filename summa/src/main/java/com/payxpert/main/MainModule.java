package com.payxpert.main;

import com.payxpert.dao.*;
import com.payxpert.entity.*;
import com.payxpert.exception.*;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeService employeeService = new EmployeeServiceImpl();
    private static final PayrollService payrollService = new PayrollServiceImpl();
    private static final TaxService taxService = new TaxServiceImpl();
    private static final FinancialRecordService financialRecordService = new FinancialRecordServiceImpl();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("------ Menu ------");
            System.out.println("1. Employee Management");
            System.out.println("2. Payroll Processing");
            System.out.println("3. Tax Calculation");
            System.out.println("4. Financial Record Management");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();
            switch (choice) {
                case 1:
                    employeeManagementMenu();
                    break;
                case 2:
                    payrollProcessingMenu();
                    break;
                case 3:
                    taxCalculationMenu();
                    break;
                case 4:
                    financialRecordManagementMenu();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }

    private static void employeeManagementMenu() {
        int choice;
        do {
            System.out.println("\n------ Employee Management Menu ------");
            System.out.println("1. Get Employee by ID");
            System.out.println("2. Get All Employees");
            System.out.println("3. Add Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Remove Employee");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = getIntInput();
            switch (choice) {
                case 1:
                    getEmployeeById();
                    break;
                case 2:
                    getAllEmployees();
                    break;
                case 3:
                    addEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    removeEmployee();
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        } while (choice != 6);
    }



    private static void payrollProcessingMenu() {
    int choice;
    do {
        System.out.println("\n------ Payroll Processing Menu ------");
        System.out.println("1. Generate Payroll");
        System.out.println("2. Get Payroll by ID");
        System.out.println("3. Get Payrolls for Employee");
        System.out.println("4. Get Payrolls for Period");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
        choice = getIntInput();
        switch (choice) {
            case 1:
                generatePayroll();
                break;
            case 2:
                getPayrollById();
                break;
            case 3:
                getPayrollsForEmployee();
                break;
            case 4:
                getPayrollsForPeriod();
                break;
            case 5:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice! Please enter a number between 1 and 5.");
        }
    } while (choice != 5);
}

    private static void taxCalculationMenu() {
    int choice;
    do {
        System.out.println("\n------ Tax Calculation Menu ------");
        System.out.println("1. Calculate Tax");
        System.out.println("2. Get Tax by ID");
        System.out.println("3. Get Taxes for Employee");
        System.out.println("4. Get Taxes for Year");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
        choice = getIntInput();
        switch (choice) {
            case 1:
                calculateTax();
                break;
            case 2:
                getTaxById();
                break;
            case 3:
                getTaxesForEmployee();
                break;
            case 4:
                getTaxesForYear();
                break;
            case 5:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice! Please enter a number between 1 and 5.");
        }
    } while (choice != 5);
}


    private static void financialRecordManagementMenu() {
    int choice;
    do {
        System.out.println("\n------ Financial Record Management Menu ------");
        System.out.println("1. Add Financial Record");
        System.out.println("2. Get Financial Record by ID");
        System.out.println("3. Get Financial Records for Employee");
        System.out.println("4. Get Financial Records for Date");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
        choice = getIntInput();
        switch (choice) {
            case 1:
                addFinancialRecord();
                break;
            case 2:
                getFinancialRecordById();
                break;
            case 3:
                getFinancialRecordsForEmployee();
                break;
            case 4:
                getFinancialRecordsForDate();
                break;
            case 5:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice! Please enter a number between 1 and 5.");
        }
    } while (choice != 5);
}

    private static void getEmployeeById() {
        System.out.print("Enter Employee ID: ");
        int employeeId = getIntInput();
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            System.out.println("Employee found: " + employee);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Employee not found!");
        }
    }

    private static void getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (!employees.isEmpty()) {
            System.out.println("All Employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No employees found!");
        }
    }

   private static void addEmployee() {
    System.out.println("Enter Employee Details:");
    System.out.print("Employee ID: ");
    int employeeID = getIntInput();
    System.out.print("First Name: ");
    String firstName = scanner.next();
    System.out.print("Last Name: ");
    String lastName = scanner.next();
    System.out.print("Date of Birth (yyyy-mm-dd): ");
    String dateOfBirth = scanner.next();
    System.out.print("Gender: ");
    String gender = scanner.next();
    System.out.print("Email: ");
    String email = scanner.next();
    System.out.print("Phone Number: ");
    String phoneNumber = scanner.next();
    System.out.print("Address: ");
    String address = scanner.next();
    System.out.print("Position: ");
    String position = scanner.next();
    System.out.print("Joining Date (yyyy-mm-dd): ");
    String joiningDate = scanner.next();
    System.out.print("Termination Date (optional, yyyy-mm-dd): ");
    String terminationDate = scanner.next();

    try {
        
        employeeService.addEmployee(new Employee(employeeID, firstName, lastName, dateOfBirth, gender, email,
                phoneNumber, address, position, joiningDate, terminationDate));
        System.out.println("Employee added successfully!");
    } catch (Exception e) {
        System.out.println("Error occurred while adding employee: " + e.getMessage());
    }
}
 

    private static void updateEmployee() {
    System.out.print("Enter Employee ID to update: ");
    int employeeId = getIntInput();
    
    try {
       
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if (existingEmployee != null) {
            System.out.println("Enter Updated Employee Details:");
            System.out.print("First Name: ");
            String firstName = scanner.next();
            System.out.print("Last Name: ");
            String lastName = scanner.next();
            System.out.print("Date of Birth (yyyy-mm-dd): ");
            String dateOfBirth = scanner.next();
            System.out.print("Gender: ");
            String gender = scanner.next();
            System.out.print("Email: ");
            String email = scanner.next();
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.next();
            System.out.print("Address: ");
            String address = scanner.next();
            System.out.print("Position: ");
            String position = scanner.next();
            System.out.print("Joining Date (yyyy-mm-dd): ");
            String joiningDate = scanner.next();
            System.out.print("Termination Date (optional, yyyy-mm-dd): ");
            String terminationDate = scanner.next();

          
            Employee updatedEmployee = new Employee(employeeId, firstName, lastName, dateOfBirth, gender, email,
                    phoneNumber, address, position, joiningDate, terminationDate);
            employeeService.updateEmployee(updatedEmployee);
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee with ID " + employeeId + " not found!");
        }
    } catch (EmployeeNotFoundException e) {
        System.out.println("Employee not found!");
    } catch (Exception e) {
        System.out.println("Error occurred while updating employee: " + e.getMessage());
    }
}


    private static void removeEmployee() {
        System.out.print("Enter Employee ID: ");
        int employeeId = getIntInput();
        try {
            employeeService.removeEmployee(employeeId);
            System.out.println("Employee removed successfully!");
        } catch (EmployeeNotFoundException e) {
            System.out.println("Employee not found!");
        }
    }

   
    private static void generatePayroll() {
        System.out.print("Enter Employee ID: ");
        int employeeId = getIntInput();
        System.out.print("Enter Pay Period Start Date (yyyy-mm-dd): ");
        String startDate = scanner.next();
        System.out.print("Enter Pay Period End Date (yyyy-mm-dd): ");
        String endDate = scanner.next();
        System.out.print("Enter Basic Salary: ");
        BigDecimal basicSalary = getBigDecimalInput();
        System.out.print("Enter Overtime Pay: ");
        BigDecimal overtimePay = getBigDecimalInput();
        System.out.print("Enter Deductions: ");
        BigDecimal deductions = getBigDecimalInput();

        try {
            payrollService.generatePayroll(employeeId, startDate, endDate, basicSalary, overtimePay, deductions);
            System.out.println("Payroll generated successfully!");
        } catch (PayrollGenerationException e) {
            System.out.println("Error generating payroll: " + e.getMessage());
        }
    }

    private static void getPayrollById() {
        System.out.print("Enter Payroll ID: ");
        int payrollId = getIntInput();
        try {
            Payroll payroll = payrollService.getPayrollById(payrollId);
            System.out.println("Payroll details:\n" + payroll);
        } catch (Exception e) {
            System.out.println("Error getting payroll details: " + e.getMessage());
        }
    }

    private static void getPayrollsForEmployee() {
        System.out.print("Enter Employee ID: ");
        int employeeId = getIntInput();
        try {
            List<Payroll> payrolls = payrollService.getPayrollsForEmployee(employeeId);
            if (!payrolls.isEmpty()) {
                System.out.println("Payrolls for Employee ID " + employeeId + ":");
                for (Payroll payroll : payrolls) {
                    System.out.println(payroll);
                }
            } else {
                System.out.println("No payrolls found for Employee ID " + employeeId);
            }
        } catch (Exception e) {
            System.out.println("Error getting payrolls for employee: " + e.getMessage());
        }
    }

    private static void getPayrollsForPeriod() {
        System.out.print("Enter Pay Period Start Date (yyyy-mm-dd): ");
        String startDate = scanner.next();
        System.out.print("Enter Pay Period End Date (yyyy-mm-dd): ");
        String endDate = scanner.next();
        try {
            List<Payroll> payrolls = payrollService.getPayrollsForPeriod(startDate, endDate);
            if (!payrolls.isEmpty()) {
                System.out.println("Payrolls for Period " + startDate + " to " + endDate + ":");
                for (Payroll payroll : payrolls) {
                    System.out.println(payroll);
                }
            } else {
                System.out.println("No payrolls found for the specified period");
            }
        } catch (Exception e) {
            System.out.println("Error getting payrolls for period: " + e.getMessage());
        }
    }

    private static void calculateTax() {
   
    System.out.print("Enter tax ID: ");
    int taxId = getIntInput();
    System.out.print("Enter tax percent: ");
    int taxPercent = getIntInput();
    
    try {
        taxService.calculateTax(taxId, taxPercent);
        System.out.println("Tax calculation completed.");
    } catch (TaxCalculationException e) {
        System.out.println("Error calculating tax: " + e.getMessage());
    }
}

private static void getTaxById() {
    System.out.print("Enter Tax ID: ");
    int taxId = getIntInput();
    try {
        Tax tax = taxService.getTaxById(taxId);
        System.out.println("Tax details:\n" + tax);
    } catch (Exception e) {
        System.out.println("Error getting tax details: " + e.getMessage());
    }
}

private static void getTaxesForEmployee() {
    System.out.print("Enter Employee ID: ");
    int employeeId = getIntInput();
    try {
        List<Tax> taxes = taxService.getTaxesForEmployee(employeeId);
        if (!taxes.isEmpty()) {
            System.out.println("Taxes for Employee ID " + employeeId + ":");
            for (Tax tax : taxes) {
                System.out.println(tax);
            }
        } else {
            System.out.println("No taxes found for Employee ID " + employeeId);
        }
    } catch (Exception e) {
        System.out.println("Error getting taxes for employee: " + e.getMessage());
    }
}

private static void getTaxesForYear() {
    System.out.print("Enter Tax Year: ");
    int taxYear = getIntInput();
    try {
        List<Tax> taxes = taxService.getTaxesForYear(taxYear);
        if (!taxes.isEmpty()) {
            System.out.println("Taxes for Year " + taxYear + ":");
            for (Tax tax : taxes) {
                System.out.println(tax);
            }
        } else {
            System.out.println("No taxes found for Year " + taxYear);
        }
    } catch (Exception e) {
        System.out.println("Error getting taxes for year: " + e.getMessage());
    }
}

private static void addFinancialRecord() {
   
    System.out.print("Enter Record ID: ");
    int recordId = getIntInput();
    System.out.print("Enter Employee ID: ");
    int employeeId = getIntInput();
    System.out.print("Enter Record Date (yyyy-mm-dd): ");
    String recordDate = scanner.next();
    System.out.print("Enter Description: ");
    String description = scanner.next();
    System.out.print("Enter Amount: ");
    BigDecimal amount = getBigDecimalInput();
    System.out.print("Enter Record Type: ");
    String recordType = scanner.next();

    try {
        financialRecordService.addFinancialRecord(recordId, employeeId, recordDate, description, amount, recordType);
        System.out.println("Financial record added successfully!");
    } catch (FinancialRecordException e) {
        System.out.println("Error adding financial record: " + e.getMessage());
    }
}

private static void getFinancialRecordById() {
    System.out.print("Enter Record ID: ");
    int recordId = getIntInput();
    try {
        FinancialRecord financialRecord = financialRecordService.getFinancialRecordById(recordId);
        System.out.println("Financial record details:\n" + financialRecord);
    } catch (Exception e) {
        System.out.println("Error getting financial record details: " + e.getMessage());
    }
}

private static void getFinancialRecordsForEmployee() {
    System.out.print("Enter Employee ID: ");
    int employeeId = getIntInput();
    try {
        List<FinancialRecord> financialRecords = financialRecordService.getFinancialRecordsForEmployee(employeeId);
        if (!financialRecords.isEmpty()) {
            System.out.println("Financial records for Employee ID " + employeeId + ":");
            for (FinancialRecord financialRecord : financialRecords) {
                System.out.println(financialRecord);
            }
        } else {
            System.out.println("No financial records found for Employee ID " + employeeId);
        }
    } catch (Exception e) {
        System.out.println("Error getting financial records for employee: " + e.getMessage());
    }
}

private static void getFinancialRecordsForDate() {
    System.out.print("Enter Record Date (yyyy-mm-dd): ");
    String recordDate = scanner.next();
    try {
        List<FinancialRecord> financialRecords = financialRecordService.getFinancialRecordsForDate(recordDate);
        if (!financialRecords.isEmpty()) {
            System.out.println("Financial records for Date " + recordDate + ":");
            for (FinancialRecord financialRecord : financialRecords) {
                System.out.println(financialRecord);
            }
        } else {
            System.out.println("No financial records found for Date " + recordDate);
        }
    } catch (Exception e) {
        System.out.println("Error getting financial records for date: " + e.getMessage());
    }
}


    private static int getIntInput() {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); 
            }
        }
        return input;
    }

    private static BigDecimal getBigDecimalInput() {
        while (true) {
            try {
                return new BigDecimal(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }
}
