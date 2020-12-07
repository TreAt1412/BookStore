package model;

import java.io.Serializable;

public class CartDetail implements Serializable {
	private int id, cartID, bookID, quantity;

	public CartDetail(int id, int cartID, int bookID, int quantity) {
		super();
		this.id = id;
		this.cartID = cartID;
		this.bookID = bookID;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
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
	
}
