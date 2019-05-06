package graph;

/**
 * A <i>vertex</i> is a core element of a <i>graph</i>. The {@code Vertex} interface has the purpose of 
 * providing the {@code Graph} interface the methods with are more low-level. In other words, the {@code Graph}
 * interface is, in a sense, the master of this interface. For instance, when a reference of {@code Graph} calls a method
 * for removing or adding an edge, it utilises the methods provided by this interface. Whereas the {@code Graph} manages all
 * vertices, {@code Vertex} is responsible for handling each vertex individually.<p>
 * 
 * The user should keep in mind that the methods provided by this interface <strong>are necessary if the graph is 
 * implemented using an adjacency lists</strong>. For this implementation, <strong>each vertex has a collection of edges</strong>. 
 * Should be graph be implemented using an adjacency matrix, operations on edges should be handled by the methods 
 * of {@code Graph} interface.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see Edge
 */

public interface Vertex {
	
	/**
	 * Returns the ID of this vertex.
	 * 
	 * @return the integer with identifies this vertex
	 */
	int getId();
	
	/**
	 * Adds a new outward edge between this vertex and the other vertex passed as a parameter.<p>
	 * <strong>As it is not allowed to have multiple outward edges pointed to same node</strong>,
	 * if an edge is found between this and another vertex by {@code Vertex.containsEdge(...)},
	 * that edge is not inserted and {@code false} is returned.
	 * 
	 * @param v the vertex to which a outward edge is to be connected.
	 * @param weight the weight to be assigned to the new edge.
	 * @return {@code true} if the insertion of the new edge is successful
	 */
	boolean addEdge(Vertex v, int weight);
	
	/**
	 * Removes an outward edge between this vertex and the other vertex passed as a parameter.<p>
	 * Should the edge between this and the other vertex not to exist, verified by {@code Vertex.containsEdge(...)},
	 * {@code false} is returned.
	 * 
	 * @param v the vertex to which a outward edge is to be removed.
	 * @return {@code true} if the removal of the specified edge is successful
	 */
	boolean removeEdge(Vertex v);
	
	/**
	 * Asserts if there is an outward edge connecting this vertex and the other passed as a parameter.
	 * 
	 * @param v the finish vertex of the outward edge to be tested.
	 * @return {@code true} if an outward edge exists between this and the other vertex
	 */
	boolean containsEdge(Vertex v);
	
	/**
	 * Returns the outward edge connecting this vertex and the other passed as a parameter.<p>
	 * Should the edge between this and the other vertex not to exist, verified by {@code Vertex.containsEdge(...)},
	 * {@code null} is returned.
	 * 
	 * @param v the finish vertex of the outward edge to be retrieved.
	 * @return the outward edge connecting this vertex and another
	 */
	Edge findEdge(Vertex v);
	
	/**
	 * Returns an array containing all edges connected to the this vertex.<p>
	 * The distinction of inward or outward edges is not performed.<p>
	 * If the vertex is disconnected from all other, an {@code empty} array is returned.
	 * 
	 * @return an array containing all edges connected to this vertex
	 */
	Edge[] getAllEdges();
	
	/**
	 * Returns an array containing all inward edges connected to the this vertex.<p>
	 * If the vertex contains no edges directed to itself, an empty array is returned.
	 * 
	 * @return an array containing all inward edges connected to this vertex
	 */
	Edge[] getInwardEdges();
	
	/**
	 * Returns an array containing all inward edges departing from this vertex.<p>
	 * If the vertex contains no edges pointing to other vertices, an empty array is returned.
	 * 
	 * @return an array containing all outward edges connected to this vertex
	 */
	Edge[] getOutwardEdges();
	
	
	/**
	 * Returns the number of edge touching this vertex(the <i>degree</i> of the vertex).<p>
	 * The distinction of inward or outward edges is not performed.<p>
	 * 
	 * @return the number of edges touching this vertex
	 */
	int degreeOf();
	
	/**
	 * Returns the number of inward edges of this vertex In other words, returns the count
	 * of edges directed to this vertex.<p>
	 * 
	 * @return the number of edges incoming to this vertex
	 */
	int degreeOfInward();
	
	
	/**
	 * Returns the number of outward edges of this vertex. In other words, returns the count
	 * of edges that depart from this vertex.<p>
	 * 
	 * @return the number of edges leaving from the specified vertex
	 */
	int degreeOfOutward();
}
