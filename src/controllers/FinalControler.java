package controllers;

import model.AVLTree;
import model.Btree;
import model.Datatype;
import model.LinkedList;
import model.Node;
import model.Stack;
import javafx.scene.control.Button;
import java.io.IOException;
import application.GlobalVar;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FinalControler {
	@FXML
	AnchorPane displayPane;
	@FXML
	TextField InputField;
	@FXML
	TextField inputIndex;
	@FXML
	Button btnInsert;
	@FXML
	Button btnDelete;
	@FXML
	MenuButton menuInsert, menuDelete;
	
	Node newnode;
	double offset,fixset;
	int indi = 0;
	int value;
	Datatype dt;
	LinkedList ll = new LinkedList();
	AVLTree tree = new AVLTree();
	Stack stk = new Stack();
	
	
	private void setFixset(double i) { 
		this.fixset = displayPane.getHeight()*i-GlobalVar.LABEL_HEIGHT/2; 
	}
	private void setOffset(double i){
		this.offset = displayPane.getWidth()*i;
	}
	public void initLL() throws InterruptedException {	
		btnInsert.setVisible(false);
		btnDelete.setVisible(false);

		menuInsert.setVisible(true);
		menuDelete.setVisible(true);
		
		inputIndex.setVisible(true);
		
		dt = new LinkedList();
		this.indi = 1;
		setFixset(0.5);
		displayPane.getChildren().clear();
		dt.nodelist.clear();
		System.out.println("LList");
	}
	
	public void initStk() {
		btnInsert.setVisible(true);
		btnDelete.setVisible(true);
		
		menuInsert.setVisible(false);
		menuDelete.setVisible(false);
		
		inputIndex.setVisible(false);
		
		dt = new Stack();
		this.indi = 2;
		System.out.println("Stack");
		displayPane.getChildren().clear();
		dt.nodelist.clear();
	}

	public void initAvl() {
		btnInsert.setVisible(true);
		btnDelete.setVisible(true);
		
		menuInsert.setVisible(false);
		menuDelete.setVisible(false);
		
		inputIndex.setVisible(false);
		
		dt = new AVLTree();
    	this.indi = 3;
		setFixset(0.1);
		displayPane.getChildren().clear();
		dt.nodelist.clear();
		dt.isEmpty();
		System.out.println("AVLtree");
		setOffset(0.5);
		newnode = new Node();
		newnode.setData(10);
		((AVLTree)dt).insert(newnode.getData());
		
		newnode.setData(20);
		((AVLTree)dt).insert(newnode.getData());
		
		newnode.setData(30);
		((AVLTree)dt).insert(newnode.getData());
		
		((AVLTree)dt).preorder();
		
		System.out.println("\nsize of nodeList: " + dt.nodelist.size());
		
		((AVLTree)dt).drawNotAni(dt.nodelist,offset,fixset);
		for(int i=0;i<dt.nodelist.size();i++) {
			displayPane.getChildren().add(dt.nodelist.get(i).getLabel());
			if(dt.nodelist.get(i).getArrowl()!=null&&dt.nodelist.get(i).getLeft()!=null)
			displayPane.getChildren().add(dt.nodelist.get(i).getArrowl());
			if(dt.nodelist.get(i).getArrowr()!=null&&dt.nodelist.get(i).getRight()!=null)
			displayPane.getChildren().add(dt.nodelist.get(i).getArrowr());
		}		
	}
	
	public void initBtree() {
		btnInsert.setVisible(true);
		btnDelete.setVisible(true);
		
		menuInsert.setVisible(false);
		menuDelete.setVisible(false);
		
		inputIndex.setVisible(false);
		
		dt = new Btree(3);
		this.indi = 4;
		setFixset(0.1);
		displayPane.getChildren().clear();
		dt.nodelist.clear();
		dt.isEmpty();
		System.out.println("Btree");
		setOffset(0.5);
		((Btree)dt).insert(20);
		((Btree)dt).insert(40);
		((Btree)dt).insert(10);
		((Btree)dt).insert(3);
		((Btree)dt).insert(8);
		((Btree)dt).insert(29);
		((Btree)dt).insert(12);
		((Btree)dt).insert(39);
		((Btree)dt).insert(9);
		((Btree)dt).insert(25);
		((Btree)dt).insert(2);
		((Btree)dt).insert(59);
		((Btree)dt).insert(7);
		((Btree)dt).insert(6);
		
		
		((Btree)dt).ShowAll();
		((Btree)dt).preoder();
		
		System.out.println("\nsize of nodelist: "+dt.nodelist.size());
		
		((Btree)dt).drawAni(dt.nodelist, offset, fixset);		
		
		for(int i = 0 ; i < dt.nodelist.size(); i++) {
			displayPane.getChildren().add(dt.nodelist.get(i).getLabel());
			if(dt.nodelist.get(i).getArrow() != null && dt.nodelist.get(i).getChild().length != 0){
				displayPane.getChildren().add(dt.nodelist.get(i).getArrow());
			}			
		}
		
	}
	
	public static boolean isNumeric(String str) { //kiem tra xem co phai la so hay khong
		return str.matches("-?\\d+(\\.\\d+)?"); 
	}
	
	public void insert() {
		System.out.println("inserting ...");
		switch (indi) {
			case 1:
				break;
			case 2: 
				insertStack();
				break;
			case 3: 
				insertAvlTree();
				break;
			case 4:
				insertBtree();
				break;
			default:
				showError();
				break;
		}
		
	}
	
	private void showError() {
		Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must select a function! please select function.",ButtonType.OK);
		alert.showAndWait()	
		.filter(response -> response == ButtonType.OK)
		.ifPresent(response->alert.close());
		InputField.clear();
	}
	
	
	private boolean checkInputNumber() {
		String checkInput = InputField.getText();
		if(checkInput.equals("")) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"You have not entered anything! Please input a number.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
			return false;
		}else if(isNumeric(checkInput) == false){
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must enter a number! Please enter again.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
			return false;
		}else if (dt.isFull()) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Stack is full",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
			return false;
		}
		
		return true;
	}
	
	private boolean checkInputIndex() {
		String checkInputIndex = inputIndex.getText();
		
		if(checkInputIndex.equals("") == true) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must enter 2 fields completely! Please input again.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
			return false;
		}else if(Integer.parseInt(inputIndex.getText()) > dt.sizeDt()) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Input position exceeds the number of elements in the linked list! Please input again.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
			return false;
		}else if(isNumeric(checkInputIndex) == false){
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must enter a number! Please enter again.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
			return false;
		}
		return true;
	}
	
	private void insertStack() {
		if(checkInputNumber() == true){
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			dt.AddNode(newnode);
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
			offset= displayPane.getHeight()-(dt.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
			dt.AddAni(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
		}
		InputField.clear();
	}
	private void insertAvlTree() {
		System.out.println("insertAvlTree");
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
	
	private void insertBtree() {
		System.out.println("InsertBtree");
		int d = 0;
		try {
			d = Integer.parseInt(InputField.getText());
			InputField.clear();
		} catch (NumberFormatException | NullPointerException nfe) {
			
		}
		displayPane.getChildren().clear();
		((Btree)dt).insert(d);
		((Btree)dt).nodelist.clear();
		((Btree)dt).preoder();
		((Btree)dt).drawAni(dt.nodelist, offset - 50, fixset);
		for(int i = 0 ; i < dt.nodelist.size(); i++) {
			displayPane.getChildren().add(dt.nodelist.get(i).getLabel());
//			if(dt.nodelist.get(i).getArrow() != null && dt.nodelist.get(i).getChild().length != 0){
//				displayPane.getChildren().add(dt.nodelist.get(i).getArrow());
//			}			
		}
	}
	
	public void insertHead() {
		if(checkInputNumber() == true){
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			dt.nodelist.addFirst(newnode);
			System.out.println(dt.nodelist.get(dt.sizeDt() - 1));
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
			offset= displayPane.getHeight()-(dt.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
			dt.AddAni(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
			this.reInsertFirst();
		}
		InputField.clear();
	}
	
	public void insertLast() {
		if(checkInputNumber() == true){
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			dt.nodelist.addLast(newnode);
			System.out.println(dt.nodelist.get(dt.sizeDt() - 2));
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
			offset= displayPane.getHeight()-(dt.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
			dt.AddAni(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
			this.reInsertFirst();
		}
		InputField.clear();
	}
	
	public void insertAnyPosition(){
		if(checkInputNumber() == true && checkInputIndex() == true){
			int d = Integer.parseInt(InputField.getText());
			int position = Integer.parseInt(inputIndex.getText());
			newnode = new Node(d);
			dt.nodelist.add(position, newnode);
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
			offset= displayPane.getHeight()-(dt.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
			dt.AddAni(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
			this.reInsertFirst();
		}
		InputField.clear();
		inputIndex.clear();
	}
	
	public void deleteHead() {
		try {      
			newnode = dt.nodelist.getFirst();
        } catch (ArithmeticException e) {

        }finally {
        	if (dt.isEmpty()) {
        		Alert alert= new Alert(Alert.AlertType.INFORMATION,"LinkedList is empty",ButtonType.OK);
        		alert.showAndWait()
        		.filter(response -> response == ButtonType.OK)
        		.ifPresent(response->alert.close());
        	}else{
        		System.out.println("Delele");
        		dt.removeNode(newnode);
        		dt.DelAni(newnode).setOnFinished(finishHim ->{
        			displayPane.getChildren().remove(newnode.getLabel());
        		}
        				);
        		dt.DelAni(newnode).play();
        		this.reArrangeLL();
        	}
        }
	}
	
	public void deleteLast() {
		try {
			newnode = dt.nodelist.getLast();            
        } catch (ArithmeticException e) {

        }finally {
        	if (dt.isEmpty()) {
        		Alert alert= new Alert(Alert.AlertType.INFORMATION,"LinkedList is empty",ButtonType.OK);
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
		}
	}
	
	public void deleteAnyPosition() {
		if(checkInputNumber() == true){		
			int d = Integer.parseInt(InputField.getText());
			int findIndex = dt.findIndexNode(d);
			if(findIndex == -1) {
				Alert alert= new Alert(Alert.AlertType.INFORMATION,"Can't find node to delete!",ButtonType.OK);
				alert.showAndWait()
				.filter(response -> response == ButtonType.OK)
				.ifPresent(response->alert.close());
			}else {
				newnode = dt.nodelist.get(findIndex);
				System.out.println("Delele");
				dt.removeNode(newnode);
				dt.DelAni(newnode).setOnFinished(finishHim ->{
					displayPane.getChildren().remove(newnode.getLabel());
				}
						);
				dt.DelAni(newnode).play();
				this.reArrangeLL();
			}
			InputField.clear();
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
			deleteAvlTree();
			break;
		case 4:
			deleteBtree();
			break;
		}
			
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
		

	}
	
	private void deleteStk() {
		try {
			newnode = dt.nodelist.getFirst();            
        } catch (ArithmeticException e) {

        }finally {
        	if (dt.isEmpty()) {
        		Alert alert= new Alert(Alert.AlertType.INFORMATION,"Stack is empty",ButtonType.OK);
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
		}
	}
	

	private void deleteAvlTree() {
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
	
	private void deleteBtree() {
		System.out.println("deleteNode");
		int d = 0;
		try {
			d = Integer.parseInt(InputField.getText());
			InputField.clear();
		} catch (NumberFormatException | NullPointerException nfe) {
			
		}
		displayPane.getChildren().clear();
		((Btree)dt).Remove(d);
		dt.nodelist.clear();
		((Btree)dt).preoder();
		((Btree)dt).drawAni(dt.nodelist, offset, fixset);
		for(int i = 0 ; i < dt.nodelist.size(); i++) {
			displayPane.getChildren().add(dt.nodelist.get(i).getLabel());
//			if(dt.nodelist.get(i).getArrow() != null && dt.nodelist.get(i).getChild().length != 0){
//				displayPane.getChildren().add(dt.nodelist.get(i).getArrow());
//			}			
		}
		
	}
	
	public void reArrangeLL() {
		Node node;
		for (int i = 0 ; i < dt.nodelist.size() ; i++) {
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
	
	public void reInsertFirst() {
		Node node;
		for(int i = 0 ; i < dt.nodelist.size() ; i++){
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

}

