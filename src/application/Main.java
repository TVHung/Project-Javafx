package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import application.GlobalVar;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/view/Final.fxml"));
//			root.setPrefHeight(GlobalVar.WINDOW_HEIGHT);
//			root.setPrefWidth(GlobalVar.WINDOW_WIDTH);
			
			Scene scene = new Scene(root,GlobalVar.WINDOW_WIDTH,GlobalVar.WINDOW_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Final Project Team 7, Topic 2");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
