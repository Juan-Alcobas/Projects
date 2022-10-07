package hw05;

import java.util.ArrayList;

/**
 * This class is designed to be bale to create, store, and manipulate a 2
 * dimensional set of linked lists
 * 
 * @author Juan Alcobas, 4186523, CS2013 - 09/10
 *
 */
public class Array2D<E> {
	private int rowSize;
	private int colSize;
	protected Array2DNode head;
	protected Array2DNode rowTail;
	protected Array2DNode colTail;

	public Array2D() {
		this.rowSize = 0;
		this.colSize = 0;
		this.rowTail = null;
		this.colTail = null;
	}

	public Array2D(E[][] values) {

		this.rowSize = values[0].length;
		this.colSize = values.length;
		for (int counter = values.length; counter > 0; counter--) {
			addFirstCol(values[0]);
		}

	}

	/**
	 * Adds a set of values to be the first column in the 2D linkedList
	 * 
	 * @param values are what will be passed into the nodes item values
	 */
	public void addFirstCol(E... values) {

		Array2DNode colNode = head;

		Array2DNode newNode = new Array2DNode(values[0]);

		if (this.isEmpty()) {
			this.head = this.colTail = this.rowTail = newNode;
			this.rowSize++;
		}
		if (values.length != this.rowSize) {
			throw new IllegalArgumentException("The number of rows don't match");
		}

		newNode.nextCol = colNode;
		this.head = newNode;

		for (int counter = 1; counter < values.length; counter++) {

			Array2DNode rowNode = new Array2DNode(values[counter]);// assigns the column node
			newNode.nextRow = rowNode;// assigns the next row
			colNode = colNode.nextRow;// gets the next row value under the original head node
			newNode = newNode.nextRow;// changes newNode to the value at its next row node
			newNode.nextCol = colNode;// assigns the next column of the first column

		}

		this.colTail = newNode;
		this.colSize++;
	}

	/**
	 * Adds a set of values to be the first row in the 2D linkedList
	 * 
	 * @param values are what will be passed into the nodes item values
	 */
	public void addFirstRow(E... values) {

		Array2DNode rowNode = head;
		Array2DNode newNode = new Array2DNode(values[0]);

		if (this.isEmpty()) {
			this.head = this.colTail = this.rowTail = newNode;
			colSize++;
		}
		if (values.length != this.colSize) {
			throw new IllegalArgumentException("The number of columns don't match");
		}

		newNode.nextRow = rowNode;
		this.head = newNode;
		for (int counter = 1; counter < values.length; counter++) {

			Array2DNode colNode = new Array2DNode(values[counter]);
			newNode.nextCol = colNode;
			rowNode = rowNode.nextCol;
			newNode = newNode.nextCol;
			newNode.nextRow = rowNode;

		}

		this.rowSize++;
		this.rowTail = newNode;
	}

	/**
	 * Adds a column of nodes to be connected to the current last column of nodes
	 * 
	 * @param values are the values that will be plugged into the new nodes
	 */
	public void addLastCol(E... values) {

		Array2DNode colNode = head;

		Array2DNode newNode = new Array2DNode(values[0]);

		if (this.isEmpty()) {
			this.head = this.colTail = this.rowTail = newNode;
			this.rowSize++;
		}

		if (values.length != this.rowSize) {
			throw new IllegalArgumentException("The number of rows don't match");
		}

		while (colNode.nextCol != null) {
			colNode = colNode.nextCol;
		}

		colNode.nextCol = newNode;
		this.rowTail = newNode;

		for (int counter = 1; counter < values.length; counter++) {
			Array2DNode rowNode = new Array2DNode(values[counter]);
			newNode.nextRow = rowNode;// sets row Node
			colNode = colNode.nextRow;// changes the column node to one below so its ready to attach by column
			newNode = newNode.nextRow;// sets the newNode to the node it just attached itself to
			colNode.nextCol = newNode;// sets the column node attachment to the newNode
		}

		this.colSize++;
	}

