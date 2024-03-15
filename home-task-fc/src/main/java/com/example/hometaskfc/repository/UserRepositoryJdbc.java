package com.example.hometaskfc.repository;

import com.example.hometaskfc.dto.EmployeeDTO;
import com.example.hometaskfc.models.EmployeeModel;
import com.example.hometaskfc.models.EmployeeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepositoryJdbc implements EmployeeImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(EmployeeModel userModel) {
        createTable();
        return jdbcTemplate.update("INSERT INTO employee " +
                        "(employee_id," +
                        "employee_position," +
                        "employee_name," +
                        "employee_phone," +
                        "employee_email) VALUES (?,?,?,?,?)",
                userModel.getEmployeeId(),
                userModel.getEmployeePosition(),
                userModel.getEmployeeName(),
                userModel.getEmployeePhone(),
                userModel.getEmployeeEmail());
    }


    private void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS employee(" +
                "employee_id SERIAL PRIMARY KEY," +
                "employee_position VARCHAR(255)," +
                "employee_name VARCHAR(255)," +
                "employee_phone VARCHAR(255)," +
                "employee_email VARCHAR(255))");
    }

    @Override
    public int update(EmployeeModel userModel) {
        return jdbcTemplate.update("UPDATE employee SET " +
                        "employee_position = ?, " +
                        "employee_name = ?, " +
                        "employee_phone = ?, " +
                        "employee_email = ? " +
                        "WHERE employee_id = ?",
                userModel.getEmployeePosition(),
                userModel.getEmployeeName(),
                userModel.getEmployeePhone(),
                userModel.getEmployeeEmail(),
                userModel.getEmployeeId());
    }


    @Override
    public int updateUserById(EmployeeDTO employeeDTO) {
        return jdbcTemplate.update("UPDATE employee SET " +
                        "employee_id = ?, " +
                        "employee_position = ?, " +
                        "employee_name = ?, " +
                        "employee_phone = ?, " +
                        "employee_email = ? " +
                        "WHERE employee_id = ?",
                employeeDTO.getEmployeeId(),
                employeeDTO.getEmployeePosition(),
                employeeDTO.getEmployeeName(),
                employeeDTO.getEmployeePhone(),
                employeeDTO.getEmployeeEmail(),
                employeeDTO.getPreviousId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE employee_id = ?", id);
    }

    @Override
    public List<EmployeeModel> findRequest(String column, String row) {
        String sql = "SELECT * FROM employee WHERE " + column + " = ?";
        return jdbcTemplate.query(sql, new Object[]{row},
                BeanPropertyRowMapper.newInstance(EmployeeModel.class));
    }

    @Override
    public boolean checkIfExists(int id) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employee WHERE employee_id = ?",
                Integer.class, id) > 0;
    }

    @Override
    public EmployeeModel findById(int id) {
        List<EmployeeModel> users = jdbcTemplate.query("SELECT * FROM employee WHERE employee_id = ?",
                new Object[]{id},
                BeanPropertyRowMapper.newInstance(EmployeeModel.class));
        return users.isEmpty() ? null : users.get(0);
    }
    @Override
    public List<EmployeeModel> findAllByKeyword(String keyword) {
        String query = "SELECT * FROM employee WHERE " +
                "employee_position LIKE ? OR " +
                "employee_name LIKE ? OR " +
                "employee_phone LIKE ? OR " +
                "employee_email LIKE ?";
        String keywordParam = "%" + keyword + "%";
        return jdbcTemplate.query(query,
                new Object[]{keywordParam, keywordParam, keywordParam, keywordParam},
                BeanPropertyRowMapper.newInstance(EmployeeModel.class));
    }

    @Override
    public List<EmployeeModel> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee",
                BeanPropertyRowMapper.newInstance(EmployeeModel.class));
    }

    public void dropTable() {
        jdbcTemplate.execute("DROP TABLE employee");
    }


}