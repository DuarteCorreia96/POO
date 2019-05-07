package graph;

/**
 * The {@code UndirectGraph} class implements the methods provided by the {@code Graph} interface. As the name of the class suggests
 * it implements a graph whose edges are not directed, there is no distinction between inward or outward edges. Furthermore, it was established
 * that the number of nodes of the graph remains constant for all times after an instance of this class is created. The size of the graph is kept
 * on a <i>final</i> variable.<p>
 * 
 * For the reasons above mentioned, methods for inward or outward edges were labelled as <i>@Depreciated</i>. Similarly, no implementation was
 * provided for {@code Graph.removeEdge(...)}.<p>
 * 
 * The vertices of the graph are stored in a fixed-size array ordered by their index. This graph is implemented using 
 * <strong>adjacency lists</strong> and, therefore, the methods of {@code Vertex} are fully employed.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see Node
 */

public class UndirectedGraph implements Graph{
	
	protected final int n;
	protected Node[] nodes;
	
	public UndirectedGraph(int n) {
		this.n = n;
		nodes = new Node[n];
		
		for(int j=0; j<n; j++)
			nodes[j] = new Node();
	}
		
	@Override
	public int getSize() {
		return n;
	}
	
	@Override
	public Vertex findVertex(int id) {
		if(id > n || id < 1)
			return null;
		
		return nodes[id-1];
	}

	@Override
	public boolean addEdge(int id1, int id2) {		
		return addEdge(id1,id2,DEFAULT_EDGE_WEIGHT);
	}

	@Override
	public boolean addEdge(int id1, int id2, int weight) {
		if(containsEdge(id1,id2))	return false;
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		
		if(weight < 1)	return false;
		
		return (v1.addEdge(v2, weight) && v2.addEdge(v1, weight));
	}

	@Deprecated
	public void addVertex() {}
	
	@Override
	public boolean containsEdge(int id1, int id2) {
		if(!containsVertex(id1) || !containsVertex(id2))	return false;
		
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		
		if(((Node)v1).equals((Node)v2))
			return false;
		
		if(!v1.containsEdge(v2))
			return false;
		
		return true;
	}
	
	@Override
	public boolean containsVertex(int id) {
		return (findVertex(id) != null)? true:false;
	}

	@Override
	public int degreeOf(int id) {
		if(!containsVertex(id))	return 0;
		return findVertex(id).degreeOf();
	}
	
	@Deprecated
	public int degreeOfInward(int id) {
		if(!containsVertex(id))	return 0;
		return findVertex(id).degreeOf();
	}
	
	@Deprecated
	public int degreeOfOutward(int id) {
		if(!containsVertex(id))	return 0;
		return findVertex(id).degreeOf();
	}

	@Override
	public Edge[] getAllEdges(int id) {
		if(!containsVertex(id))	return new Edge[0];
		return findVertex(id).getAllEdges();
	}
	
	@Deprecated
	public Edge[] getInwardEdges(int id) {
		if(!containsVertex(id))	return new Edge[0];
		return findVertex(id).getInwardEdges();
	}
	
	@Deprecated
	public Edge[] getOutwardEdges(int id) {
		if(!containsVertex(id))	return new Edge[0];
		return findVertex(id).getOutwardEdges();
	}

	@Override
	public int getEdgeWeight(int id1, int id2) {
		if(!containsEdge(id1,id2))	return 0;
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		
		return v1.findEdge(v2).getWeight();
	}

	@Override
	public boolean removeEdge(int id1, int id2) {
		if(!containsEdge(id1,id2))	return false;
		Vertex v1 = findVertex(id1);
		Vertex v2 = findVertex(id2);
		
		return (v1.removeEdge(v2) && v2.removeEdge(v1));
	}

	@Deprecated
	public boolean removeVertex(int id) {
		return false;
	}

	@Override
	public boolean setEdgeWeight(int id1, int id2, int weight) {
		if(!containsEdge(id1,id2))	return false;
		
		if(weight < 1)	return false;
		
		findVertex(id1).findEdge(findVertex(id2)).setWeight(weight);
		findVertex(id2).findEdge(findVertex(id1)).setWeight(weight);
		
		return true;
		
	}

	@Override
	public Vertex[] getAllVertices() {
		return nodes;
	}
		
	@Override
	public String toString() {
		
		String str = "";
		for(int j=0; j<n; j++) {
			str += nodes[j];
		}
		
		return "\n-> Graph \n\n" + str;
	}

}