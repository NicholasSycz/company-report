package edu.iup.cosc210.company.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.javera.ui.layout.JvGridLayout;
import com.javera.ui.layout.JvGridLocation;

import edu.iup.cosc210.company.bo.Company;
import edu.iup.cosc210.company.bo.Department;
import edu.iup.cosc210.company.bo.Employee;

/**
 * Employee dialog to enable entry/edit of employee data
 * 
 * @author dtsmith, Nicholas Sycz
 *
 */
public class EmployeeDialog extends JDialog {
	private static SimpleDateFormat hireDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
	private boolean okPressed = false;
	private Employee employee;
	private JTextField idField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField hiredField;
	private JTextField hourlyField;
	private JTextField salaryField;
	private JRadioButton hrButtonField;
	private JRadioButton salButtonField;
	private JRadioButton exButtonField;
	private JComboBox<Department> departmentField;

	/**
	 * Constructor
	 * 
	 * @param companyFrame
	 *            - Company frame to block
	 * @param employee
	 *            - Employee to be edited
	 * @param title
	 *            - title for the dialog, e.g. Edit Employee, New Employee
	 */
	public EmployeeDialog(SimpleCompanyFrame companyFrame, Employee employee, String title) {
		super(companyFrame, title, true);

		this.employee = employee;

		JPanel dataPanel = new JPanel(new JvGridLayout(4, 4));

		dataPanel.setBorder(BorderFactory.createTitledBorder("Employee"));

		// the Identification box
		dataPanel.add(new JLabel("ID"), new JvGridLocation(0, 0));
		idField = new JTextField(4);
		idField.setEditable(false);
		idField.setBackground(Color.LIGHT_GRAY);
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		idPanel.add(idField);
		dataPanel.add(idPanel, new JvGridLocation(0, 1));

		// the Hired box
		dataPanel.add(new JLabel("Hired"), new JvGridLocation(0, 2));
		hiredField = new JTextField(6);
		hiredField.setEditable(false);
		hiredField.setBackground(Color.LIGHT_GRAY);
		JPanel hiredPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		hiredPanel.add(hiredField);
		dataPanel.add(hiredPanel, new JvGridLocation(0, 3));

		// the First Name box
		dataPanel.add(new JLabel("First Name"), new JvGridLocation(1, 0));
		firstNameField = new JTextField(8);
		JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		firstNamePanel.add(firstNameField);
		dataPanel.add(firstNamePanel, new JvGridLocation(1, 1));

		// the Radio buttons
		dataPanel.add(new JLabel(""), new JvGridLocation(1, 2));
		JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		ButtonGroup bg = new ButtonGroup();

		hrButtonField = new JRadioButton("Hr");
		radioPanel.add(hrButtonField);
		bg.add(hrButtonField);

		salButtonField = new JRadioButton("Sal");
		radioPanel.add(salButtonField);
		bg.add(salButtonField);

		exButtonField = new JRadioButton("Ex");
		radioPanel.add(exButtonField);
		bg.add(exButtonField);

		dataPanel.add(radioPanel, new JvGridLocation(1, 2, 1, 2));

		// the Last Name box
		dataPanel.add(new JLabel("Last Name"), new JvGridLocation(2, 0));
		lastNameField = new JTextField(10);
		JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		lastNamePanel.add(lastNameField);
		dataPanel.add(lastNamePanel, new JvGridLocation(2, 1));

		// the Department drop box
		dataPanel.add(new JLabel("Department"), new JvGridLocation(3, 0));
		departmentField = new JComboBox();
		Company company = companyFrame.getCompany();
		for (int i = 0; i < company.getNoDepts(); i++) {
			departmentField.addItem(company.getDeparment(i));
		}
		JPanel departmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		departmentPanel.add(departmentField);
		dataPanel.add(departmentPanel, new JvGridLocation(3, 1));

		// the Hourly Rate box
		dataPanel.add(new JLabel("Hourly Rate"), new JvGridLocation(2, 2));
		hourlyField = new JTextField(4);
		JPanel hourlyRatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		hourlyRatePanel.add(hourlyField);
		dataPanel.add(hourlyRatePanel, new JvGridLocation(2, 3));

		// the Salary box
		dataPanel.add(new JLabel("Salary"), new JvGridLocation(3, 2));
		salaryField = new JTextField(6);
		JPanel salaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		salaryPanel.add(salaryField);
		dataPanel.add(salaryPanel, new JvGridLocation(3, 3));

		add(dataPanel);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okPressed = true;
				setVisible(false);
			}
		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		buttonPanel.add(okButton);

		buttonPanel.add(cancelButton);

		JPanel centerButtonPanel = new JPanel();

		centerButtonPanel.add(buttonPanel);

		add(centerButtonPanel, BorderLayout.SOUTH);

		pack();

		setLocation(companyFrame.getX() + (companyFrame.getWidth() - getWidth()) / 2,
				companyFrame.getY() + (companyFrame.getHeight() - getHeight()) / 2);

		setFields();
	}

	/**
	 * Was the OK button used to dismiss this dialog
	 * 
	 * @return true if the OK button was used to dismiss this dialog.
	 */
	public boolean isOkPressed() {
		return okPressed;
	}

	/**
	 * Set the fields with data from the employee
	 */
	private void setFields() {
		idField.setText("" + employee.getEmployeeNumber());
		firstNameField.setText(employee.getFirstName());
		lastNameField.setText(employee.getLastName());
		departmentField.setSelectedItem(employee.getDepartment());
		hiredField.setText(hireDateFormatter.format(employee.getHireDate()));
		if (employee.getEmployeeType() == 'H') {
			hrButtonField.setSelected(true);
		} else if (employee.getEmployeeType() == 'S') {
			salButtonField.setSelected(true);
		} else if (employee.getEmployeeType() == 'E') {
			exButtonField.setSelected(true);
		}

		hourlyField.setText(String.format("%10.2f", employee.getHourlyRate()));
		salaryField.setText(String.format("%,13.2f", employee.getSalary()));

	}

	/**
	 * Get the data from the fields and put into the employee
	 * 
	 * @throws ParseException
	 */
	private void getFields() {
		employee.setFirstName(firstNameField.getText());
		employee.setLastName(lastNameField.getText());
		employee.setDepartment((Department) departmentField.getSelectedItem());
		// employee.setHireDate(hireDateFormatter.parse(hiredField.getText()));
		employee.setHourlyRate(Double.parseDouble(hourlyField.getText()));
		employee.setSalary(Double.parseDouble(salaryField.getText()));
	}
}
