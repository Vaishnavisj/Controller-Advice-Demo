package com.example.poc.exception;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.h2.message.DbException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.example.poc.constants.PocErrorConstant;
import com.example.poc.service.PocService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice()
@Component
public class PocExceptionHandler extends ResponseEntityExceptionHandler {
	
	 private static final Logger log = LoggerFactory.getLogger(PocExceptionHandler.class);

	  //checked exceptions
		
		
		
		  @ExceptionHandler({DeptNotFoundException.class}) public
		  ResponseEntity<Object> handleException(DeptNotFoundException e,
		  HttpServletRequest req) { ErrorMessage errorMessage = new ErrorMessage();
		  errorMessage.setMessage("Dept not found"); errorMessage.setErrorCode("ER01");
		  return new ResponseEntity<Object> (errorMessage, HttpStatus.NOT_FOUND); }
		  
		 
		
		  @ExceptionHandler({NullPointerException.class,InValidDeptException.class})
		  public ResponseEntity<Object> handleException(Exception e, HttpServletRequest
		  req) {
		  
		  ErrorMessage errorMessage = new ErrorMessage();
		  errorMessage.setMessage("Invalid Dept"); errorMessage.setErrorCode("ER02");
		  return new ResponseEntity<Object> (errorMessage, HttpStatus.BAD_REQUEST); }
		 
		  
		/*
		 * @ExceptionHandler({RuntimeException.class}) public ResponseEntity<Object>
		 * handleException(JdbcSQLSyntaxErrorException e) { ErrorMessage errorMessage =
		 * new ErrorMessage(); errorMessage.setMessage("DB Error");
		 * errorMessage.setErrorCode("ER03"); log.info("DB Error"); return new
		 * ResponseEntity<Object> (errorMessage, HttpStatus.BAD_REQUEST); }
		 */
	 
		/*
		 * @Override protected ResponseEntity<Object>
		 * handleMissingServletRequestParameter( MissingServletRequestParameterException
		 * ex, HttpHeaders headers, HttpStatus status, WebRequest request) { String
		 * error = ex.getParameterName() + " parameter is missing"; return new
		 * ResponseEntity<Object> ("error", HttpStatus.BAD_REQUEST); }
		 */
	 //validate exceptions
		  @Override
		    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
		        log.info( ex.getBindingResult().getFieldError().getDefaultMessage());
				 return new ResponseEntity<Object> ("Invalid arguments", HttpStatus.OK);
		    }
		  

			
			
		 
	 //unchecked exceptions
		
		
		/*
		 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public
		 * ResponseEntity<Object> handleInternalServerErrors(HttpServletRequest request,
		 * Exception e) { ErrorMessage errorMessage = new ErrorMessage();
		 * errorMessage.setMessage("Caught 500 Error");
		 * log.info("error in throwable"+e.getMessage()); return new
		 * ResponseEntity<Object> (errorMessage, HttpStatus.INTERNAL_SERVER_ERROR); }
		 */
		  
		  @ExceptionHandler
		  @ResponseStatus(HttpStatus.BAD_REQUEST) public ResponseEntity<Object>
		  handleBadRequestErrors(HttpServletRequest request, Exception e) {
		  ErrorMessage errorMessage = new ErrorMessage();
		  errorMessage.setMessage("Bad request");
		  log.info("error in throwable : "+e.getMessage()); return new
		  ResponseEntity<Object> (errorMessage, HttpStatus.BAD_REQUEST); }
		 
		  @ExceptionHandler(Throwable.class)
		  ResponseEntity<Object>defaultHandler(Throwable throwable) 
		  { 
			  ErrorMessage errorMessage = new ErrorMessage(); 
			  errorMessage.setErrorCode("ER03"); 
			  
			  if(throwable instanceof IllegalStateException)
				 {
					 log.info("Illegal state exception"+throwable.getMessage());
					 return new ResponseEntity<Object> ("IllegalStateException", HttpStatus.BAD_REQUEST);
				 }
			  if(throwable instanceof RestClientException || throwable instanceof UnknownHostException || throwable instanceof ConnectException) 
			  {
				  errorMessage.setMessage(PocErrorConstant.PocRunTimeConnecExceptionError);
				  log.info("error in connection"+throwable.getMessage()); 
				  return new ResponseEntity<Object> (errorMessage, HttpStatus.OK); 
				  } 
			 else if(throwable instanceof HttpClientErrorException  || throwable instanceof HttpServerErrorException || throwable instanceof HTTPException || (((HTTPException)throwable).getStatusCode()==404) ){  
				  log.info("error in throwable"+throwable.getMessage()); 
				  errorMessage.setMessage(PocErrorConstant.HttpRunTimeConnecExceptionError);
				  return new ResponseEntity<Object> (errorMessage, HttpStatus.BAD_REQUEST); 
			  } 
			  else if(throwable instanceof DataAccessException || throwable instanceof DbException || throwable instanceof JdbcSQLSyntaxErrorException ||throwable instanceof DataIntegrityViolationException) {
				  errorMessage.setMessage(PocErrorConstant.PocRunTimeDBExceptionError);
				  log.info("error in throwable"+throwable.getMessage()); 
				  return new ResponseEntity<Object> (errorMessage, HttpStatus.INTERNAL_SERVER_ERROR); 
			  }
				/*
				 * if(HttpStatus.BAD_REQUEST.equals(status) ||
				 * HttpStatus.INTERNAL_SERVER_ERROR.equals(status) ) {
				 * errorMessage.setMessage(PocErrorConstant.PocRunTimeExceptionError); return
				 * new ResponseEntity<Object> (errorMessage, HttpStatus.OK);
				 * 
				 * }
				 */
			  errorMessage.setMessage(PocErrorConstant.PocRunTimeExceptionError); 
			  return new ResponseEntity<Object> (errorMessage, HttpStatus.NOT_FOUND); 
			  }		  
}
class ErrorMessage
{
	private String ErrorCode;
	private String Message;
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
}