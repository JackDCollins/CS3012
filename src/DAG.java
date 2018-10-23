import java.util.HashSet;
import java.util.Set;

public class DAG<Key extends Comparable<Key>, Value>
{

	private int vertices;
	public Bag<DAGNode> nodes;

	DAG()
	{
		this.vertices = 0;
		this.nodes = (Bag<DAGNode>) new Bag();
	}

	private class DAGNode
	{
		private Key key;
		private Value val;
		public Bag<DAGNode> children;
		public int vertices;

		public DAGNode(Key key, Value val)
		{
			this.key = key;
			this.val = val;
			this.vertices = 0;
			this.children = (Bag<DAGNode>) new Bag();
		}

		public Key key()
		{
			return this.key;
		}

		public void addChild(DAGNode node)
		{
			children.add(node);
		}

	}

	// returns the size of the graph
	public int size()
	{
		return nodes.size();
	}

	public boolean put(Key key, Value val)
	{
		if (val != null && !contains(key))
		{
			nodes.add(new DAGNode(key, val));
			this.vertices++;
			return true;
		}
		return false;
	}

	public boolean contains(Key key)
	{
		boolean r = get(key) != null;
		return r;
	}

	public Value get(Key key)
	{
		Value answer = null;
		if (key != null) for (DAGNode node : nodes)
			if (key.equals(node.key())) answer = node.val;
		return answer;
	}

	private DAGNode getNode(Key key)
	{
		DAGNode answer = null;
		if (key != null) for (DAGNode node : nodes)
			if (key.equals(node.key())) answer = node;
		return answer;
	}

	public boolean insertEdge(Key key1, Key key2)
	{
		if (key1 != null && key2 != null && (!key1.equals(key2)) && (contains(key1) && contains(key2)))
		{
			DAGNode source = getNode(key1);
			DAGNode dest = getNode(key2);

			if (!hasEdge(source, dest) && !isCycle(dest, source))
			{
				source.addChild(dest);
				dest.vertices++;
				return true;
			}
		}
		return false;
	}

	private boolean hasEdge(DAGNode source, DAGNode dest)
	{
		for (DAGNode node : source.children)
			if (node.key.equals(dest.key())) return true;
		return false;
	}

	private boolean isCycle(DAGNode source, DAGNode dest)
	{
		// TODO
		return false;
	}

	private boolean dfs(Set<DAGNode> set, DAGNode n, Key key)
	{
		if (n.key().equals(key))
		{
			set.add(n);
			return true;
		} else
		{
			boolean res = false;
			for (DAGNode child : n.children)
				res |= dfs(set, child, key);
			if (res) set.add(n);
			return res;
		}

	}

	public Set<Key> getLCA(Key keyA, Key keyB)
	{

		Set<Key> answer = null;

		if (contains(keyA) && contains(keyB))
		{
			answer = new HashSet<Key>();
			Set<DAGNode> keyANodes = new HashSet<DAGNode>();
			Set<DAGNode> keyBNodes = new HashSet<DAGNode>();
			Set<DAGNode> commonNodes = new HashSet<DAGNode>();

			for (DAGNode node : this.nodes)
				if (node.vertices == 0)
				{
					dfs(keyANodes, node, keyA);
					dfs(keyBNodes, node, keyB);
				}

			for (DAGNode n : commonNodes)
			{
				boolean hasChild = false;
				for (DAGNode child : n.children)
					if (commonNodes.contains(child)) hasChild = true;
				if (!hasChild) answer.add(n.key());
			}
		}
		return answer;
	}

}
