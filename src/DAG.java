import java.util.Set;

public class DAG<Key extends Comparable<Key>, Value>
{

	private int vertices;
	private Bag<DAGNode> nodes;

	DAG()
	{
		this.vertices = 0;
		this.nodes = (Bag<DAGNode>) new Bag();
	}

	private class DAGNode
	{
		private Key key;
		private Value val;
		public Bag<DAGNode> descendants;
		public int inDegree;

		public DAGNode(Key key, Value val)
		{
			this.key = key;
			this.val = val;
			this.inDegree = 0;
			this.descendants = (Bag<DAGNode>) new Bag();
		}

		public Key key()
		{
			return this.key;
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

	public Set<Key> getLCA(Key keyA, Key keyB)
	{

		return null;
	}

	public boolean insertEdge(Key key1, Key key2)
	{
		// TODO
		return false;
	}

	private boolean isDAGCycle(DAGNode source, DAGNode dest)
	{
		// TODO
		return false;
	}

}
