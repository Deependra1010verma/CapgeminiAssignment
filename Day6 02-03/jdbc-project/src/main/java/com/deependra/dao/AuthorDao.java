package com.deependra.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deependra.bean.Author;
import com.deependra.bean.Book;

public class AuthorDao implements IAuthorDao{
	private static Connection con;
	static {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","pass1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> getAllBook(int aId) {
		List<Book> bk = new ArrayList<Book>();
		
		try {
			PreparedStatement ps = con.prepareStatement("Select* From book Where author_id=?");
			ps.setInt(1, aId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Book b = new Book(rs.getString(2),rs.getInt(3),aId);
				bk.add(b);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return bk;
	}

	@Override
	public String addAuthor(Author a) {
		try {
			PreparedStatement ps = con.prepareStatement("Insert into Author(firstname,middlename,lastname,phoneno) Values(?,?,?,?)");
			ps.setString(1, a.getFirstName());
			ps.setString(2, a.getMiddleName());
			ps.setString(3, a.getLastName());
			ps.setString(4, a.getPhoneNo());
			int row = ps.executeUpdate();
			if(row>0) {
				return "Author added Succesfully";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return "Author Does not Added";
	}

	@Override
	public String addBook(Book b) {
		try {
			PreparedStatement ps = con.prepareStatement("Insert into book(title,price,author_id) Values(?,?,?)");
			ps.setString(1, b.getTitle());
			ps.setDouble(2, b.getPrice());
			ps.setInt(3, b.getAuthorId());
			
			int row = ps.executeUpdate();
			if(row>0) {
				return "Book added Succesfully";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return "Book Does not Added";
	}

	@Override
	public String updatePrice(String name,double price) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("Select id from author where firstname = ? ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			int id = -1;
			if(rs.next()) {
				id = rs.getInt(1);
			}
			ps = con.prepareStatement("Update Book set price = ? where author_id =?");
			ps.setDouble(1, price);
			ps.setInt(2, id);
			int row = ps.executeUpdate();
			if(row>0) {
				return "Price Updated";
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return "Price Not Updated";
	}

}
