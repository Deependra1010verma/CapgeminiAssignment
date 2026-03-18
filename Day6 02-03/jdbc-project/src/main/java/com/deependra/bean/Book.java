package com.deependra.bean;


public class Book {
	private String title;
	private double price;
	private int authorId;
	
	public Book(String title, double price, int authorId) {
		super();
		this.title = title;
		this.price = price;
		this.authorId = authorId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	

}
