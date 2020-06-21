package application;

public class InvoiceSlip {

	
	private String description;
	private String partNo;
	private String partyName;
	private String qtyDemand;
	private String qtyDelivered;
	private String rate;
	private String amount;
	
	
	
	public InvoiceSlip( String description, String partNo,String partyName, String qtyDemand, String qtyDelivered, String rate, String amount) {
	
		this.description = description;
		this.partNo = partNo;
		this.setPartyName(partyName);
		this.qtyDemand = qtyDemand;
		this.qtyDelivered = qtyDelivered;
		this.rate = rate;
		this.amount = amount;
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



	public String getQtyDemand() {
		return qtyDemand;
	}



	public void setQtyDemand(String qtyDemand) {
		this.qtyDemand = qtyDemand;
	}



	public String getQtyDelivered() {
		return qtyDelivered;
	}



	public void setQtyDelivered(String qtyDelivered) {
		this.qtyDelivered = qtyDelivered;
	}



	public String getRate() {
		return rate;
	}



	public void setRate(String rate) {
		this.rate = rate;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
		
	}



	public String getPartyName() {
		return partyName;
	}



	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	

}
