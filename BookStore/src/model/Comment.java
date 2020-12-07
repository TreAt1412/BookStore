package model;

import java.io.Serializable;

public class Comment implements Serializable{
	private int id;
	private String content;
	private int customerID, bookID;
	public Comment(int id, String content, int customerID, int bookID) {
		super();
		this.id = id;
		this.content = content;
		this.customerID = customerID;
		this.bookID = bookID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	
}
