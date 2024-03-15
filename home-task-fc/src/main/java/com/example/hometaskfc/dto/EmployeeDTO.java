package com.example.hometaskfc.dto;

public class EmployeeDTO {
    private int previousId;
    private int employeeId;
    private String employeePosition;
    private String employeeName;
    private String employeePhone;
    private String employeeEmail;
    public EmployeeDTO() {

    }

    public EmployeeDTO(int previousId, int employeeId, String employeePosition, String employeeName, String employeePhone, String employeeEmail) {
        this.previousId = previousId;
        this.employeeId = employeeId;
        this.employeePosition = employeePosition;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
    }

    public int getPreviousId() {
        return previousId;
    }

    public void setPreviousId(int previousId) {
        this.previousId = previousId;
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

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "previousId=" + previousId +
                ", employeeId=" + employeeId +
                ", employeePosition='" + employeePosition + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                '}';
    }
}
