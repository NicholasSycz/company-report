package edu.iup.cosc210.company.rpt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import edu.iup.cosc210.company.bo.Company;
import edu.iup.cosc210.company.bo.CompanyManager;
import edu.iup.cosc210.company.bo.Department;
import edu.iup.cosc210.company.bo.Employee;
import edu.iup.cosc210.company.io.DepartmentReader;
import edu.iup.cosc210.company.io.EmployeeInputStream;

/**
 * Test printing of the company report.
 * 
 * @author David T. Smith
 */
public class CompanyReport {
	private Company company;

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"MM/dd/yyyy");
	private static DecimalFormat decformatter = new DecimalFormat(
			"#,###,###.00");

	/**
	 * Constructor for the company report
	 * 
	 * @param company
	 *            - company holding the data for the report
	 */
	public CompanyReport(Company company) {
		this.company = company;
	}

	/**
	 * Main method to print the company report: Creates a company. Loads
	 * Departments from the file name given in the first command line argument.
	 * Loads Employees from the file name given in the last command line
	 * argument
	 * 
	 * @param args
	 *            - the command line arguments.
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("Usage: java edu.iup.cosc210.company.rpt.CompanyReport <department file> <employee file>");
			System.exit(-100);
		}
		CompanyManager companyManager = new CompanyManager();

		try {
			companyManager.loadDepts(args[0]);
			companyManager.loadEmployees(args[1]);
			CompanyReport companyReport = new CompanyReport(
					companyManager.getCompany());
			companyReport.printCompanyReport();

		} catch (FileNotFoundException e) {
			System.out.println("Unable to run: " + e.getMessage());
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Unable to run: " + e.getMessage());
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints a company report. Report include information on the department and
	 * a list of all employees.
	 */
	public void printCompanyReport() {
		// loop over all departments
		for (int i = 0; i < company.getNoDepts(); i++) {
			Department department = company.getDeparment(i);

			// print the department header
			System.out.println(department.getDeptName() + " Department");
			System.out.println();
			System.out.printf("%-20s%-20s\n", "Manager: ", department
					.getManager().getFirstName()
					+ " "
					+ department.getManager().getLastName());
			System.out.printf("%-20s%-20s\n", "Staff Size: ",
					department.getNoEmployees());
			System.out.printf("%-20s%d\n", "Vacation Days: ",
					department.getTotalVacationDays());
			System.out.println();

			// print the column labels for employees
			System.out.printf(
					"%-5s  %-26s  %-10s  %-3s  %-8s    %-6s   %-3s\n", "ID",
					"Employee Name", "Hire Date", "Typ", "Salary", "Rate",
					"Vac");

			// loop over all employees in the department
			for (int j = 0; j < department.getNoEmployees(); j++) {
				Employee emp = department.getEmployee(j);
				System.out.printf(
						"%5d  %-26s  %s   %c   %10s  %6s   %3d\n",
						emp.getEmployeeNumber(),
						emp.getFirstName() + " " + emp.getLastName(),
						dateFormatter.format(emp.getHireDate()),
						emp.getEmployeeType(),
						emp.getSalary() == 0 ? "" : decformatter.format(emp
								.getSalary()),
						emp.getHourlyRate() == 0 ? "" : String.format("%6.2f",
								emp.getHourlyRate()), emp.getVacationDays());
			}

			System.out.println();
			System.out.println();
		}
	}
}
