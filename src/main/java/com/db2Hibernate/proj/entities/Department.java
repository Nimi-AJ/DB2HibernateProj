package com.db2Hibernate.proj.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String departmentName;
    private String jobName;
    private String budget;
    private Integer departmentId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public void setId(Integer id){
        this.id = id;
    }

    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getId(){
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getJobName() {
        return jobName;
    }

    public String getBudget() {
        return budget;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
