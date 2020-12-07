package model;

import java.io.Serializable;

public class Cart implements Serializable {
	private int id, customerID;

	public Cart(int id, int customerID) {
		super();
		this.id = id;
		this.customerID = customerID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
}