	/**
	 * Adds a row of nodes to be connected to the current last row of nodes
	 * 
	 * @param values are the values that will be plugged into the new nodes
	 */
	public void addLastRow(E... values) {
		Array2DNode rowNode = head;

		Array2DNode newNode = new Array2DNode(values[0]);

		if (this.isEmpty()) {
			this.head = this.colTail = this.rowTail = newNode;
			this.colSize++;
		}
		if (values.length != this.colSize) {
			throw new IllegalArgumentException("The number of rows don't match");
		}

		while (rowNode.nextRow != null) {
			rowNode = rowNode.nextRow;
		}

		rowNode.nextRow = newNode;
		this.colTail = newNode;

		for (int counter = 1; counter < values.length; counter++) {
			Array2DNode colNode = new Array2DNode(values[counter]);
			newNode.nextCol = colNode;
			rowNode = rowNode.nextCol;
			newNode = newNode.nextCol;
			rowNode.nextRow = newNode;
		}

		this.rowSize++;
	}

	/**
	 * Inserts a column of nodes at the given index with the given values being the
	 * item values for the nodes
	 * 
	 * @param index  is the index where the nodes will be inserted
	 * @param values are the values the new node's item variables will have
	 */
	public void insertCol(int index, E... values) {

		if (index == 0) {
			this.addFirstCol(values);
		} 
		else if (index < 0 || index > this.colSize) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		} 
		else if (values.length != this.rowSize) {
			throw new IllegalArgumentException("The number of values don't match the number of columns");
		} 
		else if (index == this.colSize) {
			this.addLastCol(values);
		} else {
			Array2DNode current = head;
			int colCount = 0;

			while (index - 1 > colCount) {
				current = current.nextCol;
				colCount++;
			}

			int rowIndex = 0;
			Array2DNode tempCurrent = current;
			Array2DNode prev = new Array2DNode(values[rowIndex]);// insert node
			Array2DNode tempPrev = prev;
			rowIndex++;// 1

			while (rowIndex < values.length + 1) {
				if (rowIndex == values.length) {
					prev.nextCol = current.nextCol;
					rowIndex++;
				} else {
					prev.nextCol = current.nextCol;// insert node
					current = current.nextRow;
					prev.nextRow = new Array2DNode(values[rowIndex]);
					prev = prev.nextRow;// insert node
					rowIndex++;
				}
			}
			int count = 0;
			while (tempCurrent.nextRow != null) {
				tempCurrent.nextCol = tempPrev;
				tempCurrent = tempCurrent.nextRow;
				tempPrev = tempPrev.nextRow;
			}
			Array2DNode something = this.colTail;
			for (int counter = 0; count < index - 1; count++) {
				something = something.nextCol;
			}
			something.nextCol = prev;
			this.colSize++;
		}

	}

	/**
	 * inserts a set of values, making new nodes for each value and inserting it at
	 * the given index
	 * 
	 * @param index  is the index where the values will be inserted
	 * @param values are the values that will be plugged into the new nodes
	 */
	public void insertRow(int index, E... values) {

		if (index == 0) {
			this.addFirstRow(values);
		} 
		else if (index < 0 || index > this.rowSize) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		} 
		else if (index == this.rowSize) {
			this.addLastRow(values);
		} 
		else if (values.length != this.colSize) {
			throw new IllegalArgumentException("The number of values don't match the number of columns");
		} 
		else {
			Array2DNode current = head;
			int rowCount = 0;

			while (index - 1 > rowCount) {
				current = current.nextRow;
				rowCount++;
			}

			int colIndex = 0;
			Array2DNode tempCurrent = current;
			Array2DNode prev = new Array2DNode(values[colIndex]);// insert node
			Array2DNode tempPrev = prev;
			colIndex++;// 1

			while (colIndex < values.length) {
				prev.nextRow = current.nextRow;// insert node
				current = current.nextCol;
				prev.nextCol = new Array2DNode(values[colIndex]);
				prev = prev.nextCol;// insert node
				colIndex++;
			}

			while (tempCurrent.nextCol != null) {
				tempCurrent.nextRow = tempPrev;
				tempCurrent = tempCurrent.nextCol;
				tempPrev = tempPrev.nextCol;
			}
			this.rowSize++;
		}
	}

	/**
	 * Deletes the first column of nodes
	 */
	public void deleteFirstCol() {
		System.out.println("yo");
		if (isEmpty()) {
			System.out.println("There are no possible nodes to delete");
		} else if (this.rowSize == 1 && this.colSize == 1) {
			head = colTail = rowTail = null;
		} else {
			this.colTail = this.colTail.nextCol;
			this.rowTail = this.rowTail.nextRow;
			Array2DNode current = head;
			Array2DNode prev = head;
			head = current.nextCol;
			prev = null;
			this.colSize--;

		}
	}

	/**
	 * Deletes the first row of nodes
	 */
	public void deleteFirstRow() {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to delete");
		} else if (this.rowSize == 1 && this.colSize == 1) {
			head = colTail = rowTail = null;
		} else {
			Array2DNode current = head;
			this.rowTail = this.rowTail.nextRow;
			Array2DNode prev = head;
			head = current.nextRow;
			prev = null;
			this.rowSize--;
		}
	}

	/**
	 * Deletes the last column of nodes
	 */
	public void deleteLastCol() {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to delete");
		} else if (this.rowSize == 1 && this.colSize == 1) {
			head = colTail = rowTail = null;
		} else {
			int colCount = 0;
			Array2DNode current = head;
			while (colCount != this.colSize - 2) {
				current = current.nextCol;
				colCount++;
			}
			while (current != null) {
				if (colCount == this.colSize - 2) {
					this.rowTail = current;
					colCount = -1;
				}
				current.nextCol = null;
				current = current.nextRow;
			}
			this.colSize--;
		}
	}

	/**
	 * Deletes the last row of nodes
	 */
	public void deleteLastRow() {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to delete");
		} else if (this.rowSize == 1 && this.colSize == 1) {
			head = colTail = rowTail = null;
		} else {
			int rowCount = 0;
			Array2DNode current = head;
			while (rowCount != this.rowSize - 2) {
				current = current.nextRow;
				rowCount++;
			}
			while (current != null) {
				if (rowCount == this.rowSize - 2) {
					this.colTail = current;
					rowCount = -1;
				}
				current.nextRow = null;
				current = current.nextCol;
			}
			this.rowSize--;
		}

	}

	/**
	 * Deletes a column of nodes at the given index
	 * 
	 * @param index is the amount of columns that have to be skipped over
	 */
	public void deleteCol(int index) {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to delete");
		} else if ((index < 0 || index > this.colSize)) {
			throw new IndexOutOfBoundsException("One or both of the indices are out of bounds");
		} else if (index == this.colSize) {
			deleteLastCol();
		} else if (index == 0) {
			deleteFirstCol();
		} else {
			int colIndex = 0;
			Array2DNode current = head;
			while (colIndex != index - 1) {
				current = current.nextCol;
				colIndex++;
			}
			Array2DNode prev = current.nextCol;
			while (current != null) {
				current.nextCol = current.nextCol.nextCol;
				current = current.nextRow;
			}
			while (prev != null) {
				prev = null;
				this.colSize--;
			}
		}
	}

	/**
	 * Deletes a row of nodes at the given index
	 * 
	 * @param index is the amount of rows that have to be skipped over
	 */
	public void deleteRow(int index) {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to delete");
		} else if ((index < 0 || index > this.rowSize)) {
			throw new IndexOutOfBoundsException("One or both of the indices are out of bounds");
		} else if (index == 0) {
			deleteFirstRow();
		} else if (index == this.rowSize) {
			deleteLastRow();
		} else {
			int rowIndex = 0;
			Array2DNode current = head;
			while (rowIndex != index - 1) {
				current = current.nextRow;
				rowIndex++;
			}
			Array2DNode prev = current.nextRow;
			while (current != null) {
				current.nextRow = current.nextRow.nextRow;
				current = current.nextCol;
			}
			while (prev != null) {
				prev = null;
				this.rowSize--;
			}
		}
	}

	/**
	 * Returns the item value stored at the node at rowIndex and colIndex
	 * 
	 * @param rowIndex is the row you go to
	 * @param colIndex is the column you go to
	 * @return the item value at rowIndex and colIndex
	 */
	public E get(int rowIndex, int colIndex) {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to get");
		} else if ((rowIndex < 0 || rowIndex > this.rowSize) || (colIndex < 0 || colIndex > this.colSize)) {
			throw new IndexOutOfBoundsException("One or both of the indices are out of bounds");
		} else {
			int rowCount = 0;
			int colCount = 0;
			Array2DNode current = this.head;
			while (rowCount != rowIndex) {
				current = current.nextRow;
				rowCount++;
			}
			while (colCount != colIndex) {
				current = current.nextCol;
				colCount++;
			}
			return (E) current.item;
		}
		return null;
	}

	/**
	 * Gets the item values stored at the specified column into an arrayList
	 * 
	 * @param colIndex is the column you go to
	 * @return an arrayList that has all the item values at the column for colIndex
	 */
	public ArrayList<E> getCol(int colIndex) {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to get");
		} else if ((colIndex < 0 || colIndex > this.colSize - 1)) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		} else {
			ArrayList<E> returnList = new ArrayList<E>();
			Array2DNode current = head;
			int colCounter = 0;
			while (colCounter != colIndex) {
				current = current.nextCol;
				colCounter++;
			}
			while (current != null) {
				// gets all the elements in the column
				returnList.add((E) current.item);
				current = current.nextRow;
			}
			return returnList;
		}
		return null;
	}

	/**
	 * Gets the item values stored at the specified row into an arrayList
	 * 
	 * @param rowIndex is the row you go to
	 * @return an arrayList that has all the item values at the row for rowIndex
	 */
	public ArrayList<E> getRow(int rowIndex) {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to get");
		} else if ((rowIndex < 0 || rowIndex > this.rowSize - 1)) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		} else {
			ArrayList<E> returnList = new ArrayList<E>();
			Array2DNode current = head;
			int rowCounter = 0;
			while (rowCounter != rowIndex) {
				current = current.nextRow;
				rowCounter++;
			}
			while (current != null) {
				// gets all the elements in the column
				returnList.add((E) current.item);
				current = current.nextCol;
			}
			return returnList;
		}
		return null;
	}

	/**
	 * Sets a new item value to the node that is stored at the given rowIndex and
	 * colIndex
	 * 
	 * @param rowIndex is the row you go to
	 * @param colIndex is the column you go to
	 * @param item     is the new value you will use to change the previously
	 *                 assigned value to
	 */
	public void set(int rowIndex, int colIndex, E item) {

		if (isEmpty()) {
			System.out.println("There are no possible nodes to get");
		} else if ((rowIndex < 0 || rowIndex > this.rowSize) || (colIndex < 0 || colIndex > this.colSize)) {
			throw new IndexOutOfBoundsException("One or both of the indices are out of bounds");
		} else {
			int rowCount = 0;
			int colCount = 0;
			Array2DNode current = head;
			while (rowCount != rowIndex) {
				current = current.nextRow;
				rowCount++;
			}
			while (colCount != colIndex) {
				current = current.nextCol;
				colCount++;
			}
			current.item = item;
		}
	}

	/**
	 * 
	 * @return the current size of the columns
	 */
	public int colSize() {
		return this.colSize;
	}

	/**
	 * 
	 * @return the current size of the rows
	 */
	public int rowSize() {
		return this.rowSize;
	}

	/**
	 * Makes a String of the nodes of the 2D Linked List going by rows
	 * 
	 * @return the current nodes going row by row
	 */
	@Override
	public String toString() {
		
		Array2DNode current = head;
		Array2DNode headStorage = head;
		String returnVal = "";
		while (current != null) {

			if (current.nextCol == null) {
				returnVal += current.item + "\n";
				current = head;
				head = current.nextRow;
				current = head;

			} else {
				returnVal += current.item + ", ";
				current = current.nextCol;
			}

		}
		head = headStorage;
		return returnVal;
	}

	/**
	 * Makes a String of the nodes of the 2D Linked List going by columns
	 * 
	 * @return the current nodes going column by column
	 */
	public String toStringColByCol() {
		
		Array2DNode current = head;
		Array2DNode headStorage = head;
		String returnVal = "";
		while (current != null) {

			if (current.nextRow == null) {
				returnVal += current.item + "\n";
				current = head;
				head = current.nextCol;
				current = head;

			} else {
				returnVal += current.item + ", ";
				current = current.nextRow;
			}

		}
		head = headStorage;
		return returnVal;
	}

	// private methods
	/**
	 * Checks the head and tails and sizes of the rows and columns
	 * 
	 * @return whether or not the head and tails are null and the sizes are 0
	 */
	private boolean isEmpty() {
		return this.head == null && this.colTail == null && this.colSize == 0 && this.rowTail == null
				&& this.rowSize == 0;
	}

}
