package graph;

public interface Graph {
	
	int DEFAULT_EDGE_WEIGHT = 1;
	
	int getSize();
	Vertex findVertex(int id);
	boolean addEdge(int id1, int v2);
	boolean addEdge(int id1, int v2, int weight);
	void addVertex();
	boolean containsEdge(int id1, int v2);
	boolean containsVertex(int id);
	int degreeOf(int id);
	Edge[] getAllEdges(int id);
	int getEdgeWeight(int id1, int id2);
	boolean removeEdge(int id1, int id2);
	boolean removeVertex(int id);
	boolean setEdgeWeight(int id1, int id2, int weight);
	Vertex[] getAllVertices();
	
}
