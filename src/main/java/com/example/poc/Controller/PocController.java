package com.example.poc.Controller;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URISyntaxException;

import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.poc.Request.DepartmentRequest;
import com.example.poc.Response.PocResponse;
import com.example.poc.exception.DeptNotFoundException;
import com.example.poc.exception.InValidDeptException;
import com.example.poc.service.PocService;


@RestController
@RequestMapping("/poc/dept")
public class PocController {

	@Autowired
	private PocService pocService;
	
	@Autowired
	private PocResponse response;
	
	@Autowired
	private RestTemplate restTemplate;
	
	 @Value("${resource.url}")
	 String ResourceUrl ;
	
	 private static final Logger log = LoggerFactory.getLogger(PocController.class);

	 @GetMapping("/getDeptNames")
	 ResponseEntity<Object> getdeptNames() {	
		PocResponse response = new PocResponse();
		response.setCode("200");
		response.setStatus("Success");
		response.setOutput("Departments list:"+pocService.getDepartments());
	    return new ResponseEntity<Object>(response, HttpStatus.OK);
       }
	
	 	@GetMapping(value = "/{deptId}")
	    public ResponseEntity<Object> findDept(@PathVariable Long deptId) throws DeptNotFoundException,InValidDeptException{
			 if(deptId == 0) {
					throw  new InValidDeptException(); 
		        }
			 	boolean isDeptFound = pocService.findDeptById(deptId);
				response.setCode("200");
				response.setStatus("Success");
				if(isDeptFound) {
					response.setOutput("Dept found : "+isDeptFound);
					return new ResponseEntity<Object> (response, HttpStatus.OK);
				}
	        	throw  new DeptNotFoundException();
				//throw new HTTPException(201);
				// throw new ArithmeticException("Divide by zero error");
	    }
	 
	 @RequestMapping("/addDept/{deptName}" )
	    public ResponseEntity<Object> addDept(@RequestBody @Valid DepartmentRequest dptRequest)  {
		
		 if(pocService.addDepartment(dptRequest))
		 {
			 response.setCode("200");
			 response.setStatus("Success");
			 response.setOutput("Dept added");
		     return new ResponseEntity<Object> (response, HttpStatus.OK);
		 }
	     return new ResponseEntity<Object> ("Failed to add dept", HttpStatus.INTERNAL_SERVER_ERROR);		 
	 }
	 
	 @PostMapping("/deleteDeptById/{deptId}")
	    public ResponseEntity<Object> deleteDeptById(@PathVariable Long deptId )  {
		 	response.setCode("200");
		 	response.setStatus("Success");
		 	response.setOutput("Dept deleted: " +pocService.deleteDeptById(deptId));
		 	return new ResponseEntity<Object> (response, HttpStatus.OK);
	 }
	 
	 @RequestMapping("/dummyConnect")
	 public ResponseEntity<Object> dummyConnect() throws ConnectException, URISyntaxException,HTTPException
	 {
		 log.info("connecting to ResourceUrl: "+ResourceUrl);
			 ResponseEntity<String> responseentity = restTemplate.getForEntity(ResourceUrl, String.class);
			 log.info("response"+response);
			if( responseentity.getStatusCode().equals(HttpStatus.OK))
			{
				response.setCode("200");
			 	response.setStatus("Success");
			 	response.setOutput("Connection established with ConnectDemoApi");
				return new ResponseEntity<Object> (response, HttpStatus.OK);
			}
	 	return null;
	 }
	 
	 private ResponseEntity<Object> dummySocketConnect() throws IOException, URISyntaxException 
	 {
		 pocService.connect();
		return null;
		 
	 }
}
