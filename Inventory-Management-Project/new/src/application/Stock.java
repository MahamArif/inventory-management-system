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

public class Stock {
	
	private String date;
	private StringProperty particulars;
	private StringProperty partNo;
	private IntegerProperty qtyReceipt;
	private DoubleProperty rateReceipt;
	private DoubleProperty amountReceipt;
	private IntegerProperty qtyIssued;
	private DoubleProperty rateIssued;
	private DoubleProperty amountIssued;
	private IntegerProperty qtyBalance;
	private DoubleProperty rateBalance;
	private DoubleProperty amountBalance;
	private StringProperty remarks;
	
	
	public Stock(String date1, String particulars, String partNo, String qtyReceipt, String rateReceipt, String qtyIssued, String rateIssued,String qtyBalance,
			String rateBalance, String remarks) {
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = dateformat.parse(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.date = date1;
		this.particulars = new SimpleStringProperty(particulars);
		this.partNo = new SimpleStringProperty(partNo);
		
		
		if(!qtyReceipt.equals("")){
		this.qtyReceipt = new SimpleIntegerProperty(Integer.parseInt(qtyReceipt));}
		else{
			this.qtyReceipt = new SimpleIntegerProperty(0);
		}
		
		if(!rateReceipt.equals("")){
		this.rateReceipt = new SimpleDoubleProperty(Double.parseDouble(rateReceipt));}
		else{
			this.rateReceipt = new SimpleDoubleProperty(0);
		}
		
		if((!qtyReceipt.equals("")) && (!rateReceipt.equals(""))){
		this.amountReceipt = new SimpleDoubleProperty(Integer.parseInt(qtyReceipt)*Double.parseDouble(rateReceipt));}
		else{
			this.amountReceipt = new SimpleDoubleProperty(0);
		}
		
		if(!qtyIssued.equals("")){
		this.qtyIssued = new SimpleIntegerProperty(Integer.parseInt(qtyIssued));}
		else{
			this.qtyIssued = new SimpleIntegerProperty(0);
		}
		
		if(!rateIssued.equals("")){
		this.rateIssued = new SimpleDoubleProperty(Double.parseDouble(rateIssued));}
		else{
			this.rateIssued = new SimpleDoubleProperty(0);
		}
		
		if((!qtyIssued.equals("")) && (!rateIssued.equals(""))){
		this.amountIssued = new SimpleDoubleProperty(Integer.parseInt(qtyIssued)*Double.parseDouble(rateIssued));}
		else{
			this.amountIssued = new SimpleDoubleProperty(0);
		}
		
		if(!qtyBalance.equals("")){
		this.qtyBalance = new SimpleIntegerProperty(Integer.parseInt(qtyBalance));}
		else{
			this.qtyBalance = new SimpleIntegerProperty(0);
		}
		
		if(!rateBalance.equals("")){
		this.rateBalance = new SimpleDoubleProperty(Double.parseDouble(rateBalance));}
		else{
			this.rateBalance = new SimpleDoubleProperty(0);
		}
		
		if((!qtyBalance.equals("")) && (!rateBalance.equals(""))){
		this.amountBalance = new SimpleDoubleProperty(Integer.parseInt(qtyBalance)*Double.parseDouble(rateBalance));}
		else{
			this.amountBalance = new SimpleDoubleProperty(0);
		}
			
			
		
		
		this.remarks = new SimpleStringProperty(remarks);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getParticulars() {
		return particulars.get();
	}
	public void setParticulars(String parti) {
		particulars.set(parti);
	}
	public StringProperty particularsProperty() { 
		return particulars;
	}
	
	public String getPartNo() {
		return partNo.get();
	}
	public void setPartNo(String foNo) {
		partNo.set(foNo);
	}	
	public StringProperty folioNoProperty() { 
		return partNo;
	}

	public Integer getQtyReceipt() {
		return qtyReceipt.get();
	}
	public void setQtyReceipt(Integer qtyRe) {
		qtyReceipt.set(qtyRe);
	}	
	public IntegerProperty qtyReceiptProperty() { 
		return qtyReceipt;
	}	

	public double getRateReceipt() {
		return rateReceipt.get();
	}
	public void setRateReceipt(double foNo) {
		rateReceipt.set(foNo);
	}
	public DoubleProperty rateReceiptProperty() { 
		return rateReceipt;
	}

	public double getAmountReceipt() {
		return amountReceipt.get();
	}
	public void setAmountReceipt(double amount) {
		amountReceipt.set(amount);
	}
	public DoubleProperty amountReceiptProperty() { 
		return amountReceipt;
	}
	
	
	public Integer getQtyIssued() {
		return qtyIssued.get();
	}
	public void setQtyIssued(Integer qty) {
		qtyIssued.set(qty);
	}	
	public IntegerProperty qtyIssuedProperty() { 
		return qtyIssued;
	}	

	public double getAmountIssued() {
		return amountIssued.get();
	}
	public void setAmountIssued(double amount) {
		amountIssued.set(amount);
	}
	public DoubleProperty amountIssuedProperty() { 
		return amountIssued;
	}

	public double getRateIssued() {
		return rateIssued.get();
	}
	public void setRateIssued(double rate) {
		rateIssued.set(rate);
	}
	public DoubleProperty rateIssuedProperty() { 
		return rateIssued;
	}


	public Integer getQtyBalance() {
		return qtyBalance.get();
	}
	public void setQtyBalance(Integer qty) {
		qtyBalance.set(qty);
	}
	public IntegerProperty qtyBalanceProperty() { 
		return qtyBalance;
	}

	public double getRateBalance() {
		return rateBalance.get();
	}
	public void setRateBalance(double rate) {
		rateBalance.set(rate);
	}
	public DoubleProperty rateBalanceProperty() { 
		return rateBalance;
	}

	public double getAmountBalance() {
		return amountBalance.get();
	}
	public void setAmountBalance(double amount) {
		amountBalance.set(amount);
	}
	public DoubleProperty amountBalanceProperty() { 
		return amountBalance;
	}

	public String getRemarks() {
		return remarks.get();
	}
	public void setRemarks(String rem) {
		remarks.set(rem);
	}
	public StringProperty remarksProperty() { 
		return remarks;
	}
	
}
