package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeliveryChallan {

	private  String date;
	private  Integer serialNo;
	private  String description;
	private   String partNo;
	private   Integer qtyDemand;
	private   Integer qtyDelivered;
	private   Integer balance;
	private   String partyName;
	/*public DeliveryChallan() {
		
	}*/
	
	

	public DeliveryChallan(String serialNo, String Date, String description, String partNo, String partyName,String qtyDemand,String qtyDelivered, String balance) {
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = dateformat.parse(Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.date=Date;
		this.setSerialNo(Integer.parseInt(serialNo));
		this.setDescription(description);
		this.setPartNo(partNo);
		this.setQtyDemand(Integer.parseInt(qtyDemand));
		this.setQtyDelivered(Integer.parseInt(qtyDelivered));
		this.setBalance(Integer.parseInt(qtyDemand)-Integer.parseInt(qtyDelivered));
		this.setPartyName(partyName);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
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

	public Integer getQtyDelivered() {
		return qtyDelivered;
	}

	public void setQtyDelivered(Integer qtyDelivered) {
		this.qtyDelivered = qtyDelivered;
	}

	public Integer getQtyDemand() {
		return qtyDemand;
	}

	public void setQtyDemand(Integer qtyDemand) {
		this.qtyDemand = qtyDemand;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	
	
	

}