package com.booklibrary.BookLibrary.ExceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.booklibrary.BookLibrary.Exception.AuthorNotFoundException;
import com.booklibrary.BookLibrary.Exception.AuthorNotSavedException;
import com.booklibrary.BookLibrary.Exception.BookNotSavedException;
import com.booklibrary.BookLibrary.Util.ResponseStructure;

@ControllerAdvice
public class AuthorExceptionHandler {

	@Autowired
	private ResponseStructure<String> responseStructure;
	
	@ExceptionHandler(AuthorNotSavedException.class)
	public ResponseEntity<ResponseStructure<String>> authorObjectNotSaved(AuthorNotSavedException authorNotSavedException) {
		responseStructure.setData(authorNotSavedException.getMessage());
		responseStructure.setMsg("Author Object Not Saved");
		responseStructure.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ExceptionHandler(AuthorNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> authorNotFound(AuthorNotFoundException authorNotFound) {
		responseStructure.setData(authorNotFound.getMessage());
		responseStructure.setMsg("Author Not Found");
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}
