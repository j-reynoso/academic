/* Jasmin Reynoso
 * CMSC256 Fall 2019
 * This program creates a Singly Linked List implementation of Stacks. 
 * It produces a visualization of the singly linked list through Bridges.
 * 
 */
//package cmsc256;

import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.EmptyStackException;
import bridges.base.SLelement;

public class CustomStack<T> implements StackInterface<T> {
	
	
	// creating Bridges object to visualize the singly linked list
	public static void main(String[] args) {
		Bridges bridges = new Bridges(6, "reynosoj3", "341180180506");
		CustomStack<Integer> obj = new CustomStack<Integer>();
		obj.push(256);
		obj.push(10);
		obj.push(18);
		obj.push(20);
		obj.push(2019);
		obj.peek();
		obj.display();
		bridges.setDataStructure(obj.topOfStack);
		try {
			bridges.visualize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RateLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.pop();
		obj.pop();
	}
	//topOfStack instance variable 
	private SLelement<T> topOfStack;
	
 	/** Adds new entry to stack.
    @return  none
    @throws  IllegalArgumentException if the argument passed is null. */
	@Override
	public void push(T newEntry) {
		if(newEntry == null) {
			throw new IllegalArgumentException();
		} else {
			SLelement<T> newNode = new SLelement<T> (newEntry);
			newNode.setNext(topOfStack);
			topOfStack = newNode;
		}
		
	}
	/** Removes and returns this stack's top.
    @return  The object at the top of the stack. 
    @throws  EmptyStackException if the stack is empty before the operation. */
	@Override
	public T pop(){
		if(isEmpty()) {
			throw new EmptyStackException();
		}
			SLelement<T> temp = topOfStack;
			topOfStack = temp.getNext();
			return temp.getValue();
	}

	@Override
	/** Retrieves this stack's top.
    @return  The object at the top of the stack.
    @throws  EmptyStackException if the stack is empty. **/
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
			return topOfStack.getValue();
	}
	/** Detects whether this stack is empty.
    @return  True if the stack is empty. */
	@Override
	public boolean isEmpty() {
		if(topOfStack == null) {
			return true;
		}
		return false;
	}

	  /** Removes all entries from this stack. */
	@Override
	public void clear() {
		topOfStack = null;
	}
	
	/** Displays the contents in the stack*/
	public void display() {
		if(isEmpty()) {
		System.out.println("The stack is empty");
		}
		else {
		SLelement<T> current = topOfStack;
		StringBuffer output = new StringBuffer();
		output.append("Top of stack: " + current.getValue() + "\n");
		while(current.getNext() != null) {
		current = current.getNext();
		if(current.getNext() == null)
		output.append("Stack bottom: ");
		else
		output.append(" ");
		output.append(current.getValue() + "\n");
		}
		System.out.println(output.toString());
		}
		}

}
