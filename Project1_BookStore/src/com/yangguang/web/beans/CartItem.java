package com.yangguang.web.beans;

import java.io.Serializable;

import com.yangguang.domain.Book;

//购物车中的购物项
public class CartItem implements Serializable {
	private Book book;
	public CartItem(Book book) {
		this.book = book;
	}
	private int quantity;//小计数量
	private float totalPrice;//金额小计
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotalPrice() {
		return book.getPrice()*quantity;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
