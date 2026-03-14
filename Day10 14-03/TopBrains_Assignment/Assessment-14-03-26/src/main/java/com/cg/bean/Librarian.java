package com.cg.bean;

public class Librarian {
private int librarianId;
private String name;
private Book book;
public int getLibrarianId() {
	return librarianId;
}
public void setLibrarianId(int librarianId) {
	this.librarianId = librarianId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Book getBook() {
	return book;
}
public void setBook(Book book) {
	this.book = book;
}
public void issueBook() {
	System.out.println("Librarian:"+name);
	System.out.println("Issued Book Details:");
	System.out.println("Book Id:"+book.getBookId());
	System.out.println("Title:"+book.getBookId());
	System.out.println("Author:"+book.getAuthor());
}
}
