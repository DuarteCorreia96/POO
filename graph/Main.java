package graph;

public class Main {

	public static void main(String[] args) {
		
		Graph graph = new TSPGraph(3);
				
		graph.addEdge(1,2);
		graph.addEdge(1,3,4);
		
		System.out.println(graph);

		graph.setEdgeWeight(1,3,99);
		System.out.println(graph);
		
		/*Vertex v[] = new Vertex[graph.getSize()];
		v = graph.getAllVertices();
		System.out.println(v[0].print());*/


	}

}
