package com.booklibrary.BookLibrary.Exception;

public class BookNotFoundException extends RuntimeException{

	private String msg;
	
	public BookNotFoundException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.msg;
	}
}
