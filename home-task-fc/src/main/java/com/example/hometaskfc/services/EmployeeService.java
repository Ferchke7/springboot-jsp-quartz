package com.example.hometaskfc.services;
import com.example.hometaskfc.dto.EmployeeDTO;
import com.example.hometaskfc.models.EmployeeModel;
import com.example.hometaskfc.models.EmployeeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeImpl userRepository;

    public List<EmployeeModel> getSortedAllAlphabetically() {
        List<EmployeeModel> temp = userRepository.findAll();
        temp.sort(Comparator.comparing(EmployeeModel::getEmployeeName));
        return temp;
    }
    public EmployeeModel findById(int id) {
        return userRepository.findById(id);
    }
    public List<EmployeeModel> searchByKeyword(String keyword) {
        return userRepository.findAllByKeyword(keyword);
    }
    public int deleteById(int id) {
        return userRepository.deleteById(id);
    }
    public List<EmployeeModel> getAllTemp(String column, String row) {
        return userRepository.findRequest(column,row);
    }

    public int updateEmployee(EmployeeDTO employeeDTO) {
        return userRepository.updateUserById(employeeDTO);
    }

    public List<EmployeeModel> findAll() {
        return userRepository.findAll();
    }

    public void save(EmployeeModel userModel) {
        userRepository.save(userModel);
    }

    public void dropTable() {
        userRepository.dropTable();
    }
    public StringBuilder getListForEmail() {
        List<EmployeeModel> getEmployee = getSortedAllAlphabetically();
        StringBuilder emailBodyBuilder = new StringBuilder();
        emailBodyBuilder.append("<h2>Employee List:</h2><br/>");

        for (EmployeeModel employee : getEmployee) {
            emailBodyBuilder.append("<b>Employee ID:</b> ").append(employee.getEmployeeId()).append("<br/>");
            emailBodyBuilder.append("<b>Name:</b> ").append(employee.getEmployeeName()).append("<br/>");
            emailBodyBuilder.append("<b>Position:</b> ").append(employee.getEmployeePosition()).append("<br/>");
            emailBodyBuilder.append("<b>Phone:</b> ").append(employee.getEmployeePhone()).append("<br/>");
            emailBodyBuilder.append("<b>Email:</b> ").append(employee.getEmployeeEmail()).append("<br/><br/>");
        }
        return emailBodyBuilder;
    }

    public boolean ifModelCorrect(EmployeeDTO model) {
        boolean res = true;
        if (!model.getEmployeeEmail().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$") &&
                model.getEmployeeName().isEmpty() &&
                (model.getEmployeeId() < 0) &&
                (model.getEmployeePhone() == null || model.getEmployeePhone().isEmpty())) {
            System.out.println("COMING HERE");
            res = false;
        }
        return res;
    }
}
