package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;

public class DeliveryChallanController implements Initializable {
	private LoginModel mod = new LoginModel();
	private Connection conn = mod.connection;
	private PreparedStatement stat;
	
	
	private ObservableList<DeliveryChallan> Challan = FXCollections.observableArrayList();
	@FXML
	private TableView<DeliveryChallan> challan;
	@FXML
	private TableColumn<DeliveryChallan,Integer> serialNo;
	@FXML
	private TableColumn<DeliveryChallan,String> description;
	@FXML
	private TableColumn<DeliveryChallan,String> partNo;
	@FXML
	private TableColumn<DeliveryChallan,Integer> quantityDemanded;
	@FXML
	private TableColumn<DeliveryChallan,Integer> quantityDelivered;
	@FXML
	private TableColumn<DeliveryChallan,Double> Balance;
	@FXML
	private TableColumn<DeliveryChallan,String> partyName;
	@FXML
	private TextField SNo;
	@FXML
	private TextField Description;
	@FXML
	private TextField PartyName;
	@FXML
	private TextField QtyDemanded;
	@FXML
	private TextField QtyDelivered;
	@FXML
	private TextField balance;
	@FXML
	private TextField PartNo;
	@FXML
	private DatePicker Date_c;
	@FXML
	private AnchorPane Challan_print;
	@FXML
	private Button add_btn;
	@FXML
	private Button clr_btn;
	@FXML
	private Button print_btn;
	
	
	
	
	public void saveChallan(ActionEvent e){
		
		DeliveryChallan c = new DeliveryChallan(SNo.getText(),new Date().toString(),Description.getText(),PartNo.getText(),PartyName.getText(),QtyDemanded.getText(),QtyDelivered.getText(),Balance.getText());
		challan.getItems().add(c);
		challan.refresh();
		if(!SNo.getText().equals("") && !Description.getText().equals("") && !QtyDemanded.getText().equals("") && !QtyDelivered.getText().equals("") && !Balance.getText().equals("") && !PartNo.getText().equals("")){
		try {
			
			stat = conn.prepareStatement("insert into DeliveryChallan(QtyDemanded,QtyDelivered,Balance,Name,PartNo,Date,PartyName) values(?,?,?,?,?,?,?)");
			stat.setString(1, QtyDemanded.getText());
			stat.setString(2, QtyDelivered.getText());
			stat.setString(3, Balance.getText());
			stat.setString(4, Description.getText());
			stat.setString(5,  PartNo.getText());
			stat.setString(6, Date_c.getValue().toString());
			stat.setString(7, PartyName.getText());
			stat.execute();
			
			MainWindowController.fetchChallan(SNo.getText(),new Date().toString(), Description.getText(), PartNo.getText(), PartyName.getText(), QtyDemanded.getText(), QtyDelivered.getText(), Balance.getText());
			stat.close();
			
			
		
		
		   // MainWindowController.fetchInvoice(SNo.getText(), Description.getText(), PartNo.getText(), QtyDemanded.getText(), QtyDelivered.getText(), rate.getText(), Double.toString(Double.parseDouble(rate.getText())*Integer.parseInt(QtyDelivered.getText())));
			
		    Integer rec_qty = 0;
			PreparedStatement prep_inv = conn.prepareStatement("select * from StockLedger where PartNo = ?");
			prep_inv.setString(1, PartNo.getText());
			ResultSet set = prep_inv.executeQuery();
			while(set.next()){
				rec_qty = Integer.parseInt(set.getString("RecieptQty"));
			}
			Integer balQty = rec_qty - Integer.parseInt(QtyDelivered.getText());
			if(balQty<0)
				balQty = 0;
			prep_inv.close();
			set.close();
			
			PreparedStatement inv_final = conn.prepareStatement("UPDATE StockLedger SET IssuedQty = ?, BalanceQty = ? WHERE PartNo = ?");
			inv_final.setString(1, QtyDelivered.getText());
			//inv_final.setString(2,  MainWindowController.getV_rate().toString());
			//inv_final.setString(3, Double.toString(MainWindowController.getV_rate()*Integer.parseInt(QtyDelivered.getText())));
			inv_final.setString(2, balQty.toString());
			inv_final.setString(3, PartNo.getText());
			inv_final.execute();
			inv_final.close();
			
			
			SNo.clear();
			Description.clear();
			PartNo.clear();
			QtyDemanded.clear();
			QtyDelivered.clear();
			//Balance.clear(); 
			PartyName.clear();
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		
		else{

		}
	}

	/*public void saveInvoice(ActionEvent e) 
	{
		
		PreparedStatement invoice;
		try {
			invoice = conn.prepareStatement("insert into Invoice(QtyDemanded,QtyDelivered,Name,PartNo,Date,PartyName) values(?,?,?,?,?,?)");
		invoice.setString(1, QtyDemanded.getText());
		invoice.setString(2, QtyDelivered.getText());
		//invoice.setString(3, Double.toString(MainWindowController.getV_rate()));
		//invoice.setString(4, Double.toString(MainWindowController.getV_rate() *Integer.parseInt(QtyDelivered.getText())));
		invoice.setString(3, Description.getText());
		invoice.setString(4, PartNo.getText());
		invoice.setString(5, Date_c.getValue().toString());
		invoice.setString(6, PartyName.getText());
		invoice.execute();
		invoice.close();
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();}
	}*/
	
	public void deleteChallan (ActionEvent e){
		ObservableList<DeliveryChallan> productSelected, allProducts;
        allProducts = challan.getItems();
        productSelected = challan.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		serialNo.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("serialNo"));
		description.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, String>("description"));
		partNo.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, String>("partNo"));
		quantityDemanded.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("qtyDemand"));
		quantityDelivered.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("qtyDelivered"));
		Balance.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Double>("Balance"));
		partyName.setCellValueFactory(new PropertyValueFactory<DeliveryChallan,String>("partyName"));
		challan.setItems(Challan);
		
		
	}

