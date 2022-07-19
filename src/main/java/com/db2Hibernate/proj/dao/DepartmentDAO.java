package com.db2Hibernate.proj.dao;

import com.db2Hibernate.proj.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentDAO extends JpaRepository<Department, Long> {
    Optional<Department> findByDepartmentId(int id);
}
