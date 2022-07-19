package com.db2Hibernate.proj.service;

import com.db2Hibernate.proj.dao.DepartmentDAO;
import com.db2Hibernate.proj.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IDepartmentSvc {
    @Autowired
    DepartmentDAO departmentDAO;

    public void save(String departmentName, String jobName, String budget){
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setJobName(jobName);
        department.setBudget(budget);
        departmentDAO.save(department);
    }
}
