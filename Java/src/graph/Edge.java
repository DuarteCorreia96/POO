package graph;

/**
 * Whether a graph is implemented using an adjacency matrix or a adjacency list, <i>edges</i> are a essential element of a graph
 * as these establish the connection between two nodes.<p>
 * 
 * An edge, at its simplest implementation, consists on a <i>start</i> vertex and a <i>finish</i> vertex and the weight of the connection
 * between these. Notice that the <i>start</i> vertex and a <i>finish</i> vertex encode the direction of the edge.<p>
 * 
 * The {@code Edge} interface provides methods for getting and setting the two vertices and the weight of the connection between them.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 *
 */

public interface Edge {
	
	/**
	 * Sets the weight of this edge.
	 * 
	 * @param weight
	 */
	void setWeight(int weight);
	
	/**
	 * Returns the weight of this edge.
	 * 
	 * @return the weight of this edge
	 */
	int getWeight();
	
	/**
	 * Sets the <i>start</i> vertex of this edge.
	 * 
	 * @param v the vertex from which the edge departs
	 */
	void setStartVertex(Vertex v);
	
	/**
	 * Sets the <i>finish</i> vertex of this edge.
	 * 
	 * @param v the vertex at which the edge arrives
	 */
	void setFinishVertex(Vertex v);
	
	/**
	 * Returns the <i>start</i> vertex of this edge.
	 * 
	 * @return the vertex from which the edge departs
	 */
	Vertex getStartVertex();
	
	/**
	 * Returns the <i>finish</i> vertex of this edge.
	 * 
	 * @return the vertex at which the edge arrives
	 */
	Vertex getFinishVertex();

}