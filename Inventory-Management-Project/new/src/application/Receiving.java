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

public class Receiving {
	
	private String date;
	private StringProperty partyName;
	private StringProperty items;
	private IntegerProperty qty;
	private DoubleProperty rate;
	private DoubleProperty amount;
	private StringProperty status;
	private SimpleStringProperty partNo;
	private StringProperty ledger;
	private StringProperty remarks;
	
	public Receiving() {
		
	}
	
	public Receiving(String date1, String partyName, String items, String qty, String rate, String status, 
			String partNo, String ledger, String remarks) {
		//super();
		
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = dateformat.parse(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.date = date1;
		
		
		this.partyName = new SimpleStringProperty(partyName);
		this.items = new SimpleStringProperty(items);
		this.qty = new SimpleIntegerProperty(Integer.parseInt(qty));
		this.rate = new SimpleDoubleProperty(Double.parseDouble(rate));
		this.amount = new SimpleDoubleProperty(Integer.parseInt(qty)*Double.parseDouble(rate));
		this.status = new SimpleStringProperty(status);
		this.partNo = new SimpleStringProperty(partNo);
		this.ledger = new SimpleStringProperty(ledger);
		this.remarks = new SimpleStringProperty(remarks);
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPartyName() {
		return partyName.get();
	}

	public void setPartyName(String party) {
		partyName.set(party);
	}
	public StringProperty partyNameProperty() { return partyName; }

	public String getItems() {
		return items.get();
	}

	public void setItems(String item) {
		items.set(item);
	}
	public StringProperty itemsProperty() { return items; }

	public Integer getQty() {
		return qty.get();
	}

	public void setQty(Integer qt) {
		qty.set(qt);
	}
	public IntegerProperty qtyProperty() { return qty; }

	public double getRate() {
		return rate.get();
	}

	public void setRate(double ra) {
		rate.set(ra);
	}
	public DoubleProperty rateProperty() { return rate; }

	public double getAmount() {
		return amount.get();
	}

	public void setAmount(double amo) {
		amount.set(amo);
	}
	public DoubleProperty amountProperty() { return amount; }

	public String getStatus() {
		return status.get();
	}

	public void setStatus(String re) {
		status.set(re);
	}
	public StringProperty statusProperty() { return status; }

	public String getPartNo() {
		return partNo.get();
	}

	public void setPartNo(String partNo) {
		this.partNo.set(partNo);
	}
	public StringProperty folioNoProperty() { return partNo; }

	public String getLedger() {
		return ledger.get();
	}

	public void setLedger(String led) {
		ledger.set(led);
	}
	public StringProperty ledgerProperty() { return ledger; }

	public String getRemarks() {
		return remarks.get();
	}

	public void setRemarks(String remarks) {
		this.remarks.set(remarks);
	}
	public StringProperty remarksProperty() { return remarks; }
	
	
	
	
	
}
