package model;

import java.util.ArrayList;

import application.GlobalVar;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Btree extends Datatype {
private Node root;
	private int T;
	public Btree(int t) {
		root =  new Node();
		T = t;
		root.setN(0);
		root.setLeaf(true);
		root.setKey(new int[2*t]);
		root.setChild(new Node[2*t -1]);
	}
	
	// search key
	private Node Search(Node x, int key) {
		int i = 0;
		if( x == null) return x;
		for(i = 0 ; i < x.getN(); i++) {
			if(key < x.getKey(i)) break;
			if( key == x.getKey(i)) return x;
		}
		if(x.isLeaf()) return null;
		else return Search(x.getChild(i), key);
	}
	
	//splitting the node
	private void Split(Node x, int pos, Node y) {
		Node z = new Node();
		z.setLeaf(y.isLeaf());
		z.setN(T - 1);
		z.setKey(new int[2*T-1]);
		z.setChild(new Node[2*T]);
		for(int i = 0; i < T -1; i++) {
			z.setKey(i, y.getKey(i + T));
		}
		if(!y.isLeaf()) {
			for(int i = 0 ; i < T ; i++) {
				z.setChild(i, y.getChild(i + T));
			}
		}
		y.setN(T - 1);
		for (int i = x.getN(); i >= pos + 1; i --) {
			x.setChild(i + 1, x.getChild(i));
		}
		x.setChild(pos+1 , z);
		
		for (int i = x.getN()  - 1; i >= pos;  i--) {
			x.setKey(i + 1, x.getKey(i));
		}
		x.setKey(pos, y.getKey(T - 1));
		x.setN(x.getN() + 1);
	}
	
	public void insert(int key) {
		Node r = root;
		if(r.getN() ==  2 * T - 1) {
			Node s =  new Node();
			root = s;
			s.setLeaf(false);
			s.setN(0);
			s.setChild(new Node[2*T]);
			s.setKey(new int[2*T - 1]);
			s.setChild(0, r);
			Split(s, 0, r);
			insertValue(s, key);
		}else {
			insertValue(r, key);
		}
	}
	
	//inset the value
	private void insertValue(Node x, int k) {
		if(x.isLeaf()) {
			int i = 0;
			for ( i = x.getN() - 1;  i >= 0 && k < x.getKey(i); i --) {
				x.setKey(i + 1, x.getKey(i));
			}
			x.setKey(i + 1, k);
			x.setN(x.getN() + 1);
		}else {
			int i = 0;
			for (i = x.getN() - 1; i >= 0 && k < x.getKey(i); i --) {
				
			};
			i ++;
			Node tmp =  x.getChild(i);
			if(tmp.getN() == 2 * T - 1) {
				Split(x, i, tmp);
				if( k > x.getKey(i)) i ++;
			}
			insertValue(x.getChild(i), k);
		}
		
	}
	
	private void Remove(Node x, int key) {
		int pos = x.FindB(key);
		if(pos != -1) {
			if(x.isLeaf()) {
				int i = 0;
				for(i = 0; i < x.getN() && x.getKey(i) != key; i++) {
					
				};
				for(; i< x.getN(); i++) {
					if( i != 2*T -2) {
						x.setKey(i, x.getKey(i + 1));
					}
				}
				x.setN(x.getN() - 1);
				return;
			}
			
			if( !x.isLeaf()) {
				Node pred = x.getChild(pos);
				int predKey = 0;
				if(pred.getN() >= T) {
					for(;;) {
						if(pred.isLeaf()) {
							System.out.println(pred.getN());
							predKey = pred.getKey(pred.getN() - 1);
							break;
						}else {
							pred = pred.getChild(pred.getN());
						}
					}
					Remove(pred, predKey);
					x.setKey(pos, predKey);
					return;
				}
				
				Node nextNode =  x.getChild(pos+1);
				if(nextNode.getN() >= T) {
					int nextKey = nextNode.getKey(0);
					if(!nextNode.isLeaf()) {
						nextNode = nextNode.getChild(0);
						for(;;) {
							if(nextNode.isLeaf()) {
								nextKey = nextNode.getKey(nextNode.getN() - 1);
								break;
							}else {
								nextNode = nextNode.getChild(nextNode.getN());
							}
						}
					}
					Remove(nextNode, nextKey);
					x.setKey(pos, nextKey);
					return;
				}
				
				int temp = pred.getN() + 1;
				pred.setKey(pred.getN() + 1, x.getKey(pos));
				for (int i = 0, j = pred.getN(); i < pred.getN(); i++) {
					pred.setKey(j++, nextNode.getKey(i));
					pred.setN(pred.getN() + 1);
				}
				for (int i = 0; i < nextNode.getN() + 1; i++) {
					pred.setChild(temp ++, nextNode.getChild(i));
				}
				
				x.setChild(pos, pred);
				for (int i = pos; i < x.getN(); i++) {
					if(i != 2*T -2) {
						x.setChild(i, x.getChild(i+1));
					}
				}
				for(int i = pos + 1; i < x.getN() + 1; i ++) {
					if( i != 2 * T - 1) {
						x.setChild(i, x.getChild( + 1));
					}
				}
				x.setN(x.getN() -1);
				if(x.getN() == 0) {
					if(x == root) {
						root = x.getChild(0);
					}
					x = x.getChild(0);
				}
				Remove(pred, key);
				return;
			}
		}else {
			for(pos = 0; pos < x.getN(); pos ++) {
				if(x.getKey(pos) > key) {
					break;
				}
			}
			Node tmp = x.getChild(pos);
			if(tmp.getN() >= T) {
				Remove(tmp, key);
				return;
			}
			if(true) {
				Node nb= null;
				int devider =  -1;
				
				if(pos != x.getN() && x.getChild(pos + 1).getN() >= T){
					devider = x.getKey(pos);
					nb = x.getChild(pos+1);
					x.setKey(pos, nb.getKey(0));
					tmp.setKey(tmp.getN() + 1, devider);
					tmp.setChild(tmp.getN(), nb.getChild(0));
					for(int i = 1 ; i < nb.getN(); i++) {
						nb.setKey(i -1, nb.getKey(i));
					}
					for(int i = 1; i <= nb.getN(); i++) {
						nb.setChild(i -1, nb.getChild(i));
					}
					nb.setN(nb.getN() - 1);
					Remove(tmp, key);
					return;
				}else if(pos != 0  && x.getChild(pos -1).getN() >= T) {
					devider = x.getKey(pos -1);
					nb = x.getChild(pos -1);
					x.setKey(pos -1, nb.getKey(nb.getN() - 1));
					Node child = nb.getChild(nb.getN());
					nb.setN(nb.getN() - 1);
					
					for(int i = tmp.getN(); i > 0; i--) {
						tmp.setKey(i, tmp.getKey(i - 1));
					}
					tmp.setKey(0, devider);
					for(int i = tmp.getN() + 1; i > 0; i--) {
						tmp.setChild(i, tmp.getChild(i - 1));
					}
					tmp.setChild(0, child);
					tmp.setN(tmp.getN() + 1);
					Remove(tmp, key);
					return;
				}else {
					Node lt = null;
					Node rt = null;
					boolean last = false;
					if(pos != x.getN()) {
						devider = x.getKey(pos);
						lt = x.getChild(pos);
						rt = x.getChild(pos - 1);
						last = true;
						pos --;
					}
					for(int i = pos; i< x.getN() - 1; i++) {
						x.setKey(i, x.getKey(i + 1));
					}
					for(int i = pos + 1; i < x.getN(); i++) {
						x.setChild(i, x.getChild( + 1));
					}
					x.setN(x.getN() - 1);
					lt.setKey(lt.getN() + 1, devider);
					
					for(int i = 0, j = lt.getN(); i < rt.getN() + 1; i++, j++) {
						if(i < rt.getN()) {
							lt.setKey(j, rt.getKey(i));
						}
						lt.setChild(j, rt.getChild(i));
					}
					lt.setN(lt.getN() + rt.getN());
					if(x.getN() == 0) {
						if(x == root) {
							root = x.getChild(0);
						}
						x = x.getChild(0);
					}
					Remove(lt, key);
					return;
				}
			}
		}
	}
	
	public void Remove(int key) {
		Node x = Search(root, key);
		if( x == null) return;
		Remove(root, key);
	}
	
	//check if present 
	public boolean Contain(int k) {
		if(this.Search(root, k) != null) return true;
		else return false;
	}
	
	//show
	
	public void ShowAll() {
		System.out.println("\nTat ca cac phan tu la:");
		Show(root);
	}
	
	private void Show(Node x) {
		assert(x == null);
		for (int i = 0; i < x.getN(); i++) {
			System.out.print(x.getKey(i) + " ");
		}
		if(!x.isLeaf()) {
			System.out.println("\n\n");
			for(int i = 0 ;  i< x.getN() +1; i++) {
				System.out.print("          ");
				Show(x.getChild(i));
			}
		}
	}
	
	public void testShow() {
		System.out.println(root.getChild(0).getN());
	}
	
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void makeEmpty() {
		root = null;
	}

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	
	public void preoder() {
		preoder(root);
	}
	
	private void preoder(Node x) {
		if(x != null) {
			nodelist.add(x);
			for(int i = 0; i < 2*T - 1; i ++){
				preoder(x.getChild(i));
			}
		}
	}
	
	public void drawAni(java.util.LinkedList<Node> nodelist1, double offsetX, double offsetY) {
		if(nodelist.size() == 0) return;
		Node x = nodelist1.get(0);
		x.setLabel(x.form(x.getKey(), x.getN()));
		AddAni(x, offsetX, offsetY).play();
		if(x.isLeaf()) {
			return;
		}else {
			ArrayList<java.util.LinkedList<Node>> list = new ArrayList<java.util.LinkedList<Node>>();
			
			for(int i = 0; i < x.getN() + 1; i++) {
				java.util.LinkedList<Node> nodeL = new java.util.LinkedList<Node>();
				nodeL.add(x.getChild(i));	
				list.add(nodeL);
			}
			for(int i = 0; i < x.getN() + 1; i++) {
				float k = 0;
				if( T == 2) {k = 500;}
				else if( T == 3) k = 200;
				if((x.getN() + 1) % 2 == 0) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
					if(nodelist1.size() == 1) {
						drawAni(list.get(i), offsetX + 150*(i  - x.getN()/2) - 75 , offsetY + 150);
					}else {
						drawAni(list.get(i), offsetX + k*(i - x.getN()/2) -k/2 , offsetY + 150);
					}
							
				}
				else {
					if(nodelist1.size() == 1) {
						drawAni(list.get(i), offsetX + 150*(i  - x.getN()/2), offsetY + 150);
					}else {
						drawAni(list.get(i), offsetX + k*(i - x.getN()/2), offsetY + 150);
					}
				}
				
//				Arrow  arrowl = new Arrow(offsetX+GlobalVar.LABEL_WIDTH/2,offsetY+GlobalVar.LABEL_HEIGHT,  offsetX + 150*(i + 1 - x.getN())  , offsetY + 150);
//				nodelist1.get(0).setArrowl(arrowl);
//				Arrow  arrowr = new Arrow(offsetX+GlobalVar.LABEL_WIDTH/2,offsetY+GlobalVar.LABEL_HEIGHT,  offsetX + 150*(i + 1 - x.getN()) - 100 , offsetY + 150);
//				nodelist1.get(0).setArrowr(arrowr);
//				System.out.println("so luong" + list.size());
//				for(int j = 0; j < x.getN() + 1; j++) {
//					Arrow arrow = new Arrow(offsetX + GlobalVar.LABEL_WIDTH/2,offsetY+GlobalVar.LABEL_HEIGHT,  offsetX + 10*(j + 1 - x.getN())  , offsetY + 150);
//					x.setArrow(arrow);
//				}
			}
		}
	}
	
	public SequentialTransition AddAni(Node node, double offsetX, double offsetY) {
		SequentialTransition st = new SequentialTransition();

		TranslateTransition moveX = new TranslateTransition();
		moveX.setDuration(Duration.millis(0.5)); // speed
		moveX.setNode(node.getLabel());
		moveX.setToX(offsetX);
		moveX.setAutoReverse(false);

		TranslateTransition moveY = new TranslateTransition();
		moveY.setDuration(Duration.millis(0.5));
		moveY.setNode(node.getLabel());
		moveY.setToY(offsetY);
		moveY.setAutoReverse(false);

		st.getChildren().addAll(moveX, moveY);
		return st;
	}

	@Override
	public void AddNode(Node newnode) {
		nodelist.add(newnode);
	}

	@Override
	public void removeNode(Node node) {
		nodelist.remove(node);
	}

	@Override
	public Node findNode(int d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findIndexNode(int d) {
		// TODO Auto-generated method stub
		return 0;
	}
}