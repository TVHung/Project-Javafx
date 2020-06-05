package model;

import application.GlobalVar;
import model.Datatype;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;

public class Stack extends Datatype{

	public Stack() {

	}
	private void push(Node node) {
	   nodelist.push(node);
	}
	
	private Node pop() {
		tmp = nodelist.pop();
		return tmp;
	}

//	private Node peek() {
//		tmp = nodelist.peek();
//		return tmp;
//	}
	
	@Override
	public void AddNode(Node newnode) {
		this.push(newnode);
	}

	@Override
	public void removeNode(Node node) {
		this.pop();
	}
	@Override
	public Node findNode(int d) {
		Alert alert= new Alert(Alert.AlertType.INFORMATION,"The Stack cannot delete the position you want!",ButtonType.OK);
		alert.showAndWait()
		.filter(response -> response == ButtonType.OK)
		.ifPresent(response->alert.close());
		return null;
	}
	
	@Override
	public int findIndexNode(int d) {
		
		return 0;
	}
	
	private double setOffsetX(Double displayWidth) {
		return displayWidth/2 - GlobalVar.LABEL_WIDTH/2;
	}
	
	private double setOffsetY(Double displayHeight) {
		return displayHeight-(this.nodelist.size()*(GlobalVar.LABEL_HEIGHT+25));
	}
	@Override
	public SequentialTransition AddAni(Node node, double displayWidth, double displayHeight) {
		
		SequentialTransition st = new SequentialTransition();
		
		TranslateTransition moveX = new TranslateTransition();
		moveX.setDuration(Duration.millis(1000));
		moveX.setNode(node.getLabel());
		moveX.setToX(setOffsetX(displayWidth));
		moveX.setAutoReverse(false);
		
		TranslateTransition moveY = new TranslateTransition();
		moveY.setDuration(Duration.millis(1000));
		moveY.setNode(node.getLabel());
		moveY.setToY(setOffsetY(displayHeight));
		moveY.setAutoReverse(false);
		
		st.getChildren().addAll(moveX,moveY);
		return st;
	}

}

