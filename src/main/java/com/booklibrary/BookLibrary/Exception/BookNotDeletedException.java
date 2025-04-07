package com.booklibrary.BookLibrary.Exception;

public class BookNotDeletedException extends RuntimeException {

	private String msg;
	
	public BookNotDeletedException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return this.msg;
	}
}
