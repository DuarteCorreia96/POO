package graph;

public interface Graph {
	
	int DEFAULT_EDGE_WEIGHT = 1;
	
	int getSize();
	boolean addEdge(Vertex v1, Vertex v2);
	boolean addEdge(Vertex v1, Vertex v2, int weight);
	Vertex addVertex();
	boolean addVertex(Vertex v);
	boolean containsEdge(Vertex v1, Vertex v2);
	boolean containsVertex(Vertex v);
	int degreeOf(Vertex v);
	Edge[] getAllEdges(Vertex v);
	int getEdgeWeight(Vertex v1, Vertex v2);
	boolean removeEdge(Vertex v1, Vertex v2);
	boolean removeVertex(Vertex v);
	void setEdgeWeight(Vertex v1, Vertex v2, int weight);
	Vertex[] vertexArray();
	Vertex findVertex(Vertex v);
	Vertex findVertex(int id);

}
