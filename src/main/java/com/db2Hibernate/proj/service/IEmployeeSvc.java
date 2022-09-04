package com.db2Hibernate.proj.service;

import com.db2Hibernate.proj.JSONModels.Details;
import com.db2Hibernate.proj.JSONModels.Response;
import com.db2Hibernate.proj.base.RestServiceBase;
import com.db2Hibernate.proj.dao.DepartmentDAO;
import com.db2Hibernate.proj.dao.EmployeeDAO;
import com.db2Hibernate.proj.dto.BasicEmployeeDTO;
import com.db2Hibernate.proj.dto.EmployeeDTO;
import com.db2Hibernate.proj.entities.Department;
import com.db2Hibernate.proj.entities.Employee;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import com.mashape.unirest.http.Unirest;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class IEmployeeSvc {
    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    WriteToFile writeToFile;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @Qualifier("sample1_endpoint")
    RestServiceBase myService;


    //consumes external endpoint, queries database and writes to file
    public boolean fromEndpointToFile() throws IOException {
        //make endpoint call

        List<Details> listOfIds = myService.getIds();


        for(Details id : listOfIds){
            //make db call
            BasicEmployeeDTO employee = employeeDAO.findEmployeeById(id.getId());
//            build string
            StringBuilder line = new StringBuilder();
            line.append(employee.getFirstName()).append("|");
            line.append(employee.getLastName()).append("|");
            line.append(employee.getEmail()).append("|");
            line.append(employee.getSalary()).append("|");
            line.append(employee.getId()).append("\n");
//            write to file
            writeToFile.saveEmployeesToFile(employee, String.valueOf(line));

        }
        return true;
    }

    //uses join & native query
    public List<EmployeeDTO> findEmployeesWithSalaryGreaterThan(int salary){
        List<EmployeeDTO> employees = employeeDAO.findEmployeesWithSalaryGreaterThan(salary);
        return employees;
    }

    public List<Details> returnListOfIds(){
        List<Details> listOfIds = new ArrayList<>();

        Details details = new Details();
        details.setId(3);
        listOfIds.add(details);

        details = new Details();
        details.setId(1);
        listOfIds.add(details);

        details = new Details();
        details.setId(2);
        listOfIds.add(details);



        return listOfIds;
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



    public Boolean saveToFileEmployeesOrderedByDepartment() throws IOException {
        List<EmployeeDTO> employees = employeeDAO.getAllEmployeesOrderByDepartment();

        StringBuilder line = new StringBuilder();
        int prevDepartmentId = 0;
        Map<String, String> map = new HashMap<>();


        List<String> departmentEmployees = new ArrayList<>();

            EmployeeDTO prevEmployee = null;
            Iterator<EmployeeDTO> employeeIt = employees.iterator();
            while(employeeIt.hasNext()){

                EmployeeDTO employee = employeeIt.next();

                if((prevDepartmentId != employee.getDepartmentId()) && (prevDepartmentId != 0)){
                    writeToFile.saveEmployeesToFile(prevEmployee, String.valueOf(line));

                    //clear line, list
                    line = new StringBuilder();
                    departmentEmployees.clear();
                }

                line.append(employee.getFirstName()).append("|");
                line.append(employee.getLastName()).append("|");
                line.append(employee.getEmail()).append("|");
                line.append(employee.getJobName()).append("|");
                line.append(employee.getSalary()).append("|");
                line.append(employee.getDepartmentName()).append("|");
                line.append(employee.getDepartmentId()).append("\n");

                prevDepartmentId = employee.getDepartmentId();
                prevEmployee = employee;
                departmentEmployees.add(employee.toString());



                if(!employeeIt.hasNext()){

                    //write to file
                    writeToFile.saveEmployeesToFile(employee, String.valueOf(line));
                }
            }


        return true;

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
                line = employee.getFirstName()+ "|" + employee.getLastName()+ "|" + employee.getEmail() + "|" +employee.getJobName()+ "|" + employee.getSalary()+ "|" +  "|" + employee.getDepartmentId();
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
