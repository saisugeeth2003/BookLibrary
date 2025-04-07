package com.booklibrary.BookLibrary.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.booklibrary.BookLibrary.DTO.Author;
import com.booklibrary.BookLibrary.DTO.Book;
import com.booklibrary.BookLibrary.Exception.AuthorNotFoundException;
import com.booklibrary.BookLibrary.Exception.AuthorNotSavedException;
import com.booklibrary.BookLibrary.Exception.BookNotDeletedException;
import com.booklibrary.BookLibrary.Exception.BookNotFoundException;
import com.booklibrary.BookLibrary.Exception.BookNotSavedException;
import com.booklibrary.BookLibrary.Repository.AuthorRepository;
import com.booklibrary.BookLibrary.Repository.BookRepository;
import com.booklibrary.BookLibrary.Util.ResponseStructure;

@Repository
public class BookDao {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private AuthorRepository authorRepo;
	@Autowired
	private ResponseStructure responseStructure;
	
	
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}
	
	public ResponseEntity<ResponseStructure<Book>> saveBook(Book book) {
		Book bookSaved = bookRepo.save(book);
		if(bookSaved != null) {
			responseStructure.setData(bookSaved);
			responseStructure.setMsg("Book Saved");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Book>>(responseStructure,HttpStatus.CREATED);
		} else {
			throw new BookNotSavedException("Book Not Saved");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Book>> getBookById(long bookId) {
		Optional<Book> optionalBook = bookRepo.findById(bookId);
		if(optionalBook.isPresent()) {
			Book book = optionalBook.get();
			responseStructure.setData(book);
			responseStructure.setMsg("Book Found with that Id");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Book>>(responseStructure,HttpStatus.FOUND);
					
		} else {
			throw new BookNotFoundException("Book Not Found with that Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Book>> getBookByName(String bookName) {
		Optional<Book> bookFound = bookRepo.findByTitle(bookName);
		if(bookFound.isPresent()) {
			Book book = bookFound.get();
			responseStructure.setData(book);
			responseStructure.setMsg("Book Found with that Name");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Book>>(responseStructure,HttpStatus.FOUND);
		} else {
			throw new BookNotFoundException("Book Not Found With that Name");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGenre(String bookGenre) {
		List<Book> booksFound = bookRepo.findByGenre(bookGenre);
		if(!booksFound.isEmpty()) {
			responseStructure.setData(booksFound);
			responseStructure.setMsg(booksFound.size()+" books found with that Genre");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Book>>>(responseStructure,HttpStatus.FOUND);
		} else {
			throw new BookNotFoundException("Books Not Found With that Genre");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPrice(double bookPrice) {
		List<Book> booksFound = bookRepo.findByPrice(bookPrice);
		if(!booksFound.isEmpty()) {
			responseStructure.setData(booksFound);
			responseStructure.setMsg(booksFound.size()+" books found with that price");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Book>>>(responseStructure,HttpStatus.FOUND);
		} else {
			throw new BookNotFoundException("Books Not Found With that price");
		}
	}
	
	public ResponseEntity<ResponseStructure<Book>> getCostliestBook() {
		Optional<Book> bookFound = bookRepo.findTopByOrderByPriceDesc();
		if(bookFound.isPresent()) {
			responseStructure.setData(bookFound);
			responseStructure.setMsg("Costliest Book found ");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Book>>(responseStructure,HttpStatus.FOUND);
		} else {
			throw new BookNotFoundException("Costliest Book not found ");
		}
	}
	
	public ResponseEntity<ResponseStructure<Book>> getCheapestBook() {
		Optional<Book> bookFound = bookRepo.findTopByOrderByPriceAsc();
		if(bookFound.isPresent()) {
			responseStructure.setData(bookFound);
			responseStructure.setMsg("Cheapest Book found ");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Book>>(responseStructure,HttpStatus.FOUND);
		} else {
			throw new BookNotFoundException("Cheapest Book not found ");
		}
	}
	
	public ResponseEntity<ResponseStructure<Author>> updateAuthorBooksDetails(Author updatedAuthor) {
		Optional<Author> optionalAuthor = authorRepo.findById(updatedAuthor.getId());

	    if (optionalAuthor.isPresent()) {
	        Author existingAuthor = optionalAuthor.get();

	        List<Book> existingBooks = existingAuthor.getBooks();
	        List<Book> updatedBooks = updatedAuthor.getBooks();

	        for (Book updatedBook : updatedBooks) {
	            for (Book existingBook : existingBooks) {
	                if (existingBook.getId() == updatedBook.getId()) {
	                    	existingBook.setTitle(updatedBook.getTitle());
	                    	existingBook.setPrice(updatedBook.getPrice());
	                    	existingBook.setGenre(updatedBook.getGenre());
	                    	break;
	                }
	            }
	        }

	        Author authorSaved = authorRepo.save(existingAuthor);

	        responseStructure.setData(authorSaved);
	        responseStructure.setMsg("Author and Book Details Updated Successfully");
	        responseStructure.setStatus(HttpStatus.OK.value());

	        return new ResponseEntity<ResponseStructure<Author>>(responseStructure, HttpStatus.OK);
	    } else {
	        throw new AuthorNotSavedException("Author Not Found for Update");
	    }
	}
	
	public ResponseEntity<ResponseStructure<Author>> addBooksToAuthor(long authorId, List<Book> newBooks) {
		Optional<Author> optionalAuthor = authorRepo.findById(authorId);
		if(optionalAuthor.isPresent()) {
			Author existingAuthor = optionalAuthor.get();
			List<Book> updatedBooks = newBooks;
			
			for(Book book : updatedBooks) {
				book.setAuthor(existingAuthor);
			}
			existingAuthor.getBooks().addAll(updatedBooks);
			Author finalAuthor = authorRepo.save(existingAuthor);
			if(finalAuthor != null) {
				responseStructure.setData(existingAuthor);
				responseStructure.setMsg("Books added succesfully to author - "+existingAuthor.getName());
				responseStructure.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Author>>(responseStructure,HttpStatus.ACCEPTED);
			} else {
				throw new AuthorNotSavedException("Author not saved");
			}
		} else {
			throw new AuthorNotFoundException("Author Not Found with that ID");
		}
	}
	
	public ResponseEntity<ResponseStructure<Author>> removeOneBookFromAuthor(long authorId, long bookId) {
		Optional<Author> optionalAuthor = authorRepo.findById(authorId);
	    Optional<Book> optionalBook = bookRepo.findById(bookId);

	    if (optionalAuthor.isEmpty()) {
	        throw new AuthorNotFoundException("No Author Found with that id");
	    }

	    if (optionalBook.isEmpty()) {
	        throw new BookNotFoundException("Book with that id is not found");
	    }

	    Author author = optionalAuthor.get();
	    Book book = optionalBook.get();

	    // Get managed collection
	    List<Book> books = author.getBooks();

	    // Remove book from the existing list directly
	    boolean removed = books.removeIf(b -> b.getId() == book.getId());

	    if (!removed) {
	        throw new BookNotDeletedException("Book with title '" + book.getTitle() + "' not associated with this author");
	    }

	    Author updatedAuthor = authorRepo.save(author);

	    responseStructure.setData(updatedAuthor);
	    responseStructure.setMsg("Book with title " + book.getTitle() + " Deleted successfully");
	    responseStructure.setStatus(HttpStatus.OK.value());

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Author>> removeMultipleBooksFromAuthor(long authorId, List<Book> booksToRemove) {
		Optional<Author> optionalAuthor = authorRepo.findById(authorId);
		Author existingAuthor;
		List<Book> existingBooks;
		List<Book> updatedBookList = new ArrayList<>();
		
		if(optionalAuthor.isPresent()) {
			existingAuthor = optionalAuthor.get();
			existingBooks = existingAuthor.getBooks();
			
			/*for(Book book : existingBooks) {
				if(!booksToRemove.contains(book)) {
					book.setAuthor(existingAuthor);
					updatedBookList.add(book);
				}
			} */
			existingBooks.removeIf(existingBook -> 
			booksToRemove.stream().anyMatch(b-> b.getId() == existingBook.getId()));
			
//			existingAuthor.setBooks(existingBooks);
			
			Author finalAuthor = authorRepo.save(existingAuthor);
			if(finalAuthor != null) {
				responseStructure.setData(finalAuthor);
				responseStructure.setMsg("Books Are Deleted");
				responseStructure.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Author>>(responseStructure,HttpStatus.ACCEPTED);
			} else {
				throw new BookNotDeletedException("Books Not Deleted");
			}
					
		} else {
			throw new AuthorNotFoundException("Author Not Found with that ID");
		}
	} 
	
}
