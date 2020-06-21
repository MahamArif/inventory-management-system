package application;

public class InvoiceRecord {
	private String serial_inr;
	private String description_inr;
	private String partNo_inr;
	private String partyName_inr;
	private String qtyDemand_inr;
	private String qtyDelivered_inr;
	private String rate_inr;
	private String amount_inr;
	private String date_inr;
	
	
	
	public InvoiceRecord(String SNo, String date, String description, String partNo,String partyName, String qtyDemand, String qtyDelivered, String rate, String amount) {
		this.setSerial_inr(SNo);
		this.setDescription_inr(description);
		this.setDate_inr(date);
		this.setPartNo_inr(partNo);
		this.setPartyName_inr(partyName);
		this.setQtyDemand_inr(qtyDemand);
		this.setQtyDelivered_inr(qtyDelivered);
		this.setRate_inr(rate);
		this.setAmount_inr(amount);
	}



	public String getSerial_inr() {
		return serial_inr;
	}



	public void setSerial_inr(String serial_inr) {
		this.serial_inr = serial_inr;
	}



	public String getDescription_inr() {
		return description_inr;
	}



	public void setDescription_inr(String description_inr) {
		this.description_inr = description_inr;
	}



	public String getPartNo_inr() {
		return partNo_inr;
	}



	public void setPartNo_inr(String partNo_inr) {
		this.partNo_inr = partNo_inr;
	}



	public String getQtyDemand_inr() {
		return qtyDemand_inr;
	}



	public void setQtyDemand_inr(String qtyDemand_inr) {
		this.qtyDemand_inr = qtyDemand_inr;
	}



	public String getPartyName_inr() {
		return partyName_inr;
	}



	public void setPartyName_inr(String partyName_inr) {
		this.partyName_inr = partyName_inr;
	}



	public String getQtyDelivered_inr() {
		return qtyDelivered_inr;
	}



	public void setQtyDelivered_inr(String qtyDelivered_inr) {
		this.qtyDelivered_inr = qtyDelivered_inr;
	}



	public String getRate_inr() {
		return rate_inr;
	}



	public void setRate_inr(String rate_inr) {
		this.rate_inr = rate_inr;
	}



	public String getAmount_inr() {
		return amount_inr;
	}



	public void setAmount_inr(String amount_inr) {
		this.amount_inr = amount_inr;
	}



	public String getDate_inr() {
		return date_inr;
	}



	public void setDate_inr(String date_inr) {
		this.date_inr = date_inr;
	}



	

}
