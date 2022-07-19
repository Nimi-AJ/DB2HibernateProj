package com.db2Hibernate.proj.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "budget")
    private int budget;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentId")
    private List<Employee> employees = new ArrayList<>();

    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public int getBudget() {
        return budget;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
