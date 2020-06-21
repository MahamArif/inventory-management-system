package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StockController implements Initializable {
	private LoginModel model1 = new LoginModel();
	private Connection conn1 = model1.connection;
	private PreparedStatement prep;
	
	@FXML
    private TextField PartNo;
	@FXML
	private TableView<Stock> table;
	@FXML
	private TableColumn<Stock, Date> dateColumn;
	@FXML
	private TableColumn<Stock, String> particularsColumn;
	@FXML
	private TableColumn<Stock, Integer> receiptqtyColumn;
	@FXML
	private TableColumn<Stock, Double> receiptrateColumn;
	@FXML
	private TableColumn<Stock, Double> receiptamountColumn;
	@FXML
	private TableColumn<Stock, Integer> issuedqtyColumn;
	@FXML
	private TableColumn<Stock, Double> issuedrateColumn;
	@FXML
	private TableColumn<Stock, Double> issuedamountColumn;
	@FXML
	private TableColumn<Stock, Integer> balqtyColumn;
	@FXML
	private TableColumn<Stock, Double> balrateColumn;
	@FXML
	private TableColumn<Stock, Double> balamountColumn;
	@FXML
	private TableColumn<Stock, Double> partNoColumn;
	@FXML
	private TableColumn<Stock, String> remarksColumn;
	
	private ObservableList<Stock> stock;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	public void searchReceiving(ActionEvent e){
		// TODO Auto-generated method stub
        dateColumn.setCellValueFactory(new PropertyValueFactory<Stock, Date>("date"));
		particularsColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("particulars"));
		//itemColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("items"));
		receiptqtyColumn.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("qtyReceipt"));
		receiptrateColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("rateReceipt"));
		receiptamountColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("amountReceipt"));
		issuedqtyColumn.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("qtyIssued"));
		issuedrateColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("rateIssued"));
		issuedamountColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("amountIssued"));
		balqtyColumn.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("qtyBalance"));
		balrateColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("rateBalance"));
		balamountColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("amountBalance"));
		//rejectColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("reject"));
		//acceptColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("accept"));
		partNoColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("partNo"));
		//ledgerColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("ledger"));
		remarksColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("remarks"));
		table.setItems(getStock());
		
	}
	private ObservableList<Stock> getStock() {
		// TODO Auto-generated method stub
		stock = FXCollections.observableArrayList();
		try {
			ResultSet result1 ;
	    	String query1 = "SELECT * from StockLedger WHERE PartNo=?";
	    	prep = conn1.prepareStatement(query1);
	    	prep.setString(1, PartNo.getText());
	    	
	    	result1= prep.executeQuery();
			
			while(result1.next()){
				stock.add(new Stock(result1.getString("Date"),result1.getString("Particulars"),result1.getString("PartNo"),result1.getString("RecieptQty"),result1.getString("RecieptRate"),result1.getString("IssuedQty"),result1.getString("IssuedRate"),result1.getString("BalanceQty"),result1.getString("BalanceRate"),result1.getString("Remarks")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stock;
	}
	
}
