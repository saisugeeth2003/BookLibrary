package com.booklibrary.BookLibrary.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booklibrary.BookLibrary.DTO.Author;
import com.booklibrary.BookLibrary.DTO.Book;
import com.booklibrary.BookLibrary.Service.BookService;
import com.booklibrary.BookLibrary.Util.ResponseStructure;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/get-all-books")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@PostMapping("/save-book")
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	@GetMapping("/get-book-by-id/{bookId}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable long bookId) {
		System.out.println("THE Book ID IS : "+bookId);
		return bookService.getBookById(bookId);
	}
	
	@GetMapping("/get-book-by-name/{bookName}")
	public ResponseEntity<ResponseStructure<Book>> getBookByName(@PathVariable String bookName) {
		System.out.println("THE Book Name IS : "+bookName);
		return bookService.getBookByName(bookName);
	}
	
	@GetMapping("/get-book-by-genre/{bookGenre}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGenre(@PathVariable String bookGenre) {
		System.out.println("THE Book Genre IS : "+bookGenre);
		return bookService.getBookByGenre(bookGenre);
	}
	
	@GetMapping("/get-book-by-price/{bookPrice}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPrice(@PathVariable double bookPrice) {
		System.out.println("THE Book price IS : "+bookPrice);
		return bookService.getBookByPrice(bookPrice);
	}
	
	@GetMapping("/get-costliest-book")
	public ResponseEntity<ResponseStructure<Book>> getCostliestBook() {
		return bookService.getCostliestBook();
	}
	@GetMapping("/get-cheapest-book")
	public ResponseEntity<ResponseStructure<Book>> getCheapestBook() {
		return bookService.getCheapestBook();
	}
	@PutMapping("update-author-books-details")
	public ResponseEntity<ResponseStructure<Author>> updateAuthorBooksDetails(@RequestBody Author author) {
		return bookService.updateAuthorBooksDetails(author);
	}
	@PostMapping("add-books/{id}/new-books")
	public ResponseEntity<ResponseStructure<Author>> addBooksToAuthor(@PathVariable("id") long authorId , @RequestBody List<Book> newBooks) {
		return bookService.addBooksToAuthor(authorId, newBooks);
	}
	@DeleteMapping("delete-one-book/{authorId}/{bookId}")
	public ResponseEntity<ResponseStructure<Author>> removeOneBookFromAuthor(@PathVariable long authorId, 
																			@PathVariable long bookId) {
		return bookService.removeOneBookFromAuthor(authorId, bookId);
	}
	@DeleteMapping("/delete-multiple-books/{id}/books")
	public ResponseEntity<ResponseStructure<Author>> removeMutlipleBooksFromAuthor(@PathVariable("id") long authorId,@RequestBody List<Book> booksToRemove) {
		return bookService.removeMultipleBooksFromAuthor(authorId, booksToRemove);
	}
}

/*
 * update author
 * delete author
 * */
