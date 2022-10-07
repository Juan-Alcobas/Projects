package hw05;

/**
 * This class is designed to create the bluePrint for a 2 dimensional node
 * 
 * @author Juan Alcobas, 4186523, CS2013 - 09/10
 *
 */
public class Array2DNode<E> {
	protected E item;
	protected Array2DNode nextCol;
	protected Array2DNode nextRow;
	/**
	 * Constructor for a node that atkes an item of type E
	 * @param item is used for the instance variables value
	 */
	public Array2DNode(E item) {
		this.item = item;
		this.nextCol = null;
		this.nextRow = null;
	}
	/**
	 * The toString method for printing out the item value of the node
	 */
	@Override
	public String toString() {
		return this.item.toString();
	}

//	public E getItem() {
//		return item;
//	}
//
//	public void setItem(E item) {
//		this.item = item;
//	}
}
