package service;

import java.util.List;

import model.Employee;

public interface EmployeeService {
	
	//I want to store my employee information permanently to DB server
	
	public int addEmp(Employee employee);
	//I want to fetch employee info from DB server
	public List<Employee> getAllEmp();
	//I want fetch using employee id
	public Employee getEmpById(int employeeId);
	//I want to update employee info using employee id
	public Employee updateById(int employeeId);
	// I want to delete employee using employee id
	public int deleteEmpById(int empId);
	public boolean getLogin(int id, long phone);
	
	

}
