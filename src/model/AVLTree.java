package model;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

import javafx.util.Duration;

public class AVLTree extends Datatype
{
    private Node root;     
    /* Constructor */
    public AVLTree()
    {
        root = null;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root == null;
    }
    /* Make the tree logically empty */
    public void makeEmpty()
    {
        root = null;
    }
    /* Function to insert data */
    public void insert(int data)
    {
        root = insert(data, root);
    }
    /* Function to get height of node */
    private int height(Node t )
    {
        return t == null ? -1 : t.getHeight();
    }
    /* Function to max of left/right node */
    private int max(int lhs, int rhs)
    {
        return lhs > rhs ? lhs : rhs;
    }
    
    private Node minValueNode(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }
    private Node insert(int x, Node t)
    {
        if (t == null)
            t = new Node(x);
        else if (x < t.getData())
        {
            t.setLeft(insert( x, t.getLeft() )) ;
            if( height( t.getLeft()) - height( t.getRight() ) == 2 )
                if( x < t.getLeft().getData() )
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }
        else if( x > t.getData() )
        {
        	t.setRight( insert( x, t.getRight() ));
            if( height( t.getRight() ) - height( t.getLeft() ) == 2 )
                if( x > t.getRight().getData())
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }
        else
          ;  
        t.setHeight( max( height( t.getLeft() ), height( t.getRight() ) ) + 1);
        return t;
    }
      
    private Node rotateWithLeftChild(Node k2)
    {
        Node k1 = k2.getLeft();
        k2.setLeft( k1.getRight()) ;
        k1.setRight( k2);
        k2.setHeight( max( height( k2.getLeft() ), height( k2.getRight() ) ) + 1);
        k1.setHeight( max( height( k1.getLeft() ), k2.getHeight() ) + 1);
        return k1;
    }

    
    private Node rotateWithRightChild(Node k1)
    {
        Node k2 = k1.getRight();
        k1.setRight( k2.getLeft()) ;
        k2.setLeft (k1);
        k1.setHeight ( max( height( k1.getLeft() ), height( k1.getRight() ) ) + 1);
        k2.setHeight (max( height( k2.getRight() ), k1.getHeight() ) + 1);
        return k2;
    }
   
    private Node doubleWithLeftChild(Node k3)
    {
        k3.setLeft( rotateWithRightChild( k3.getLeft() ));
        return rotateWithLeftChild( k3 );
    }
      
