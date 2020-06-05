package controllers;

import model.AVLTree;
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
//		for (int i=0;i<3;i++) {
//			newnode = new Node();
//			newnode.setLabel(newnode.form());
//			displayPane.getChildren().add(newnode.getLabel());
//			dt.AddNode(newnode);
//			offset = 25+(dt.nodelist.size()-1)*(GlobalVar.LABEL_WIDTH+25);
//			dt.AddAni(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
//		}
		
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

	public void initBtree() {
		
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
	
	public static boolean isNumeric(String str) { //kiem tra dau vao co phai so khong
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
	
	private void insertStack() {
		String checkInput = InputField.getText();
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
		}else if (dt.isFull()) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Stack is full",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else{
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
	
	private void insertBtree() {
		System.out.println("insertBtree");
		String checkInput = InputField.getText();
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
		}else {
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			InputField.clear();
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
		InputField.clear();
	}
	
	public void insertHead() {
		String checkInput = InputField.getText();
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
		}else if (dt.isFull()) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"LinkedList is full",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else{
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			dt.nodelist.addFirst(newnode);
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
			offset= displayPane.getHeight()-(dt.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
			dt.AddAniFirst(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
			this.reInsertFirst();
		}
		InputField.clear();
	}
	
	public void insertLast() {
		String checkInput = InputField.getText();
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
		}else if (dt.isFull()) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"LinkedList is full",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else{
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			dt.nodelist.addLast(newnode);
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
			offset= displayPane.getHeight()-(dt.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
			dt.AddAniFirst(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
			this.reInsertFirst();
		}
		InputField.clear();
	}
	
	public void insertAnyPosition(){
		String checkInput = InputField.getText();
		String checkInputIndex = inputIndex.getText();
	
		if(checkInput.equals("") == true || checkInputIndex.equals("") == true) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must enter 2 fields completely! Please input again.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else if(Integer.parseInt(inputIndex.getText()) > dt.sizeDt()) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"Input position exceeds the number of elements in the linked list! Please input again.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else if(isNumeric(checkInput) == false || isNumeric(checkInputIndex) == false){
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must enter a number! Please enter again.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else if (dt.isFull()) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"LinkedList is full",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else{
			int d = Integer.parseInt(InputField.getText());
			int position = Integer.parseInt(inputIndex.getText());
			newnode = new Node(d);
			dt.nodelist.add(position, newnode);
			newnode.setLabel(newnode.form());
			displayPane.getChildren().add(newnode.getLabel());
			fixset = displayPane.getWidth()/2 - GlobalVar.LABEL_WIDTH/2;
			offset= displayPane.getHeight()-(dt.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
			dt.AddAniFirst(newnode,displayPane.getWidth(),displayPane.getHeight()).play();
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
		String checkInput = InputField.getText();
		String checkInputIndex = inputIndex.getText();
		
		if((checkInput.equals("") == true && checkInputIndex.equals("") == true) || (checkInput.equals("") == false && checkInputIndex.equals("") == false)) {
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must enter 1 field completely.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else if((isNumeric(checkInput) == false && checkInputIndex.equals("") == true) || (checkInput.equals("") == true && isNumeric(checkInputIndex) == false)){
			Alert alert= new Alert(Alert.AlertType.INFORMATION,"You must enter a number! Please enter again.",ButtonType.OK);
			alert.showAndWait()	
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response->alert.close());
		}else if(checkInput.equals("") == false && checkInputIndex.equals("") == true){		
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
		}else if(checkInput.equals("") == true && checkInputIndex.equals("") == false) {
			int d = Integer.parseInt(checkInputIndex);
			newnode = dt.nodelist.get(d);
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
		inputIndex.clear();
	}
	
	public void delete() throws InterruptedException {
		switch (this.indi) {
		case 1:
			break;
		case 2:
			deleteStk();
			break;
		case 3:
			deleteBtree();
			break;
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
	
	private void deleteBtree() {  //mỗi lần xóa sẽ built lại từ đầu
		System.out.println("deleteNode");
		String checkInput = InputField.getText();
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
		}else {
			int d = Integer.parseInt(InputField.getText());
			newnode = new Node(d);
			InputField.clear();
			displayPane.getChildren().clear(); 			//lệnh xóa toàn bộ cây
			((AVLTree)dt).delete(newnode.getData());
			dt.nodelist.clear();
			((AVLTree)dt).preorder();
			((AVLTree)dt).drawNotAni(dt.nodelist,offset,fixset);    //cập nhật lại cây khi xóa
			System.out.println(dt.nodelist.size());
			for(int i=0;i<dt.nodelist.size();i++) {
				displayPane.getChildren().add(dt.nodelist.get(i).getLabel());
				
				if(dt.nodelist.get(i).getArrowl() != null && dt.nodelist.get(i).getLeft() != null)
					displayPane.getChildren().add(dt.nodelist.get(i).getArrowl());
				
				if(dt.nodelist.get(i).getArrowr() != null && dt.nodelist.get(i).getRight() != null)
					displayPane.getChildren().add(dt.nodelist.get(i).getArrowr());
			}
		}
		InputField.clear();
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

