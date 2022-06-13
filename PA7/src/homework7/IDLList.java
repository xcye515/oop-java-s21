/**
* This program constructs an indexed double linked list structure. It contains an inner class of node, and it provides some methods to easily access and modify the double linked list.
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Apr 26, 2021
* COSI 12B PA7
*/

package homework7;

import java.util.ArrayList;

public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	/**
	 * This is a inner class constructing a node. It provides some basic operations to construct the node.
	 * @author Xingchen Ye
	 * @param <E>
	 */
	private static class Node<E>{
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		/**
		 * This constructor creates a node holding elem
		 * @param elem E value stored in the node
		 */
		private Node(E elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
		
		/**
		 * This constructor creates a node holding elem with two nodes being its prev and next 
		 * @param elem E value stored in the node
		 * @param prev Node<E> its previous node
		 * @param next Node<E> its next node
		 */
		private Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.next = prev;
			this.prev = next;
		}
		
		
	}
	
	/**
	 * This constructor creates a empty indexed double linked list
	 */
	public IDLList(){
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();
	}
	
	/**
	 * This method add elem at position index in the double linked list. It also updates the ArrayList indices for quick access
	 * @param index int the index to insert a node with a value of elem
	 * @param elem E 
	 * @return boolean value true if the node with value elem is successfully inserted, false otherwise
	 */
	public boolean add(int index, E elem) {
		if(index < 0 || index > this.indices.size() || elem == null) { //if the index is invalid or the elem is null, the node cannot be added successfully
			return false;
		}
		Node<E> node = new Node<E>(elem);
		this.indices.add(index, node);
		if(this.head == null) { //if the IDLL is empty, add it to the first position
			this.head = node;
			node.prev = null;
			node.next = null;
			this.size++;
			return true;
		}
		if(this.tail == null) { //if the IDLL only has its head (one node), then add it to the last position
			this.tail = node;
			node.prev = this.head;
			node.next = null;
			this.size++;
			return true;
		}
		if(index == 0) { //if the IDLL is non-empty, and we want to insert it to the head
			node.prev = null;
			node.next = this.head;
			node.next.prev = node;
			this.head = node;
		} else if (index == this.size()) { //if the IDLL is non-empty, and we want to insert it to the tail
			node.next = null;
			node.prev = this.tail;
			node.prev.next = node;
			this.tail = node;
		} else {
			node.prev = this.indices.get(index-1);
			node.prev.next = node;
			node.next = this.indices.get(index+1);
			node.next.prev = node;
		}
		this.size++;
		return true;
	}
	
	/**
	 * This method adds the elem at the front of the list. It overrides and calls the add(index, elem) method above to avoid redundancy
	 * @return boolean value true if the node with value elem is successfully inserted, false otherwise
	 */
	public boolean add(E elem) { // add the node at position 0
		return this.add(0, elem);
	}
	
	/**
	 * This method adds the elem at the end of the list. It overrides and calls the add(index, elem) method above to avoid redundancy
	 * @return boolean value true if the node with value elem is successfully inserted, false otherwise
	 */
	public boolean append(E elem) { // add the node after the last node in the current list
		return this.add(this.indices.size(), elem);
	}
	
	/**
	 * This method receives a index and returns the element stored in the node at the index in the list
	 * @param index int
	 * @return E elem sotred in the node at the index
	 */
	public E get(int index) { 
		if(index < 0 || index > this.indices.size()) { // if the index is invalid, then return null
			return null;
		}
		return this.indices.get(index).data;
	}
	
	/**
	 * This method returns the head of the double linked list
	 * @return E the value field in the head node
	 */
	public E getHead() {
		if(this.head == null) {
			return null;
		}
		return this.head.data;
	}
	
	/**
	 * This method returns the tail of the double linked list
	 * @return E the value field in the head node
	 */
	public E getLast() {
		if(this.size() == 1) { //if the list only has one element and the element is stored in its head, then return to the head
			return this.getHead();
		} else {
			return this.tail.data;
		}
	}
	
	/**
	 * This method returns the size of the double linked list
	 * @return int value the size of the double linked list
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * This method removes and returns the element at the head
	 * @return E element at the head
	 */
	public E remove() {
		E head = this.getHead();
		if(head == null && this.tail == null) { //if the list is initially empty, then return null directly
			return null;
		} 
		this.indices.remove(0);
		this.size--;
		this.head = this.indices.get(0);
		if(this.head != null) { //if after the removal, the list is not empty, then we update the prev field of the new head node
			this.head.prev = null;
		}
		return head;
	}
	
	/**
	 * This method removes and returns the element at the tail
	 * @return E element at the tail
	 */
	public E removeLast() {
		E last = this.getLast();
		if(this.head == null && last == null) {//if the list is initially empty, then return null directly
			return null;
		}
		this.indices.remove(this.size()-1);
		this.size--;
		if(this.size() == 0) {//after removal, the list is empty
			this.head = null;
			this.tail = null;
		} else if (this.size() == 1){//after removal, the list only has one node
			this.tail = null;
		} else {
			this.tail = this.indices.get(this.size()-1);
			if(this.tail != null) {
				this.tail.next = null;
			}
		}
		return last;
	}
	
	/**
	 * This method removes and returns the element at the index
	 * @return E element at the tail
	 */
	public E removeAt(int index) {
		if(index < 0 || index > this.indices.size()) {//if the index is invalid
			return null;
		}
		if(index == 0) {//if the index is 0, then calls the remove() method
			return this.remove();
		} else if (index == this.size()-1) {//if the index points to the last element in the list, then calls the removeLast() method
			return this.removeLast();
		} 
		Node<E> elem = this.indices.get(index);
		this.indices.remove(index);
		this.size--;
		elem.prev.next = elem.next;
		elem.next.prev = elem.prev;
		return elem.data;
	}
	
	/**
	 * This method removes the first occurrence of elem in the list
	 * @param elem E
	 * @return true if the element is found and removed, false otherwise
	 */
	public boolean remove(E elem) {
		for(int i = 0; i < this.size(); i++) {
			if(this.indices.get(i).data.equals(elem)) {
				this.removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	/**
	 * This method returns a string representation of the IDLList.
	 */
	public String toString() {
		if(this.head == null) {
			return "[]";
		}
		String s = "[";
		for(int i = 0; i < this.size()-1; i++) {
			s += this.indices.get(i).data + ", ";
		}
		s += this.indices.get(this.size()-1).data + "]";
		return s;
	}
}
