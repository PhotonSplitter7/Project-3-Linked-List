package Project3;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;


/*********************************************
 * MyDoubleWithoutTailLinkedList: this class handles the creation and
 * maintenance of a double tailed linked list.
 * adds rentals, clears rentals, and measures size of list.
 * @author Dan George & Kirk Lemaire
 * @version 11/29/2020
 *********************************************/

public class MyDoubleWithOutTailLinkedList implements Serializable {

	private DNode top;

	public MyDoubleWithOutTailLinkedList() {
		top = null;
	}

	/** This method has been provided and you are not permitted to modify*/
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
	 *	Add method: adds a rental to linked list, sorting by games first,
	 *	consoles second, sorting by due date
	 * finally sorting by name if due dates same.
	 * @param s a rental to be added
	 **********************************************************************/
	public void add(Rental s) {
		DNode temp = top;

		/** Case 0: no list exists*/
		if (top == null ) {
			top = new DNode(s, null, null);
			return;
		}

		/**ADD TO TOP*/
		/** Case 1: add to top: s is game top is console*/
		if(s instanceof  Game && top.getData() instanceof Console){
			top = new DNode(s, top, null);
			top.getNext().setPrev(top);
			return;
		}

		/** Case 2: add to top: s date before top date, top is also game*/
		if(s.getDueBack().before(top.getData().dueBack) && s instanceof Game && top.getData() instanceof Game ){
			top = new DNode(s, top, null);
			top.getNext().setPrev(top);
			return;
		}

		/** Case 3: add to top: s date before top date, top and s are both consoles*/
		if(s.getDueBack().before(top.getData().dueBack) && s instanceof Console && top.getData() instanceof Console){
			top = new DNode(s, top, null);
			top.getNext().setPrev(top);
			return;
		}

		/** Case 4: add to top: equal dates, s.name lexicographically shorter than or equal to top.name- both game*/
		if(s.getDueBack().equals(top.getData().dueBack) && s.nameOfRenter.compareTo(top.getData().nameOfRenter) <= 0 && s instanceof Game && temp.getData() instanceof Game){
			top = new DNode(s, top, null);
			top.getNext().setPrev(top);
			return;
		}

		/** Case 5: add to top: equal dates, s.name lexicographically shorter than or equal to top.name- both console*/
		if(s.getDueBack().equals(top.getData().dueBack) && s.nameOfRenter.compareTo(top.getData().nameOfRenter) <= 0 && s instanceof Console && temp.getData() instanceof Console){
			top = new DNode(s, top, null);
			top.getNext().setPrev(top);
			return;
		}

		/*************add to end && list size == 1************/

		/** Case 6: add to end: list size 1: top game, s console*/
		if(s instanceof Console && top.getData() instanceof Game && size() == 1){
			top.setNext(new DNode(s, null,top));
			return;
		}

		/** Case 7: add to end: list size 1: s date after top.date */
		if(s.getDueBack().after(top.getData().dueBack) && size() == 1){
			top.setNext(new DNode(s, null,top));
			return;
		}

		/** Case 8: add to end: size 1: equal dates, name lexicographically longer than top.name*/
		if(s.getDueBack().equals(top.getData().dueBack) && size() == 1 && s.nameOfRenter.compareTo(top.getData().nameOfRenter) > 0){
			top.setNext(new DNode(s, null,top));
			return;
		}

		/******************** ADD TO MIDDLE (list longer than 1) && add to end (list longer than 1)*************************/

		/** While loop if s == Console first passes all consoles, then passes all dates before s*/
		if(s instanceof Console && temp.getNext() != null){
			/** move temp past games until end*/
			while (temp.getData() instanceof Game && temp.getNext() != null){
				temp = temp.getNext();
			}
			/** if consoles available in list, check them for earlier due date*/
			while(temp.getNext() != null && s.getDueBack().after(temp.getData().dueBack)){
				temp = temp.getNext();
			}
		}

		/************************** While loop if s == Game**********************/
		if(s instanceof Game){
			/** move temp past games with earier due date till end*/
			while (temp.getNext() != null && s.getDueBack().after(temp.getData().dueBack) && temp.getData() instanceof Game){
				temp = temp.getNext();
			}
		}

		/**Case 9: Add to middle- add above temp: list longer than 1: temp is console and s is game*/
		if(temp.getData() instanceof Console && s instanceof Game){
			temp = new DNode(s, temp, temp.getPrev());
			temp.getPrev().setNext(temp);
			temp.getNext().setPrev(temp);
			return;
		}

		/** Case 10: Add to middle- add above temp: list longer than 1: s.date earlier than temp.date: both rentals game */
		if(s.getDueBack().before(temp.getData().dueBack) && s instanceof Game && temp.getData() instanceof Game){
			temp = new DNode(s, temp, temp.getPrev());
			temp.getPrev().setNext(temp);
			temp.getNext().setPrev(temp);
			return;
		}

		/** Case 11: Add to middle- add above temp: list longer than 1: s.date earlier than temp.date: both rentals Console */
		if(s.getDueBack().before(temp.getData().dueBack) && s instanceof Console && temp.getData() instanceof Console){
			temp = new DNode(s, temp, temp.getPrev());
			temp.getPrev().setNext(temp);
			temp.getNext().setPrev(temp);
			return;
		}

		/** Case 12: Add to middle: list longer than 1: dates equal, s name lexicographically shorter than temp: both games*/
		if(s.getDueBack().equals(temp.getData().dueBack) && s.nameOfRenter.compareTo(temp.getData().nameOfRenter) <= 0 && s instanceof Game && temp.getData() instanceof Game){
			temp = new DNode(s, temp, temp.getPrev());
			temp.getPrev().setNext(temp);
			temp.getNext().setPrev(temp);
			return;
		}

		/** Case 13: Add to middle: list longer than 1: dates equal, s name lexicographically shorter than temp: both Consoles*/
		if(s.getDueBack().equals(temp.getData().dueBack) && s.nameOfRenter.compareTo(temp.getData().nameOfRenter) <= 0 && s instanceof Console && temp.getData() instanceof Console){
			temp = new DNode(s, temp, temp.getPrev());
			temp.getPrev().setNext(temp);
			temp.getNext().setPrev(temp);
			return;
		}

		/** Case 14: Add to middle: list longer than 1: dates equal, s name lexicographically shorter than temp: both games
		 *  sort by name*/
		if (s.getDueBack().equals(temp.getData().dueBack) && s instanceof Game && temp.getData() instanceof Game) {
			while (temp.getNext() != null && s.nameOfRenter.compareTo(temp.getData().nameOfRenter) > 0 && s.getDueBack().equals(temp.getData().dueBack) && s instanceof Game && temp.getData() instanceof Game) {
				temp = temp.getNext();
			}
			/** if not at end of list: add before temp*/
			if (temp.getNext() != null) {
				temp = new DNode(s, temp, temp.getPrev());
				temp.getPrev().setNext(temp);
				temp.getNext().setPrev(temp);
				return;
			}
			/**If at end of list, if temp.getNext == null, skip to line 260 and add to list there*/
		}

		/** Case 15: Add to middle: list longer than 1: dates equal, s name lexicographically shorter than temp: both Consoles*/
		if(s.getDueBack().equals(temp.getData().dueBack) && s.nameOfRenter.compareTo(temp.getData().nameOfRenter) > 0 && s instanceof Console && temp.getData() instanceof Console){
			while(temp.getNext() != null && s.nameOfRenter.compareTo(temp.getData().nameOfRenter) > 0 && s.getDueBack().equals(temp.getData().dueBack) && s instanceof Console && temp.getData() instanceof Console){
				temp = temp.getNext();
			}
			/** if not at end of list: add before temp*/
			if(temp.getNext() != null){
				temp = new DNode(s, temp, temp.getPrev());
				temp.getPrev().setNext(temp);
				temp.getNext().setPrev(temp);
				return;
			}
			/**If at end of list, if temp.getNext == null, skip to line 267 and add to list there*/
		}

		/**Case 16: add to end IF temp last node: list games, s Console*/
		if(temp.getNext() == null && s instanceof Console && temp.getData() instanceof Game){
			temp.setNext(new DNode(s,null,temp));
			return;
		}

		/** Case 17: add to end IF temp last node: s date after temp date: both games*/
		if(temp.getNext() == null && s.getDueBack().after(temp.getData().dueBack) && s instanceof Game && temp.getData() instanceof Game){
			temp.setNext(new DNode(s,null,temp));
			return;
		}

		/** Case 18: add to end IF temp last node: s date after temp date: both consoles*/
		if(temp.getNext() == null && s.getDueBack().after(temp.getData().dueBack) && s instanceof Console && temp.getData() instanceof Console){
			temp.setNext(new DNode(s,null,temp));
			return;
		}

		/** Case 19: add to end IF temp last node: s and temp dates equal: s name lexicographically longer: both games*/
		if(temp.getNext() == null && s.getDueBack().equals(temp.getData().dueBack) && s.nameOfRenter.compareTo(temp.getData().nameOfRenter) >= 0 && s instanceof Game && temp.getData() instanceof Game){
			temp.setNext(new DNode(s,null,temp));
			return;
		}

		/** Case 20: add to end IF temp last node: s and temp dates equal: s name lexicographically longer: both consoles*/
		if(temp.getNext() == null && s.getDueBack().equals(temp.getData().dueBack) && s.nameOfRenter.compareTo(temp.getData().nameOfRenter) >= 0 && s instanceof Console && temp.getData() instanceof Console){
			temp.setNext(new DNode(s,null,temp));
			return;
		}

	}




