package com.samaspace.web;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.samaspace.dao.EntityRepositoryEmployee;
import com.samaspace.entities.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EntityRepositoryEmployee employeesDAO;

	/*
	 * @RequestMapping(value = "/index") public String index(Model model,
	 * 
	 * @RequestParam(name="page", defaultValue = "0")int page,
	 * 
	 * @RequestParam(name="size", defaultValue = "6")int size) {
	 * 
	 * 
	 * 
	 * // Pagination Page<Employee> myEmployees =
	 * employeesDAO.findAll(PageRequest.of(page, size));
	 * model.addAttribute("ListOfEmployees", myEmployees.getContent());
	 * 
	 * // table contain the number of employees pages int [] allPagesEmployees = new
	 * int[myEmployees.getTotalPages()]; model.addAttribute("AllPages",
	 * allPagesEmployees); model.addAttribute("CurrentPage", page);
	 * model.addAttribute("Size", size);
	 * 
	 * return "Employees.html"; // List of employees
	 * 
	 * List<Employee> myEmployees = employeesDAO.findAll();
	 * model.addAttribute("ListOfEmployees", myEmployees); return "Employees.html";
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/user/index")
	public String index(Model model, 
			@RequestParam(name="page", defaultValue = "0")int page, 
			@RequestParam(name="size", defaultValue = "6")int size,
			@RequestParam(name="key", defaultValue = "")String key) {
		
		 
		
		// Pagination
		  Page<Employee> myEmployees = employeesDAO.findByNameEmployee("%"+key+"%", PageRequest.of(page, size));
		  model.addAttribute("ListOfEmployees", myEmployees.getContent());
		  
		  //  table contain the number of employees pages
		  int [] allPagesEmployees = new int[myEmployees.getTotalPages()];
		  model.addAttribute("AllPages", allPagesEmployees);
		  model.addAttribute("CurrentPage", page);
		  model.addAttribute("Size", size);
		  model.addAttribute("Key", key);
		 
		  return "Employees.html";
		// List of employees
			/*
			 * List<Employee> myEmployees = employeesDAO.findAll();
			 * model.addAttribute("ListOfEmployees", myEmployees); return "Employees.html";
			 */
	}
	
	
	@RequestMapping(value="/admin/deleteEmployee", method = RequestMethod.GET)
	//Method is GET by default even I do not specify it
	public String deleteEmployee(Long id,String key, int page, int size) {
		employeesDAO.deleteById(id);
		return "redirect:/user/index?page="+page+"&size="+size+"&key="+key;
	}
	
	@RequestMapping(value="/admin/addEmployee", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		
		model.addAttribute("e", new Employee());
		return "addEmployee.html";
	}
	
	@RequestMapping(value="/admin/Insert", method = RequestMethod.POST)
	public String Insert(Model model, @Valid @ModelAttribute("employee") Employee employee, BindingResult br) {
		
		if(br.hasErrors()) {
			System.out.println(br.getAllErrors());
			System.out.println("ERROR :"+br);
			model.addAttribute("e", new Employee());
			return "addEmployee";
			}
		else 
			employeesDAO.save(employee);
			return "EmployeeAdded.html";		
	}
	
	
	@RequestMapping(value="/admin/Edit", method =RequestMethod.GET) 
	public String Edit(Long idE, Model model) {
		
		Employee emp = new Employee();
		emp = employeesDAO.findEmp(idE);
		model.addAttribute("emp", emp);
		return "EditEmployee.html";
	}
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String homePage() {
		return "redirect:/user/index";
	}
	
	@RequestMapping(value="/403")
	public String errorPage() {
		return "403.html";
	}
	
	
	
	

}
