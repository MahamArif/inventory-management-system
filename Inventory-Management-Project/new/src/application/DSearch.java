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

public class DSearch implements Initializable {
	private LoginModel model1 = new LoginModel();
	private Connection conn1 = model1.connection;
	private PreparedStatement prep;
	
	@FXML
	private DatePicker datepicker;
	@FXML
	private TextField PartyName;
	@FXML
	private TableView<DeliveryChallan> table1;
	@FXML
	private TableColumn<DeliveryChallan, Date> dateColumn;
	@FXML
	private TableColumn<DeliveryChallan, Integer> serialNoColumn;
	@FXML
	private TableColumn<DeliveryChallan, String> descriptionColumn;
	@FXML
	private TableColumn<DeliveryChallan, String> partNoColumn;
	@FXML
	private TableColumn<DeliveryChallan, Integer> qtyDemandColumn;
	@FXML
	private TableColumn<DeliveryChallan, Integer> qtyDeliveredColumn;
	@FXML
	private TableColumn<DeliveryChallan, Double> balanceColumn;
	@FXML
	private TableColumn<DeliveryChallan, String> PartyNameColumn;
	
	private ObservableList<DeliveryChallan> delivery= FXCollections.observableArrayList();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		dateColumn.setCellValueFactory(new PropertyValueFactory<DeliveryChallan,Date >("date"));
		serialNoColumn.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("serialNo"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, String>("description"));
		partNoColumn.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, String>("partNo"));
		PartyNameColumn.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, String>("partyName"));
		qtyDemandColumn.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("qtyDemand"));
		qtyDeliveredColumn.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("qtyDelivered"));
		balanceColumn.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Double>("balance"));
		table1.setItems(delivery);
	}
	
	
	public void searchDelivery(ActionEvent e){
		delivery.clear();
		try {
			ResultSet result1 ;
	    	String query1 = "SELECT * from DeliveryChallan WHERE Date=?";
	    	prep = conn1.prepareStatement(query1);
	    	prep.setString(1, datepicker.getValue().toString());
	    	
	    	result1= prep.executeQuery();
			
			while(result1.next()){
				delivery.add(new DeliveryChallan(result1.getString("SerialNo"),result1.getString("Date"),result1.getString("Name"),result1.getString("PartNo"),result1.getString("PartyName"),result1.getString("QtyDemanded"),result1.getString("QtyDelivered"),result1.getString("Balance")));			}
		table1.refresh();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
	
	

	public void searchDelivery2(ActionEvent e){
	// TODO Auto-generated method stub
		delivery.clear();
	try {
		ResultSet result1 ;
    	String query1 = "SELECT * from DeliveryChallan WHERE PartyName=?";
    	prep = conn1.prepareStatement(query1);
    	prep.setString(1, PartyName.getText());
    	
    	result1= prep.executeQuery();
		
		while(result1.next()){
			delivery.add(new DeliveryChallan(result1.getString("SerialNo"),result1.getString("Date"),result1.getString("Name"),result1.getString("PartNo"),result1.getString("PartyName"),result1.getString("QtyDemanded"),result1.getString("QtyDelivered"),result1.getString("Balance")));
		}
		
		table1.refresh();
	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	}
}
	

