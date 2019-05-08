package graph;

/**
 * A <i>graph</i> is a set of <i>vertices</i> (sometimes referred to as <i>nodes</i>) interconnected 
 * by <i>edges</i>. These vertices and edges are mathematical abstractions, that is, these can be anything. 
 * In contrast to other data structures such as a {@code List} or a {@code Set}, which consist on a 
 * collection of nodes that are connected to one another, one node can only point to the 
 * previous or the following node. On the other hand, a <i>graph</i> establishes the concept of network
 * between various nodes.<p>
 * 
 * The {@code Graph} interface provides the fundamental functions that are needed when employing a generic 
 * weighted <i>graph</i> as a data structure, that is to all edges a numeric weight is assigned. Usually, this
 * weight is a positive integer number and, as such, <strong>this interface associates all edges 
 * a positive weight</strong>. Moreover, <strong>edges which connect a vertex to itself are not allowed</strong>.<p>
 * 
 * Another aspect to be considered is that <strong>all vertices, independently of their content, are identified 
 * by an integer number, an ID</strong>. In this manner, operations as finding a node, accessing, adding or removing 
 * edges are fairly simplified. The assignment of this ID should be <i>unique</i> for each node, as a graph contains no
 * duplicates vertices.<p>
 * 
 * Before implementing this interface, the core elements of a graph, which are vertices and edges, must be defined.
 * In this manner, the service provided by the interfaces {@code Vertex} and {@code Edge} must be implemented, as 
 * the methods provided by the {@code Graph} rely on the implementation of the before mentioned interfaces.<p>
 * 
 * In summary, the {@code Graph} interface provides methods for:
 * 
 * <ul>
 * <li> Adding a new vertex to the graph or remove a vertex given an ID.
 * <li> Adding or removing edges given the IDs of two existing nodes. Notice that in order to implement
 * a directed graph, the {@code Vertex} and {@code Edge} interfaces should be implemented accordingly.
 * <li> Getting and setting the weight of a specific edge given two vertex IDs.
 * <li> Getting the edge that links to vertices identified by their IDs.
 * <li> Getting a vertex with a specified ID.
 * <li> Obtaining all edges touching the a certain vertex.
 * <li> Retrieving all vertices in the graph.
 * </ul>
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see Vertex
 * @see Edge
 */

public interface Graph {
	
	/**
	 * Whenever an edge is added without specifying its weight, the default value is defined by this
	 * constant.
	 */
	int DEFAULT_EDGE_WEIGHT = 1;
	
	/**
	 * Returns the total number of vertices in the graph.
	 * 
	 * @return the number of vertices in the graph
	 */
	int getSize();
	
	/**
	 * Given the identifier of a vertex, such vertex is returned from a collection of all nodes
	 * belonging to the graph. If the vertex with such ID does not exist, an exception is thrown. 
	 * 
	 * @param id the identifier of the vertex to be found and returned.
	 * @return the vertex with the specified identifier
	 * @throws VertexNotFound if the specified ID does not correspond to any vertex belonging
	 * to the graph
	 */
	Vertex findVertex(int id) throws VertexNotFound;
	
	/**
	 * Adds an edge between two vertices.<p>
	 * It should be noticed that <strong>it is not allowed to have multiple edges connecting the same two nodes</strong>. Hence,
	 * before adding the new edge, it is verified whether the new edge already exists.<p>
	 * Should the graph to be implemented to be directed, the order of the IDs given as parameters for this function must
	 * be fixed. Additionally, the implementation of {@code Vertex} should be done accordingly.<p> 
	 * Furthermore, as the weight of the edge is not specified, the weight of the new edge is equal to
	 * {@code Graph.DEFAULT_EDGE_WEIGHT}.<p>
	 * Finally, no edge can be added between one vertex and itself.
	 * 
	 * @param id1 the identifier of the first vertex.
	 * @param id2 the identifier of the second vertex.
	 * @return {@code true} if the an edge between two vertices specified by the two IDs is inserted
	 * successfully.
	 * @throws SameVertex if the new edge is to be inserted connecting a vertex to itself
	 */
	boolean addEdge(int id1, int id2) throws SameVertex;
	
