/*
Author: Scott Field
Date: 09/13/2023
Version: 1.0
Purpose: 

Implement a new class named TwoWayLinkedList 
that uses a doubly linked list to store elements. 
Define TwoWayLinkedList to implements MyList. 
You need to implement all the methods defined in MyLinkedList as well as the methods listIterator() 
and listIterator(int index). Both return an instance of java.util.ListIterator<E> (see Figure 20.4). 
The former sets the cursor to the head of the list and the latter to the element at the specified index. 
Test your new class using this code from https://liveexample.pearsoncmg.com/test/Exercise24_03.txt.

*/

import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size;

    /** Create a default list */
    public TwoWayLinkedList() {
    }

    /*Start Provided Methods */

    /** Create a list from an array of objects */
    public TwoWayLinkedList(E[] objects) {
      for (E e : objects)
        add(e);
    }

    /** Return the head element in the list */
    public E getFirst() {
      if (size == 0) {
        return null;
      } else {
        return head.element;
      }
    }

    /** Return the last element in the list */
    public E getLast() {
      if (size == 0) {
        return null;
      } else {
        return tail.element;
      }
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("[");

      Node<E> current = head;
      for (int i = 0; i < size; i++) {
        result.append(current.element);
        current = current.next;
        if (current != null) {
          result.append(", "); // Separate two elements with a comma
        } else {
          result.append("]"); // Insert the closing ] in the string
        }
      }

      return result.toString();
    }

    /** Clear the list */
    public void clear() {
      head = tail = null;
    }

    /*End Provided Methods */

    /*Start Author Defined Methods */

    /** Return true if this list contains the element o */
    public boolean contains(Object e) {
        //start at the head of the list
        Node<E> current = head; 
        //while the end of the list has not been reached
        while (current !=null){ //while the end of the list has not been reached
            //check if the current element equals the provided element
            if (current.element.equals(e)){ 
                //if so it is contained
                return true; 
            }
            //Check the next element
            current = current.next; 
        }
        //otherwise return false;
        return false; 
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
        //If the index is out of bounds
        if (index < 0 || index >= size) {
            //Throw an error 
            throw new IndexOutOfBoundsException(); 
        }
        //start at the head of the list
        Node<E> current = head; 
        //for each element in the list
        for (int i = 0; i < index; i++) { 
            //once the item at the index has been found exit the loop
            if (i == index){
                break;
            }
            //move to the next element until the correct one is found
            current = current.next; 
        }
        return current.element;
    } //continue work after this point tommorow

    /**
    * Return the index of the head matching element in this list. Return -1 if
    * no match.
    */
    public int indexOf(Object e) {
        //start at the head of the list
        Node<E> current = head;
        //for each element in the list
        for (int i = 0; i < size; i++) { 
            //If the current element is equal to the element you are searching for
            if (current.element.equals(e)) {
                //Then the element you are searching for is at index i, return it 
                return i; 
            }
            //Keep moving until finding the element or the end of the list is reached
            current = current.next; 
        }
        //if the element is not found return -1
        return -1; 
    }

    /**
    * Return the index of the last matching element in this list Return -1 if
    * no match.
    */
    public int lastIndexOf(Object e) {
        //start at the tail of the list
        Node<E> current = tail;
        //for each element in the list (starting at the tail and moving backwards)
        for (int i = size - 1; i >= 0; i--) { 
            //if the current element is equal to the element you are searching for
            if (current.element.equals(e)) {
                //Then the element you are searching for is at index i, return it
                return i; 
            }
            //Keep moving (backwards) until finding the element or the end of the list is reached
            current = current.previous;
        }
        //if the element is not found return -1
        return -1; 
    }

    /**
    * Replace the element at the specified position in this list with the
    * specified element.
    */
    public E set(int index, E e) {
        if (index < 0 || index >= size) { // Check if the index is out of bounds
            throw new IndexOutOfBoundsException(); // Throw an exception if the index is out of bounds
        }
        Node<E> current = head; // Start at the head of the list
        for (int i = 0; i < index; i++) { // Iterate over the list to find the node at the given index
            current = current.next; // Move to the next element
        }
        E oldElement = current.element; // Store the old element
        current.element = e; // Set the element at the given index to the new element
        return oldElement; // Return the old element
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
      //create the node from e to be added
      Node<E> newNode = new Node<E>(e); 
      //attach the new node to the head
      newNode.next = head;
      //set head to the new first node (newNode)
      head = newNode; 
      //increase the size of the list by 1
      size++; 
      
      //if the list was empty
      if (tail == null) 
        //then the tail is equal to the new head as well
        tail = head;
      
      //check if the list is a two way linked list
      if (head != tail)
        //if the head and tail are different, the previous field of head most point to the newNod
        head.next.previous = head; 
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
      //create the node from e to be added
      Node<E> newNode = new Node<E>(e); 
      
      //if the list was empty then
      if (tail == null) {
        //both the head and tail are equal to the new Node
        head = newNode; 
        tail = newNode;
      //otherwise (if the list contained other elements)
      } else {
        //attach the new node to the tail
        tail.next = newNode;
        //have the previous field of tail point to the new Node
        newNode.previous = tail;
        //set tail equal to the last node
        tail = tail.next; 

      size++; // Increase size
      }
      
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     */
    public void add(int index, E e) {
        //If the item to be added has index 0
        if (index == 0) {
            //add it to the first item in the List
            addFirst(e);
        } 
        //If the item to be added has index that is out of bounds
        else if (index >= size) {
            //add it to the last item in the list
            addLast(e);
        } 
        //Otherwise find the location of where to add the item before adding it
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;

            //Set the previous, previous field to point to the current field
            temp.previous = current.next;
            //And the next elements previous field to point the current field
            current.next.previous = current;
        }
    }
    

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     */
    public E removeFirst() {
      //If the list is empty there is nothing to remove
      if (size == 0) {
        return null;
      //otherwise remove the head node
      } else {
        //set stepper
        Node<E> temp = head;
        //move the head
        head = head.next;
        //decrease the size
        size--;
        //if the list is now empty
        if (head == null) {
          //then set the tail to empty as well
          tail = null;
        }
        //return the first element
        return temp.element;
      }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     */
    public E removeLast() {
      //if the list is empty there is nothing to remove
      if (size == 0) {
        return null;
      //otherwise if the list contains only 1 item
      } else if (size == 1) {
        //set stepper
        Node<E> temp = head;
        //set both head and tail to null
        head = null;
        tail = null;
        //reduce size
        size = 0;
        //return the last element
        return temp.element;
      //otherwise find the last element and remove it
      }else {
        //set current node to head
        Node<E> current = head;
        //find the last element through iteration (size - 1 is the last element)
        for (int i = 0; i < size - 1; i++) {
          current = current.next;
        }
        //set stepper
        Node<E> temp = tail;
        //adjust tail
        tail = current;
        //change next element after tail to null (nothing after the last element)
        tail.next = null;
        //adjust size to match
        size--;
        //return the last element
        return temp.element;
      }
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     */
    public E remove(int index) {
      //if the item isn't within the list return nothing
      if (index < 0 || index >= size) {
        return null;
      //if the first item is set to be removed, remove the first item
      } else if (index == 0) {
        return removeFirst();
      //if the last item is set to be removed, remove the last item
      } else if (index == size - 1) {
        return removeLast();
      //otherwise find the element at the listed index, then remove it
      } else {
        //start at the head
        Node<E> previous = head;
        //iterate across the list until reaching the item at the index
        for (int i = 1; i <= index; i++) {
          previous = previous.next;
        }
        //after the item has been reached remove it
        Node<E> current = previous.next;
        previous.next = current.next;
        current.next.previous = previous;
        //reduce the size of the list
        size--;
        return current.element;
      }
    }

    //Implement The Custom List Iterator
    //This is defined in the same class file to more easily make use of global variables
    private class LinkedListIterator implements java.util.ListIterator<E> {
      //current index
      private Node<E> current = head; 

      public LinkedListIterator() {
      }
      
      public LinkedListIterator(int index) {
        if (index < 0 || index >= size)
          throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
            + size);
        for (int nextIndex = 0; nextIndex < index; nextIndex++)
          current = current.next;
      }
      //set the current node equal to the tial
      public void setLast() {
    	current = tail;
      }
      
      //check if their is a next node
      @Override
      public boolean hasNext() {
        return (current != null);
      }

      //get the next node
      @Override
      public E next() {
        E e = current.element;
        current = current.next;
        return e;
      }

      //remove the current node from the list
      @Override
      public void remove() {
         if (current == null) {
            throw new IllegalStateException();
        }
        if (current == head) {
            removeFirst();
        } else if (current == tail) {
            removeLast();
        } else {
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
        }
      }

      //add the element to the list
      @Override
      public void add(E e) {
        Node<E> newNode = new Node<E>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
      }

      //check if there is a previous node
      @Override
      public boolean hasPrevious() {
        return current != head;
      }

      //return the index of the next node
      @Override
      public int nextIndex() {
        if (current == null) {
            return size;
        }
        int index = 0;
        Node<E> temp = head;
        while (temp != current) {
            index++;
            temp = temp.next;
        }
        return index;
      }
      
      //return the previous node
      @Override
      public E previous() {
        E e = current.element;
        current = current.previous;
        return e;
      }

      //return the index of the previous node
      @Override
      public int previousIndex() {
         if (current == null) {
            return -1;
        }
        int index = 0;
        Node<E> temp = head;
        while (temp != current.previous) {
            index++;
            temp = temp.next;
        }
        return index;
      }

      //set the current element to the provided element
      @Override
      public void set(E e) {
        current.element = e; 
      }
    }

    //Rewrite the Node class as per Assignment directions
    private class Node<E> {
      E element;
      Node<E> next;
      Node<E> previous;

      public Node(E o) {
        element = o;
      }
    }

    //Get size (length of list)
    @Override
    public int size() {
      return size;
    }

    //Define listIterator based on constructor
    public ListIterator<E> listIterator() {
      return new LinkedListIterator(); 
    }
    
    public ListIterator<E> listIterator(int index) {
      return new LinkedListIterator(index); 
    }

    //Define Iterator based on constructor
    @Override
    public Iterator<E> iterator() {
      return new LinkedListIterator(size);
    }
}
