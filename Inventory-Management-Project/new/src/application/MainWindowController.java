package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.sun.org.apache.xml.internal.serialize.Printer;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;

public class MainWindowController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	@Override
	public void setScreenParent(ScreensController screenPage) {
		
		myController = screenPage;
	}
	
	LoginModel model1 = new LoginModel();
	Connection conn1 = model1.connection;
	
	 // Stock Table
	static ObservableList<Stock> stock;
	@FXML
	private TableView<Stock> Stock;
	@FXML
	private TableColumn<Stock, Date> date;
	@FXML
	private TableColumn<Stock, String> particulars;
	@FXML
	private TableColumn<Stock, String> partno;
	@FXML
	private TableColumn<Stock, Double> receipt;
	@FXML
	private TableColumn<Stock, Double> qty1;
	@FXML
	private TableColumn<Stock, Double> rate1;
	@FXML
	private TableColumn<Stock, Double> amnt1;
	@FXML
	private TableColumn<Stock, Double> issued;
	@FXML
	private TableColumn<Stock, Double> qty2;
	@FXML
	private TableColumn<Stock, Double> rate2;
	@FXML
	private TableColumn<Stock, Double> amnt2;
	@FXML
	private TableColumn<Stock, Double> balance;
	@FXML
	private TableColumn<Stock, Double> qty3;
	@FXML
	private TableColumn<Stock, Double> rate3;
	@FXML
	private TableColumn<Stock, Double> amnt3;
	@FXML
	private TableColumn<Stock, String> remarks;
	
	
	// Stock Receiving
	static ObservableList<Receiving> receiving;
	@FXML
	private TableView<Receiving> Receiving;
	@FXML
	private TableColumn<Receiving, Date> date_rec;
	@FXML
	private TableColumn<Receiving, String> partyName;
	@FXML
	private TableColumn<Receiving, String> items;
	@FXML
	private TableColumn<Receiving, Double> receipt_rec;
	@FXML
	private TableColumn<Receiving, Integer> qty_rec;
	@FXML
	private TableColumn<Receiving, Double> rate_rec;
	@FXML
	private TableColumn<Receiving, Double> amnt_rec;
	@FXML
	private TableColumn<Receiving, String> status;
	@FXML
	private TableColumn<Receiving, Double> partno_rec;
	@FXML
	private TableColumn<Receiving, String> ledger;
	@FXML
	private TableColumn<Receiving, String> remarks_rec;
	
	// Delivery Challan
	
	static ObservableList<DeliveryChallan> deliveryChallan;
	@FXML
	private TableView<DeliveryChallan> Challan;
	@FXML
	private TableColumn<DeliveryChallan, Integer> serialNo;
	@FXML
	private TableColumn<DeliveryChallan, String> desc;
	@FXML
	private TableColumn<DeliveryChallan, String> party;
	@FXML
	private TableColumn<DeliveryChallan, String> partNo;
	@FXML
	private TableColumn<DeliveryChallan, Integer> qtyDem;
	@FXML
	private TableColumn<DeliveryChallan, Integer> qtyDel;
	@FXML
	private TableColumn<DeliveryChallan, Double> bal;
	@FXML
	private TableColumn<DeliveryChallan,Date> date_c;
	
	
	//Invoice Record
	
	static ObservableList<InvoiceRecord> Invoice_Record;
	@FXML
	private TableView<InvoiceRecord> InvoiceRecord1;
	@FXML
	private TableColumn<InvoiceRecord, String> serialNo_inr;
	@FXML
	private TableColumn<InvoiceRecord, String> desc_inr;
	@FXML
	private TableColumn<InvoiceRecord, String> party_inr;
	@FXML
	private TableColumn<InvoiceRecord, String> partNo_inr;
	@FXML
	private TableColumn<InvoiceRecord, String> qtyDem_inr;
	@FXML
	private TableColumn<InvoiceRecord, String> qtyDel_inr;
	@FXML
	private TableColumn<InvoiceRecord, String> rate_inr;
	@FXML
	private TableColumn<InvoiceRecord, String> amount_inr;
	@FXML
	private TableColumn<InvoiceRecord,String> date_inr;
	
	//Invoice
	
	private static ObservableList<InvoiceSlip> invoice = FXCollections.observableArrayList();
	@FXML
	private TableView<InvoiceSlip> invoiceTable;
	@FXML
	private TableColumn<InvoiceSlip,String> description_in;
	@FXML
	private TableColumn<InvoiceSlip,String> part_in;
	@FXML
	private TableColumn<InvoiceSlip,String> qtyDemand_in;
	@FXML
	private TableColumn<InvoiceSlip,String> qtyDelivered_in;
	@FXML
	private TableColumn<InvoiceSlip,String> rate_in;
	@FXML
	private TableColumn<InvoiceSlip,String> amount_in;
	@FXML
	private TableColumn<InvoiceSlip, String> party_in;
	@FXML
	private TextField serial_in;
	@FXML
	private TextField invoice_date;
	@FXML
	private TextField rate;
	@FXML
	private AnchorPane Invoice_Print;
	@FXML
	private Button gen_inv_btn;
	@FXML
	private Button clr_inv_btn;
	@FXML
	private Button prt_inv_btn;
	
	private static String ser_in = "";
	private static String des_in = "";
	private static String pNo = "";
	private static String demand = "";
	private static String deliver = "";
	private static String price_in = "";
	private static String amnt_in = "";
	private static String Party_in = "";
	
	
	// Quotation
	
	
	private ObservableList<Quotation> quotation = FXCollections.observableArrayList();
	@FXML
	private TableView<Quotation> quote;
	
	@FXML
	private TableColumn<Quotation,String> description_q;
	@FXML
	private TableColumn<Quotation,String> part_qu;
	@FXML
	private TableColumn<Quotation,String> quantity_q;
	@FXML
	private TableColumn<Quotation,String> unit_price_q;
	@FXML
	private TableColumn<Quotation,String> amount_q;
	@FXML
	private TextField serial;
	@FXML
	private TextField quote_date;
	@FXML
	private TextField quote_desc;
	@FXML
	private TextField quote_part;
	@FXML
	private TextField quote_qty;
	@FXML
	private TextField quote_price;
	@FXML
	private TextField quote_partyname;
	
	
	@FXML
	private AnchorPane Hbox;
	@FXML
	private AnchorPane Quote_Print;
	
	//ReportGeneration
	
	@FXML
	private DatePicker From;
	@FXML
	private DatePicker To;

	@FXML 
	private TextField userField;
	@FXML
	private PasswordField passField;
	@FXML
	private Label loginLabel;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Stock
		
		date.setCellValueFactory(new PropertyValueFactory<Stock, Date>("date"));
		particulars.setCellValueFactory(new PropertyValueFactory<Stock, String>("particulars"));
		partno.setCellValueFactory(new PropertyValueFactory<Stock, String>("partNo"));
		receipt.setCellValueFactory(new PropertyValueFactory<Stock, Double>("receipt"));
		qty1.setCellValueFactory(new PropertyValueFactory<Stock, Double>("qtyReceipt"));
		rate1.setCellValueFactory(new PropertyValueFactory<Stock, Double>("rateReceipt"));
		amnt1.setCellValueFactory(new PropertyValueFactory<Stock, Double>("amountReceipt"));
		issued.setCellValueFactory(new PropertyValueFactory<Stock, Double>("issued"));
		qty2.setCellValueFactory(new PropertyValueFactory<Stock, Double>("qtyIssued"));
		rate2.setCellValueFactory(new PropertyValueFactory<Stock, Double>("rateIssued"));
		amnt2.setCellValueFactory(new PropertyValueFactory<Stock, Double>("amountIssued"));
		balance.setCellValueFactory(new PropertyValueFactory<Stock, Double>("balance"));
		qty3.setCellValueFactory(new PropertyValueFactory<Stock, Double>("qtyBalance"));
		rate3.setCellValueFactory(new PropertyValueFactory<Stock, Double>("rateBalance"));
		amnt3.setCellValueFactory(new PropertyValueFactory<Stock, Double>("amountBalance"));
		remarks.setCellValueFactory(new PropertyValueFactory<Stock, String>("remarks"));
		Stock.setItems(getStock());
		
		//Receiving
		
		date_rec.setCellValueFactory(new PropertyValueFactory<Receiving, Date>("date"));
		partyName.setCellValueFactory(new PropertyValueFactory<Receiving, String>("partyName"));
		items.setCellValueFactory(new PropertyValueFactory<Receiving, String>("items"));
		receipt_rec.setCellValueFactory(new PropertyValueFactory<Receiving, Double>("receipt"));
		qty_rec.setCellValueFactory(new PropertyValueFactory<Receiving, Integer>("qty"));
		rate_rec.setCellValueFactory(new PropertyValueFactory<Receiving, Double>("rate"));
		amnt_rec.setCellValueFactory(new PropertyValueFactory<Receiving, Double>("amount"));
		status.setCellValueFactory(new PropertyValueFactory<Receiving, String>("status"));
		partno_rec.setCellValueFactory(new PropertyValueFactory<Receiving, Double>("partNo"));
		ledger.setCellValueFactory(new PropertyValueFactory<Receiving, String>("ledger"));
		remarks_rec.setCellValueFactory(new PropertyValueFactory<Receiving, String>("remarks"));
		Receiving.setItems(getReceiving());
		
		// Challan
		
		serialNo.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("serialNo"));
		desc.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, String>("description"));
		partNo.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, String>("partNo"));
		party.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, String>("partyName"));
		qtyDem.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("qtyDemand"));
		qtyDel.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Integer>("qtyDelivered"));
		bal.setCellValueFactory(new PropertyValueFactory<DeliveryChallan, Double>("balance"));
		date_c.setCellValueFactory(new PropertyValueFactory<DeliveryChallan,Date >("date"));
		Challan.setItems(getChallan());
		
		//Invoice Record
		serialNo_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord, String>("serial_inr"));
		desc_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord, String>("description_inr"));
		partNo_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord, String>("partNo_inr"));
		party_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord, String>("partyName_inr"));
		qtyDem_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord, String>("qtyDemand_inr"));
		qtyDel_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord, String>("qtyDelivered_inr"));
		rate_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord, String>("rate_inr"));
		amount_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord, String>("amount_inr"));
		date_inr.setCellValueFactory(new PropertyValueFactory<InvoiceRecord,String >("date_inr"));
		InvoiceRecord1.setItems(getInvoiceRecord());
		
		
		
		// Quotation
		
		//serialNO.setCellValueFactory(new PropertyValueFactory<Quotation, String>("serialNo"));
		description_q.setCellValueFactory(new PropertyValueFactory<Quotation, String>("description"));
		part_qu.setCellValueFactory(new PropertyValueFactory<Quotation, String>("partNo"));
		quantity_q.setCellValueFactory(new PropertyValueFactory<Quotation, String>("quantity"));
		unit_price_q.setCellValueFactory(new PropertyValueFactory<Quotation, String>("unit_price"));
		amount_q.setCellValueFactory(new PropertyValueFactory<Quotation, String>("amount"));
		quote.setItems(quotation);
		
		//Invoice
		
		//sNo_in.setCellValueFactory(new PropertyValueFactory<InvoiceSlip, String>("serial_invoice"));
		description_in.setCellValueFactory(new PropertyValueFactory<InvoiceSlip, String>("description"));
		part_in.setCellValueFactory(new PropertyValueFactory<InvoiceSlip, String>("partNo"));
		party_in.setCellValueFactory(new PropertyValueFactory<InvoiceSlip, String>("partyName"));
		qtyDemand_in.setCellValueFactory(new PropertyValueFactory<InvoiceSlip, String>("qtyDemand"));
		qtyDelivered_in.setCellValueFactory(new PropertyValueFactory<InvoiceSlip, String>("qtyDelivered"));
		rate_in.setCellValueFactory(new PropertyValueFactory<InvoiceSlip, String>("rate"));
		amount_in.setCellValueFactory(new PropertyValueFactory<InvoiceSlip, String>("amount"));
		invoiceTable.setItems(invoice);
		

	}
	
	/*private ObservableList<Quotation> getQuote(){
		quotation = FXCollections.observableArrayList(new Quotation("1","2","6","5","8","0"));
		return quotation;
	}*/
	
	private ObservableList<Stock> getStock() {
		// TODO Auto-generated method stub
		stock = FXCollections.observableArrayList();
		try {
			//PreparedStatement stat1 = conn1.prepareStatement("select * from stockledger");
			Statement stat = conn1.createStatement();
			ResultSet result1 = stat.executeQuery("select * from StockLedger");
			while(result1.next()){
				stock.add(new Stock(result1.getString("Date"),result1.getString("Particulars"),result1.getString("PartNo"),result1.getString("RecieptQty"),result1.getString("RecieptRate"),result1.getString("IssuedQty"),result1.getString("IssuedRate"),result1.getString("BalanceQty"),result1.getString("BalanceRate"),result1.getString("Remarks")));
			}
			stat.close();
			result1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(NullPointerException e2){
			
		}	
		
		return stock;
	}
	
	private ObservableList<Receiving> getReceiving() {
		// TODO Auto-generated method stub
		receiving = FXCollections.observableArrayList();
		Statement stat;
		try {
			stat = conn1.createStatement();
			ResultSet result1 = stat.executeQuery("select * from StockRecieving");
			while(result1.next()){
				receiving.add(new Receiving(result1.getString("Date"),result1.getString("PartyName"),result1.getString("Particulars"),result1.getString("RecieptQty"),result1.getString("RecieptRate"),result1.getString("Status"),result1.getString("PartNo"),result1.getString("Ledger"),result1.getString("Remarks")));
			}
			stat.close();
			result1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return receiving;
	}
	
	 private  ObservableList<DeliveryChallan> getChallan() {
		deliveryChallan = FXCollections.observableArrayList();
		Statement stat;
		try {
			stat = conn1.createStatement();
			ResultSet result1 = stat.executeQuery("select * from DeliveryChallan");
			while(result1.next()){
				deliveryChallan.add(new DeliveryChallan(result1.getString("SerialNo"),result1.getString("Date"),result1.getString("Name"),result1.getString("PartNo"),result1.getString("PartyName"),result1.getString("QtyDemanded"),result1.getString("QtyDelivered"),result1.getString("Balance")));
			}
			stat.close();
			result1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deliveryChallan;
	 }
		
		 private  ObservableList<InvoiceRecord> getInvoiceRecord() {
				Invoice_Record = FXCollections.observableArrayList();
				Statement stat;
				try {
					stat = conn1.createStatement();
					ResultSet result1 = stat.executeQuery("select * from Invoice");
					while(result1.next()){
						Invoice_Record.add(new InvoiceRecord(result1.getString("Sno"),result1.getString("Date"),result1.getString("Description"),result1.getString("PartNo"),result1.getString("PartyName"),result1.getString("QtyDemanded"),result1.getString("QtyDelivered"),result1.getString("Rate"),result1.getString("Amount")));
					}
					stat.close();
					result1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return Invoice_Record;
		
	
	 }
	//fetch for stock
	public static void fetch(String date,String part, String partno, String qtyRec, String rateRec, String qtyIssued, String rateIssued, String qtyBalance, String rateBalance, String remarks){
		stock.add(new Stock(date, part, partno, qtyRec, rateRec, qtyIssued, rateIssued, qtyBalance, rateBalance, remarks));
	}
	
	public void addRecieving(ActionEvent e){
		Stage primaryStage = new Stage();
		Pane root;
		FXMLLoader loader = new FXMLLoader();
		try {
			root = loader.load(getClass().getResource("/application/AddReceiving.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(NumberFormatException e1){
			
		}
		
	}
	
	
	
	public void addChallan(ActionEvent e1){
		Stage primaryStage = new Stage();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/DeliveryChallan.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		catch(NumberFormatException e5){
			
		}
		
	}
	
	
	public static void fetchChallan(String SNo, String Date,String Name, String partno,String partyname, String qtydem, String qtydel, String bal){
		deliveryChallan.add(new DeliveryChallan(SNo,Date,Name,partno,partyname,qtydem,qtydel,bal));
	

	}
	
	//public static void fetchInvoiceRecord(String SNo, String Date,String Name, String partyName,String  )
	
	public static void fetchReceiving(String date,String party, String item, String qtyRec, String rateRec, String status, String partNo, String ledger, String remarks){
		receiving.add(new Receiving(date, party,item,qtyRec,rateRec,status,partNo,ledger,remarks));
	}
	
	public void logOut(ActionEvent e1){
		myController.setScreen(Main.screen1ID);
	}
	
	public void refresh(ActionEvent e3){
		stock.clear();
		try {
			PreparedStatement preparedresult = conn1.prepareStatement("select * from StockLedger");
			ResultSet set = preparedresult.executeQuery();
			while(set.next()){
				stock.add(new Stock(set.getString("Date"),set.getString("Particulars"),set.getString("PartNo"),set.getString("RecieptQty"),set.getString("RecieptRate"),set.getString("IssuedQty"),set.getString("IssuedRate"),set.getString("BalanceQty"),set.getString("BalanceRate"),set.getString("Remarks")));
			}
			preparedresult.close();
			set.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stock.refresh();
		
	}
	
	private static Double v_rate;
	
	
	public void generateInvoice(ActionEvent e) throws FileNotFoundException, DocumentException {
        ObservableList<DeliveryChallan> productSelected;
        productSelected = Challan.getSelectionModel().getSelectedItems();
        DeliveryChallan record = productSelected.get(0);
        //ser_in = record.getSerialNo().toString();
		des_in = record.getDescription().toString();
		pNo = record.getPartNo().toString();
		demand = record.getQtyDemand().toString();
		deliver = record.getQtyDelivered().toString();
		price_in = rate.getText().toString();
		//setV_rate(Double.parseDouble(price_in));
		Party_in = record.getPartyName().toString();
		amnt_in = Double.toString(Double.parseDouble(price_in)*Integer.parseInt(deliver));
		
          InvoiceSlip inv = new InvoiceSlip (des_in, pNo,Party_in, demand, deliver, price_in, amnt_in);
          invoiceTable.getItems().add(inv);
          invoiceTable.refresh();
          
          InvoiceRecord inv1 = new InvoiceRecord(serial_in.getText(), invoice_date.getText(), des_in, pNo, Party_in, demand, deliver, price_in, amnt_in);
          InvoiceRecord1.getItems().add(inv1);
          InvoiceRecord1.refresh();
          
          
         PreparedStatement invoice;
  		try {
  			invoice = conn1.prepareStatement("insert into Invoice(Sno, Date, Description, PartNo, PartyName, QtyDemanded, QtyDelivered, Rate, Amount) values(?,?,?,?,?,?,?,?,?)");
  		invoice.setString(1, serial_in.getText());
  		invoice.setString(2, invoice_date.getText());
  		//invoice.setString(3, Double.toString(MainWindowController.getV_rate()));
  		//invoice.setString(4, Double.toString(MainWindowController.getV_rate() *Integer.parseInt(QtyDelivered.getText())));
  		invoice.setString(3, des_in);
  		invoice.setString(4, pNo);
  		invoice.setString(5, Party_in);
  		invoice.setString(6, demand);
  		invoice.setString(7, deliver);
  		invoice.setString(8, price_in);
  		invoice.setString(9, amnt_in);
  		invoice.execute();
  		invoice.close();
  		
  		
  		 
  		String var = Party_in;

  	// Listing 1. Instantiation of document object
  	Document document = new Document(PageSize.A4, 50, 50, 50, 50);

  	String start = "E:\\";
  	String end = ".pdf";
  	String filename= start+var+end;

  	// Listing 2. Creation of PdfWriter object
  	PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(filename));

  	document.open();




  	Paragraph heading = new Paragraph("Sudais Traders",
  			FontFactory.getFont(FontFactory.COURIER_BOLD, 28, Font.BOLD,
  					BaseColor.BLACK));
  	heading.setAlignment(Element.ALIGN_CENTER);
  	document.add(heading);

  	Paragraph tagline = new Paragraph("We deals in all kinds of Industrial/Generators parts and Compressor service kits",FontFactory.getFont(FontFactory.COURIER_BOLD, 10, Font.BOLD,
  			BaseColor.BLACK));
  	tagline.setAlignment(Element.ALIGN_CENTER);
  	document.add(tagline);

  	Paragraph address1 = new Paragraph("306,3rd Floor, Bhayani Shops, Offices & Medical Center Block-M, North Nazimabad Karachi, Karachi",FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK));
  	address1.setAlignment(Element.ALIGN_CENTER);
  	document.add(address1);

  	Paragraph address2 = new Paragraph("Tel: 03219251932 Email: sudaistraders@gmail.com",FontFactory.getFont(FontFactory.COURIER_BOLD, 10,BaseColor.BLACK));
  	address2.setAlignment(Element.ALIGN_CENTER);
  	document.add(address2);

  	Paragraph title = new Paragraph("INVOICE",FontFactory.getFont(FontFactory.COURIER_BOLD, 20, Font.BOLD,BaseColor.BLACK));
  	title.setAlignment(Element.ALIGN_CENTER);
  	document.add(title);


  	// Listing 6. Creation of table object
  	PdfPTable t = new PdfPTable(8);
  	//t.setWidthPercentage(288 / 5.23f);
  	t.setWidths(new int[]{3, 8, 4, 8, 5, 5, 5, 5});
  	t.setSpacingBefore(25);
  	t.setSpacingAfter(25);
  	PdfPCell c1 = new PdfPCell(new Phrase("S.No"));
  	t.addCell(c1);
  	PdfPCell c2 = new PdfPCell(new Phrase("Description"));
  	t.addCell(c2);
  	PdfPCell c3 = new PdfPCell(new Phrase("Part No"));
  	t.addCell(c3);
  	PdfPCell c4 = new PdfPCell(new Phrase("Party Name"));
  	t.addCell(c4);
  	PdfPCell c5 = new PdfPCell(new Phrase("Quantity Demanded"));
  	t.addCell(c5);
  	PdfPCell c6 = new PdfPCell(new Phrase("Quantity Delivered"));
  	t.addCell(c6);
  	PdfPCell c7 = new PdfPCell(new Phrase("Rate"));
  	t.addCell(c7);
  	PdfPCell c8 = new PdfPCell(new Phrase("Amount"));
  	t.addCell(c8);
  	t.addCell("001");
  	t.addCell(des_in);
  	t.addCell(pNo);
  	t.addCell(Party_in);
  	t.addCell(demand);
  	t.addCell(deliver);
  	t.addCell(price_in);
  	t.addCell(amnt_in);

  	document.add(t);


  	document.close();
  		
  		
  		} catch (SQLException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();}
          
	}
	
	

	public void print_invoice(ActionEvent e){
		print_scene(Invoice_Print, gen_inv_btn, prt_inv_btn);
	}
 
	public void print_scene(Node node, Node node1, Node node2){
		
		PrinterJob job = PrinterJob.createPrinterJob();
		final PageLayout pageLayout = job.getJobSettings().getPageLayout();
		final double scaleX = pageLayout.getPrintableWidth() / Invoice_Print.getWidth();
	    final double scaleY = pageLayout.getPrintableHeight() / Invoice_Print.getHeight();
	    final double scale = Math.min(scaleX, scaleY);
	    if (scale < 1.0) {
	        Invoice_Print.getTransforms().add(new Scale(scale, scale));
	    }
		
		if(job != null  && job.showPrintDialog(node.getScene().getWindow())){
			node1.setVisible(false);
			node2.setVisible(false);
			clr_inv_btn.setVisible(false);
			boolean success = job.printPage(node);
			if(success){
				job.endJob();
			}
			/*final double Y=Invoice_Print.getHeight();
			final double X=Invoice_Print.getWidth();
			final double scale_again = Math.min(X, Y);
			Invoice_Print.getTransforms().add(new Scale(scale_again, scale_again));*/
			
			node1.setVisible(true);
			node2.setVisible(true);
			clr_inv_btn.setVisible(true);
		}
	}
	
	
	public void search_Receiving(ActionEvent e){
		Stage primaryStage = new Stage();
		Pane root;
		FXMLLoader loader = new FXMLLoader();
		try {
			root = loader.load(getClass().getResource("/application/DateSearch.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		catch(NumberFormatException e5){
			
		}
	}
	
	public void Search_Delivery(ActionEvent e){
		Stage primaryStage = new Stage();
		Pane root;
		FXMLLoader loader = new FXMLLoader();
		try {
			root = loader.load(getClass().getResource("/application/DSearch.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		catch(NumberFormatException e5){
			
		}
	}
	
	public void search_Stock(ActionEvent e){
		Stage primaryStage = new Stage();
		Pane root;
		FXMLLoader loader = new FXMLLoader();
		try {
			root = loader.load(getClass().getResource("/application/search.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		catch(NumberFormatException e5){
			
		}
	}
	
	public void clearAll(ActionEvent e){
		quotation.clear();
		quote.refresh();
		serial.clear();
		quote_date.clear();
		quote_desc.clear();
		quote_part.clear();
		quote_qty.clear();
		quote_price.clear();
		quote_partyname.clear();
		
	}
	public void clearAllInvoice(ActionEvent e){
		invoice.clear();
		invoiceTable.refresh();
		serial_in.clear();
		invoice_date.clear();
	}
	
	public void addQuote(ActionEvent e) throws FileNotFoundException, DocumentException{
		Quotation q = new Quotation(quote_desc.getText(),quote_part.getText(),quote_qty.getText(),quote_price.getText());
		
		String var = quote_date.getText().toString();

	  	// Listing 1. Instantiation of document object
	  	Document document = new Document(PageSize.A4, 50, 50, 50, 50);

	  	String start = "E:\\";
	  	String end = ".pdf";
	  	String filename= start+var+end;

	  	// Listing 2. Creation of PdfWriter object
	  	PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(filename));

	  	document.open();




	  	Paragraph heading = new Paragraph("Sudais Traders",
	  			FontFactory.getFont(FontFactory.COURIER_BOLD, 28, Font.BOLD,
	  					BaseColor.BLACK));
	  	heading.setAlignment(Element.ALIGN_CENTER);
	  	document.add(heading);

	  	Paragraph tagline = new Paragraph("We deals in all kinds of Industrial/Generators parts and Compressor service kits",FontFactory.getFont(FontFactory.COURIER_BOLD, 10, Font.BOLD,
	  			BaseColor.BLACK));
	  	tagline.setAlignment(Element.ALIGN_CENTER);
	  	document.add(tagline);

	  	Paragraph address1 = new Paragraph("306,3rd Floor, Bhayani Shops, Offices & Medical Center Block-M, North Nazimabad Karachi, Karachi",FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK));
	  	address1.setAlignment(Element.ALIGN_CENTER);
	  	document.add(address1);

	  	Paragraph address2 = new Paragraph("Tel: 03219251932 Email: sudaistraders@gmail.com",FontFactory.getFont(FontFactory.COURIER_BOLD, 10,BaseColor.BLACK));
	  	address2.setAlignment(Element.ALIGN_CENTER);
	  	document.add(address2);

	  	Paragraph title = new Paragraph("QUOTATION",FontFactory.getFont(FontFactory.COURIER_BOLD, 20, Font.BOLD,BaseColor.BLACK));
	  	title.setAlignment(Element.ALIGN_CENTER);
	  	document.add(title);


	  	// Listing 6. Creation of table object
	  	PdfPTable t = new PdfPTable(4);
	  	//t.setWidthPercentage(288 / 5.23f);
	  	t.setWidths(new int[]{8, 8, 4, 8});
	  	t.setSpacingBefore(25);
	  	t.setSpacingAfter(25);
	  	PdfPCell c1 = new PdfPCell(new Phrase("quote_desc"));
	  	t.addCell(c1);
	  	PdfPCell c2 = new PdfPCell(new Phrase("quote_part"));
	  	t.addCell(c2);
	  	PdfPCell c3 = new PdfPCell(new Phrase("quote_qty"));
	  	t.addCell(c3);
	  	PdfPCell c4 = new PdfPCell(new Phrase("quote_price"));
	  	t.addCell(c4);
	  	/*PdfPCell c5 = new PdfPCell(new Phrase("Quantity Demanded"));
	  	t.addCell(c5);
	  	PdfPCell c6 = new PdfPCell(new Phrase("Quantity Delivered"));
	  	t.addCell(c6);
	  	PdfPCell c7 = new PdfPCell(new Phrase("Rate"));
	  	t.addCell(c7);
	  	PdfPCell c8 = new PdfPCell(new Phrase("Amount"));
	  	t.addCell(c8);*/
	  	while(!(quote.getItems().add(q))){
	  	t.addCell(quote_desc.getText().toString());
	  	t.addCell(quote_part.getText().toString());
	  	t.addCell(quote_qty.getText().toString());
	  	t.addCell(quote_price.getText().toString());
	  	quote.refresh();
	  	}
	  	/*t.addCell(demand);
	  	t.addCell(deliver);
	  	t.addCell(price_in);
	  	t.addCell(amnt_in);*/

	  	document.add(t);


	  	document.close();
		/*quote.getItems().add(q);
		quote.refresh();*/
	}
	
	
	public void print_Quotation(ActionEvent e){
		print(Quote_Print,Hbox);
	}
	
	public void print(Node node, Node node1){
		PrinterJob job = PrinterJob.createPrinterJob();
		final PageLayout pageLayout = job.getJobSettings().getPageLayout();
		final double scaleX = pageLayout.getPrintableWidth() / Quote_Print.getWidth();
	    final double scaleY = pageLayout.getPrintableHeight() / Quote_Print.getHeight();
	    final double scale = Math.min(scaleX, scaleY);
	    if (scale < 1.0) {
	        Quote_Print.getTransforms().add(new Scale(scale, scale));
		if(job != null  && job.showPrintDialog(node.getScene().getWindow())){
			node1.setVisible(false);
			boolean success = job.printPage(node);
			if(success){
				job.endJob();
			}
			node1.setVisible(true);
		}
	}

	}	
	public void generate_Report(ActionEvent e){
		Stage primaryStage = new Stage();
		Pane root;
		FXMLLoader loader = new FXMLLoader();
		try {
			root = loader.load(getClass().getResource("/application/report1.fxml").openStream());
			ReportController reportController = (ReportController)loader.getController();
			reportController.date_get(From.getValue().toString(), To.getValue().toString());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		catch(NumberFormatException e5){
			
		}
	}
	
	public void Login_Bank(ActionEvent e){
		String user = userField.getText();
		String pass = passField.getText();
		if(!user.equals("") && !pass.equals("") ){
			if(user.equals("abid") && pass.equals("candyyumyum")){
				
				Stage primaryStage = new Stage();
				Pane root;
				FXMLLoader loader = new FXMLLoader();
				try {
					root = loader.load(getClass().getResource("/application/BankRecord.fxml").openStream());
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (IOException e10) {
					// TODO Auto-generated catch block
					e10.printStackTrace();
				}
				catch(NumberFormatException e5){
					
				}
			}
			else{
				loginLabel.setText("Username/Password is incorrect.");
				userField.clear();
				passField.clear();
			}
		}
		else{
			if(user.equals("")){
				userField.setPromptText("Please enter a username!");
			}
			if(pass.equals("")){
				passField.setPromptText("Please enter a password!");
			}
			loginLabel.setText("");
			
		}
		
	}
	
	public void delete_quote(ActionEvent e){
        ObservableList<Quotation> productSelected, allProducts;
		
        allProducts = quote.getItems();
        productSelected = quote.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
	}
	
	public void delete_Rec(ActionEvent e){
		 Window win = new Window();
		 boolean result = win.display();
		 if(result){
			 AlertBox alertbox = new AlertBox();
			 boolean answer = alertbox.display();
			 if(answer){
				 ObservableList<Receiving> productSelected, allProducts;
				 allProducts = Receiving.getItems();
				 productSelected = Receiving.getSelectionModel().getSelectedItems();
				 Receiving rec_item = productSelected.get(0);
				 String item_part = rec_item.getPartNo();
				 String date = rec_item.getDate();
				 int quantity = rec_item.getQty();
				 Double rate = rec_item.getRate();
				 //System.out.println(Integer.toString(Receiving.getSelectionModel().getSelectedIndex()+1));
				 productSelected.forEach(allProducts :: remove);
				 try{
			        	PreparedStatement prep;
			        	prep = conn1.prepareStatement("DELETE FROM StockRecieving WHERE PartNo = ? AND Date = ?");
			        	prep.setString(1,item_part); 
			        	//Integer.toString(Receiving.getSelectionModel().getSelectedIndex()+1));
			        	prep.setString(2,date); 
			        	prep.execute();
			        	prep.close();
			        	
			        	prep = conn1.prepareStatement("VACUUM StockRecieving");
			        	prep.execute();
			        	prep.close();
			        	
			        	prep = conn1.prepareStatement("select * from StockLedger where PartNo = ?");
						prep.setString(1, item_part);
						ResultSet s1 = prep.executeQuery();
			        	
						Integer qty_result=0;
						while(s1.next()){
							qty_result = Integer.parseInt(s1.getString("RecieptQty"));
						}
						Integer item_result = qty_result - quantity;
						s1.close();
						prep.close();
						
						if(item_result>0)
						{
						PreparedStatement statement = conn1.prepareStatement("UPDATE StockLedger SET RecieptQty = ?, RecieptRate = ?, RecieptAmount = ? WHERE PartNo = ?");
						statement.setString(1, item_result.toString());
						statement.setString(2, rate.toString());
						statement.setString(3, Double.toString(rate * item_result));
						statement.setString(4, item_part);
						statement.execute();
						statement.close();
						}
						else{
							PreparedStatement statement = conn1.prepareStatement("DELETE FROM StockLedger WHERE PartNo = ?");
							statement.setString(1, item_part);
							statement.execute();
							statement.close();
						}
			        	
			        }catch(SQLException e2){
			        	
			        }
			 
		    }
		 }
	}
		 
		 public void delete_Del(ActionEvent e){
			 Window win = new Window();
			 boolean result = win.display();
			 if(result){
				 AlertBox alertbox = new AlertBox();
				 boolean answer = alertbox.display();
				 if(answer){
					 ObservableList<DeliveryChallan> productSelected, allProducts;
					 allProducts = Challan.getItems();
					 productSelected = Challan.getSelectionModel().getSelectedItems();
					// System.out.println(Integer.toString(product.getSelectionModel().getSelectedIndex()+1));
					 DeliveryChallan rec = productSelected.get(0);
					 productSelected.forEach(allProducts :: remove);
					 try{

				        	PreparedStatement prep;
				        	prep = conn1.prepareStatement("DELETE FROM DeliveryChallan WHERE SerialNo = ?");
				        	prep.setString(1, rec.getSerialNo().toString());
				        	prep.execute();
				        	prep.close();
				        	
				        	
				        }catch(SQLException e2){
				        	
				        }
				 }
			    }
		 }

		public static Double getV_rate() {
			return v_rate;
		}

		public void setV_rate(Double v_rate) {
			MainWindowController.v_rate = v_rate;
		}
		 
	}
	
	
	


