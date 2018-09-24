
public class BST<Key extends Comparable<Key>, Value>
	{
		public static final int EMPTY_ERROR = -1;
		public static final String NULL_STRING = "-null";
		public static final String NEXT_LINE = "\n";
		private Node root; // root of BST

		/**
		 * Private node class.
		 */
		private class Node
		{
			private Key key; // sorted by key
			private Value val; // associated data
			private Node left, right; // left and right subtrees
			private int N; // number of nodes in subtree

			public Node(Key key, Value val, int N)
			{
				this.key = key;
				this.val = val;
				this.N = N;
			}
		}
	}

