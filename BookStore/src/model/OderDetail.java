package model;

import java.io.Serializable;

public class OderDetail implements Serializable {
	private int id, bookID, quantity, orderid;

	public OderDetail(int id, int bookID, int quantity, int orderid) {
		super();
		this.id = id;
		this.bookID = bookID;
		this.quantity = quantity;
		this.orderid = orderid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	
	
}