	/**
	 * Adds an edge between two vertices.<p>
	 * It should be noticed that <strong>it is not allowed to have multiple edges connecting the same two nodes</strong>. Hence,
	 * before adding the new edge, it is verified whether the new edge already exists.<p>
	 * Should the graph to be implemented to be directed, the order of the IDs given as parameters for this function must
	 * be fixed. Additionally, the implementation of {@code Vertex} should be done accordingly.<p> 
	 * Furthermore, the weight of new edge needs to be specified. This weight must be greater or equal to 1.<p> 
	 * Finally, no edge can be added between one vertex and itself.
	 * 
	 * @param id1 the identifier of the first vertex.
	 * @param id2 the identifier of the second vertex.
	 * @param weight the weight of the edge to be inserted between the two vertices.
	 * @return {@code true} if the an edge between two vertices specified by the two IDs is inserted
	 * successfully.
	 * @throws SameVertex if the new edge is to be inserted connecting a vertex to itself
	 */
	boolean addEdge(int id1, int id2, int weight) throws SameVertex;
	
	/**
	 * Adds a new vertex to the graph. The assignment of the ID of this new vertex should 
	 * be handled by the implementation of {@code Vertex}. The ID assigned to the new vertex should be the next number 
	 * following the ID of the last node inserted.
	 */
	void addVertex();
	
	/**
	 * Returns the edge connecting the vertices indexed by the IDs.<p>
	 * Should the graph to be implemented to be directed, the order of the IDs given as parameters for this function must
	 * be fixed.
	 * 
	 * @param id1 the identifier of the first vertex.
	 * @param id2 the identifier of the second vertex.
	 * @return {@code true} if an edge between the two specified vertices exists
	 * @throws EdgeNotFound if there is no edge linking the two vertices
	 * @throws SameVertex if the new edge is to be inserted connecting a vertex to itself
	 * @throws VertexNotFound if one of the vertices does not exist
	 */
	Edge findEdge(int id1, int id2) throws EdgeNotFound, SameVertex, VertexNotFound;
		
	/**
	 * Returns the number of edge touching the vertex with the specified ID.
	 * (the <i>degree</i> of the vertex).<p>
	 * The distinction of inward or outward edges is not done.<p>
	 * The implementation of this method may require the implemented methods of {@code Vertex}
	 * {@code Edge} interfaces.
	 * 
	 * @param id the ID of the vertex of which the degree is to be returned.
	 * @return the number of edges touching the specified vertex
	 */
	int degreeOf(int id);
	
	/**
	 * Returns the number of inward edges of the specified vertex. In other words, returns the count
	 * of edges directed to vertex with such ID.<p>
	 * The implementation of this method may require the implemented methods of {@code Vertex}
	 * {@code Edge} interfaces.
	 * 
	 * @param id the ID of the vertex of which the number of inward edges is to be determined.
	 * @return the number of edges incoming to the specified vertex
	 */
	int degreeOfInward(int id);
	
	/**
	 * Returns the number of outward edges of the specified vertex. In other words, returns the count
	 * of edges which begin at the vertex with such ID.<p>
	 * The implementation of this method may require the implemented methods of {@code Vertex}
	 * {@code Edge} interfaces.
	 * 
	 * @param id the ID of the vertex of which the number of outward edges is to be determined.
	 * @return the number of edges leaving from the specified vertex
	 */
	int degreeOfOutward(int id);
	
	
	/**
	 * Returns an array containing all edges connected to the specified node.<p>
	 * The distinction of inward or outward edges is not done.<p>
	 * The implementation of this method may require the implemented methods of {@code Vertex}
	 * and {@code Edge} interfaces.<p>
	 * If the vertex is disconnected from all other, an {@code empty} array is returned.
	 * 
	 * @param id the ID of the vertex of which all edges linked to which are returned.
	 * @return an array containing all edges connected to the specified node
	 */
	Edge[] getAllEdges(int id);
	
