import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * Binary Search Tree class. Adapted from Sedgewick and Wayne.
 *
 * @author Jack Donal Collins
 *
 *         Assignment adapted from 2nd Year Programming Assignment
 *************************************************************************/

public class BST<Key extends Comparable<Key>, Value>
{
	public static final int EMPTY_ERROR = -1;
	public static final String NULL_STRING = "-null";
	public static final String NEXT_LINE = "\n";
	Node root; // root of BST

	/**
	 * Private node class.
	 */

	private List<Value> path1 = new ArrayList<>();
	private List<Value> path2 = new ArrayList<>();

	public class Node
	{
		private Key key; // sorted by key
		private Value val; // associated data
		Node left, right; // left and right subtrees
		private int N; // number of nodes in subtree

		public Node(Key key, Value val, int N)
		{
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public Value getLCA(Value i, Value j)
	{
		path1.clear();
		path2.clear();
		return findLCAInternal(root, i, j);

	}

	private Value findLCAInternal(Node root, Value n1, Value n2)
	{
		if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) return null;
		int i;
		for (i = 0; i < path1.size() && i < path2.size(); i++)
			if (!path1.get(i).equals(path2.get(i))) break;
		return path1.get(i - 1);
	}

	public boolean findPath(Node root, Value n2, List<Value> path)
	{
		// base case
		if (root == null) { return false; }

		// Store this node . The node will be removed if
		// not in path from root to n.
		path.add(root.val);

		if (root.val == n2) { return true; }

		if (root.left != null && findPath(root.left, n2, path)) { return true; }

		if (root.right != null && findPath(root.right, n2, path)) { return true; }

		path.remove(path.size() - 1);

		return false;
	}

	// is the symbol table empty?
	public boolean isEmpty()
	{
		return size() == 0;
	}

	// return number of key-value pairs in BST
	public int size()
	{
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	private int size(Node x)
	{
		if (x == null)
			return 0;
		else
			return x.N;
	}

	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: Theta(N)
	 * Explanation: In the worst case it will have to go through every node in a
	 * straight line fashion
	 * 
	 * @return the number of links from the root to the deepest leaf.
	 *
	 *         Example 1: for an empty tree this should return -1. Example 2:
	 *         for a tree with only one node it should return 0. Example 3: for
	 *         the following tree it should return 2. B / \ A C \ D
	 * 
	 * 
	 */
	public int height()
	{
		return height(root);
	}

	private int height(Node node)
	{
		if (isNull(node)) { return EMPTY_ERROR; }
		if (size(node) == 1) { return 0; }

		return 1 + Math.max(height(node.left), height(node.right));
	}

	/**
	 * Search BST for given key. Does there exist a key-value pair with given
	 * key?
	 *
	 * @param key
	 *            the search key
	 * @return true if key is found and false otherwise
	 */
	public boolean contains(Key key)
	{
		return get(key) != null;
	}

	/**
	 * Search BST for given key. What is the value associated with given key?
	 *
	 * @param key
	 *            the search key
	 * @return value associated with the given key if found, or null if no such
	 *         key exists.
	 */
	public Value get(Key key)
	{
		return get(root, key);
	}

	private Value get(Node x, Key key)
	{
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	/**
	 * Insert key-value pair into BST. If key already exists, update with new
	 * value.
	 *
	 * @param key
	 *            the key to insert
	 * @param val
	 *            the value associated with key
	 */
	public void put(Key key, Value val)
	{
		if (val == null)
		{
			delete(key);
			return;
		}
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val)
	{
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * Print all keys of the tree in a sequence, in-order. That is, for each
	 * node, the keys in the left subtree should appear before the key in the
	 * node. Also, for each node, the keys in the right subtree should appear
	 * before the key in the node. For each subtree, its keys should appear
	 * within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()" Example 2: Tree containing only "A"
	 * -- output: "(()A())" Example 3: Tree: B / \ A C \ D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment:
	 * (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder()
	{
		if (isEmpty()) return parenthesize(new String());
		return parenthesize(printKeysInOrder(root));
	}

	private String printKeysInOrder(Node node)
	{
		String result = new String();
		if (isNull(node))
		{
			return new String();
		} else
		{
			result += parenthesize(printKeysInOrder(node.left)) + node.key + parenthesize(printKeysInOrder(node.right));
			return result;
		}
	}

	/**
	 * Deteles a key from a tree (if the key is in the tree). Note that this
	 * method works symmetrically from the Hibbard deletion: If the node to be
	 * deleted has two child nodes, then it needs to be replaced with its
	 * predecessor (not its successor) node.
	 *
	 * @param key
	 *            the key to delete
	 */

	public void delete(Key key)
	{
		root = delete(root, key);
	}

	private Node delete(Node node, Key key)
	{
		if (node == null) return null;

		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			node.left = delete(node.left, key);
		else if (cmp > 0)
			node.right = delete(node.right, key);
		else
		{
			if (node.right == null) return node.left;

			Node tmp = node;
			node = max(tmp.left);
			node.left = delMax(tmp.left);
			node.right = tmp.right;
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	private Node max(Node node)
	{
		if (node.right == null)
			return node;
		else
			return max(node.right);
	}

	private Node delMax(Node node)
	{
		// Check if Right side doesn't exist
		if (node.right == null) return node.left;
		node.right = delMax(node.right);
		node.N = size(node.right) + size(node.left) + 1;
		return node;
	}

	public static String parenthesize(String s)
	{
		return "(" + s + ")";

	}

	public boolean isNull(Object o)
	{
		if (o != null) return false;

		return true;
	}

}
