package com.example.hometaskfc.services;

import com.example.hometaskfc.models.EmployeeModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileGeneratorService {
    public String generateCsv(List<EmployeeModel> employeeModels) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Id,Position,Name,Phone,Email\n");
        for (EmployeeModel employeeModel: employeeModels) {
            csvContent.append(employeeModel.getEmployeeId()).append(",")
                    .append(employeeModel.getEmployeePosition()).append(",")
                    .append(employeeModel.getEmployeeName()).append(",")
                    .append(employeeModel.getEmployeePhone()).append(",")
                    .append(employeeModel.getEmployeeEmail()).append("\n");
        }
        return csvContent.toString();
    }

    public Workbook generateXls(List<EmployeeModel> employeeModels) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("직원 정보");

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Position");
        headerRow.createCell(2).setCellValue("Name");
        headerRow.createCell(3).setCellValue("Phone");
        headerRow.createCell(4).setCellValue("Email");

        for (EmployeeModel employeeModel: employeeModels) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employeeModel.getEmployeeId());
            row.createCell(1).setCellValue(employeeModel.getEmployeePosition());
            row.createCell(2).setCellValue(employeeModel.getEmployeeName());
            row.createCell(3).setCellValue(employeeModel.getEmployeePhone());
            row.createCell(4).setCellValue(employeeModel.getEmployeeEmail());
        }

        return workbook;
    }
}

