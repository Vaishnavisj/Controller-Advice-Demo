package com.example.poc.repository;
import com.example.poc.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PocRepository extends JpaRepository<Department, Long>
{	
	Department findById(long id);
	
}
