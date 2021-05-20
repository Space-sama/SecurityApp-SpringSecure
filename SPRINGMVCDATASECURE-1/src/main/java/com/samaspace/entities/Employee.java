package com.samaspace.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Empl")
public class Employee implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long idEmployee;
	
	
	
	@Size(max = 20, min = 4)
	private String nameEmployee;
	
	
	
	
	@Size(max = 20, min = 4)
	private String nationalityEmployee;
	
	
	
	
	
	@Min(18)
	@Max(60)
	private int ageEmployee;

	public Employee(String nameEmployee, String nationalityEmployee, int ageEmployee) {
		super();
		this.nameEmployee = nameEmployee;
		this.nationalityEmployee = nationalityEmployee;
		this.ageEmployee = ageEmployee;
	}

	public Employee() {
		super();
	}

	public Long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getNameEmployee() {
		return nameEmployee;
	}

	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}

	public String getNationalityEmployee() {
		return nationalityEmployee;
	}

	public void setNationalityEmployee(String nationalityEmployee) {
		this.nationalityEmployee = nationalityEmployee;
	}

	public int getAgeEmployee() {
		return ageEmployee;
	}

	public void setAgeEmployee(int ageEmployee) {
		this.ageEmployee = ageEmployee;
	}
	
	
	
}
