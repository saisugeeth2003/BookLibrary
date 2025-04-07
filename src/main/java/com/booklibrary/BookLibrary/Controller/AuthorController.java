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
import com.booklibrary.BookLibrary.Service.AuthorService;
import com.booklibrary.BookLibrary.Util.ResponseStructure;

@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/get-all-authors")
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}
	
	@PostMapping("/save-author")
	public ResponseEntity<ResponseStructure<Author>> saveAuthor(@RequestBody Author author) {
//		System.out.println("This is the author object : "+author);
		return authorService.saveAuthor(author);
	}
	
	@GetMapping("/get-author-by-id/{authorId}")
	public ResponseEntity<ResponseStructure<Author>> getAuthorById(@PathVariable long authorId) {
		System.out.println("THE AUTHOR ID IS : "+authorId);
		return authorService.getAuthorById(authorId);
	}
	
	@GetMapping("/get-author-by-name/{authorName}")
	public ResponseEntity<ResponseStructure<Author>> getAuthorByName(@PathVariable String authorName) {
		System.out.println("THE AUTHOR ID IS : "+authorName);
		return authorService.getAuthorByName(authorName);
	}
	
	@PutMapping("/update-author-name/{authorId}/{new-name}")
	public ResponseEntity<ResponseStructure<Author>> updateAuthorNameById(@PathVariable("authorId") long authorId,
																		@PathVariable("new-name") String authorNewName) {
		
		return authorService.updateAuthorNameById(authorId, authorNewName);
	}
	
	@PutMapping("/update-author-name")
	public ResponseEntity<ResponseStructure<Author>> updateAuthorName(@RequestBody Author authorInfo) {
		return authorService.updateAuthorName(authorInfo);
	}
	
	@DeleteMapping("delete-by-id/{id}")
	public ResponseEntity<ResponseStructure<Author>> deleteAuthor(@PathVariable("id") long authorId) {
		return authorService.deleteAuthor(authorId);
	}
	
	
	@GetMapping("/test")
	public String test() {
		return "Server is running";
	}
	

}

/*	update author
 * 	delete author
 */
