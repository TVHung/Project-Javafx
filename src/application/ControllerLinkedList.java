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

public class ControllerLinkedList implements Initializable{
	@FXML
	private AnchorPane pane;
	@FXML 
		private TextField inputLinkedList;
	@FXML 
		private Button addNode;
	@FXML 
		private Button deleteNode;
	@FXML 
	private Button findAndDelete;
	@FXML 
		private Button back;
	
	public void addNode(ActionEvent event) {
		
	}
	
	public void deleteNode(ActionEvent event) {
		
	}
	
	public void findAndDelete(ActionEvent event) {
		
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