	/**
	 * Returns an array containing all edges inward connected to the specified node.<p> 
	 * The implementation of this method may require the implemented methods of {@code Vertex}
	 * {@code Edge} interfaces.<p>
	 * If the vertex contains no edges directed to itself, an empty array is returned.
	 * 
	 * @param id the ID of the vertex of which all inward are returned.
	 * @return an array containing all inward edges connected to the specified node
	 */
	Edge[] getInwardEdges(int id);
	
	/**
	 * Returns an array containing all edges departing from the specified node.<p> 
	 * The implementation of this method may require the implemented methods of {@code Vertex}
	 * {@code Edge} interfaces.<p>
	 * If the vertex contains no edges pointing to other vertices, an empty array is returned.
	 * 
	 * @param id the ID of the vertex of which all outward are returned.
	 * @return an array containing all outward edges connected to the specified node
	 */
	Edge[] getOutwardEdges(int id);
	
	/**
	 * Returns the weight of the edge linking the two specified nodes.<p>
	 * Should the graph to be implemented to be directed, the order of the IDs given as parameters for this function must
	 * be fixed. Additionally, the implementation of {@code Vertex} should be done accordingly.
	 * 
	 * @param id1 the identifier of the first vertex.
	 * @param id2 the identifier of the second vertex.
	 * @return 0 if the edge between the two specified nodes does not exist
	 */
	int getEdgeWeight(int id1, int id2);
	
	
	/**
	 * Removes an edge between the two specified vertices.<p>
	 * In this case, before removing the an edge, it is verified whether the this edge does indeed exist. 
	 * Should this edge be inexistent and {@code false} is returned.<p> 
	 * Should the graph to be implemented to be directed, the order of the IDs given as parameters for this function must
	 * be fixed. Additionally, the implementation of {@code Vertex} should be done accordingly.<p>
	 * 
	 * @param id1 the identifier of the first vertex.
	 * @param id2 the identifier of the second vertex.
	 * @return {@code true} if the specified edge is successfully removed.
	 */
	boolean removeEdge(int id1, int id2);
	
	/**
	 * Removes the specified vertex from the graph. Additionally, all inward or outward edges connected to this vertex
	 * are also deleted.<p>
	 * If the vertex labelled with the specified ID does not exist, the removal
	 * of the requested node is unsuccessful and, therefore, {@code false} is returned.<p>
	 * 
	 * After successfully removing a vertex, the value returned by {@code Graph.getSize()} is be decremented.
	 * 
	 * @param id the ID of the vertex to be removed.
	 * @return {@code true} if the requested vertex is successfully removed from the graph
	 */
	boolean removeVertex(int id);
	
	/**
	 * Sets the weight of the edge linking the two specified nodes.<p>
	 * Should the graph to be implemented to be directed, the order of the IDs given as parameters for this function must
	 * be fixed. Additionally, the implementation of {@code Vertex} should be done accordingly.<p> 
	 * If that edge does not exist, {@code false} is returned.
	 * 
	 * @param id1 the identifier of the first vertex.
	 * @param id2 the identifier of the second vertex.
	 * @param weight the weight to be assigned to the specified edge.
	 * @return {@code true} if the assignment of the weight of the requested edge is successful
	 */
	boolean setEdgeWeight(int id1, int id2, int weight);
	
	/**
	 * Returns an array containing all vertices in the graph. If the graph contains <i>zero</i> nodes, an <i>empty</i> array
	 * is returned.
	 * 
	 * @return an array with all vertices of the graph
	 */
	Vertex[] getAllVertices();
	
}
