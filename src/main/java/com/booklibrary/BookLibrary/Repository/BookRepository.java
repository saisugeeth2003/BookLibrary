package com.booklibrary.BookLibrary.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booklibrary.BookLibrary.DTO.Author;
import com.booklibrary.BookLibrary.DTO.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByTitle(String bookName);
	List<Book> findByGenre(String bookGenre);
	List<Book> findByPrice(double bookPrice);
	Optional<Book> findTopByOrderByPriceDesc();
	Optional<Book> findTopByOrderByPriceAsc();
}
