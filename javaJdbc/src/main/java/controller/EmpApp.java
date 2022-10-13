package controller;

import java.sql.Connection;
import java.util.Scanner;

import dao.EmployeeServiceImpl;
import model.Employee;
import service.EmployeeService;
import utility.JdbcUtility;

public class EmpApp {

	public static void main(String[] args) {

		Scanner scanner=new Scanner(System.in);
		
		EmployeeService service=new EmployeeServiceImpl();
//		System.out.println("Enter employee id");
//		int id=Integer.parseInt(scanner.nextLine());
//		System.out.println("Enter employee name");
//		String name=scanner.nextLine();
//		System.out.println("Enter employee phone");
//		long ph=Long.parseLong(scanner.nextLine());
//		System.out.println("Enter employee salary");
//		double salary=Double.parseDouble(scanner.nextLine());
//		System.out.println("Enter employee address");
//		String addr=scanner.nextLine();
//		Employee empObj=new Employee();
//		empObj.setEmpId(id);
//		empObj.setEmpName(name);
//		empObj.setEmpPhone(ph);
//		empObj.setEmpSalary(salary);
//		empObj.setEmpAddress(addr);
//		int k=service.addEmp(empObj);
//		if(k>0)
//		{
//			System.out.println("Employee registered ");
//		}
//		else
//		{
//			System.out.println("not registered");
//		}

//search by id
		
	System.out.println("Search by employee Id:");
	int empId=scanner.nextInt();
	scanner.nextLine();
	System.out.println(service.getEmpById(empId).getEmpName());
	//update by id
	service.updateById(empId);
	System.out.println(service.updateById(empId).getEmpName()+" your data has been updated");
	
	//delete row by id
	if(service.deleteEmpById(empId)==1) {
		System.out.println(empId+" id hase been removed");
	}
//	
	//login by id and phone number
	System.out.println("Enter user id");
	int logid=scanner.nextInt();
	scanner.nextLine();
	System.out.println("Enter your phone number");
	long logph=scanner.nextLong();
	scanner.nextLine();
	service.getLogin(logid, logph);
	
	
	
	}

}

