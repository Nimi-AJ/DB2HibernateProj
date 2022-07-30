package com.db2Hibernate.proj.dto;

import com.db2Hibernate.proj.entities.Department;

public interface EmployeeDTO {
    String getFirstName();

    String getLastName();

    String getAge();

    String getEmail();

    Integer getSalary();

    Integer getDepartmentId();

    String getDepartmentName();

    String getJobName();



}
