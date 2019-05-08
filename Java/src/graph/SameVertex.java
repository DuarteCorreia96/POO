package graph;

/**
 * The {@code SameVertex} class contains the {@code exception} to be thrown when the user 
 * attempts to access an edge which links a vertex to itself.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 *
 */
public class SameVertex extends Exception{
	private static final long serialVersionUID = 1L;
	
	public SameVertex() {
		super("No edge may connect a vertex to itself");
	}
	
}