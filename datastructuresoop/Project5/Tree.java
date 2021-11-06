/********
Jasmin Reynoso
CMSC 256 Fall '19
Project 5
This file implements the methods of containsAll(Collection<?> c), addAll(Collection<? extends E> c), 
removeAll(Collection<?> c), retainAll(Collection<?> c), toArray(), and toArray(T[] array) of the Tree interface.
*********/
package cmsc256;

import java.util.Collection;
import java.util.Iterator;

public interface Tree<E> extends java.util.Collection<E> {
  /** Return true if the element is in the tree */
  public boolean search(E e);

  /** Insert element into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e);

  /** Delete the specified element from the tree
   * Return true if the element is deleted successfully */
  public boolean delete(E e);

  /** Get the number of nodes in the tree */
  public int getSize();

  /** Inorder traversal from the root*/
  public default void inorder() {
  }

  /** Postorder traversal from the root */
  public default void postorder() {
  }

  /** Preorder traversal from the root */
  public default void preorder() {
  }

  @Override /** Return true if the tree is empty */
  public default boolean isEmpty() {
    return size() == 0;
  };

  @Override
  public default boolean contains(Object e) {
    return search((E)e);
  }

  @Override
  public default boolean add(E e) {
    return insert(e);
  }

  @Override
  public default boolean remove(Object e) {
    return delete((E)e);
  }

  @Override
  public default int size() {
    return getSize();
  }
  /** Returns true if this collection contains all the elements in the
  specified collection. */
  @Override
  public default boolean containsAll(Collection<?> c) {
    boolean isFound = true;
    for(Object obj: c) {
    	isFound = search((E) obj);
    	if(!isFound) {
    		return false;
    	}
    }
    return isFound;
  }
  /** Adds all the elements in the specified collection to this
  collection */
  @Override
  public default boolean addAll(Collection<? extends E> c) {
	  boolean isAdded = true;
	    for(Object obj: c) {
	    	isAdded = add((E) obj);
	    	if(!isAdded) {
	    		return false;
	    	}
	    }
	    return isAdded;
  }
  /** Removes all this collection's elements that are also contained in
  the specified collection */
  @Override
  public default boolean removeAll(Collection<?> c) {
	  boolean isRemoved = true;
	    for(Object obj: c) {
	    	isRemoved = remove((E) obj);
	    	if(!isRemoved) {
	    		return false;
	    	}
	    }
	    return isRemoved;
  }
  /** Retains only the elements in this collection that are contained in
  the specified collection */
  @Override
  public default boolean retainAll(Collection<?> c) {
	  boolean isRetained = false;
	  Iterator<?> i = iterator();
	  while(i.hasNext()) {
		  Object obj = i.next();
		  if(!c.contains(obj)) {
			  remove(obj);
			  isRetained = true;
		  }
	  }
	  return isRetained;
  }
  /** Returns an array containing all the elements in this collection.
  */
  @Override
  public default Object[] toArray() {
    Object[] objArray = new Object[getSize()];
    Iterator<?> i = iterator();
    int counter = 0;
    while(i.hasNext()) {
    	objArray[counter] = i.next();
    	counter++;
    }
    return objArray;
  }
  /** Returns an array containing all the elements in this collection;
  the runtime type of the returned array is that of the specified array
  */
  @Override
  public default <T> T[] toArray(T[] array) {
	Iterator<?> i = iterator();
	int counter = 0;
    while(i.hasNext()) {
    	array[counter] = (T) i.next();
	counter++;
    }
return array;
  }
  }
  
