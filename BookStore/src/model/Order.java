package model;

import java.io.Serializable;
import java.util.Date;


public class Order implements Serializable{
	private int id;
	private Date date;
	private int price;
	private int type;
	private String address;
	private int customerID;
	public Order(int id, Date date, int price, int type, String address, int customerID) {
		super();
		this.id = id;
		this.date = date;
		this.price = price;
		this.type = type;
		this.address = address;
		this.customerID = customerID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCartID() {
		return customerID;
	}
	public void setCartID(int customerID) {
		this.customerID = customerID;
	}
	
}
