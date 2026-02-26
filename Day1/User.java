package com.deepu;

public class User implements IValidator {
	private static final String Null = null;
	private String userId;
	private String password;
	
	public User(){
		
	};
	public String getUserid() {
		return userId;
	}
	public void setUserid(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addUser(String userId, String password) {
		this.userId = userId;
		this.password = password;
		System.out.println("User added successfully");
	}
	
	public boolean IsAuthenticated(String userId, String password) {
		if(this.userId != Null && this.password != Null) {
			return this.userId.equals(userId) && this.password.equals(password);
		}
		return false;
	}
	public String toString() {
		return "User [userId= "+userId + "]";
	}

}
