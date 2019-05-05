package graph;

public class Main {

	public static void main(String[] args) {
		
		Graph graph = new TSPGraph(10);
				
		graph.addEdge(1, 2);
    graph.addEdge(1, 3, 4);
    graph.addEdge(1, 4, 20);
		
		//System.out.println(graph);

    Edge[] edgeList = graph.getAllEdges(1);
    
    for (Edge edge : edgeList) {
      
      System.out.println(edge.getStartVertex().getId() + " → " + edge.getFinishVertex().getId() + ": " + edge.getWeight());
    }

		graph.setEdgeWeight(1,3,99);
		//System.out.println(graph);
		
		/*Vertex v[] = new Vertex[graph.getSize()];
		v = graph.getAllVertices();
		System.out.println(v[0].print());*/


	}

}
