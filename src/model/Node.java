package model;

import java.util.Random;

import application.GlobalVar;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Node {
	private int data;
	private int height;
	private Node next; //for stack and ll
	private Node left, right; //for tree
//	public int place ;
	private Label label;
	private Arrow arrowl;
	private Arrow arrowr;
	
	private Random 	rand = new Random();
	
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	

	public Arrow getArrowl() {
		return arrowl;
	}

	public void setArrowl(Arrow arrowl) {
		this.arrowl = arrowl;
	}

	public Arrow getArrowr() {
		return arrowr;
	}

	public void setArrowr(Arrow arrowr) {
		this.arrowr = arrowr;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Node() {
		// TODO Auto-generated constructor stub
		super();
		left = null;
        right = null;
        height = 0;
        arrowl= null;
    	arrowr = null;
		data = rand.nextInt(100);
		this.label = form();

	}

	public Node(int data) {
		super();
		left = null;
        right = null;
        height = 0;
        arrowl= null;
    	arrowr = null;
		this.data = data;
		this.label = form();
	}
	
	public Label form() {
		Label label = new Label();
		label.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
		label.setText("" + data);
		label.setLayoutX(GlobalVar.PRIMARY_X);
		label.setLayoutY(GlobalVar.PRIMARY_Y);
		label.setPrefHeight(GlobalVar.LABEL_HEIGHT);
		label.setPrefWidth(GlobalVar.LABEL_WIDTH);
		label.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		label.setFont(new Font(30));
		label.setAlignment(Pos.CENTER);
		
		return label;
	}

}
