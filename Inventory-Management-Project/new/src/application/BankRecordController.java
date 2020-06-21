package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class BankRecordController implements Initializable {

	private LoginModel model = new LoginModel();
	private Connection conn = model.connection;

	
	private ObservableList<Bank> bankRecord;
	@FXML
	private TableView<Bank> bank;
	@FXML
	private TableColumn<Bank,String> date_rec;
	@FXML
	private TableColumn<Bank,String> invoiceNo_b;
	@FXML
	private TableColumn<Bank,String> chequeNo_b;
	@FXML
	private TableColumn<Bank,String> partyName_b;
	@FXML
	private TableColumn<Bank,String> totalAmount_b;
	@FXML
	private TableColumn<Bank,String> received_b;
	@FXML
	private TableColumn<Bank,String> remaining_b;
	
	@FXML
	private TextField bank_invoice;
	@FXML
	private TextField bank_cheque;
	@FXML
	private TextField bank_partyname;
	@FXML
	private TextField bank_amount;
	@FXML
	private TextField bank_received;
	
	@FXML
	private DatePicker dateBank;
	
	@FXML
	private AnchorPane Hbox;
	@FXML
	private AnchorPane Hbox1;
	public void clearAll(ActionEvent e){
		bankRecord.clear();
		bank.refresh();
		bank_invoice.clear();
		bank_cheque.clear();
		bank_partyname.clear();
		bank_amount.clear();
		bank_received.clear();
		
		try{
        	PreparedStatement prep = null;
        	prep = conn.prepareStatement("DELETE FROM BankRecord");
        	//prep.setString(1, record.getInvoiceNo());
        	prep.execute();
        	prep.close();
        	
        }catch(SQLException e2){
        	
        }
	}
	
	public void addBank(ActionEvent e){
		Bank b = new Bank(dateBank.getValue().toString(),bank_invoice.getText(),bank_cheque.getText(),bank_partyname.getText(),bank_amount.getText(),bank_received.getText());
		bank.getItems().add(b);
		bank.refresh();
		if(!dateBank.getValue().toString().equals("") && !bank_invoice.getText().equals("") && !bank_cheque.getText().equals("") && !bank_partyname.getText().equals("") && !bank_amount.getText().equals("") && !bank_received.getText().equals("") ){

		try {
			//this codes saves in first table
			PreparedStatement stat;
			stat = conn.prepareStatement("insert into BankRecord values (?,?,?,?,?,?,?)");
			stat.setString(1, dateBank.getValue().toString());
			stat.setString(2, bank_invoice.getText());
			stat.setString(3, bank_cheque.getText());
			stat.setString(4, bank_partyname.getText());
			stat.setString(5, bank_amount.getText());
			stat.setString(6, bank_received.getText());
			stat.setString(7, Double.toString(Double.parseDouble(bank_amount.getText())-Double.parseDouble(bank_received.getText())));
			stat.execute();
			stat.close();
			
			
			bank_invoice.clear();
			bank_cheque.clear();
			bank_partyname.clear();
			bank_amount.clear();
			bank_received.clear();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}
	
	public void deleteBank (ActionEvent e){
		
        
        
         Window win = new Window();
		 boolean result = win.display();
		 if(result){
			 AlertBox alertbox = new AlertBox();
			 boolean answer = alertbox.display();
			 if(answer){
				 ObservableList<Bank> productSelected, allProducts;
					
			        allProducts = bank.getItems();
			        productSelected = bank.getSelectionModel().getSelectedItems();
			 
			        //ArrayList<Bank> array = (ArrayList<Bank>)productSelected;
			        Bank record = productSelected.get(0);
			        String invoice_no = record.getInvoiceNo();
			        productSelected.forEach(allProducts::remove);
			        try{
			        	//PreparedStatement prep = null;
			        	PreparedStatement prep = conn.prepareStatement("DELETE FROM BankRecord WHERE InvoiceNo = ?");
			        	prep.setString(1, invoice_no);
			        	prep.execute();
			        	prep.close();
			        	
			        }catch(SQLException e2){
			        	
			        }
		    }
		 }
        
        
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//serialNO.setCellValueFactory(new PropertyValueFactory<Quotation, String>("serialNo"));
		date_rec.setCellValueFactory(new PropertyValueFactory<Bank,String>("date"));
		invoiceNo_b.setCellValueFactory(new PropertyValueFactory<Bank, String>("invoiceNo"));
		chequeNo_b.setCellValueFactory(new PropertyValueFactory<Bank, String>("chequeNo"));
		partyName_b.setCellValueFactory(new PropertyValueFactory<Bank, String>("partyName"));
		totalAmount_b.setCellValueFactory(new PropertyValueFactory<Bank, String>("totalAmount"));
		received_b.setCellValueFactory(new PropertyValueFactory<Bank, String>("received"));
		remaining_b.setCellValueFactory(new PropertyValueFactory<Bank, String>("remaining"));
		bank.setItems(getBankRecord());
	}
	
	private ObservableList<Bank> getBankRecord() {
		// TODO Auto-generated method stub
		bankRecord = FXCollections.observableArrayList();
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet result1 = stat.executeQuery("select * from BankRecord");
			while(result1.next()){
				bankRecord.add(new Bank(result1.getString("Date"),result1.getString("InvoiceNo"),result1.getString("Cheque"),result1.getString("PartyName"),result1.getString("TotalAmount"),result1.getString("AmountReceived")));
			}
			stat.close();
			result1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bankRecord;
	}
	
}