/*	public void add_C(ActionEvent e)
	{
		DeliveryChallan c = new DeliveryChallan(SNo.getText(),Description.getText(),PartNo.getText(),QtyDemanded.getText(),QtyDelivered.getText(),Balance.getText(),rate.getText());
		challan.getItems().add(c);
		challan.refresh();
		clearAll(e);
	}*/

	public void clearAll(ActionEvent e){
		Challan.clear();
		challan.refresh();
		SNo.clear();
		Description.clear();
		PartNo.clear();
		QtyDemanded.clear();
		QtyDelivered.clear();
		//Balance.clear();
		PartyName.clear();
	}
	
	public void print_challan(ActionEvent e){
		print_scene_challan(Challan_print);
	}
 
	public void print_scene_challan(Node node){
		double width = Challan_print.getPrefWidth();
		double height = Challan_print.getPrefHeight();
		PrinterJob job = PrinterJob.createPrinterJob();
		if(job != null  && job.showPrintDialog(node.getScene().getWindow())){
			final PageLayout pageLayout = job.getJobSettings().getPageLayout();
			final double scaleX = pageLayout.getPrintableWidth() / Challan_print.getWidth();
		    final double scaleY = pageLayout.getPrintableHeight() / Challan_print.getHeight();
		    final double scale = Math.min(scaleX, scaleY);
		    if (scale < 1.0) {
		    	Challan_print.getTransforms().add(new Scale(scale, scale));
			add_btn.setVisible(false);
			clr_btn.setVisible(false);
			print_btn.setVisible(false);
			SNo.setVisible(false);
			Description.setVisible(false);
			PartyName.setVisible(false);
			QtyDemanded.setVisible(false);
			QtyDelivered.setVisible(false);
			Balance.setVisible(false);
			PartNo.setVisible(false);
			boolean success = job.printPage(node);
			if(success){
				job.endJob();
			}
			Challan_print.setPrefHeight(675);
			Challan_print.setPrefWidth(901);
			//challan.setPrefHeight(height);
			add_btn.setVisible(true);
			clr_btn.setVisible(true);
			print_btn.setVisible(true);
			SNo.setVisible(true);
			Description.setVisible(true);
			PartyName.setVisible(true);
			QtyDemanded.setVisible(true);
			QtyDelivered.setVisible(true);
			Balance.setVisible(true);
			PartNo.setVisible(true);
		}

}
	}
}
