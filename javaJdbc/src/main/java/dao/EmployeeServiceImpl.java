package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Employee;
import service.EmployeeService;
import utility.JdbcUtility;

public class EmployeeServiceImpl implements EmployeeService {
//store data to DB server
	public int addEmp(Employee employee)  {

		int status=0;
		try
		{
   //to connect with DB
	Connection connection=	JdbcUtility.connect();
		//Creating statement object
	//Statement s=	connection.createStatement(); 
	// PreparedStatement ps=prepareStatement(String sqlQuery)throws SQL exception
	//Create table Employee(emp_id int primary key, emp_name varchar(40), emp_phone bigint, emp_salary real, emp_address varchar(40));
	  PreparedStatement      ps=       connection.prepareStatement("insert into Employee values(?,?,?,?,?)");
	ps.setInt(1, employee.getEmpId());
	ps.setString(2, employee.getEmpName());
	ps.setLong(3,employee.getEmpPhone());
	ps.setDouble(4, employee.getEmpSalary());
	ps.setString(5, employee.getEmpAddress());
	
		status=ps.executeUpdate();
		
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return status;
	}

	public List<Employee> getAllEmp() {
		List<Employee> list=new ArrayList<Employee>();
		try
		{
			Connection connection=	JdbcUtility.connect();
			PreparedStatement ps= connection.prepareStatement("select * from Employee");
     
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Employee employee=new Employee();
				
				int id=   rs.getInt("emp_id");
				String name=rs.getString("emp_name");
				long ph=rs.getLong(3);
				double salary=rs.getDouble(4);
				String address=	rs.getString(5);
				employee.setEmpId(id);
				employee.setEmpName(name);
				employee.setEmpPhone(ph);
				employee.setEmpSalary(salary);
				employee.setEmpAddress(address);
				list.add(employee);
			}
		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
	}

	public Employee getEmpById(int employeeId) {
		
		try
		{
			Connection connection=	JdbcUtility.connect();
			PreparedStatement ps= connection.prepareStatement("select * from Employee where emp_id="+employeeId);
     
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Employee employee=new Employee();
				
				int id=   rs.getInt("emp_id");
				String name=rs.getString("emp_name");
				long ph=rs.getLong(3);
				double salary=rs.getDouble(4);
				String address=	rs.getString(5);
				employee.setEmpId(id);
				employee.setEmpName(name);
				employee.setEmpPhone(ph);
				employee.setEmpSalary(salary);
				employee.setEmpAddress(address);

			return employee;
			}
			
		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return null;
	}

	public Employee updateById(int employeeId) {
		int status=0;
		try
		{
			Scanner sc=new Scanner(System.in);
			Connection connection=	JdbcUtility.connect();
			PreparedStatement ps= connection.prepareStatement("update Employee set emp_name=?,emp_phone=?,emp_salary=?,emp_address=? where emp_id="+employeeId);
     
			System.out.println("Enter name");
			String name=sc.nextLine();
			ps.setString(1, name);
			
			System.out.println("Enter Phone no");
			long ph=sc.nextLong();
			ps.setLong(2,ph);
			sc.nextLine();
			
			System.out.println("Enter Salary");
			double salary=sc.nextDouble();
			ps.setDouble(3, salary);
			sc.nextLine();
			
			System.out.println("Enter Address");
			String address=sc.nextLine();
			ps.setString(4, address);
			
			status=ps.executeUpdate();
			
			Employee employee=new Employee();
			employee.setEmpName(name);
			employee.setEmpPhone(ph);
			employee.setEmpSalary(salary);
			employee.setEmpAddress(address);
			
			return employee;
		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return null;
	}

	public int deleteEmpById(int empId) {
		int status=0;
		try
		{
			
			Connection connection=	JdbcUtility.connect();
			PreparedStatement ps= connection.prepareStatement("delete from Employee where emp_id="+empId);
     
			status=ps.executeUpdate();
			return status;

		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return status;
		
	}

	public boolean getLogin(int id, long phone) {
		int count=0;
		try
		{
			
			Connection connection=	JdbcUtility.connect();
			PreparedStatement ps= connection.prepareStatement("select * from Employee where emp_id=? and emp_phone=?");
			ps.setInt(1, id);
			ps.setLong(2, phone);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				count++;
			}
			
			if(count==1){
				System.out.println("logged in successfully");
				return true;
			}
			else {
				System.out.println("Login failed. User Doesn't Exists");
				return false;
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return false;
	}

}
