package com.example.poc.service;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.poc.Request.DepartmentRequest;
import com.example.poc.entity.Department;
import com.example.poc.repository.PocRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;



@Component
@Transactional
@AllArgsConstructor
public class PocService {

	@Autowired
	private PocRepository pocRepo;
	
	 private static final Logger log = LoggerFactory.getLogger(PocService.class);
	
	@Transactional(timeout=5)
	public List<Department> getDepartments()
	{
		//pocRepo.save(new Department(1,"abcdv"));
		//pocRepo.save(new Department(3,"efgh"));
		log.info("dept"+pocRepo.findAll());
		log.debug("dept"+pocRepo.findAll());
		return pocRepo.findAll().stream()
				.collect(Collectors.toList());		
	}

	public boolean addDepartment(DepartmentRequest dptRequest) {
		log.info("Department need to be added having id: "+dptRequest.getDeptId()+"and name:"+dptRequest.getDeptName());
		pocRepo.save(new Department(dptRequest.getDeptId(),dptRequest.getDeptName()));
		log.info("Department added");
		return true;	
	}
	
	public boolean deleteDeptById(Long deptId ) {
		pocRepo.deleteById(deptId);
		log.info("Department deleted " +pocRepo.findById(deptId));
		return true;	
	}

	public boolean findDeptById(Long deptId) {
		pocRepo.findById(deptId).stream();
		log.info("Department found " +pocRepo.findById(deptId));
		if(!pocRepo.findById(deptId).isEmpty())
		{
			return true;
		}
		return false;
	}

	public void connect() throws UnknownHostException, IOException {
		/*
		 * URL url = new URL("https://www.jednsanckedjxk.io"); //invalid hostname
		 * log.info("Connecting to url: "+url); HttpsURLConnection connection =
		 * (HttpsURLConnection) url.openConnection(); log.info("Connection"
		 * +connection); int code = connection.getResponseCode(); String message =
		 * connection.getResponseMessage(); String connectedUrl =
		 * connection.getURL().toURI().toString();
		 * log.info("message"+message+"url"+connectedUrl+"ResponseCode"+code);
		 */
		String host = "localht";
		int port = 5000;
		log.info("host"+host);

		Socket clientSocket = new Socket(host, port);
		log.info("socket"+clientSocket);
	    clientSocket.close();
	}
	
	
}
