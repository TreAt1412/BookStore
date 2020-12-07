/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Viet Anh
 *
 */
public class Customer implements Serializable{
	private int id;
	private String username, password, name, phone, email;
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param name
	 * @param phone
	 * @param email
	 */
	public Customer(int id, String username, String password, String name, String phone, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + ", email=" + email + "]";
	}
	

}
