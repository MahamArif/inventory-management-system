package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DateSearchController implements Initializable {
	private LoginModel model1 = new LoginModel();
	private Connection conn1 = model1.connection;
	private PreparedStatement prep;
	
	@FXML
	private DatePicker datepicker;
	@FXML
	private TextField PartyName;
	@FXML
	private TableView<Receiving> table;
	@FXML
	private TableColumn<Receiving, Date> dateColumn;
	@FXML
	private TableColumn<Receiving, String> partyNameColumn;
	@FXML
	private TableColumn<Receiving, String> itemColumn;
	@FXML
	private TableColumn<Receiving, Double> receiptColumn;
	@FXML
	private TableColumn<Receiving, Integer> qtyColumn;
	@FXML
	private TableColumn<Receiving, Double> rateColumn;
	@FXML
	private TableColumn<Receiving, Double> amountColumn;
	@FXML
	private TableColumn<Receiving, String> statusColumn;
	@FXML
	private TableColumn<Receiving, String> ledgerColumn;
	@FXML
	private TableColumn<Receiving, Double> partNoColumn;
	@FXML
	private TableColumn<Receiving, String> remarksColumn;
	
	private ObservableList<Receiving> receiving= FXCollections.observableArrayList();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		dateColumn.setCellValueFactory(new PropertyValueFactory<Receiving, Date>("date"));
		partyNameColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("partyName"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("items"));
		receiptColumn.setCellValueFactory(new PropertyValueFactory<Receiving, Double>("receipt"));
		qtyColumn.setCellValueFactory(new PropertyValueFactory<Receiving, Integer>("qty"));
		rateColumn.setCellValueFactory(new PropertyValueFactory<Receiving, Double>("rate"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Receiving, Double>("amount"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("status"));
		partNoColumn.setCellValueFactory(new PropertyValueFactory<Receiving, Double>("partNo"));
		ledgerColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("ledger"));
		remarksColumn.setCellValueFactory(new PropertyValueFactory<Receiving, String>("remarks"));
		table.setItems(receiving);
	}
	
	
	public void searchReceiving(ActionEvent e){
		receiving.clear();
		try {
			ResultSet result1 ;
	    	String query1 = "SELECT * from StockRecieving WHERE Date=?";
	    	prep = conn1.prepareStatement(query1);
	    	prep.setString(1, datepicker.getValue().toString());
	    	
	    	result1= prep.executeQuery();
			
			while(result1.next()){
				receiving.add(new Receiving(result1.getString("Date"),result1.getString("PartyName"),result1.getString("Particulars"),result1.getString("RecieptQty"),result1.getString("RecieptRate"),result1.getString("Status"),result1.getString("PartNo"),result1.getString("Ledger"),result1.getString("Remarks")));
			}
		table.refresh();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
	
	

	public void searchReceiving2(ActionEvent e){
	// TODO Auto-generated method stub
		receiving.clear();
	try {
		ResultSet result1 ;
    	String query1 = "SELECT * from StockRecieving WHERE PartyName=?";
    	prep = conn1.prepareStatement(query1);
    	prep.setString(1, PartyName.getText());
    	
    	result1= prep.executeQuery();
		
		while(result1.next()){
			receiving.add(new Receiving(result1.getString("Date"),result1.getString("PartyName"),result1.getString("Particulars"),result1.getString("RecieptQty"),result1.getString("RecieptRate"),result1.getString("Status"),result1.getString("PartNo"),result1.getString("Ledger"),result1.getString("Remarks")));
		}
		
		table.refresh();
	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	}
}
	

