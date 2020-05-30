package application;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Stack {
	public  LinkedList <String> stack = new LinkedList <String>();
	
	//Return size of List
    public int size() {
        return stack.size();
      }
    
    //List is empty?
    public boolean isEmpty() {
        return size() == 0;
      }

    public boolean isFull() {
    	return size() == 13;
    }
    
    public void push(String str) {
    	if(isFull())
    		JOptionPane.showMessageDialog(null,"Stack is FULL!");
    	stack.addLast(str);
    }
    
    public void pop() {
		if(isEmpty())
    		JOptionPane.showMessageDialog(null,"Stack is EMPTY!");
		int i;
		int dem=1;
		for(i=0; i< stack.size(); i++) {
			if(stack.get(i) == "")
			dem++;
		}
		stack.remove(dem);
		stack.addFirst("");
    }
		
    public void deleteStack() {
    	stack.clear();
    }
}
