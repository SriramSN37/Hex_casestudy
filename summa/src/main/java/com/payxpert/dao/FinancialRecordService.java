package com.payxpert.dao;

import com.payxpert.entity.FinancialRecord;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialRecordService {
    void addFinancialRecord(int recordID, int employeeId, String recordDate, String description, BigDecimal amount, String recordType);
    FinancialRecord getFinancialRecordById(int recordId);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDate(String recordDate);
}

