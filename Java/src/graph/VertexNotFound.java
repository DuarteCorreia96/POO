package graph;

/**
 * The {@code VertexNotFound} class contains the {@code exception} to be thrown when the user 
 * attempts to access a vertex which does not belong to the graph.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 *
 */
public class VertexNotFound extends Exception{
	private static final long serialVersionUID = 1L;

	public VertexNotFound(int id) {
		super("Vertex " + id + " not found");
	}
	
	public VertexNotFound(String msg) {
		super(msg);
	}
}
