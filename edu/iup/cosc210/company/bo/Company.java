package edu.iup.cosc210.company.bo;

import java.util.ArrayList;
import java.util.List;


/**
 * A Company.  Maintains a list of departments and methods access
 * the company's departments.
 * 
 * @author David T. Smith
 */
public class Company {
	private List<Department>departments = new ArrayList<Department>();
	private List<Employee> employees = new ArrayList<Employee>();

	/**
	 * Add a Department to the list of departments for this company.
	 * 
	 * @param department - the company to be added
	 */
	public void addDepartment(Department department) {
		departments.add(department);
	}

	/**
	 * Find a department with a given department code
	 * 
	 * @param deptCode - the department code used to find a department
	 * @return the department with the given code.  Returns null if 
	 * a department by the given department code is not found.
	 */
	public Department findDepartment(String deptCode) {
		for (Department department : departments) {
			if (deptCode.equals(department.getDeptCode())) {
				return department;
			}
		}
		
		return null;
	}

	/**
	 * Get the number of departments in this company.
	 * @return the number of departments in this company.
	 */
	public int getNoDepts() {
		return departments.size();
	}

	/**
	 * Get the ith department in this company
	 * @param i - index identifying the department to be returned
	 * @return the ith department in this company
	 */
	public Department getDeparment(int i) {
		return departments.get(i);
	}
	
	/**
	 * Add an employee to the company's list of employees
	 * 
	 * @param employee -
	 *            employee to add to the company
	 */

	public void addEmployee(Employee employee) {
		employees.add(employee);
		Department dept = findDepartment(employee.getDeptCode());
		dept.addEmployee(employee);

	}
	/**
	 * Get the number of employees in the company
	 * 
	 * @return - the number of employees in the company
	 */
	public int getNoEmployees() {
		return employees.size();
	}

	/**
	 * Get an employee given an index position
	 * 
	 * @param emp -
	 *            the employee to return
	 * @return - the employee at the given position
	 * @throws IndexOutOfBoundsException
	 *             if the index is less that 0 or greater than or equal to the
	 *             number of employees.
	 */
	public Employee getEmployee(int index) {
		return employees.get(index);
	}


}
