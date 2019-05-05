package graph;

public interface Vertex {
	
	int getId();
	boolean addEdge(Vertex v, int weight);
	boolean removeEdge(Vertex v);
	boolean containsEdge(Vertex v);
	Edge findEdge(Vertex v);
	Edge[] getAllEdges();
	int degreeOf();
}
