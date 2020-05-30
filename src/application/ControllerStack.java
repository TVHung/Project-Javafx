package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControllerStack implements Initializable{
	@FXML
	private AnchorPane pane;
	@FXML 
		private TextField inputStack;
	@FXML 
		private Button pop;
	@FXML 
		private Button push;
	@FXML 
		private Button back;
	@FXML 
	private Button no1,no2,no3,no4,no5,no6,no7,no8,no9,no10,no11,no12;
	Stack s = new Stack();
	
	public void setText(Integer num,String str) {
		if(num==1) {
			no1.setText(str);
		}
		if(num==2) {
			no2.setText(str);
		}
		if(num==3) {
			no3.setText(str);
		}
		if(num==4) {
			no4.setText(str);
		}
		if(num==5) {
			no5.setText(str);
		}
		if(num==6) {
			no6.setText(str);
		}
		if(num==7) {
			no7.setText(str);
		}
		if(num==8) {
			no8.setText(str);
		}
		if(num==9) {
			no9.setText(str);
		}
		if(num==10) {
			no10.setText(str);
		}
		if(num==11) {
			no11.setText(str);
		}
		if(num==12) {
			no12.setText(str);
		}
	}	
	
	public void clearText(Integer num) {
		if(num==1) {
			no1.setText("");
		}
		if(num==2) {
			no2.setText("");
		}
		if(num==3) {
			no3.setText("");
		}
		if(num==4) {
			no4.setText("");
		}
		if(num==5) {
			no5.setText("");
		}
		if(num==6) {
			no6.setText("");
		}
		if(num==7) {
			no7.setText("");
		}
		if(num==8) {
			no8.setText("");
		}
		if(num==9) {
			no9.setText("");
		}
		if(num==10) {
			no10.setText("");
		}
		if(num==11) {
			no11.setText("");
		}
		if(num==12) {
			no12.setText("");
		}
	}	
	
	public void push(ActionEvent event) {
		String str = inputStack.getText();
		s.push(str);	
		setText(s.size(),str);
		inputStack.clear();
	}
	
	public void pop(ActionEvent event) {
		s.pop();
		int i, dem=0;
		for(i=0; i<s.size(); i++) {
			if(s.stack.get(i) == "" ) {
				dem++;
			}
		}
		clearText(dem);
	}
	
	public void back() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		pane.getChildren().setAll(child);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
