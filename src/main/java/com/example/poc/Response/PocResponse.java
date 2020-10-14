package com.example.poc.Response;

import org.springframework.stereotype.Component;

@Component
public class PocResponse {
	
	private String status;
	private String Code;
	private String Output;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getOutput() {
		return Output;
	}
	public void setOutput(String output) {
		Output = output;
	}

}
