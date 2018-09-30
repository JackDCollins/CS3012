import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import jdk.internal.org.objectweb.asm.tree.analysis.Value;

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
	public void testCalls()
	{

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

		assertNull("Check no Node", tree.get(0));
		assertEquals("Check Node Right", "90", String.valueOf(tree.get(90)));
		assertEquals("Check Left Node", "30", String.valueOf(tree.get(30)));
		assertEquals("Check Double Node", "40", String.valueOf(tree.get(40)));

		tree = new BST<Integer, Integer>();
		tree.put(10, 10);
		tree.put(20, 20);
		tree.put(5, 5);
		assertEquals("Check Right Node", "20", String.valueOf(tree.get(20)));
		assertEquals("Check Left Node", "5", String.valueOf(tree.get(5)));
		assertEquals("Check the ability to get root node with two childs and that is all.", "10",
				String.valueOf(tree.get(10)));

	}

	@Test
	public void DeleteTest()
	{
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.delete(1);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

		bst.put(7, 7); // _7_
		bst.put(8, 8); // / \
		bst.put(3, 3); // _3_ 8
		bst.put(1, 1); // / \
		bst.put(2, 2); // 1 6
		bst.put(6, 6); // \ /
		bst.put(4, 4); // 2 4
		bst.put(5, 5); // \
						// 5

		assertEquals("Checking order of constructed tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
				bst.printKeysInOrder());

		bst.delete(9);
		assertEquals("Deleting non-existent key", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

		bst.delete(6);
		assertEquals("Deleting node with single child", "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

		bst.delete(3);
		assertEquals("Deleting node with two children", "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
	}

	@Test
	public void testHeight()
	{
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		assertEquals("Empty Height", "-1", "" + tree.height());

		tree.put(100, 100);
		tree.put(200, 200);
		tree.put(300, 300);
		tree.put(150, 150);
		tree.put(250, 250);
		tree.put(600, 600);
		tree.put(50, 50);
		tree.put(40, 40);

		assertEquals("Check the height of the BST", "3", "" + tree.height());

		tree = new BST<Integer, Integer>();
		tree.put(1, 1);
		tree.put(2, 2);

		assertEquals("Check 1 Node Height", "1", String.valueOf(tree.height()));

	}

	@Test
	public void testPut()
	{
		// Check Null
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		tree.put(1, null);
		assertNull("Check Null", tree.get(1));

		// Check Put
		tree = new BST<Integer, Integer>();
		tree.put(80, 80);
		tree.put(90, 90);
		tree.put(40, 40);
		tree.put(20, 20);
		tree.put(30, 30);
		tree.put(70, 70);
		tree.put(50, 50);
		tree.put(60, 60);

		// root
		assertEquals("Check the ability to get root node.", "80", "" + tree.get(80));
		// Level 1
		assertEquals("Check Left", "30", "" + tree.get(30));
		assertEquals("Check Right", "90", "" + tree.get(90));
		// Level 2
		assertEquals("Check Right Child", "20", "" + tree.get(20));
		assertEquals("Check Left Child", "70", "" + tree.get(70));
		assertEquals("Check Two Child Node", "40", "" + tree.get(40));
		// assertNull("Check the ability to show an error if there is no node to
		// check", tree.get(100));

		// Check Double put
		tree = new BST<Integer, Integer>();
		tree.put(100, 100);
		tree.put(100, 100);
		assertEquals("Check Double put with same key", Integer.valueOf(100), tree.get(100));
	}

	@Test
	public void testLCA()
	{

		// simple above node LCA
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		tree.put(80, 80);
		tree.put(40, 40);
		tree.put(100, 100);
		tree.put(20, 20);
		tree.put(60, 60);

		System.out.println(tree.printKeysInOrder());

		// test simple 1 above
		assertEquals("", Integer.valueOf(40), tree.getLCA(20, 60));

		// test non existant node
		assertEquals("", null, tree.getLCA(30, 60));
		
		//check same ancentor is  including self
		assertEquals("", Integer.valueOf(40), tree.getLCA(60, 40));
	}

	@Test
	public void testFindPath()
	{
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		List<Integer> test1 = new ArrayList<>();
		assertEquals("", null, tree.getLCA(30, 60));
		
		
		tree.put(80, 80);
		tree.put(40, 40);
		tree.put(100, 100);
		tree.put(20, 20);
		tree.put(60, 60);
		//check simple path
		assertEquals(tree.findPath(tree.root, Integer.valueOf(20), test1),true);
		
		//check no path
		assertEquals(tree.findPath(tree.root.left, Integer.valueOf(100), test1),false);
		
		//check same item is true
		assertEquals(tree.findPath(tree.root, Integer.valueOf(80), test1),true);
	}

}