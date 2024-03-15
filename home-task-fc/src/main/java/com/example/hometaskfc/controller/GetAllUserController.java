package com.example.hometaskfc.controller;

import com.example.hometaskfc.models.EmployeeModel;
import com.example.hometaskfc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class GetAllUserController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getusers")
    public String getUser(Model model) {
        List<EmployeeModel> employeeList = employeeService.getSortedAllAlphabetically();
        if (employeeList.isEmpty()) {
            return "exceptionPages/emptypage";
        }
        model.addAttribute("userList", employeeList);
        return "getusers";
    }

    @GetMapping("/getusersearch")
    public String getSpecialUser(Model model, @Param("keyword") String keyword) {
        List<EmployeeModel> employeeModels = employeeService.searchByKeyword(keyword);
        model.addAttribute("userList", employeeModels);
        return "getusers";
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@RequestParam("id") int id) {
        employeeService.deleteById(id);
        return "redirect:/getusers";
    }

    @GetMapping("/searchById")
    public String searchById(Model model, @RequestParam int column) {
        EmployeeModel employeeModel = employeeService.findById(column);
        model.addAttribute("userList", employeeModel);
        return "getusers";
    }

}
//    @GetMapping("/searchByParam")
//    public String searchByColRow(Model model, @RequestParam String column, @RequestParam String row) {
//        List<EmployeeModel> employeeModels = employeeService.getAllTemp(column, row);
//        model.addAttribute("userList", employeeModels);
//        return "getusers";
//    }





