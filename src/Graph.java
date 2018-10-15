
public class Graph
{
	Node root;

	class Node
	{
		int value;
		Node left;
		Node right;

		Node(int value)
		{
			this.value = value;
			right = null;
			left = null;
		}
	}

	public void add(int value)
	{
		root = addRecursive(root, value);
	}

	private Node addRecursive(Node current, int value)
	{
		if (current == null) { return new Node(value); }

		if (value < current.value)
		{
			current.left = addRecursive(current.left, value);
		} else if (value > current.value)
		{
			current.right = addRecursive(current.right, value);
		} else
		{
			// value already exists
			return current;
		}

		return current;
	}

	// https://www.baeldung.com/java-binary-tree
}