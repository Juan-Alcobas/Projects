package hw05;

/**
 * This class is designed to test the methods and classes of my Array2D and
 * Array2DNode classes
 * 
 * @author Juan Alcobas, 4186523, CS2013 - 09/10
 *
 */
public class Array2DTester {

	public static void main(String[] args) {
		
		Array2D test=new Array2D();
		
		test.insertRow(0, 0);

		test.insertCol(0,1);
		test.insertCol(0,2);

		System.out.println(test.toString());
		System.out.println(test.head);
		System.out.println(test.colTail);
		System.out.println(test.rowTail);
		test.insertRow(0, 21,5,17);
		System.out.println(test.head);
		System.out.println(test.colTail);
		System.out.println(test.rowTail);

		System.out.println(test.toString());

		test.insertCol(3, 9,8);
	
		System.out.println(test.toString());

		test.insertRow(1, 10,11,13,14);
		

		System.out.println(test.toString());
		
		test.insertCol(2, 99,99,99);
		
		System.out.println(test.toString());
		
		test.deleteCol(1);
		
		System.out.println(test.toString());
		
		test.deleteRow(0);
		
		System.out.println(test.toString());
//		System.out.println(test.toStringColByCol());
		

	}

}
