package br.univille.projcolabassistant.viewmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.univille.projcolabassistant.model.Institution;

@Component
@Scope
public class ShoppingCart {
	private String userSessionID;
	private Date date;
	private List<ItemShoppingCart> itensList = new ArrayList<ItemShoppingCart>();
	private Institution institution = new Institution();
	
	
	
	
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
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
