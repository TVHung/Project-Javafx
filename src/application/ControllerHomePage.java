package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ControllerHomePage implements Initializable{
	@FXML
	private AnchorPane pane;

	public void stack() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("StackPage.fxml"));
		pane.getChildren().setAll(child);
	}
	
	public void linkedList() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("LinkedListPage.fxml"));
		pane.getChildren().setAll(child);
	}
	
	public void balanceTree() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("BalanceTreePage.fxml"));
		pane.getChildren().setAll(child);
	}

	public void about() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("AboutPage.fxml"));
		pane.getChildren().setAll(child);
	}
	
	public void exit(ActionEvent event){
		Platform.exit();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
