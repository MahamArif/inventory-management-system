package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bank {

	private String date;
	private String invoiceNo;
	private String chequeNo;
	private String partyName;
	private String totalAmount;
	private String received;
	private double remaining;
	public Bank(String date, String invoiceNo, String chequeNo, String partyName, String totalAmount, String received) {
		this.date=date;
		this.invoiceNo = invoiceNo;
		this.chequeNo = chequeNo;
		this.partyName = partyName;
		this.totalAmount = totalAmount;
		this.received = received;
		this.remaining = Double.parseDouble(totalAmount)-Double.parseDouble(received);
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/*public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = new Date();
	}*/
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getReceived() {
		return received;
	}
	public void setReceived(String received) {
		this.received = received;
	}
	public Double getRemaining() {
		return remaining;
	}
	public void setRemaining(Double remaining) {
		this.remaining = remaining;
	}
	
	
	
	
		
}
