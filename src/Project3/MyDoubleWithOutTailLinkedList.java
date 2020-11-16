package Project3;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

public class MyDoubleWithOutTailLinkedList implements Serializable {

	private DNode top;

	public MyDoubleWithOutTailLinkedList() {
		top = null;
	}

	// This method has been provided and you are not permitted to modify
	public int size() {
		if (top == null)
			return 0;

		int total = 0;
		DNode temp = top;
		while (temp != null) {
			total++;
			temp = temp.getNext();
		}

		int totalBack = 0;
		temp = top;
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}

		while (temp != null) {
			totalBack++;
			temp = temp.getPrev();
		}

		if (total != totalBack) {
			throw new RuntimeException("next links " + total + " do not match prev links " + totalBack);
		}

		return total;

	}

	// This method has been provided and you are not permitted to modify
	public void clear () {
		Random rand = new Random();
		while (size() > 0) {
			int number = rand.nextInt(size());
			remove(number);
		}
	}

	/******************************************************************
	 *
	 * Your task is to complete this method.  Have the following
	 * ordering:
	 * Sort by Games first (ordered by dueDate), then by Consoles second
	 * (ordered by dueDate).
	 * Once you have Task 2 completed, sort by the renter's name if the
	 * dueDates are equal to complete Task 3.
	 *
	 */
	public void add(Rental s) {
		DNode temp = top;

		/** Case 0: no list*/
		if (top == null) {
			top = new DNode(s, null, null);
			return;
		}

		/** Case1: s is a Game, and s goes on top- list exists (older dates on bottom)*/
		if (s instanceof Game && top.getData().getDueBack().after(s.dueBack)) {

			//1 element list
			if (size() == 1) {
				top = new DNode(s, top, null);
				return;
			}

			top = new DNode(s, top, null);
			top.getNext().setPrev(top);
			return;
		}


			/** Case 2: s doesnt go on top (older dates on bottom) */
			while (temp.getNext() != null && temp.getData().getDueBack().before(s.dueBack)) {
				temp = temp.getNext();
			}
			temp = new DNode(s, temp, temp.getPrev());
			/**TODO add here*/
			return;
	}

	/******************************************************************
	 *Remove - deletes a selected node
	 *
	 * @param index - node number to delete
	 * @return tempData - rental that was deleted
	 *
	 */
	// TODO: More code goes here.
	public Rental remove(int index) {
		/** Create temp pointer to move down list*/
		DNode temp = top;
		/** Tempdata to return deleted Rental*/
		Rental tempData;

		/** Case 0: no list exists- top = null*/
		if(top == null){
			return null;
		}

		/** Case 1: Index out of bounds*/
		if(index > size()){
			JOptionPane.showMessageDialog(null, "Index larger than list");
			return null;
		}

		/** Case 2: list 1 long*/
		if(size() == 1){
			tempData = top.getData();
			top = null;
			return tempData;
		}


		/**Case 3: list exists*/
		if(index == 0){
			tempData = top.getData();
			top = top.getNext();
			top.getNext().setPrev(null);
			return tempData;
		}
		//move pointer to correct element
		int i = 0;
		while(i < index){
			temp = temp.getNext();
			i++;
		}
		//if last element chosen, delete
		if(index == size()-1){
			tempData = temp.getData();
			temp.getPrev().setNext(null);
			return tempData;
		}
		//if element in between first and last delete
		tempData = temp.getData();
		temp.getPrev().setNext(temp.getNext());
		temp.getNext().setPrev(temp.getPrev());
		return tempData;
	}

	/******************************************************************
	 *
	 * Your task is to complete this method.
	 */
	public Rental get(int index) {
		// TODO: More code goes here.

		/** Case 0: no list*/
		if (top == null)
			return null;

		/** Case 1: 1 node long list, or 1st element called*/
		if(size() == 1 || index == 0)
			return top.getData();
		/** Case 2: index out of bounds*/
		if(index > size())
			return null;

		/** Case 3: list more than 1 long*/
		DNode temp = top;
		for(int i = 1; i < index; i++){
			temp = temp.getNext();
		}
		return temp.getData();


	}

	public void display() {
		DNode temp = top;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
	}

	public String toString() {
		return "DLL {" +
				"top=" + top +
				", size=" + size() +
				'}';
	}

}