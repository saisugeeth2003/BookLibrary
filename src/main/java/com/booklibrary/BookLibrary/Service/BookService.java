package com.booklibrary.BookLibrary.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.booklibrary.BookLibrary.DAO.BookDao;
import com.booklibrary.BookLibrary.DTO.Author;
import com.booklibrary.BookLibrary.DTO.Book;
import com.booklibrary.BookLibrary.Util.ResponseStructure;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}
	
	public ResponseEntity<ResponseStructure<Book>> saveBook(Book book) {
		return bookDao.saveBook(book);
	}
	public ResponseEntity<ResponseStructure<Book>> getBookById(long bookId) {
		return bookDao.getBookById(bookId);
	}
	public ResponseEntity<ResponseStructure<Book>> getBookByName(String bookName) {
		return bookDao.getBookByName(bookName);
	}
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGenre(String bookGenre) {
		return bookDao.getBookByGenre(bookGenre);
	}
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPrice(double bookPrice) {
		return bookDao.getBookByPrice(bookPrice);
	}
	public ResponseEntity<ResponseStructure<Book>> getCostliestBook() {
		return bookDao.getCostliestBook();
	}
	public ResponseEntity<ResponseStructure<Book>> getCheapestBook() {
		return bookDao.getCheapestBook();
	}
	public ResponseEntity<ResponseStructure<Author>> updateAuthorBooksDetails(Author author) {
		return bookDao.updateAuthorBooksDetails(author);
	}
	public ResponseEntity<ResponseStructure<Author>> addBooksToAuthor(long authorId, List<Book> newBooks) {
		return bookDao.addBooksToAuthor(authorId, newBooks);
	}
	public ResponseEntity<ResponseStructure<Author>> removeOneBookFromAuthor(long authorId, long bookId) {
		return bookDao.removeOneBookFromAuthor(authorId, bookId);
	}
	public ResponseEntity<ResponseStructure<Author>> removeMultipleBooksFromAuthor(long authorId, List<Book> booksToRemove) {
		return bookDao.removeMultipleBooksFromAuthor(authorId, booksToRemove);
	}
}
