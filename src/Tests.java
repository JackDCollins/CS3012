import static org.junit.Assert.assertEquals;

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

	public void testContains()
	{
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		assertEquals("Check Doesnt Contain", false, tree.contains(10));
		tree.put(5, 5);
		assertEquals("Check Doesnt contain 15", false, tree.contains(15));
		assertEquals("Check Contins 5", true, tree.contains(5));

	}
	
	
}