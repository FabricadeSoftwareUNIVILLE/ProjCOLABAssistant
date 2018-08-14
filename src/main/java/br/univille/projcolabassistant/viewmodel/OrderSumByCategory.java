package br.univille.projcolabassistant.viewmodel;

public class OrderSumByCategory {
	private long idCategory;
	private String nameCategory;
	private long sumOrderCategory;
	
	public OrderSumByCategory() {
		// TODO Auto-generated constructor stub
	}
	public OrderSumByCategory(long idCategory,String nameCategory,long sumOrderCategory) {
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.sumOrderCategory = sumOrderCategory;
	}
	
	
	public long getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public long getSumOrderCategory() {
		return sumOrderCategory;
	}
	public void setSumOrderCategory(long sumOrderCategory) {
		this.sumOrderCategory = sumOrderCategory;
	}
}
