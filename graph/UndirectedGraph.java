package graph;
import java.util.ArrayList;

public class UndirectedGraph implements Graph{
	
	private int n;
	private ArrayList<Node> nodes;
	
	public UndirectedGraph(int n) {
		this.n = n;
		nodes = new ArrayList<Node>(n);
		
		/*for(int j=0; j<n; j++)
			nodes.add(j, new Node());*/
	}
	
	@Override
	public int getSize() {
		return n;
	}

	@Override
	public boolean addEdge(Vertex v1, Vertex v2) {		
		return (v1.addEdge(v2, DEFAULT_EDGE_WEIGHT) && v2.addEdge(v1, DEFAULT_EDGE_WEIGHT));
	}

	@Override
	public boolean addEdge(Vertex v1, Vertex v2, int weight) {		
		return (v1.addEdge(v2, weight) && v2.addEdge(v1, weight));
	}

	@Override
	public Vertex addVertex() {
		
		Node n = new Node();
		nodes.add(n);
		
		return n;
	}

	@Override
	public boolean addVertex(Vertex v) {
		
		if(containsVertex(v))
			return false;
		
		nodes.add((Node)v);
		return true;
	}
	
	public boolean containsEdge(Vertex v1, Vertex v2) {
				
		if(((Node)v1).equals((Node)v2))
			return false;
		
		if(v1.containsEdge(v2))
			return false;
		
		return true;
	}
	
	@Override
	public boolean containsVertex(Vertex v) {
		
		for(Node j:nodes) {
			if(j.equals((Node)v))
				return true;
		}
			
		return false;
	}

	@Override
	public int degreeOf(Vertex v) {
		return v.degreeOf();
	}

	@Override
	public Edge[] getAllEdges(Vertex v) {		
		return v.getAllEdges();
	}

	@Override
	public int getEdgeWeight(Vertex v1, Vertex v2) {

		for(Node j:nodes) {
			if(j.equals(v1)) {
				return j.findEdge(v2).getWeight();
			}
		}
		return 0;
	}

	@Override
	public boolean removeEdge(Vertex v1, Vertex v2) {			
		return (v1.removeEdge(v2) && v2.removeEdge(v1));
	}

	@Override
	public boolean removeVertex(Vertex v) {
		
		for(Node j:nodes)
			j.removeEdge(v);
		
		nodes.remove((Node)v);
		
		return true;
	}

	@Override
	public void setEdgeWeight(Vertex v1, Vertex v2, int weight) {
		
		for(Node j:nodes) {
			if(j.equals(v1)) {
				j.findEdge(v2).setWeight(weight);
				break;
			}
		}
		
	}

	@Override
	public Vertex[] vertexArray() {
		return (Vertex[])nodes.toArray();
	}
	
	@Override
	public Vertex findVertex(Vertex v) {
		
		for(Node j:nodes) {
			if(j.equals((Node)v))
				return j;
		}
		
		return null;		
	}
	
	@Override
	public Vertex findVertex(int id) {
		
		for(Node j:nodes) {
			if(j.getId() == id)
				return j;
		}
		
		return null;		
	}
	
	@Override
	public String toString() {
		
		String str = "";
		for(int j=0; j<n; j++) {
			str += nodes.get(j).print();
		}
		
		return "\n-> Graph \n\n" + str;
	}

}
