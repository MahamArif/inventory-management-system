package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDate;
//import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

//import javax.swing.text.DateFormatter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
//import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;

public class ReportController implements Initializable {
	 
	
    //private ObservableList<UserData> data;
    @FXML
	private Label QtyOfProductsPurchased;
	@FXML
	private Label QtyOfProductsSold;
	@FXML
	private Label PurchasedAmount;
	@FXML
	private Label SaleAmount;
	@FXML
	private Label Profit;
	@FXML
	private Label FromDate;
	@FXML
	private Label ToDate;
	@FXML
	private AnchorPane Report_Print;
	@FXML
	private Button Btn_rep;
	
	
	public void date_get(String from, String to)
	{
	    FromDate.setText(from);
	    ToDate.setText(to);
	    try { 
			LoginModel model = new LoginModel();
			Connection con = model.connection;
		    PreparedStatement prep;
		    ResultSet res; 
		
	    	String query1 = "SELECT sum(RecieptQty) AS \"TotalQty\" FROM StockRecieving WHERE Date BETWEEN ? AND ?";
	    	prep = con.prepareStatement(query1);
	    	prep.setString(1,from);
	    	prep.setString(2,to);
	    	res= prep.executeQuery();
	    	String s1=res.getString("TotalQty");
	    	QtyOfProductsPurchased.setText(s1);
	    	
	    	String query2 = "SELECT sum(QtyDelivered) AS \"TotalQty\" FROM Invoice WHERE Date BETWEEN ? AND ?";
	    	prep = con.prepareStatement(query2);
	    	prep.setString(1,from);
	    	prep.setString(2,to);
	    	res=prep.executeQuery();
	    	String s2=res.getString("TotalQty");
	    	System.out.println(s2);
	    	QtyOfProductsSold.setText(s2);
	    	
	    	String query3 = "SELECT sum(RecieptAmount) AS \"TotalAmount1\" FROM StockRecieving WHERE Date BETWEEN ? AND ?";
	    	prep = con.prepareStatement(query3);
	    	prep.setString(1,from);
	    	prep.setString(2,to);
	    	res=prep.executeQuery();
	    	String s3=res.getString("TotalAmount1");
	    	PurchasedAmount.setText(s3);
	    	
	    	String query4 = "SELECT sum(Amount) AS \"TotalAmount2\" FROM Invoice WHERE Date BETWEEN ? AND ?";
	    	prep = con.prepareStatement(query4);
	    	prep.setString(1,from);
	    	prep.setString(2,to);
	    	res=prep.executeQuery();
	    	String s4=res.getString("TotalAmount2");
	    	SaleAmount.setText(s4);
	    	
	    	String query5= "SELECT  (SELECT sum(Amount) FROM Invoice WHERE Date BETWEEN ? AND ?) - (SELECT sum(RecieptAmount) FROM StockRecieving WHERE Date BETWEEN ? AND ?) AS \"PROFIT\"";     
	    	prep = con.prepareStatement(query5);
	    	prep.setString(1,from);
	    	prep.setString(2,to);
	    	prep.setString(3,from);
	    	prep.setString(4,to);
	    	res=prep.executeQuery();
	    	String s5=res.getString("PROFIT");
	    	Profit.setText(s5);

	    } catch (SQLException e1) {
	        e1.printStackTrace();
	        System.out.println("Error on Building Data");
	    } catch (DateTimeException e3) {System.out.println("catched");}

	}
	
	public void print_Report(ActionEvent e){
		print(Report_Print,Btn_rep);
	}
	
	public void print(Node node, Node node1){
		PrinterJob job = PrinterJob.createPrinterJob();
		if(job != null  && job.showPrintDialog(node.getScene().getWindow())){
			node1.setVisible(false);
			boolean success = job.printPage(node);
			if(success){
				job.endJob();
			}
			node1.setVisible(true);
		}
	}
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
	
}


/*private String Date(DatePicker fROM2) {
	// TODO Auto-generated method stub
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
	LocalDate dateFrom = FROM.getValue();
	LocalDate dateTO = TO.getValue();
	return null;
}*/


}
