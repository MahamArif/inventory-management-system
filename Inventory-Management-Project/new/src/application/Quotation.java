package application;

import javafx.beans.property.SimpleStringProperty;

public class Quotation {
	private String description;
	private String partNo;
	private Double quantity;
	private Double unit_price;
	private Double amount;
	public Quotation(String description, String partNo, String quantity, String unit_price) {
		this.description = description;
		this.partNo = partNo;
		this.quantity = Double.parseDouble(quantity);
		this.unit_price = Double.parseDouble(unit_price);
		this.amount = Double.parseDouble(quantity)*Double.parseDouble(unit_price);

	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	

}
