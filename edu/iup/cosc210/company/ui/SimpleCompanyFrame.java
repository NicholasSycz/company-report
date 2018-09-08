package edu.iup.cosc210.company.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.iup.cosc210.company.bo.Company;
import edu.iup.cosc210.company.bo.CompanyManager;
import edu.iup.cosc210.company.bo.Employee;

/**
 * A Simple Company Frame used to test Employee Dialog. The Open button will
 * display the Employee Dialog for each employe in the company in sequence.
 * 
 * @author dtsmith
 *
 */
public class SimpleCompanyFrame extends JFrame {

	private Company company;

	private int nextEmp = 0;

	/**
	 * Constructor
	 * 
	 * @param company
	 *            - company holding the employees to be displayed.
	 */
	public SimpleCompanyFrame(Company company) {
		super("Company");
		this.company = company;

		setSize(500, 400);

		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((screenDim.width - getWidth()) / 2, (screenDim.height - getHeight()) / 2);

		Action openAction = new AbstractAction("Open...", new ImageIcon("images/Open.gif")) {
			public void actionPerformed(ActionEvent e) {
				doOpen();

			}
		};

		JButton openButton = new JButton(openAction);

		JPanel bottomPanel = new JPanel(new FlowLayout());

		bottomPanel.add(openButton);

		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

	}

	public Company getCompany() {
		return company;
	}

	public void doOpen() {
		int i = nextEmp++;

		if (i >= 0) {
			Employee employee = company.getEmployee(i);

			EmployeeDialog employeeDialog = new EmployeeDialog(this, employee, "Edit Employee");
			employeeDialog.setVisible(true);

			if (employeeDialog.isOkPressed()) {
				// TO DO fire event
			}
		}
	}

	/**
	 * Main. Pass the name of the department file and employee file as arguments.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println(
					"Usage: java edu.iup.cosc210.company.ui.SimpleCompanyFrame <department file> <employee file>");
			System.exit(-100);
		}
		CompanyManager companyManager = new CompanyManager();

		try {
			companyManager.loadDepts(args[0]);
			companyManager.loadEmployees(args[1]);
			SimpleCompanyFrame companyFrame = new SimpleCompanyFrame(companyManager.getCompany());
			companyFrame.setVisible(true);

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
}
