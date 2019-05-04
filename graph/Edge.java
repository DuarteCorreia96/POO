package graph;

public interface Edge {
	
	void setWeight(int weight);
	int getWeight();
	void setStartVertex(Vertex v);
	void setFinishVertex(Vertex v);
	Vertex getStartVertex();
	Vertex getFinishVertex();

}
