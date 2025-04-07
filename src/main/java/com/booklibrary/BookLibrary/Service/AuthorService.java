package com.booklibrary.BookLibrary.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.booklibrary.BookLibrary.DAO.AuthorDao;
import com.booklibrary.BookLibrary.DTO.Author;
import com.booklibrary.BookLibrary.DTO.Book;
import com.booklibrary.BookLibrary.Util.ResponseStructure;

@Service
public class AuthorService {

	@Autowired
	private AuthorDao authorDao;
	
	public List<Author> getAllAuthors() {
		return authorDao.getAllAuthors();
	}
	
	public ResponseEntity<ResponseStructure<Author>> saveAuthor(Author author) {
		return authorDao.saveAuthor(author);
	}
	
	public ResponseEntity<ResponseStructure<Author>> getAuthorById(long authorId) {
		return authorDao.getAuthorById(authorId);
	}
	
	public ResponseEntity<ResponseStructure<Author>> getAuthorByName(String authorName) {
		return authorDao.getAuthorByName(authorName);
	}
	
	public ResponseEntity<ResponseStructure<Author>> updateAuthorNameById(long authorId, String authorNewName) {
		return authorDao.updateAuthorNameById(authorId, authorNewName);
	}

	public ResponseEntity<ResponseStructure<Author>> updateAuthorName(Author authorInfo) {
		return authorDao.updateAuthorName(authorInfo);
	}
	
	public ResponseEntity<ResponseStructure<Author>> deleteAuthor(long authorId) {
		return authorDao.deleteAuthor(authorId);
	}
	
}