    private Node doubleWithRightChild(Node k1)
    {
        k1.setRight( rotateWithLeftChild( k1.getRight() )) ;
        return rotateWithRightChild( k1 );
    }    
    public boolean search(int val)
    {
        return search(root, val);
    }
    private boolean search(Node r, int val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            int rval = r.getData();
            if (val < rval)
                r = r.getLeft();
            else if (val > rval)
                r = r.getRight();
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    public void preorder()
    {
        preorder(root);
    }
    private void preorder(Node r)
    {
        if (r != null)
        {
        	nodelist.add(r);
            System.out.print(r.getData() +" "+r.getHeight()+"   ");
            preorder(r.getLeft());             
            preorder(r.getRight());
        }
    }

	
	
	public Node findNode(int data) {
		for (int i=0;i<nodelist.size();i++) {
			tmp = nodelist.get(i);
			if (tmp.getData() == data) return tmp;
		}
		return null;
	}
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public void delete(int value)
	{
		deleteNode(root,value);
	}
	 public Node deleteNode(Node root, int value) {
	        // STEP 1: PERFORM STANDARD BST DELETE

	        if (root == null)
	            return root;

	        // If the value to be deleted is smaller than the root's value,
	        // then it lies in left subtree
	        if ( value < root.getData() )
	            root.setLeft( deleteNode(root.getLeft(), value));

	        // If the value to be deleted is greater than the root's value,
	        // then it lies in right subtree
	        else if( value > root.getData() )
	            root.setRight( deleteNode(root.getRight(), value));

	        // if value is same as root's value, then This is the node
	        // to be deleted
	        else {
	            // node with only one child or no child
	            if( (root.getLeft() == null) || (root.getRight() == null) ) {

	                Node temp;
	                if (root.getLeft() != null)
	                        temp = root.getLeft();
	                else
	                    temp = root.getRight();

	                // No child case
	                if(temp == null) {
	                    temp = root;
	                    root = null;
	                }
	                else // One child case
	                    root = temp; // Copy the contents of the non-empty child

	              
	            }
	            else {
	                // node with two children: Get the inorder successor (smallest
	                // in the right subtree)
	                Node temp = minValueNode(root.getRight());

	                // Copy the inorder successor's data to this node
	                root.setData( temp.getData()) ;

	                // Delete the inorder successor
	                root.setRight (deleteNode(root.getRight(), temp.getData()));
	            }
	        }

	        // If the tree had only one node then return
	        if (root == null)
	            return root;

	        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
	        root.setHeight( Math.max(height(root.getLeft()), height(root.getRight())) + 1);

	        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
	        //  this node became unbalanced)
	        int balance = getBalance(root);

	        // If this node becomes unbalanced, then there are 4 cases

	        // Left Left Case
	        if (balance > 1 && getBalance(root.getLeft()) >= 0)
	            this.root= rotateWithLeftChild(root);

	        // Left Right Case
	        if (balance > 1 && getBalance(root.getLeft()) < 0) {
	            this.root= doubleWithLeftChild(root);
	        }

	        // Right Right Case
	        if (balance < -1 && getBalance(root.getRight()) <= 0)
	             this.root=rotateWithRightChild(root);

	        // Right Left Case
	        if (balance < -1 && getBalance(root.getRight()) > 0) {
	        	this.root=doubleWithRightChild(root);
	        }

	        return root;
	    }
	 private int getBalance(Node N) {
	        if (N == null)
	            return 0;
	        return height(N.getLeft()) - height(N.getRight());
	    }
	public void drawNotAni(java.util.LinkedList<Node> nodelist1,double offsetX, double offsetY)
	{
		if (nodelist1.size()==0) return;
		java.util.LinkedList<Node> nodelist2=new java.util.LinkedList<Node>();
		java.util.LinkedList<Node> nodelist3=new java.util.LinkedList<Node>();
		nodelist1.get(0).setLabel(nodelist1.get(0).form());
		AddAni(nodelist1.get(0),offsetX,offsetY).play();
		for (int i=1;i<nodelist1.size();i++) {
			if(nodelist1.get(i).getData()>nodelist1.get(0).getData()) nodelist2.add(nodelist1.get(i));
			else nodelist3.add(nodelist1.get(i));
		}
		if(nodelist2.size()!=0)
		{drawNotAni(nodelist2,offsetX+150,offsetY+100);
		Arrow arrowr=new Arrow(offsetX+75/2,offsetY+50,offsetX+150,offsetY+100);
		nodelist1.get(0).setArrowr(arrowr);}
		if(nodelist3.size()!=0) {
		drawNotAni(nodelist3,offsetX-150,offsetY+100);
		Arrow arrowl=new Arrow(offsetX,offsetY+50,offsetX-150,offsetY+100);
		nodelist1.get(0).setArrowl(arrowl);
		}
	}
public SequentialTransition AddAni(Node node, double offsetX, double offsetY) {
		
		SequentialTransition st = new SequentialTransition();
		
		TranslateTransition moveX = new TranslateTransition();
		moveX.setDuration(Duration.millis(1));
		moveX.setNode(node.getLabel());
		moveX.setToX(offsetX);
		moveX.setAutoReverse(false);
		
		TranslateTransition moveY = new TranslateTransition();
		moveY.setDuration(Duration.millis(1));
		moveY.setNode(node.getLabel());
		moveY.setToY(offsetY);
		moveY.setAutoReverse(false);
		
		st.getChildren().addAll(moveX,moveY);
		return st;
	}
public void AddNode(Node node) {
	nodelist.add(node);

}

public void removeNode(Node node) {
	nodelist.remove(node);

}
}

