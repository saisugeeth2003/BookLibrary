# 📚 Library Book Management API

A flexible RESTful backend-only API project for managing a collection of books and their authors. Built using Spring Boot and MySQL, this project offers a rich set of endpoints to perform dynamic CRUD operations, demonstrating powerful ORM capabilities through Hibernate and JPA.

---

##  Technologies Used

- Java 17  
- Spring Boot 3.4.4  
- Spring MVC  
- Spring Data JPA  
- Hibernate (ORM)  
- MySQL  
- Maven  
- Lombok  
- Postman (for API testing)  
- REST API

---

##  Features Implemented

###  Author Management
- Save a new author  
- Get all authors  
- Get author by ID  
- Get author by name  
- Update author name (by ID or via request body)  
- Delete author by ID

###  Book Management
- Save a new book  
- Get all books  
- Get book by ID  
- Get book by name  
- Get book by genre  
- Get book by price  
- Get the cheapest book  
- Update author and their books' details  
- Add one or multiple books to an author  
- Delete a specific book from an author  
- Delete multiple books from an author

---

## 📡 API Endpoints

### Author APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/save-author` | Save a new author |
| `GET` | `/get-all-authors` | Get all authors |
| `GET` | `/get-author-by-id/{authorId}` | Get author by ID |
| `GET` | `/get-author-by-name/{authorName}` | Get author by name |
| `PUT` | `/update-author-name/{authorId}/{new-name}` | Update author name by path variables |
| `PUT` | `/update-author-name` | Update author name via request body |
| `DELETE` | `/delete-by-id/{id}` | Delete author by ID |

### Book APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/save-book` | Save a new book |
| `GET` | `/get-all-books` | Get all books |
| `GET` | `/get-book-by-id/{bookId}` | Get book by ID |
| `GET` | `/get-book-by-name/{bookName}` | Get book by name |
| `GET` | `/get-book-by-genre/{bookGenre}` | Get book by genre |
| `GET` | `/get-book-by-price/{bookPrice}` | Get book by price |
| `GET` | `/get-cheapest-book` | Get the cheapest book |
| `PUT` | `/update-author-books-details` | Update books under an author |
| `POST` | `/add-books/{id}/new-books` | Add one or more books to an author |
| `DELETE` | `/delete-one-book/{authorId}/{bookId}` | Delete a specific book from an author |
| `DELETE` | `/delete-multiple-books/{id}/books` | Delete multiple books from an author |

---

## Database Configuration

Defined in `application.properties`:

```properties
spring.application.name=BookLibrary
spring.datasource.url=jdbc:mysql://localhost:3306/book_spring?createDatabaseIfNotExist=true 
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

Database Configurations
-----------------------
Database name: book_spring
Table names: author, book
Port: localhost:8080

Sample Book JSON (Postman)
-------------------------
{
  "title": "Authobiography of a yogi",
  "genre": "Spirituality",
  "price": 999
}

Future Enhancements
--------------------
Implement security using JWT or OAuth 2.0
Add Swagger UI for API documentation
Deploy to cloud platforms like Heroku or Render

Author -Developed by KODURI SAI SUGEETH