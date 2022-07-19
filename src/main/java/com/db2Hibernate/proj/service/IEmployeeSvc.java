package com.db2Hibernate.proj.service;

import com.db2Hibernate.proj.dao.DepartmentDAO;
import com.db2Hibernate.proj.dao.EmployeeDAO;
import com.db2Hibernate.proj.dto.EmployeeDTO;
import com.db2Hibernate.proj.entities.Department;
import com.db2Hibernate.proj.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class IEmployeeSvc {
    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DepartmentDAO departmentDAO;


    //uses join & native query
    public List<EmployeeDTO> findEmployeesWithSalaryGreaterThan(int salary){
        List<EmployeeDTO> employees = employeeDAO.findEmployeesWithSalaryGreaterThan(salary);
        return employees;
    }

    public void saveEmployee(String firstName, String lastName, String age, String email, int salary, int departmentId){
        Employee employee = new Employee();
        employee.setAge(age);
        employee.setEmail(email);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);

        Optional<Department> departOpt = departmentDAO.findByDepartmentId(departmentId);

        Department department = null;
        if(departOpt.isPresent()){
            department = departOpt.get();
        }

        employee.setDepartmentId(department);
        employeeDAO.save(employee);
    }

    public boolean saveToFileEmployeesWithSalaryGreaterThan(int salary) throws IOException {
        List<EmployeeDTO> employees = this.findEmployeesWithSalaryGreaterThan(salary);
        boolean saved = false;

        File file = new File("data.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String line = "";

        try {
            for(EmployeeDTO employee : employees){
                line = employee.getFirstName()+ "|" + employee.getLastName()+ "|" + employee.getEmail() + "|" +employee.getJobName()+ "|" + employee.getSalary()+ "|" + employee.getDepartmentName()+ "|" + employee.getDepartmentId();
                bw.write(line);
                bw.newLine();
            }
            saved = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally
        {
            bw.close();
        }
        return saved;
    }
}