	/******************************************************************
	 *Remove - deletes a selected node
	 * @param index - node number to delete
	 * @return tempData - rental that was deleted
	 ******************************************************************/


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
		if(index > size() - 1){
			JOptionPane.showMessageDialog(null, "Index larger than list");
			return null;
		}

		/** Case 2: list 1 long*/
		if(size() == 1){
			tempData = top.getData();
			top = null;
			return tempData;
		}


		/**Case 3: list exists, delete top*/
		if(index == 0){
			tempData = top.getData();
			top = top.getNext();
			top.setPrev(null);
			return tempData;
		}
		/**move pointer to correct element*/
		int i = 0;
		while(i < index){
			temp = temp.getNext();
			i++;
		}
		/**if last element chosen, delete*/
		if(index == size()-1){
			tempData = temp.getData();
			temp.getPrev().setNext(null);
			return tempData;
		}
		/** if element in between first and last delete*/
		tempData = temp.getData();
		temp.getPrev().setNext(temp.getNext());
		temp.getNext().setPrev(temp.getPrev());
		return tempData;
	}

	/******************************************************************
	 * get: returns rental from linked list given its index
	 * @param index an int representing index of rental
	 * @return Rental from linked list
	 ******************************************************************/
	public Rental get(int index) {


		/** Case 0: no list*/
		if (top == null)
			return null;

		/** Case 1: 1 node long list, or 1st element called*/
		if(size() == 1 || index == 0)
			return top.getData();
		/** Case 2: index out of bounds*/
		if(index >= size()) {
			JOptionPane.showMessageDialog(null, "Index larger than list");
			return null;
		}

		/** Case 3: list more than 1 long*/
		DNode temp = top;
		int i = 0;
		while(i < index){
			temp = temp.getNext();
			i++;
		}
		return temp.getData();


	}

	/********************************
	 * display: displays linked list
	 *******************************/
	public void display() {
		DNode temp = top;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
	}

	/******************************************************
	 * toString: returns a string represenging linked list
	 * @return String: representing linked list information
	 * ****************************************************/
	public String toString() {
		return "DLL {" +
				"top=" + top +
				", size=" + size() +
				'}';
	}




}