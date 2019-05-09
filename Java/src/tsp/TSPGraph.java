package tsp;

import java.util.List;
import graph.*;

/**
 * The {@code TSPGraph} class extends the {@code UndirectedGraph} class. It
 * implements additional methods regarding the update of <i>pheromones</i> of
 * the various edges of the graph (<i>read</i> on <i>Ant Colony Optimisation</i>
 * for solving the <i>Travelling Salesman Problem</i>).
 * <p>
 * 
 * The <i>pheromone level</i> of each edge is stored in a <i>n x n</i> matrix
 * where each entry <i>(i,j)</i> saves the <i>pheromone level</i> of the edge
 * connecting node <i>i</i> and <i>j</i>.
 * 
 * It also saves the best <i>Hamiltonina Cycle</i> found so far.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see UndirectedGraph
 * @see EvaporationEvent
 */

public class TSPGraph extends UndirectedGraph{
  
    private final double rho;
    private final double eta;
	private double[][] pheromones;
    private int W;
    
    private int[] bestCycle;
    private int bestCycleCost = Integer.MAX_VALUE;

    /**
     * Constructor for the {@code TSPGraph} objects.
     * 
     * @param n    number of vertices in the graph
     * @param info a <i>list</i> of integer arrays with three entries. Each entry of 
     *             this array must contain the ID of the <i>first</i> vertex, the ID of 
     *             the <i>second</i> vertex and the <i>weight</i> of the connection
     *             between the two, respectively.
     * @param rho Amount of pheromones evaporated by the edges after each evaporation
     * @param eta Average value of the exponential distribution of the time between each 
     *             evaporation
     */
	public TSPGraph(int n, List<int[]> info, double rho, double eta) {

        super(n);
        this.rho = rho;
        this.eta = eta;
		pheromones = new double[n][n];
		TSPGraphBuilder(info);
		computeW();
    }
    
    private void TSPGraphBuilder(List<int[]> info) {
        for(int[] j : info){
            try {
                addEdge(j[0], j[1], j[2]);
			} catch (SameVertex e) {
				e.printStackTrace();
			}
        }
    }
    
    /**
	 * Asserts if an edge connects the two vertices.
	 * 
	 * @param id1 the identifier of the first vertex.
	 * @param id2 the identifier of the second vertex.
	 * @return {@code true} if the edge exists
	 */
	private boolean containsEdge(int id1, int id2) {
		try {
			Vertex v1 = findVertex(id1);
			Vertex v2 = findVertex(id2);
			
			if(((Node)v1).equals((Node)v2))
				return false;
			
			if(!v1.containsEdge(v2))
				return false;
			
		} catch (VertexNotFound e) {
            e.printStackTrace();
			return false;
		}
		
		return true;
	}
    
	private void computeW() {
		for(int j=0; j<n; j++) {
			for(int k=j+1; k<n; k++) {
				if(!containsEdge(j+1,k+1))	continue;
				
				try {
					W += nodes[j].findEdge(findVertex(k+1)).getWeight();
				} catch (VertexNotFound e) {
					System.out.println(e);
				}			
			}
		}
    }
    
	/**
     * Computing the sum of the weight of all edges (W) is required for obtaining
     * the amount of pheromones is to be laid down by an <i>Ant</i> once it
     * completes one <i>hamiltonian</i> cycle (<i>read</i> on <i>Ant Colony
     * Optimisation</i> for solving the <i>Travelling Salesman Problem</i>).
     * 
     * @return W the sum of the weight of all edges in the graph
     */
	public int getW() {
		return W;
	}
    
    /**
     * Checks if for the edge between the two vertices as already been scheduled or
     * occurred an <i>evaporation</i> event.
     * 
     * @param id1 identifier of the first vertex.
     * @param id2 identifier of the second vertex.
     * @return {@code true} if for the edge connecting the two vertices has already
     *         been scheduled or occurred an <i>evaporation</i> event
     */
	public boolean checkEdgeEvaporation(int id1, int id2) {
		return (pheromones[id1-1][id2-1] > 0)?	true:false;
	}
    
