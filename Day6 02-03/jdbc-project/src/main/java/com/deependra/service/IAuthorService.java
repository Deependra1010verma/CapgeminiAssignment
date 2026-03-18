package com.deependra.service;


import java.util.List;

import com.deependra.bean.Author;
import com.deependra.bean.Book;

public interface IAuthorService {
	public List<Book> getAllBook(int aId);
	public String addAuthor(Author a);
	public String addBook(Book b);
	public String updatePrice(String name,double price);

}
