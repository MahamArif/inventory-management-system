package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class AddReceivingController implements Initializable{
	private LoginModel mod = new LoginModel();
	private Connection conn = mod.connection;
	private PreparedStatement stat;
	
	@FXML
	private Label checkRecieving;
	@FXML
	private TextField partyName;
	@FXML
	private TextField items;
	@FXML
	private TextField recQty;
	@FXML
	private TextField recRate;
	@FXML
	private TextField status;
	@FXML
	private TextField partNo;
	@FXML
	private TextField ledger;
	@FXML
	private TextField remarks;
	@FXML
	private DatePicker date_rec;
	
	
	public void saveReceiving(ActionEvent e){
		if(!date_rec.getValue().toString().equals("") && !partyName.getText().equals("") && !items.getText().equals("") && !recQty.getText().equals("") && !recRate.getText().equals("") && !status.getText().equals("") &&  !partNo.getText().equals("") && !ledger.getText().equals("") && !remarks.getText().equals("")){
		    checkRecieving.setText("");
			boolean answer;
		try {
			//this codes saves in first table
			
			stat = conn.prepareStatement("insert into stockrecieving values (?,?,?,?,?,?,?,?,?,?)");
			stat.setString(1, date_rec.getValue().toString());
			stat.setString(2, partyName.getText());
			stat.setString(3, items.getText());
			stat.setString(4, recQty.getText());
			stat.setString(5, recRate.getText());
			stat.setString(6, Double.toString(Double.parseDouble(recRate.getText()) * Integer.parseInt(recQty.getText())));
			stat.setString(7, status.getText());
			stat.setString(8, partNo.getText());
			stat.setString(9, ledger.getText());
			stat.setString(10, remarks.getText());
			stat.execute();
			
			MainWindowController.fetchReceiving(date_rec.getValue().toString(), partyName.getText(), items.getText(), recQty.getText(), recRate.getText(),status.getText(), partNo.getText(), ledger.getText(), remarks.getText());
			stat.close();
			
			
			//this is for the second table
			
			
			PreparedStatement stat1 = conn.prepareStatement("select * from StockLedger where PartNo = ?");
			stat1.setString(1, partNo.getText());
			ResultSet s1 = stat1.executeQuery();
			if(!s1.isBeforeFirst()){
				answer = false;
				stat1.close();
				s1.close();
			}
			else{
				answer = true;
			}	
			
			if(!answer){
				PreparedStatement state = conn.prepareStatement("insert into StockLedger values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				state.setString(1, date_rec.getValue().toString());
				state.setString(2, items.getText());
				state.setString(3, partNo.getText());
				state.setString(4, recQty.getText());
				state.setString(5, recRate.getText());
				state.setString(6, Double.toString(Double.parseDouble(recRate.getText()) * Integer.parseInt(recQty.getText())));
				state.setString(7, "0");
				state.setString(8, "0");
				state.setString(9, "0");
				state.setString(10, "0");
				state.setString(11, "0");
				state.setString(12, "0");
				state.setString(13, remarks.getText());
				state.execute();
				state.close();
				
				MainWindowController.fetch(date_rec.getValue().toString(),items.getText(), partNo.getText(), recQty.getText(), recRate.getText(), "0", "0", "0", "0", remarks.getText());
			}
			else{
		        Integer qty_result=0,qty_final;
				while(s1.next()){
					qty_result = Integer.parseInt(s1.getString("RecieptQty"));
				}
				qty_final = Integer.parseInt(recQty.getText());
				Integer result = qty_final+qty_result;
				s1.close();
				stat1.close();
				PreparedStatement statement = conn.prepareStatement("UPDATE StockLedger SET RecieptQty = ?, RecieptRate = ?, RecieptAmount = ? WHERE PartNo = ?");
				statement.setString(1, result.toString());
				statement.setString(2, recRate.getText());
				statement.setString(3, Double.toString(Double.parseDouble(recRate.getText()) * result));
				statement.setString(4, partNo.getText());
				statement.execute();
				statement.close();
			}
			
			partyName.clear();
			items.clear();
			recQty.clear();
			recRate.clear();
			status.clear();
			partNo.clear();
			ledger.clear();
			remarks.clear();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		else{
			checkRecieving.setText("Please fill all the fields!");
		}
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
