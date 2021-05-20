package com.samaspace.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.samaspace.entities.Employee;


//@Repository("EmployeeDAO")
public interface EntityRepositoryEmployee extends JpaRepository<Employee, Long>{
	
	@Query("SELECT e FROM Employee e WHERE e.nameEmployee like :name")
	public List<Employee> findByName(@Param("name")String name);
	
	@Query("SELECT e FROM Employee e WHERE e.nameEmployee like :name")
	public Page<Employee> findByNameEmployee(@Param("name")String name, Pageable pageable);

	
	@Query("SELECT e FROM Employee e WHERE e.idEmployee = :idEmployee")
	public Employee findEmp(@Param("idEmployee")Long idEmployee);

	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Employee e SET e.nameEmployee=:name, e.nationalityEmployee=:nat where e.idEmployee=:id")
	void upadteEmp(@Param("name") String name, @Param("nat") String nat, @Param("id")Long id);
	
}
