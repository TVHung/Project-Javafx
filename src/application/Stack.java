package application;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Stack {
	public  LinkedList <String> stack = new LinkedList <String>();
	
    public int size() {
        return stack.size();
      }

    public boolean isEmpty() {
        return size() == 0;
      }

    public boolean isFull() {
    	return size() == 12;
    }
    
    public void push(String str) {
    	if(stack.size() == 12) {
    		JOptionPane.showMessageDialog(null,"Stack is full!");
    	} else {
    		stack.addLast(str);
    	}
    }
    
    public void pop() {
		if(stack.size() == 0) {
    		JOptionPane.showMessageDialog(null,"Stack is empty!");
		}else {
			stack.remove(stack.size() - 1); 							//xoa phan tu khoi stack
		}
    }
		
    public void deleteStack() {
    	stack.clear();
    }
}
