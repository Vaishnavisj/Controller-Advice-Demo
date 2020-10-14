package com.example.poc.entity;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENTLIST" ,uniqueConstraints = @UniqueConstraint(columnNames = "DEPARTMENTID"))
public class Department {
	
	@Id
	@GeneratedValue
	@Column(name = "DEPARTMENTID")
	private long departmentId;
	
	@Column(name = "NAME")
	private String name;

	public long getDepartmentId() {
		return departmentId;
	}

	
	public Department() {
		super();
		
	}

	public Department(long departmentId,String name) {
		this.departmentId = departmentId;
		this.name = name;
		
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 @Override
	public String toString() {
		return "[departmentId=" + departmentId + ", name=" + name + "]";
	}


}
