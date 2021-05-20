package com.samaspace;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.samaspace.dao.EntityRepositoryEmployee;
import com.samaspace.entities.Employee;

@SpringBootApplication
public class Springmvcdatasecure1Application {

	public static void main(String[] args) {
		ApplicationContext atx =
		SpringApplication.run(Springmvcdatasecure1Application.class, args);
		//System.out.println("Helloooo "+atx.getApplicationName());
		
		EntityRepositoryEmployee employeeDAO = (EntityRepositoryEmployee) atx.getBean(EntityRepositoryEmployee.class);
		Employee e1 = new Employee("Oussama", "Moroccan", 24);
		Employee e2 = new Employee("Samiiiiii", "Ukkkkk", 20);
		Employee e3 = new Employee("Hkander", "Swiss", 23);
		employeeDAO.save(e1);
		employeeDAO.save(e2);
		employeeDAO.upadteEmp("nOppps", "DANMAKOLEEE", 1L);
		//employeeDAO.save(e2);
		//employeeDAO.save(e3);
		
		
		List<Employee> myEmployees = employeeDAO.findAll();
		System.out.println("########### Employees ##########");
		
		for(int i=0;i<myEmployees.size();++i) {
			System.out.println("name :"+" "+myEmployees.get(i).getNameEmployee());
			System.out.println("nationality :"+" "+myEmployees.get(i).getNationalityEmployee());
			System.out.println("age :"+" "+myEmployees.get(i).getAgeEmployee()+" years old");
			System.out.println("********");
			
		}
		
		
		System.out.println("#############################");
		
		
List<Employee> myEmployees1 = employeeDAO.findByName("%"+"k"+"%");
		
		System.out.println("########### Employees by names ##########");
		
		for(int i=0;i<myEmployees1.size();++i) {
			System.out.println("name :"+" "+myEmployees1.get(i).getNameEmployee());
			System.out.println("nationality :"+" "+myEmployees1.get(i).getNationalityEmployee());
			System.out.println("age :"+" "+myEmployees1.get(i).getAgeEmployee()+" years old");
			System.out.println("********");
			
		}
		
		
		System.out.println("#############################");
		
		System.out.println("FIND BY ID");
		Employee ee = employeeDAO.findEmp(1L);
		System.out.println(ee.getIdEmployee());
		System.out.println(ee.getNameEmployee());
	}

}