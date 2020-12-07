package model;

import java.io.Serializable;

public class Address implements Serializable {
	private int id;
	private String province, district, ward, detail;
	private int customerID;
	/**
	 * @param id
	 * @param province
	 * @param district
	 * @param ward
	 * @param detail
	 * @param customerID
	 */
	public Address(int id, String province, String district, String ward, String detail, int customerID) {
		super();
		this.id = id;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.detail = detail;
		this.customerID = customerID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
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
	public String toString() {
		return getWard() + " " + getDistrict() + " " + getProvince();
	}
}
