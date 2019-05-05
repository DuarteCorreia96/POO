package graph;

public class TSPGraph extends UndirectedGraph{
	
	private double[][] pheromones;
	private int W;

	public TSPGraph(int n, int[][] info) {
		super(n);
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
	
	
	public boolean decrementEdgePheromones(int id1, int id2, double amount) {
		if(!containsEdge(id1,id2))	return false;
		
		pheromones[id1-1][id2-1] -= amount;
		pheromones[id2-1][id1-1] -= amount;
		return true;
	}

}
