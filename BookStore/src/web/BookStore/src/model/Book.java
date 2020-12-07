package model;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable{
	private int id;
	private String url;
	private int quantity;
	private String name, author;
	private int price;
	private String detail;
	/**
	 * @param id
	 * @param url
	 * @param quantity
	 * @param name
	 * @param author
	 * @param price
	 * @param detail
	 */
	public Book(int id, String url, int quantity, String name, String author, int price, String detail) {
		super();
		this.id = id;
		this.url = url;
		this.quantity = quantity;
		this.name = name;
		this.author = author;
		this.price = price;
		this.detail = detail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
