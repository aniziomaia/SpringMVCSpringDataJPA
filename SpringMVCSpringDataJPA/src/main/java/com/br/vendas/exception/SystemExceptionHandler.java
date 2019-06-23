package com.br.vendas.exception;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SystemExceptionHandler extends ResponseEntityExceptionHandler{

	
	/**
	 * For Validating Path Variables and Request Parameters @Validate 
	 * Validacao para parametros na url
	 * @param response
	 * @throws IOException
	 */
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
    	System.out.println("###############ConstraintViolationException##################");
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
    
    /**
     * Validação para a anotação @Valid
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        
    	System.out.println("###############MethodArgumentNotValidException##################");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //pega todos os erros de validaocao e colocar em uma lista de strings
        List<String> errors = ex.getBindingResult().getFieldErrors()
                              .stream().map(x -> x.getDefaultMessage())
                              .collect(Collectors.toList());
        
        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

    
	/**
	 * Validacao geral de erro: nao consegui fazer funcionar
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = {Exception.class}) 
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		System.out.println("###############exception ResponseEntity##################");
		String erroMessageDescription = ex.getLocalizedMessage();
		System.out.println(">>>>>>>>>>>>message: " + erroMessageDescription);
		if(erroMessageDescription == null) erroMessageDescription = ex.toString();
		
		System.out.println(">>>>>>>>>>>>message: " + erroMessageDescription);
		ErrorMessage erroMessage = new ErrorMessage(new Date(), erroMessageDescription, HttpStatus.values().toString());
		return new ResponseEntity<Object>(erroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * validacao especifica de erro dentro do metodo
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = {ClienteNullPointerRestException.class}) 
	public ResponseEntity<Object> handleAnyException(ClienteNullPointerRestException ex, WebRequest request){
		System.out.println("###############ClienteNullPointerRestException##################");
		String erroMessageDescription = ex.getLocalizedMessage();
		System.out.println(">>>>>>>>>>>>message: " + erroMessageDescription);
		if(erroMessageDescription == null) erroMessageDescription = ex.toString();
		
		System.out.println(">>>>>>>>>>>>message: " + erroMessageDescription);
		ErrorMessage erroMessage = new ErrorMessage(new Date(), erroMessageDescription, HttpStatus.values().toString());
		return new ResponseEntity<Object>(erroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
