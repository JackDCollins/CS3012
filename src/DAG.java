
public class DAG<Key extends Comparable<Key>, Value>
{

	private int vertices;
	private Bag<GraphNode> nodes;

	DAG()
	{
		this.vertices = 0;
		this.nodes = (Bag<GraphNode>) new Bag();
	}

	private class GraphNode{
		private Key key;           				
	    private Value val;         
	    public Bag<GraphNode> descendants; 
	    public int inDegree;          

	    public GraphNode(Key key, Value val) {
	      this.key = key;
	      this.val = val;
	      this.inDegree = 0;
	      this.descendants = (Bag<GraphNode>) new Bag();
    }
}