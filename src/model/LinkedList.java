package model;

import application.GlobalVar;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class LinkedList extends Datatype{
	
	public LinkedList() {

	}
	
	private double setOffsetX() {
		return 25+(this.nodelist.size()-1)*(GlobalVar.LABEL_WIDTH+25);
	}
	
	private double setOffsetY(Double displayHeight,double d) {
		return displayHeight*d-GlobalVar.LABEL_HEIGHT/2;
	}
	
	public void AddNode(int data) {
		Node node = new Node(data);
		AddNode(node);
	}
	
	public void addFirst(Node node) {
		nodelist.addFirst(node);
	}
	
	public void AddNode() {
		Node node = new Node();
		AddNode(node);
	}
	
	public void AddNode(Node node) {
		nodelist.add(node);
	}
	
	public void removeNode(Node node) {
		nodelist.remove(node);
	}
	
	public Node findNode(int data) {
		for (int i = 0 ; i < nodelist.size() ; i++) {
			tmp = nodelist.get(i);
			if (tmp.getData() == data) return tmp;
		}
		return null;
	}
	
	@Override
	public int findIndexNode(int d) {
		for (int i = 0 ; i < nodelist.size() ; i++) {
			tmp = nodelist.get(i);
			if (tmp.getData() == d) return i;
		}
		return -1;
	}

	@Override
	public SequentialTransition AddAni(Node node, double displayWidth, double displayHeight) {
		
		SequentialTransition st = new SequentialTransition();
		
		TranslateTransition moveX = new TranslateTransition();
		moveX.setDuration(Duration.millis(1000));
		moveX.setNode(node.getLabel());
		moveX.setToX(setOffsetX());
		moveX.setAutoReverse(false);
		
		TranslateTransition moveY = new TranslateTransition();
		moveY.setDuration(Duration.millis(1000));
		moveY.setNode(node.getLabel());
		moveY.setToY(setOffsetY(displayHeight,0.5));
		moveY.setAutoReverse(false);
		
		st.getChildren().addAll(moveX,moveY);
		return st;
	}
	
//	@Override 
//	public SequentialTransition AddAniFirst(Node node, double displayWidth, double displayHeight) {
//		
//		SequentialTransition st = new SequentialTransition();
//		
//		TranslateTransition moveX = new TranslateTransition();
//		moveX.setDuration(Duration.millis(1000));
//		moveX.setNode(node.getLabel());
//		moveX.setToX(25);     				//vi tri cua label moi se den
//		moveX.setAutoReverse(false);
//		TranslateTransition moveY = new TranslateTransition();
//		moveY.setDuration(Duration.millis(1000));
//		moveY.setNode(node.getLabel());
//		moveY.setToY(setOffsetY(displayHeight,0.5));
//		moveY.setAutoReverse(false);
//
//		
//		st.getChildren().addAll(moveX,moveY);
//
//		return st;
//	}

}
