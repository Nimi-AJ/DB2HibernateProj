package com.db2Hibernate.proj.service;

import com.db2Hibernate.proj.dao.DepartmentDAO;
import com.db2Hibernate.proj.dao.EmployeeDAO;
import com.db2Hibernate.proj.dto.EmployeeDTO;
import com.db2Hibernate.proj.entities.Department;
import com.db2Hibernate.proj.entities.Employee;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
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


    //consumes external endpoint, queries database and writes to file
    public boolean fromEndpointToFile(){
        //make endpoint call
//        final String uri = "http://localhost:8086/external/endpoint";
//
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//
//        System.out.println(result);
//
//
////                HttpResponse<JsonNode> jsonResponse = Unirest.post(EndPoint)
////                .header("Content-Type", "application/json")
////                .body(body)
////                .asJson();
////
////        System.out.println(jsonResponse.getBody());
////
////        if (jsonResponse.getStatus() != 200){
////            throw new RuntimeException("An error occurred, request not completed, please retry");
////        }
////
////        JsonNode responseBody = jsonResponse.getBody();
////        assert responseBody != null;
////
////        JSONObject res = responseBody.getObject();
////        System.out.println(res);
////
////        if (!res.getBoolean("IsSuccessful")) {
////            throw new IllegalStateException("Error creating wallet");
////        }
////
////        JSONObject message = res.getJSONObject("Message");
////
////        Map<String, Object> resp = new HashMap<>();
////        resp.put("key", message.getString("AccountNumber"));
////        resp.put("name", message.getString("FullName"));
//
//        List<Integer> listOfIds = this.returnListOfIds();
//
//
//        for(Integer id : listOfIds){
//            //make db call
//            EmployeeDTO employee = employeeDAO.findEmployeeById(id);
//            //build string
//            StringBuilder line = new StringBuilder();
//            line.append(employee.getFirstName()).append("|");
//            line.append(employee.getLastName()).append("|");
//            line.append(employee.getEmail()).append("|");
//            line.append(employee.getSalary()).append("|");
//            line.append(employee.getDepartmentId()).append("\n");
//            //write to file
//            writeToFile.saveEmployeesToFile(employee, String.valueOf(line));
//
//        }
        return true;
    }

    //uses join & native query
    public List<EmployeeDTO> findEmployeesWithSalaryGreaterThan(int salary){
        List<EmployeeDTO> employees = employeeDAO.findEmployeesWithSalaryGreaterThan(salary);
        return employees;
    }

    public List<Integer> returnListOfIds(){
        List<Integer> listOfIds = new ArrayList<>(List.of(3, 1, 2));
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



//        SSHClient client = new SSHClient();


        return saved;
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
//                    map.put(prevEmployee.getDepartmentName(), departmentEmployees.toString());
                    System.out.println(map);
                    System.out.println("list" + departmentEmployees.toString());
                    writeToFile.saveEmployeesToFile(prevEmployee, String.valueOf(line));

                    //clear line, list and map
                    line = new StringBuilder();
//                    map.clear();
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
//                    map.put(employee.getDepartmentName(), departmentEmployees.toString());
                    System.out.println(map);
                    //write to file
                    writeToFile.saveEmployeesToFile(employee, String.valueOf(line));
                }
            }





//        SSHClient client = new SSHClient();
        return true;

    }
}
