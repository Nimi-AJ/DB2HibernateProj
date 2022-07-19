package com.db2Hibernate.proj.controller;

import com.db2Hibernate.proj.dto.EmployeeDTO;
import com.db2Hibernate.proj.service.IDepartmentSvc;
import com.db2Hibernate.proj.service.IEmployeeSvc;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class api {
    @Autowired
    IEmployeeSvc iEmployeeSvc;

    @Autowired
    IDepartmentSvc iDepartmentSvc;

    @PostMapping("employee")
    public ResponseEntity<Map<String, Object>> saveEmployee(@RequestBody ObjectNode objectNode) {
        Map<String, Object> res = new HashMap<>();
        String firstName = objectNode.get("firstName").asText();
        String lastName = objectNode.get("lastName").asText();
        String age = objectNode.get("age").asText();
        String email = objectNode.get("email").asText();
        int salary = objectNode.get("salary").asInt();
        int departmentId = objectNode.get("departmentId").asInt();
        iEmployeeSvc.saveEmployee(firstName, lastName, age, email, salary, departmentId);

        //if user is empty ?
        res.put("status", HttpStatus.OK.value());
        res.put("code", HttpStatus.OK);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("employee")
    public ResponseEntity<Map<String, Object>> findEmployeeWithSalaryGreaterThan(@RequestParam(name = "salary") int salary ) {
        Map<String, Object> res = new HashMap<>();

        List<EmployeeDTO> employees = iEmployeeSvc.findEmployeesWithSalaryGreaterThan(salary);

        res.put("status", HttpStatus.OK.value());
        res.put("code", HttpStatus.OK);
        res.put("data", employees);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("employee/file")
    public ResponseEntity<Map<String, Object>> saveToFile(@RequestParam(name = "salary") int salary ) throws IOException {
        Map<String, Object> res = new HashMap<>();

        boolean saved = iEmployeeSvc.saveToFileEmployeesWithSalaryGreaterThan(salary);

        if (saved) {
            res.put("status", HttpStatus.OK.value());
            res.put("code", HttpStatus.OK);
            res.put("message", "File saved");
        } else{
            res.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            res.put("message", "Failed To Save File");
        }


        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("department")
    public ResponseEntity<Map<String, Object>> saveDepartment(@RequestBody ObjectNode objectNode ) {
        Map<String, Object> res = new HashMap<>();
        String departmentName = objectNode.get("departmentName").asText();
        String jobName = objectNode.get("jobName").asText();
        int budget = objectNode.get("budget").asInt();


        iDepartmentSvc.save(departmentName, jobName, budget);

        res.put("status", HttpStatus.OK.value());
        res.put("code", HttpStatus.OK);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
