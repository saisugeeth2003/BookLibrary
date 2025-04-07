package com.booklibrary.BookLibrary.Exception;

public class AuthorNotSavedException extends RuntimeException{


	private String msg;
	
	public AuthorNotSavedException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.msg;
	}
	
}
