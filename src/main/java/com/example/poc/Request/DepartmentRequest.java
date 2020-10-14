package com.example.poc.Request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

//import org.springframework.format.annotation.NumberFormat;


import lombok.*;


@AllArgsConstructor
public class DepartmentRequest {

	@NotBlank
	@Digits(integer = 5,fraction = 0) 
	private long deptId;
	
	
	@NotNull
	private String deptName;

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
