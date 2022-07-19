package com.db2Hibernate.proj.controller;

import com.db2Hibernate.proj.dto.EmployeeDTO;
import com.db2Hibernate.proj.service.IDepartmentSvc;
import com.db2Hibernate.proj.service.IEmployeeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class api {
    @Autowired
    IEmployeeSvc iEmployeeSvc;

    @Autowired
    IDepartmentSvc iDepartmentSvc;

    @PostMapping("employee")
    public ResponseEntity<Map<String, Object>> saveEmployee(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName,@RequestParam(name = "age") String age,@RequestParam(name = "email") String email,@RequestParam(name = "salary") int salary, @RequestParam(name = "departmentId") int departmentId) {
        Map<String, Object> res = new HashMap<>();

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
    public ResponseEntity<Map<String, Object>> saveDepartment(@RequestParam(name = "departmentName") String departmentName, @RequestParam(name = "jobName") String jobName, @RequestParam(name = "budget")  String budget) {
        Map<String, Object> res = new HashMap<>();

        iDepartmentSvc.save(departmentName, jobName, budget);

        //if user is empty ?
        res.put("status", HttpStatus.OK.value());
        res.put("code", HttpStatus.OK);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
