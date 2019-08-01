package com.yash.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yash.app.Services.MyService;
import com.yash.app.model.Employee;

@RestController
public class Mycontroller {

	@Autowired
	MyService service;

	@RequestMapping(value= "/employee/all", method= RequestMethod.GET)
	public List<Employee> getEmployees() {
		System.out.println(this.getClass().getSimpleName() + " - Get all employees service is invoked.");
		return service.getEmployees();
	}

	@RequestMapping(value= "/employee/{id}", method= RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		return emp.get();
	}
	
	@RequestMapping(value= "/employee/add", method= RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee newemp) {
		System.out.println(this.getClass().getSimpleName() + " - Create new employee method is invoked.");
		return service.addNewEmployee(newemp);
	}
	
	@RequestMapping(value = "/employee/update/{id}", method=RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") int id) throws Exception {
		Optional<Employee> emp = service.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("could not found emplyoee with id : "+id);
		
		if(employee.getName()==null || employee.getName().isEmpty())
			employee.setName(emp.get().getName());
		if(employee.getDepartment()==null || employee.getDepartment().isEmpty())
			employee.setDepartment(emp.get().getDepartment());
		if(employee.getSalary() == 0)
			employee.setSalary(emp.get().getSalary());
		employee.setId(id);
		
		return service.updateEmployee(employee);
		
	}
	
	@RequestMapping(value = "/employee/delete/{id}", method =RequestMethod.DELETE )
	public void deleteEmployee(@PathVariable("id") int id) throws Exception {
		Optional<Employee> emp = service.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("could not found emplyoee with id : "+id);
		
		service.deleteEmployeeById(id);
			
	}
}
