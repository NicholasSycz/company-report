package edu.iup.cosc210.company.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import edu.iup.cosc210.company.io.DepartmentReader;
import edu.iup.cosc210.company.io.EmployeeInputStream;

/**
 * Test printing of the company report.
 * 
 * @author David T. Smith
 */
public class CompanyManager {
	private Company company = new Company();
	
	
	/**
	 * Load departments from a text file.
	 * @param fileName - the filename of the file that contains the departments.
	 * @throws IOException - in the event the file cannot be opened or read.
	 */
	public void loadDepts(String fileName) throws NumberFormatException,
			IOException {
		DepartmentReader in = new DepartmentReader(fileName);
		Department department;

		while ((department = in.readDepartment()) != null) {
			company.addDepartment(department);
		}
	}
	
	/**
	 * Load Employees from a binary file.  The employees are added to the list of employees
	 * for their respective Department as indicated by deptCode.
	 * @param fileName - the name of the file that contains the employees.
	 * @throws Exception - catches an Exception.
	 */
	public void loadEmployees(String fileName) throws Exception {
		EmployeeInputStream in = new EmployeeInputStream(fileName);
		
		Employee employee;

		while ((employee = in.readEmployee()) != null) {
			company.addEmployee(employee);
		}
	}	
	
	/**
	 * Get the company from the manager
	 * 
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
}
