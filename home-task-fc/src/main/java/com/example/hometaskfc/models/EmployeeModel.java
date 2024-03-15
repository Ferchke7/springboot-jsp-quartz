package com.example.hometaskfc.models;

public class EmployeeModel {
    private int employeeId;
    private String employeePosition;
    private String employeeName;
    private String employeePhone;
    private String employeeEmail;
    public EmployeeModel() {

    }

    public EmployeeModel(int employeeId, String employeePosition, String employeeName, String employeePhone, String employeeEmail) {
        this.employeeId = employeeId;
        this.employeePosition = employeePosition;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

}
