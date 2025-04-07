package com.booklibrary.BookLibrary.Exception;

public class BookNotSavedException extends RuntimeException {

	private String msg;
	
	public BookNotSavedException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.getMessage();
	}
}
