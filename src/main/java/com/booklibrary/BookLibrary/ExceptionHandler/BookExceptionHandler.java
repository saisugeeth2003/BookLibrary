package com.booklibrary.BookLibrary.ExceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.booklibrary.BookLibrary.Exception.AuthorNotFoundException;
import com.booklibrary.BookLibrary.Exception.BookNotDeletedException;
import com.booklibrary.BookLibrary.Exception.BookNotFoundException;
import com.booklibrary.BookLibrary.Exception.BookNotSavedException;
import com.booklibrary.BookLibrary.Util.ResponseStructure;

@ControllerAdvice
public class BookExceptionHandler {

	@Autowired
	private ResponseStructure<String> responseStructure;
	
	@ExceptionHandler(BookNotSavedException.class)
	public ResponseEntity<ResponseStructure<String>> bookObjectNotSaved(BookNotSavedException bookNotSavedException) {
		responseStructure.setData(bookNotSavedException.getMessage());
		responseStructure.setMsg("Book Object Not Saved");
		responseStructure.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> authorNotFound(BookNotFoundException bookNotFound) {
		responseStructure.setData(bookNotFound.getMessage());
		responseStructure.setMsg("Book Not Found");
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookNotDeletedException.class)
	public ResponseEntity<ResponseStructure<String>> bookNotDeleted(BookNotDeletedException bookNotDeleted) {
		responseStructure.setData(bookNotDeleted.getMessage());
		responseStructure.setMsg("Book Not Deleted");
		responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_MODIFIED);
	}
}
