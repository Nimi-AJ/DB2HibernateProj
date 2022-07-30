package com.db2Hibernate.proj.dao;

import com.db2Hibernate.proj.dto.EmployeeDTO;
import com.db2Hibernate.proj.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    //Joins Employee and Department table details in result
    @Query(value = "select e.first_name as firstName, e.last_name as lastName, e.age, e.email, e.salary, d.department_id as departmentId, d.department_name as departmentName, d.job_name as jobName FROM employees as e JOIN department as d ON e.department_id = d.department_id WHERE e.salary >= :VALUE", nativeQuery = true)
    List<EmployeeDTO> findEmployeesWithSalaryGreaterThan(@Param("VALUE") Integer salary);

    //column or not?
    @Query(value="select e.first_name as firstName, e.last_name as lastName, e.age, e.email, e.salary, d.department_id as departmentId, d.department_name as departmentName, d.job_name as jobName FROM employees as e JOIN department as d ON e.department_id = d.department_id ORDER BY d.department_id;", nativeQuery=true)
    List<EmployeeDTO> getAllEmployeesOrderByDepartment();
}
