package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Controller
public class EmployeeController {
	private final EmployeeService employeeService;
	@GetMapping
	public String home(Model theModel) {
		theModel.addAttribute("theDate",new Date());
		return "index";
	}
	@GetMapping("/employees")
	public String getAllEmployees(Model theModel) {
		theModel.addAttribute("employees",employeeService.getEmployees());
		return "list-employee";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		theModel.addAttribute("employee",new Employee());
		return "employee-form";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
									Model theModel) {

		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);

		// send over to our form
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
}
