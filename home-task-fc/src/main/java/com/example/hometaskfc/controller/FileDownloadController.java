package com.example.hometaskfc.controller;

import com.example.hometaskfc.models.EmployeeModel;
import com.example.hometaskfc.services.EmployeeService;
import com.example.hometaskfc.services.FileGeneratorService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class FileDownloadController {
    @Autowired
    private FileGeneratorService fileGeneratorService;

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/export/csv")
    @ResponseBody
    public ResponseEntity<String> exportCsv() {
        List<EmployeeModel> employees = employeeService.findAll();
        String csvContent = fileGeneratorService.generateCsv(employees);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.csv")
                .contentType(MediaType.TEXT_PLAIN).body(csvContent);
    }

    @GetMapping("/export/xls")
    @ResponseBody
    public ResponseEntity<InputStreamResource> exportXls() {
        List<EmployeeModel> employees = employeeService.findAll();
        Workbook workbook = fileGeneratorService.generateXls(employees);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            workbook.write(baos);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.xls")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
        } catch (IOException e) {
            throw new RuntimeException("Error generating XLS file", e);
        }
    }
}
