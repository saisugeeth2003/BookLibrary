package com.booklibrary.BookLibrary.Exception;

public class AuthorNotFoundException extends RuntimeException {

	private String msg;
	
	public AuthorNotFoundException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.msg;
	}
}
