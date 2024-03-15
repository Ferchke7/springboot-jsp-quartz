package com.example.hometaskfc.controller;

import com.example.hometaskfc.models.EmployeeModel;
import com.example.hometaskfc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private EmployeeService userRepository;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/adduser")
    public String getPage(){
        return "adduser";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute EmployeeModel userModel) {
        System.out.println(userModel.getEmployeeName());
        if(userModel.getEmployeeName() != null) {
            userRepository.save(userModel);
        }
        else {
            System.out.println("Has not been added " + userModel.getEmployeeName());
            System.out.println(userModel.getEmployeeId());
            System.out.println(userModel.getEmployeeEmail());
        }
        return "redirect:/adduser";
    }

    @PostMapping("/users/drop")
    public String dropTable() {
        userRepository.dropTable();
        return "redirect:/adduser";
    }


}
