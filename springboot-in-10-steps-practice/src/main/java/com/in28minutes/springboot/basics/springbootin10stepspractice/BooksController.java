package com.in28minutes.springboot.basics.springbootin10stepspractice;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
	
	@GetMapping("/books") //localhost:8080/books
	public List<Book> getAllBooks()
	{
		return Arrays.asList(new Book(1L, "My Book", "John Doe"));
	}
	
	
}
