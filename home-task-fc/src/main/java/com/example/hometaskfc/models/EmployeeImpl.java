package com.example.hometaskfc.models;

import com.example.hometaskfc.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeImpl {
    int save(EmployeeModel userModel);

    int update(EmployeeModel userModel);
    int updateUserById(EmployeeDTO employeeDTO);
    EmployeeModel findById(int id);

    List<EmployeeModel> findAll();
    void dropTable();

    List<EmployeeModel> findAllByKeyword(String keyword);

    int deleteById(int id);

    List<EmployeeModel> findRequest(String column, String row);

    boolean checkIfExists(int id);
}
