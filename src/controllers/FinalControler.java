package controllers;

import model.AVLTree;
import model.Datatype;
import model.LinkedList;
import model.Node;
import model.Stack;

import java.awt.Button;
import java.io.IOException;
import application.GlobalVar;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FinalControler {
	@FXML
	AnchorPane displayPane;
	@FXML
	TextField InputField;
	
	Node newnode;
	double offset,fixset;
	int indi;
	int value;
	Datatype dt;
	LinkedList ll = new LinkedList();
	AVLTree tree = new AVLTree();
	Stack stk = new Stack();
	
	private void setFixset(double i) { 
		this.fixset = displayPane.getHeight()*i-GlobalVar.LABEL_HEIGHT/2; 
	}
	private void setOffset(double i){
		this.offset=displayPane.getWidth()*i;
	}
	public void initLL() throws InterruptedException {
		dt = new LinkedList();
		this.indi = 1;
		setFixset(0.5);
		displayPane.getChildren().clear();
//		stk.nodelist.clear();
//		ll.nodelist.clear();
//		tree.nodelist.clear();
		dt.nodelist.clear();
		System.out.println("LList");
		for (int i=0;i<3;i++) {
			newnode = new Node();
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			dt.AddNode(newnode);
			offset = 25+(dt.nodelist.size()-1)*(GlobalVar.LABEL_WIDTH+25);
			dt.AddAni(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
//			Thread.sleep(500);
		}
		
	}
	
	public void initStk() {
		dt = new Stack();
		this.indi = 2;
		System.out.println("Stack");
		displayPane.getChildren().clear();
//		stk.nodelist.clear();
//		ll.nodelist.clear();
//		tree.nodelist.clear();
		dt.nodelist.clear();
	}

	public void initBtree() {
		dt = new AVLTree();
    	this.indi = 3;
		setFixset(0.1);
		displayPane.getChildren().clear();
//		stk.nodelist.clear();
//		ll.nodelist.clear();
//		tree.nodelist.clear();
		dt.nodelist.clear();
		dt.isEmpty();
		System.out.println("Btree");
		setOffset(0.5);
			newnode = new Node();
			newnode.setData(10);
			((AVLTree)dt).insert(newnode.getData());
			
			newnode.setData(20);
			((AVLTree)dt).insert(newnode.getData());
			
			newnode.setData(30);
			((AVLTree)dt).insert(newnode.getData());
			
			((AVLTree)dt).preorder();
			((AVLTree)dt).drawNotAni(dt.nodelist,offset,fixset);
			for(int i=0;i<dt.nodelist.size();i++) {
				displayPane.getChildren().add(dt.nodelist.get(i).getLabel());
				if(dt.nodelist.get(i).getArrowl()!=null&&dt.nodelist.get(i).getLeft()!=null)
				displayPane.getChildren().add(dt.nodelist.get(i).getArrowl());
				if(dt.nodelist.get(i).getArrowr()!=null&&dt.nodelist.get(i).getRight()!=null)
				displayPane.getChildren().add(dt.nodelist.get(i).getArrowr());
			}

		
	}
	
	public static boolean isNumeric(String str) { //kiểm tra đầu vào có phải là số hay không
		return str.matches("-?\\d+(\\.\\d+)?"); 
	}
	
	public void insert() {
		System.out.println("inserting ...");
		String checkInput = InputField.getText();
		if(indi==3){
			insertBtree();
			return;
		}
		try {
			if(checkInput.equals("")) {
				Alert alert= new Alert(Alert.AlertType.INFORMATION,"You have not entered anything! Please input a number.",ButtonType.OK);
				alert.showAndWait()	
				.filter(response -> response == ButtonType.OK)
				.ifPresent(response->alert.close());
			}else if(isNumeric(checkInput) == false){
				Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must enter a number! Please enter again.",ButtonType.OK);
				alert.showAndWait()	
				.filter(response -> response == ButtonType.OK)
				.ifPresent(response->alert.close());
			}else{
				int d = Integer.parseInt(InputField.getText());
				newnode = new Node(d);
			}
			InputField.clear();
		} catch (NumberFormatException | NullPointerException nfe) {
			newnode = new Node();
		}
		if (dt.isFull()) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Stack is full",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else if(checkInput.equals("") == false) {
			dt.AddNode(newnode);
//			newnode.label.setLayoutX(25+(stk.nodelist.size()-1)*(25+GlobalVar.LABEL_WIDTH));
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
			offset= displayPane.getHeight()-(dt.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
			dt.AddAni(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
		}
	}
	
//	private void insertLL() {
//		System.out.println("insertLL");
//		try {
//			int d = Integer.parseInt(InputField.getText());
//			newnode = new Node(d);
//			InputField.clear();
//		} catch (NumberFormatException | NullPointerException nfe) {
//			newnode = new Node();
//		}
//		
//		displayPane.getChildren().add(newnode.getLabel());
//		ll.AddNode(newnode);
//		offset = 25+(ll.nodelist.size()-1)*(GlobalVar.LABEL_WIDTH+25);
//		ll.AddAni(newnode, offset, fixset).play();
//	}
	
//	private void insertStk() {
//		try {
//			int d = Integer.parseInt(InputField.getText());
//			newnode = new Node(d);
//			InputField.clear();
//		} catch (NumberFormatException | NullPointerException nfe) {
//			newnode = new Node();
//		}
//		if (stk.isFull()) {
//			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Stack is full",ButtonType.OK);
//			alert.showAndWait()	
//			.filter(response -> response == ButtonType.OK)
//			.ifPresent(response->alert.close());
//		}
//		else {
//			stk.push(newnode);
////			newnode.label.setLayoutX(25+(stk.nodelist.size()-1)*(25+GlobalVar.LABEL_WIDTH));
//			newnode.setLabel(newnode.form());
//			displayPane.getChildren().add(newnode.getLabel());
//			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
//			offset= displayPane.getHeight()-(stk.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
//			stk.AddAni(newnode, fixset, offset).play();
//		}
//	}

	private void insertBtree() {
		System.out.println("insertBtree");
		try {
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			InputField.clear();
		} catch (NumberFormatException | NullPointerException nfe) {
			
		}
		displayPane.getChildren().clear();
		((AVLTree)dt).insert(newnode.getData());
		((AVLTree)dt).nodelist.clear();
		((AVLTree)dt).preorder();
		((AVLTree)dt).drawNotAni(dt.nodelist,offset,fixset);
		for(int i=0;i<dt.nodelist.size();i++) {
			displayPane.getChildren().add(dt.nodelist.get(i).getLabel());
			if(dt.nodelist.get(i).getArrowl()!=null&&dt.nodelist.get(i).getLeft()!=null)
			displayPane.getChildren().add(dt.nodelist.get(i).getArrowl());
			if(dt.nodelist.get(i).getArrowr()!=null&&dt.nodelist.get(i).getRight()!=null)
			displayPane.getChildren().add(dt.nodelist.get(i).getArrowr());
		}

		
	}
	
	public void delete() throws InterruptedException {
		switch (this.indi) {
		case 1:
			deleteLL();
			break;
		case 2:
			deleteStk();
			break;
		case 3:
			deleteBtree();
			break;
		}
//		if(indi==3){
//			deleteBtree();
//			return;
//		}
//		try {
//			int d = Integer.parseInt(InputField.getText());
//			InputField.clear();
//			newnode = dt.findNode(d);
//			
//		} catch (NumberFormatException | NullPointerException nfe) {
//			newnode = dt.nodelist.getFirst();
//		}
//		if (newnode == null) {
//			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Error data not in list",ButtonType.OK);
//			alert.showAndWait()
//			.filter(response -> response == ButtonType.OK)
//			.ifPresent(response->alert.close());
//		}
//		else {
//			System.out.println("Delele");
//			dt.removeNode(newnode);
//			dt.DelAni(newnode).setOnFinished(finishHim ->{
//					displayPane.getChildren().remove(newnode.getLabel());
//				}
//			);
//			dt.DelAni(newnode).play();
//			//this.reArrangeLL();
//		}
	}
	
	private void deleteLL() throws InterruptedException {
		try {
			int d = Integer.parseInt(InputField.getText());
			InputField.clear();
			newnode = dt.findNode(d);
			
		} catch (NumberFormatException | NullPointerException nfe) {
			newnode = dt.nodelist.getFirst();
		}
		if (newnode == null) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Error data not in list",ButtonType.OK);
			alert.showAndWait()
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}
		else {
			System.out.println("Delele");
			dt.removeNode(newnode);
			dt.DelAni(newnode).setOnFinished(finishHim ->{
					displayPane.getChildren().remove(newnode.getLabel());
				}
			);
			dt.DelAni(newnode).play();
			this.reArrangeLL();
		}
		
		
		
		
//		try {
//			int d = Integer.parseInt(InputField.getText());
//			InputField.clear();
//			newnode = ll.findNode(d);
//			
//			
//		} catch (NumberFormatException | NullPointerException nfe) {
//			newnode = ll.nodelist.getFirst();
//		}
//		if (newnode == null) {
//			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Error data not in list",ButtonType.OK);
//			alert.showAndWait()
//			.filter(response -> response == ButtonType.OK)
//			.ifPresent(response->alert.close());
//		}
//		else {
//			ll.removeNode(newnode);
//			ll.DelAni(newnode).setOnFinished(finishHim ->{
//				displayPane.getChildren().remove(newnode.getLabel());
//				}
//			);
//			ll.DelAni(newnode).play();
//			this.reArrangeLL();
//		}

	}
	
	private void deleteStk() {
		try {
			int d = Integer.parseInt(InputField.getText());
			InputField.clear();
			newnode = dt.findNode(d);
			
		} catch (NumberFormatException | NullPointerException nfe) {
			newnode = dt.nodelist.getFirst();
		}
		if (newnode == null) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Error data not in list",ButtonType.OK);
			alert.showAndWait()
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}
		else {
			System.out.println("Delele");
			dt.removeNode(newnode);
			dt.DelAni(newnode).setOnFinished(finishHim ->{
					displayPane.getChildren().remove(newnode.getLabel());
				}
			);
			dt.DelAni(newnode).play();
		}
		
		
		
//	if(!stk.isEmpty()){
//		displayPane.getChildren().remove(stk.pop().getLabel());
//	}else {
//			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Stack is empty",ButtonType.OK);
//			alert.showAndWait()
//			.filter(response -> response == ButtonType.OK)
//			.ifPresent(response->alert.close());
//		}
	}
	

	private void deleteBtree() {
		System.out.println("deleteNode");
		try {
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			InputField.clear();
		} catch (NumberFormatException | NullPointerException nfe) {
			
		}
		displayPane.getChildren().clear();
		((AVLTree)dt).delete(newnode.getData());
		dt.nodelist.clear();
		((AVLTree)dt).preorder();
		((AVLTree)dt).drawNotAni(dt.nodelist,offset,fixset);
		System.out.println(dt.nodelist.size());
		for(int i=0;i<dt.nodelist.size();i++) {
			displayPane.getChildren().add(dt.nodelist.get(i).getLabel());
			if(dt.nodelist.get(i).getArrowl()!=null&&dt.nodelist.get(i).getLeft()!=null)
			displayPane.getChildren().add(dt.nodelist.get(i).getArrowl());
			if(dt.nodelist.get(i).getArrowr()!=null&&dt.nodelist.get(i).getRight()!=null)
			displayPane.getChildren().add(dt.nodelist.get(i).getArrowr());
		}

	}
	
	public void reArrangeLL() {
		Node node;
		for (int i=0;i<dt.nodelist.size();i++) {
			node = dt.nodelist.get(i);
			offset = 25+(i)*(GlobalVar.LABEL_WIDTH+25);
			TranslateTransition move = new TranslateTransition();
			move.setDuration(Duration.millis(1000));
			move.setNode(node.getLabel());
			move.setToX(offset);
			move.setAutoReverse(false);
			move.play();
		}
	}
	
	public void exit(ActionEvent event){
		Platform.exit();
	}
	
	public void about() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AboutPage.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.show();
	}
	
	public void tutorial() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Tutorial.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));  
		stage.show();
	}
	
//	public void ok(ActionEvent event){
//		Platform.exit();
//	}
}

