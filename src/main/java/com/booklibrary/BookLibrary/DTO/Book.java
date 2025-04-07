package com.booklibrary.BookLibrary.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private long id;
	
	@Column(name="book_title", nullable=false)
	private String title;
	
	@Column(name="book_genre", nullable=false)
	private String genre;
	
	@Column(name="book_price", nullable=false)
	private double price;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	@JsonBackReference
	private Author author;

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", genre=" + genre + ", "
				+ "price=" + price + ", authorId=" + (author != null ? author.getId() : "null")
				+ "]";
	}
	
	
}
