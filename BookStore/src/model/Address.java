package model;

import java.io.Serializable;

public class Address implements Serializable {
	private int id;
	private String detail;
	private int customerID;
	/**
	 * @param id
	 * @param province
	 * @param district
	 * @param ward
	 * @param detail
	 * @param customerID
	 */
	public Address(int id, String detail, int customerID) {
		super();
		this.id = id;
		
		this.detail = detail;
		this.customerID = customerID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
}
