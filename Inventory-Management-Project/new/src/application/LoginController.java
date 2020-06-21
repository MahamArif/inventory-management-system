package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable, ControlledScreen{
	
	
    ScreensController myController;
	

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;}
	
	
	
	@FXML TextField username;
	@FXML PasswordField password;
	@FXML Label label;
	LoginModel login = new LoginModel();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
	}
	
	public void Login(ActionEvent e){
		String user = username.getText();
		String pass = password.getText();
		if(!user.equals("") && !pass.equals("") ){
			if(user.equals("sudais") && pass.equals("sudais")){
				password.clear();
				myController.setScreen(Main.screen2ID);
			}
			else{
				label.setText("Username/Password is incorrect.");
				username.clear();
				password.clear();
			}
		}
		else{
			if(user.equals("")){
				username.setPromptText("Please enter a username!");
			}
			if(pass.equals("")){
				password.setPromptText("Please enter a password!");
			}
			label.setText("");
			
		}
		
	}
	
	public void Exit(ActionEvent e){
		System.exit(0);
	}

}
