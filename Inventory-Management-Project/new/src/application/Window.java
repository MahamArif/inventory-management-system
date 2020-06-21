package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Window {
	boolean answer;
	
	public boolean display(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Admin Confirmation");
		//window.setIconified(true);
		window.setOnCloseRequest(e -> {
			answer = false;
			window.close();
			});
		
		AnchorPane a = new AnchorPane() ;
		a.setPrefWidth(500.0);
		a.setPrefHeight(450.0);
		
		 Label label1 = new Label();
		 label1.setText("User Name :");
		 label1.setTextAlignment(TextAlignment.LEFT);
		 label1.setLayoutX(33.0);
		 label1.setLayoutY(107.0);
		 label1.setPrefHeight(32.0);
		 label1.setPrefWidth(123.0);
		 label1.setFont(new Font(22.0));
		 
		 Label label2 = new Label("Password :");
		 label2.setLayoutX(33.0);
		 label2.setLayoutY(200.0);
		 label2.setFont(new Font(22.0));
		 
		 TextField user = new TextField();
		 user.setLayoutX(185.0);
		 user.setLayoutY(99.0);
		 user.setPrefHeight(48.0);
		 user.setPrefWidth(275.0);
		 user.setFont(new Font(22.0));
		 
		 PasswordField pass = new PasswordField();
		 pass.setLayoutX(186.0);
		 pass.setLayoutY(184.0);
		 pass.setFont(new Font(22.0));
		 
		 Label label3 = new Label();
		 label3.setTextAlignment(TextAlignment.LEFT);
		 label3.setLayoutX(34.0);
		 label3.setLayoutY(278.0);
		 label3.setPrefWidth(327.0);
		 label3.setFont(new Font(17.0));
		 label3.setTextFill(Paint.valueOf("#e40a0a"));
		 
		 Button btn = new Button ();
		 btn.setText("Login");
		 btn.setTextAlignment(TextAlignment.CENTER);
		 btn.setFont(new Font(21.0));
		 btn.setLayoutX(197.0);
		 btn.setLayoutY(337.0);
		 btn.setMnemonicParsing(false);
		 btn.setPrefHeight(44.0);
		 btn.setPrefWidth(107.0);
		 btn.setOnAction(e -> {
			 
			 if(!user.getText().equals("") && !pass.getText().equals("") ){
					if(user.getText().equals("user1") && pass.getText().equals("pass1")){
					
						answer = true;
						window.close();
					}
					else{
						label3.setText("Username/Password is incorrect.");
						user.clear();
						pass.clear();
					}
				}
				else{
					if(user.getText().equals("")){
						user.setPromptText("Please enter a username!");
					}
					if(pass.getText().equals("")){
						pass.setPromptText("Please enter a password!");
					}
					label3.setText("");
					
				}
			 
		    	
		    	});
		 
		 a.getChildren().addAll(label1, label2, label3, user, pass,  btn);
		
		 Scene scene = new Scene(a);
		 window.setScene(scene);
		 window.showAndWait();
		
		return answer;
		
	}

}
