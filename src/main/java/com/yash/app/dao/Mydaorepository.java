package com.yash.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.app.model.Employee;

@Repository
public interface Mydaorepository extends JpaRepository<Employee, Integer> {

}
