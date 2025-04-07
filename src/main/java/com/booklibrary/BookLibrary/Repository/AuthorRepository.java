package com.booklibrary.BookLibrary.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booklibrary.BookLibrary.DTO.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByName(String authorName);
}
