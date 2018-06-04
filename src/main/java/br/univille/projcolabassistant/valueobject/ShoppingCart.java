package br.univille.projcolabassistant.valueobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
	private String userSessionID;
	private Date date;
	private List<ItemShoppingCart> itensList = new ArrayList<ItemShoppingCart>();
	public String getUserSessionID() {
		return userSessionID;
	}
	public void setUserSessionID(String userSessionID) {
		this.userSessionID = userSessionID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<ItemShoppingCart> getItensList() {
		return itensList;
	}
	public void setItensList(List<ItemShoppingCart> itensList) {
		this.itensList = itensList;
	}
}
