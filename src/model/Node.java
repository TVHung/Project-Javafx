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
	
	//for stack and link list
	private Node next; 			
	
	//for AVL tree
	private Node left;
	private Node right;	
	
	//for BalancedTree
	private int n;
	private int key[];
	private Node child[];
	private boolean leaf;
	public int FindB(int k) {
		for (int i = 0; i < this.n; i++) {
			if(this.key[i] == k) {
				return i;
			}
		}
		return -1;
	}
	
	//for draw
	private Label label;		
	private Arrow arrowl;		
	private Arrow arrowr;	
	private Arrow arrow;
	
	private Random 	rand = new Random();
	
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
	
	public Arrow getArrow() {
		return arrow;
	}

	public void setArrow(Arrow arrow) {
		this.arrow = arrow;
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
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getKey(int i) {
		return key[i];
	}
	
	public int [] getKey() {
		return key;
	}

	public void setKey(int i, int m) {
		this.key[i] = m;
	}
	
	public void setKey(int [] key) {
		this.key = key;
	}

	public Node getChild(int i) {
		return child[i];
	}

	public void setChild(int i, Node b) {
		this.child[i] = b;
	}
	
	public void setChild(Node [] child) {
		this.child = child;
	}
	
	public Node[] getChild() {
		return child;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Node() {
		super();
		data = rand.nextInt(100);
		this.height = 0;
		this.next = null;
		this.left = null;
		this.right = null;
		this.label = form();
		this.arrowl = null;
		this.arrowr = null;
	}

	public Node(int data) {
		super();
		this.data = data;
		this.height = 0;
		this.next = null;
		this.left = null;
		this.right = null;
		this.label = form();
		this.arrowl = null;
		this.arrowr = null;
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
	
	public Label form(int [] key, int k) {
		Label label = new Label();
		label.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
		String str = "";
		for( int i = 0; i < k; i++) {
			if(i == k -1) str += key[i];
			else str += key[i] + " | ";
		}
		label.setText(str);
		label.setLayoutX(GlobalVar.PRIMARY_X);
		label.setLayoutY(GlobalVar.PRIMARY_Y);
		label.setPrefHeight(GlobalVar.LABEL_HEIGHT);
		label.setPrefWidth(GlobalVar.LABEL_WIDTH);
		label.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		label.setFont(new Font(10));
		label.setAlignment(Pos.CENTER);
		
		return label;
	}
	

}