    /**
     * Given a path with the visited vertices, this method adds the specified amount
     * of pheromones to all edges belonging to this collection. The visited vertices
     * are identified by their ID. For instance, let a path be {1,2,3}. This method
     * lays down pheromones on the following edges: 1-2, 2-3.
     * 
     * @param path   an array containing the IDs of the visited vertices
     * @param amount the value of pheromones which is to added to all edges
     *               contained in the path array
     */
	public void layPheromones(int path[], double amount) {
		for(int j=0; j<path.length-1;j++) {
			pheromones[path[j]-1][path[j+1]-1] += amount;
			pheromones[path[j+1]-1][path[j]-1] += amount;
		}
	}
    
    /**
     * Given a path with the visited vertices, this method computes the sum of
     * weight of all edges connecting these vertices. The visited vertices are
     * identified by their ID. For instance, let a path be {1,2,3}. The method
     * returns the sum of the cost of the following edges: 1-2, 2-3.
     * 
     * @param path an array containing the IDs of the visited vertices
     * @return the cost of all edges contained in the set of visited vertices
     */
	public int getPathCost(int path[]) {
		int cost = 0;
		
		for(int j=0; j<path.length-1;j++) {
			try {
				cost += findVertex(path[j]).findEdge(findVertex(path[j+1])).getWeight();
			} catch (VertexNotFound e) {
				e.printStackTrace();
			}
		}
		
		return cost;
	}
    
    /**
     * Returns the <i>pheromone level</i> of the edge connecting the two vertices.
     * <p>
     * The method returns -1 if the specified edge does not exist.
     * 
     * @param id1 identifier of the first vertex
     * @param id2 identifier of the second vertex
     * @return the <i>pheromone level</i> of the edge connecting the two vertices
     */
	public double getEdgePheromones(int id1, int id2) {
		if(!containsEdge(id1,id2))	return -1;
		return pheromones[id1-1][id2-1];
	}
	
	/**
     * Decrements <i>pheromone level</i> of the edge connecting the two vertices
     * {@code rho} units
     * 
     * @param id1 Start {@code node} id
     * @param id2 Finish {@code node} id
     * @return {@code true} if the edege still has pheromones, {@code false} if its
     *         empty
     */
	public boolean decrementEdgePheromones(int id1, int id2) {
    
        double newlevel = pheromones[id1 - 1][id2 - 1] - rho;

        pheromones[id1-1][id2-1] = newlevel > 0 ? newlevel : 0;
        pheromones[id2-1][id1-1] = newlevel > 0 ? newlevel : 0;

	    return newlevel > 0;
    }
  
    /**
     * Returns {@code eta} value from the {@code TSPGraph} {@code eta} is the mean value of
     * the exponential distribuiton used to simulate the time in which the edge
     * pheromones are evaporated
     * @return {@code eta}
     */
    public double getEta() {
        return eta;
    }

    /**
     * Returns the cost of the best <i>Hamilton Cycle</i> found so far.
     * 
     * @return {@code bestCycleCost} cost of best <i>Hamilton Cycle</i>.
     */
    public int getBestCost() {
        return bestCycleCost;
    }

    /**
     * Returns the best <i>Hamilton Cycle</i> found so far.
     * 
     * @return {@code bestCycle} path of the best <i>Hamilton Cycle</i>
     */
    public int[] getBestCycle() {
        return bestCycle;
    }

    /**
     * Sets the new best <i>Hamilton Cycle</i> found and its cost.
     * 
     * @param path the new best <i>Hamilton Cycle</i> found.
     * @param cost the new best cost of a <i>Hamilton Cycle</i> found.
     */
    public void setBestCycle(int[] path, int cost) {
        bestCycleCost = cost;
        bestCycle = path;
    }

    /**
     * Returns the best <i>Hamilton Cycle</i> in String form
     * 
     * @return String of the best <i>Hamilton Cycle</i>
     */
    public String bestPathString() {

        int[] path = bestCycle;

        if (bestCycle == null)
            return "";

        String str = "{" + path[0];

        for (int i = 1; i < path.length - 1 && path[i] != -1; i++) {
            str += "," + path[i];
        }

        str += "}";
        return str;
    }
}
