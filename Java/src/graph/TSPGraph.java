package graph;

public class TSPGraph extends UndirectedGraph{
  
  private final double rho;
  private final double eta;
	private double[][] pheromones;
	private int W;

	public TSPGraph(int n, int[][] info, double rho, double eta) { // também mudei isto 

    super(n);
    this.rho = rho;
    this.eta = eta;
		pheromones = new double[n][n];
		TSPGraphBuilder(info);
		computeW();
	}

	private void TSPGraphBuilder(int[][] info) {
		for(int j=0; j<info.length; j++) {
			addEdge(info[j][0],info[j][1],info[j][2]);
		}
			
	}
	
	private void computeW() {
		for(int j=0; j<n; j++) {
			for(int k=j+1; k<n; k++) {
				if(!containsEdge(j+1,k+1))	continue;
				W += nodes[j].findEdge(findVertex(k+1)).getWeight();
			}
		}
		
	}
	
	public int getW() {
		return W;
	}
	
	public boolean checkEdgeEvaporation(int id1, int id2) {
		return (pheromones[id1-1][id2-1] > 0)?	true:false;
	}
	
	public void layPheromones(int path[], double amount) {
		for(int j=0; j<path.length-1;j++) {
			pheromones[path[j]-1][path[j+1]-1] += amount;
			pheromones[path[j+1]-1][path[j]-1] += amount;
		}
	}
	
	public int getCycleCost(int path[]) {
    
    int cost = 0;
		for(int j=0; j<path.length-1;j++) {
			cost += findVertex(path[j]).findEdge(findVertex(path[j+1])).getWeight();
		}
		
		return cost;
	}
	
	public double getEdgePheromones(int id1, int id2) {
		if(!containsEdge(id1,id2))	return -1;
		return pheromones[id1-1][id2-1];
	}
	
	/**
     * Decrements edege pheromones {@code rho} units
     * @param id1 Start {@code node} id 
     * @param id2 Finish {@code node} id
     * @return {@code true} if edege still has pheromones, {@code false} if its empty 
    */
	public boolean decrementEdgePheromones(int id1, int id2) {
    
        double newlevel = pheromones[id1 - 1][id2 - 1] - rho;

        pheromones[id1-1][id2-1] = newlevel > 0 ? newlevel : 0;
        pheromones[id2-1][id1-1] = newlevel > 0 ? newlevel : 0;

	    return newlevel > 0;
    }
  
    /**
     * Returns {@code eta} value from the TSPGraph; {@code eta} is the mean value of
     * the exponential distribuiton used to simulate the time in which the edge
     * pheromones are evaporated
     * @return {@code eta}
     */
    public double getEta() {
        return eta;
    }
}
