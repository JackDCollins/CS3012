import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class Tests
{

	@Test
	public void testPrints()
	{
		// Check Null/Empty
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		assertEquals("Check Empty Tree print", "()", tree.printKeysInOrder());

		// Check Left
		tree = new BST<Integer, Integer>();
		tree.put(30, 30);
		tree.put(45, 45);
		assertEquals("Check Left Node", "(()30(()45()))", tree.printKeysInOrder());

		// Check Right
		tree = new BST<Integer, Integer>();
		tree.put(10, 10);
		tree.put(30, 30);
		assertEquals("Check print in order with right child node", "(()10(()30()))", tree.printKeysInOrder());
	}

	
	@Test
	public void testContains()
	{
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		assertEquals("Check Doesnt Contain", false, tree.contains(10));
		tree.put(5, 5);
		assertEquals("Check Doesnt contain 15", false, tree.contains(15));
		assertEquals("Check Contins 5", true, tree.contains(5));

	}
	
	
	@Test
	public void testCalls() {

		// Check Empty
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		assertNull("Check Empty", tree.get(1));

		// Check 2 Node tree
		tree = new BST<Integer, Integer>();
		tree.put(30, 30);
		tree.put(50, 50);
		assertEquals("Check Root 30", "30", String.valueOf(tree.get(30)));
		assertEquals("Check Solo Right Node", "50", String.valueOf(tree.get(50)));
		
		tree = new BST<Integer, Integer>();
		tree.put(80, 80);
		tree.put(90, 90);
		tree.put(40, 40);
		tree.put(20, 20);
		tree.put(30, 30);
		tree.put(70, 70);
		tree.put(50, 50);
		tree.put(60, 60);

		tree = new BST<Integer, Integer>();
		tree.put(10, 10);
		tree.put(20, 20);
		tree.put(5, 5);
		assertEquals("Check Right Node", "20", String.valueOf(tree.get(20)));
		assertEquals("Check Left Node", "5", String.valueOf(tree.get(5)));
		assertEquals("Check the ability to get root node with two childs and that is all.", "10",
				String.valueOf(tree.get(10)));

		

		assertNull("Check no Node", tree.get(0));
		assertEquals("Check Node Right", "90", String.valueOf(tree.get(90)));
		assertEquals("Check Left Node", "30", String.valueOf(tree.get(30)));
		assertEquals("Check Double Node", "40", String.valueOf(tree.get(40)));

	}

	
	
}