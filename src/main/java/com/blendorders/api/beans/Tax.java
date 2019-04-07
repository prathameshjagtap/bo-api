package com.blendorders.api.beans;

public class Tax {

	String taxType;
	double amount;
	
	public Tax() {
		// TODO Auto-generated constructor stub
	}
	
	public Tax(String taxType, double amount) {
		super();
		this.taxType = taxType;
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	
}
