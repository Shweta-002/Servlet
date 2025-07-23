package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submitform")
public class Employee extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empID = request.getParameter("empId");
		String FullName = request.getParameter("fullName");
		
		String DOB =  request.getParameter("dob");
		LocalDate emp_dob = LocalDate.parse(DOB);
		
		String gender =request.getParameter("gender");
		String email =request.getParameter("email");
		
		String MobNo=request.getParameter("phone");
		Long PhoneNo=Long.parseLong(MobNo);
		
		String address = request.getParameter("address");
		String department = request.getParameter("department");
		String designation = request.getParameter("designation");
		
		String DOJ = request.getParameter("doj");
		LocalDate emp_doj = LocalDate.parse(DOJ);
		
		String salary = request.getParameter("salary");
		Double emp_salary = Double.parseDouble(salary);
		
		String status = request.getParameter("status");
		String manager = request.getParameter("manager");
		String skills = request.getParameter("skills");
		String remarks = request.getParameter("remarks");
		
		System.out.println(empID);
		System.out.println(FullName);
		System.out.println(emp_dob);
		System.out.println(gender);
		System.out.println(email);
		System.out.println(PhoneNo);
		System.out.println(address);
		System.out.println(department);
		System.out.println(designation);
		System.out.println(emp_doj);
		System.out.println(emp_salary);
		System.out.println(status);
		System.out.println(manager);
		System.out.println(skills);
		System.out.println(remarks);
		
		PrintWriter out = response.getWriter();
		out.println(empID);
		out.println(FullName);
		out.println(emp_dob);
		out.println(gender);
		out.println(email);
		out.println(PhoneNo);
		out.println(address);
		out.println(department);
		out.println(designation);
		out.println(emp_doj);
		out.println(emp_salary);
		out.println(status);
		out.println(manager);
		out.println(skills);
		out.println(remarks);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_registration", "root","root");
			PreparedStatement ps = c.prepareStatement("insert into Employee_Info(empID, emp_name, emp_dob, emp_gender, emp_gmail, emp_mob, emp_address, emp_department, emp_designation, emp_doj, emp_salary, emp_status, emp_manager, emp_skills, emp_remark)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, empID);
			ps.setString(2, FullName);
			ps.setDate(3, java.sql.Date.valueOf(emp_dob));
			ps.setString(4, gender);
			ps.setString(5, email);
			ps.setLong(6, PhoneNo);
			ps.setString(7, address);
			ps.setString(8, department);
			ps.setString(9, designation);
			ps.setDate(10, java.sql.Date.valueOf(emp_doj));
			ps.setDouble(11, emp_salary);
			ps.setString(12, status);
			ps.setString(13, manager);
			ps.setString(14, skills);
			ps.setString(15, remarks);
			
			int result = ps.executeUpdate();
			if(result > 0) {
				out.println("Submitted Sucessfully...");
			}else {
				out.println("Not submitted...");
			}
			
			c.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
