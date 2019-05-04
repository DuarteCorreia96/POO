package graph;
import java.util.ArrayList;

public class TSPGraph implements Graph{
	
	private final int n;
	private ArrayList<Node> nodes;
	
	public TSPGraph(int n) {
		this.n = n;
		nodes = new ArrayList<Node>(n);
		
		for(int j=0; j<n; j++)
			nodes.add(j, new Node());
	}
	
	@Override
	public int getSize() {
		return n;
	}
	
	@Override
	public Vertex findVertex(int id) {
		if(id > n)
			return null;
		
		return nodes.get(id-1);
	}
	
	private boolean existingVertices(Vertex v1, Vertex v2) {
		if(v1 == null)	return false;
		if(v2 == null)	return false;	
		return true;
	}

	@Override
	public boolean addEdge(int id1, int id2) {		
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		if(!existingVertices(v1,v2))	return false;
		
		return (v1.addEdge(v2, DEFAULT_EDGE_WEIGHT) && v2.addEdge(v1, DEFAULT_EDGE_WEIGHT));
	}

	@Override
	public boolean addEdge(int id1, int id2, int weight) {	
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		if(!existingVertices(v1,v2))	return false;
		
		if(weight < 1)	return false;
		
		return (v1.addEdge(v2, weight) && v2.addEdge(v1, weight));
	}

	@Override
	public void addVertex() {
		Node n = new Node();
		nodes.add(n);
	}
	
	public boolean containsEdge(int id1, int id2) {
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		if(!existingVertices(v1,v2))	return false;
		
		if(((Node)v1).equals((Node)v2))
			return false;
		
		if(v1.containsEdge(v2))
			return false;
		
		return true;
	}
	
	@Override
	public boolean containsVertex(int id) {
		return (findVertex(id) != null)? true:false;
	}

	@Override
	public int degreeOf(int id) {
		Vertex v = findVertex(id);
		if(!containsVertex(id))	return 0;
		return v.degreeOf();
	}

	@Override
	public Edge[] getAllEdges(int id) {
		Vertex v = findVertex(id);
		if(!containsVertex(id))	return new Edge[0];
		return v.getAllEdges();
	}

	@Override
	public int getEdgeWeight(int id1, int id2) {
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		if(!existingVertices(v1,v2))	return 0;
		
		for(Node j:nodes) {
			if(j.equals(v1)) {
				return j.findEdge(v2).getWeight();
			}
		}
		
		return 0;
	}

	@Override
	public boolean removeEdge(int id1, int id2) {
		return false;
	}

	@Override
	public boolean removeVertex(int id) {		
		return false;
	}

	@Override
	public boolean setEdgeWeight(int id1, int id2, int weight) {
		
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		if(!existingVertices(v1,v2))	return false;
		
		if(weight < 1)	return false;
		
		for(Node j:nodes) {
			if(j.equals(v1)) {
				j.findEdge(v2).setWeight(weight);
				break;
			}
		}
		
		for(Node j:nodes) {
			if(j.equals(v2)) {
				j.findEdge(v1).setWeight(weight);
				break;
			}
		}	
		return true;
		
	}

	@Override
	public Vertex[] getAllVertices() {
		return (Vertex[])nodes.toArray();
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
