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
}