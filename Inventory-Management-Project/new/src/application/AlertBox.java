package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	boolean answer;
	public boolean display(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Alert Box");
		//window.setIconified(true);
		window.setOnCloseRequest(e -> {
			answer = false;
			window.close();
			});
		
		AnchorPane a = new AnchorPane() ;
		a.setPrefWidth(593.0);
		a.setPrefHeight(144.0);
		
		ImageView imageview = new ImageView(getClass().getResource("/application/alert.png").toExternalForm());
		//Image image = new Image ("alert.png");
		//imageview.setImage(image);
		imageview.setFitHeight(48.0);
		imageview.setFitWidth(56.0);
	    imageview.setLayoutX(57.0);
	    imageview.setLayoutY(23.0);
	    imageview.setPickOnBounds(true);
	    imageview.setPreserveRatio(true);
	    
	    Label label = new Label();
	    label.setText("Are you sure you want to delete the entry?");
	    label.setTextAlignment(TextAlignment.CENTER);
	    label.setLayoutX(144.0);
	    label.setLayoutY(23.0);
	    label.setPrefHeight(37.0);
	    label.setPrefWidth(394.0);
	    label.setFont(new Font(20.0));
	    
	    Button btn1 = new Button ();
	    btn1.setText("Yes");
	    btn1.setLayoutX(216.0);
	    btn1.setLayoutY(86.0);
	    btn1.setMnemonicParsing(false);
	    btn1.setPrefHeight(31.0);
	    btn1.setPrefWidth(60.0);
	    btn1.setOnAction(e -> {
	    	answer = true;
	    	window.close();
	    	});
	    
	    Button btn2 = new Button ();
	    btn2.setText("No");
	    btn2.setLayoutX(320.0);
	    btn2.setLayoutY(86.0);
	    btn2.setMnemonicParsing(false);
	    btn2.setPrefHeight(31.0);
	    btn2.setPrefWidth(60.0);
	    btn2.setOnAction(e -> {
	    	answer = false;
	    	window.close();}
	    	);
	    
	    a.getChildren().addAll(label, imageview, btn1, btn2);
	    
	    Scene scene = new Scene(a);
	    window.setScene(scene);
	    window.showAndWait();
	    
	    return answer;
	}

}
