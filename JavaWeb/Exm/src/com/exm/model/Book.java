package com.exm.model;

public class Book {
	private String isbn;

	private String name;

	private String author;

	private String available;

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAvailable() {
		return this.available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public Book() {
	}

	public Book(String isbn, String name, String author, String available) {
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.available = available;
	}
}
