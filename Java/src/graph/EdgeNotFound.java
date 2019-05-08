package graph;

/**
 * The {@code EdgeNotFound} class contains the {@code exception} to be thrown when the user 
 * attempts to access an edge that does not exist in the graph.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 *
 */
public class EdgeNotFound extends Exception{
	private static final long serialVersionUID = 1L;
	
	public EdgeNotFound(int id1, int id2) {
		super("No edge was found connecting vertices " + id1 + " and " + id2);
	}
	
}
