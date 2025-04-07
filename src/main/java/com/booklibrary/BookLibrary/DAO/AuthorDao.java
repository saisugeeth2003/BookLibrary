package com.booklibrary.BookLibrary.DAO;

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
import com.booklibrary.BookLibrary.Repository.AuthorRepository;
import com.booklibrary.BookLibrary.Util.ResponseStructure;

@Repository
public class AuthorDao {

	@Autowired
	private AuthorRepository authorRepo;
	@Autowired
	private ResponseStructure responseStructure;
	
	public List<Author> getAllAuthors() {
		return authorRepo.findAll();
	}
	
	public ResponseEntity<ResponseStructure<Author>> saveAuthor(Author author) {
		Author authorFound =  authorRepo.save(author);
		if(authorFound != null) {
			responseStructure.setData(authorFound);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMsg("Author Saved");
			return new ResponseEntity<ResponseStructure<Author>>(responseStructure,HttpStatus.CREATED);
		} else {
			throw new AuthorNotSavedException("Author saving failed");
		}
	}
	
	public ResponseEntity<ResponseStructure<Author>> getAuthorById(long authorId) {
		Optional<Author> authorFound = authorRepo.findById(authorId);
		if(authorFound.isPresent()) {
			Author author = authorFound.get();
			responseStructure.setData(author);
			responseStructure.setMsg("Author Found with that Id");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Author>>(responseStructure,HttpStatus.FOUND);
		} else {
			throw new AuthorNotFoundException("Book Not Found With That Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Author>> getAuthorByName(String authorName) {
		Optional<Author> authorFound = authorRepo.findByName(authorName);
		if(authorFound.isPresent()) {
			Author author = authorFound.get();
			responseStructure.setData(author);
			responseStructure.setMsg("Author Found with that Name");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Author>>(responseStructure,HttpStatus.FOUND);
		} else {
			throw new AuthorNotFoundException("Author Not Found With that Name");
		}
	}
	
	public ResponseEntity<ResponseStructure<Author>> updateAuthorNameById(long authorId, String authorNewName) {
		Optional<Author> authorFound = authorRepo.findById(authorId);
		if(authorFound.isPresent()) {
			Author author = authorFound.get();
			author.setName(authorNewName);
			Author updatedAuthor = authorRepo.save(author);
			
			if(updatedAuthor != null) {
				responseStructure.setData(updatedAuthor);
				responseStructure.setMsg("Author Name Updated Successfull to - "+authorNewName);
				responseStructure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Author>>(responseStructure,HttpStatus.OK);
			} else {
				throw new AuthorNotSavedException("Author Found but Not UPDATED");
			}	
		} else {
			throw new AuthorNotFoundException("Auhtor Not Found With That authorId");
		}
	}
	
	public ResponseEntity<ResponseStructure<Author>> updateAuthorName(Author authorInfo) {
		long authorId = authorInfo.getId();
		String authorNewName = authorInfo.getName();
		
		Optional<Author> authorFound = authorRepo.findById(authorId);
		if(authorFound.isPresent()) {
			Author author = authorFound.get();
			author.setName(authorNewName);
			Author updatedAuthor = authorRepo.save(author);
					
			if(updatedAuthor != null) {
				responseStructure.setData(updatedAuthor);
				responseStructure.setMsg("Author Name Updated Successfull to - "+authorNewName);
				responseStructure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Author>>(responseStructure,HttpStatus.OK);
			} else {
				throw new AuthorNotSavedException("Author Found but Not UPDATED");
			}
			
		} else {
			throw new AuthorNotFoundException("Auhtor Not Found With That authorId");
		}
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<Author>> deleteAuthor(long authorId) {
		Optional<Author> optionalAuthor = authorRepo.findById(authorId);
		Author author;
		if(optionalAuthor.isPresent()) {
			author = optionalAuthor.get();
			authorRepo.delete(author);
			responseStructure.setData(author);
			responseStructure.setMsg("Author '"+author.getName()+"' is deleted");
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Author>>(responseStructure,HttpStatus.OK);
		} else {
			throw new AuthorNotFoundException("Author not found with id - "+authorId);
		}
	}
}
