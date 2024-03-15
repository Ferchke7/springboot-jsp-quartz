package com.example.hometaskfc.controller;

import com.example.hometaskfc.dto.EmployeeDTO;
import com.example.hometaskfc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeEmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/changeEmployee")
    public String changeEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(employeeDTO);
        if (employeeService.ifModelCorrect(employeeDTO)) {
            return "exceptionPages/failpage";
        }
        return "getusers";
    }

}
