package com.deependra.pl;


import com.deependra.bean.Author;
import com.deependra.bean.Book;
import com.deependra.service.AuthorService;
import com.deependra.service.IAuthorService;

public class AuthorMain {

	public static void main(String[] args) {
		IAuthorService as = new AuthorService();
//		Author a = new Author("anil", "kumar","mishra", "+91-8418838491");
		
//		Book b = new Book("First Book", 2500.00, 1);
		
		System.out.println(as.updatePrice("aman", 2000.00));

	}

}
