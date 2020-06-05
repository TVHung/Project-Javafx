package model;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;

public abstract class Datatype {
	private static final int MAX_SIZE = 6;
	public java.util.LinkedList<Node> nodelist = new java.util.LinkedList<Node>();
	Node tmp;
		
//	String color;
	
	public Datatype() {
		// TODO Auto-generated constructor stub
	}

	public java.util.LinkedList<Node> getNodelist() {
		return nodelist;
	}
	
	public abstract SequentialTransition AddAni(Node node, double displayWidth, double displayHeight);
	
	public FadeTransition DelAni(Node node) {
		FadeTransition ft = new FadeTransition();
		ft.setDuration(Duration.millis(500));
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.setNode(node.getLabel());
		
		return ft;
	}

	public abstract void AddNode(Node newnode);
	public abstract void removeNode(Node node);

	public boolean isEmpty() {
		return (nodelist.size() == 0);
	}
	
	public int sizeDt() {
		return nodelist.size();
	}

	public boolean isFull() {
		return (nodelist.size() == MAX_SIZE);
	}

	public abstract Node findNode(int d);
	public abstract int findIndexNode(int d);

	public SequentialTransition AddAniFirst(Node node, double displayWidth, double displayHeight) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
