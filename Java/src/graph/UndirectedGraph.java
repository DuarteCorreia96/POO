package graph;

/**
 * The {@code UndirectGraph} class implements the methods provided by the {@code Graph} interface. As the name of the class suggests
 * it implements a graph whose edges are not directed, there is no distinction between inward or outward edges. Furthermore, it was established
 * that the number of nodes of the graph remains constant for all times after an instance of this class is created. The size of the graph is kept
 * in a <i>final</i> variable.<p>
 * 
 * For the reasons above mentioned, methods for inward or outward edges were labelled as <i>@Depreciated</i>. Similarly, no implementation was
 * provided for {@code Graph.removeVertex(...)}.<p>
 * 
 * The vertices of the graph are stored in a fixed-size array ordered by their index. This graph is implemented using 
 * <strong>adjacency lists</strong> and, therefore, the methods of {@code Vertex} are fully implemented.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see Node
 */

public class UndirectedGraph implements Graph{
	
	protected final int n;
	protected Node[] nodes;
	
	/**
	 * Constructs an undirected graph with <strong>n</strong>
	 * vertices.
	 * 
	 * @param n the number of vertices in the graph
	 * to be created
	 */
	
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
	public Vertex findVertex(int id) throws VertexNotFound {
		if(id > n || id < 1)
			throw new VertexNotFound(id);
		
		return nodes[id-1];
	}
	
	@Override
	public boolean addEdge(int id1, int id2) throws SameVertex {
		if(id1 == id2)	throw new SameVertex();
		return addEdge(id1,id2,DEFAULT_EDGE_WEIGHT);
	}

	@Override
	public boolean addEdge(int id1, int id2, int weight) throws SameVertex {
		if(weight < 1)	return false;
				
		try {
			Vertex v1 = findVertex(id1);
			Vertex v2 = findVertex(id2);
			if(id1 == id2)	throw new SameVertex();
			return (v1.addEdge(v2, weight) && v2.addEdge(v1, weight));
		} catch (VertexNotFound e) {
			e.printStackTrace();
			return false;
		}
	}

	@Deprecated
	public void addVertex() {}
		
	@Override
	public Edge findEdge(int id1, int id2) throws EdgeNotFound, SameVertex, VertexNotFound{				
		try {
			Vertex v1 = findVertex(id1);
			Vertex v2 = findVertex(id2);
			
			if(((Node)v1).equals((Node)v2))
				throw new SameVertex();
			
			Edge edge = v1.findEdge(v2);
			if(edge == null)
				throw new EdgeNotFound(id1,id2);
			
			return edge;
			
		} catch (VertexNotFound e) {
			e.printStackTrace();
			throw new VertexNotFound("One the vertices of the edge " + id1 + " → " + id2 + " does not exist");
		}
	}

	@Override
	public int degreeOf(int id) {
		try {
			return findVertex(id).degreeOf();
		}
		catch (VertexNotFound e) {
			e.printStackTrace();
			return 0;	
		}
	}
	
	@Deprecated
	public int degreeOfInward(int id) {
		try {
			return findVertex(id).degreeOfInward();
		}
		catch (VertexNotFound e) {
			e.printStackTrace();
			return 0;	
		}
	}
	
	@Deprecated
	public int degreeOfOutward(int id) {
		try {
			return findVertex(id).degreeOfOutward();
		}
		catch (VertexNotFound e) {
			e.printStackTrace();
			return 0;	
		}
	}

	@Override
	public Edge[] getAllEdges(int id) {
		try {
			return findVertex(id).getAllEdges();
		}
		catch (VertexNotFound e) {
			e.printStackTrace();
			return new Edge[0];
		}
	}
	
	@Deprecated
	public Edge[] getInwardEdges(int id) {
		try {
			return findVertex(id).getInwardEdges();
		}
		catch (VertexNotFound e) {
			e.printStackTrace();
			return new Edge[0];
		}				
	}
	
	@Deprecated
	public Edge[] getOutwardEdges(int id) {
		try {
			return findVertex(id).getOutwardEdges();
		}
		catch (VertexNotFound e) {
			e.printStackTrace();
			return new Edge[0];
		}				
	}

	@Override
	public int getEdgeWeight(int id1, int id2) {	
		try {
			return findEdge(id1,id2).getWeight();
		}
		catch (EdgeNotFound | SameVertex | VertexNotFound e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean removeEdge(int id1, int id2) {	
		try {
			Edge edge = findEdge(id1,id2);
			Vertex v1 = edge.getStartVertex();
			Vertex v2 = edge.getFinishVertex();
			return (v1.removeEdge(v2) && v2.removeEdge(v1));
		}
		catch (EdgeNotFound | SameVertex | VertexNotFound e) {
			e.printStackTrace();
			return false;
		}
	}

	@Deprecated
	public boolean removeVertex(int id) {
		return false;
	}

	@Override
	public boolean setEdgeWeight(int id1, int id2, int weight) {
		if(weight < 1)	return false;
		
		try {		
			findEdge(id1,id2).setWeight(weight);
			findEdge(id2,id1).setWeight(weight);
			return true;
		}
		catch (EdgeNotFound | SameVertex | VertexNotFound e) {
			e.printStackTrace();
			return false;
		}
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
