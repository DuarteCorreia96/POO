package graph;

public class Main {

	public static void main(String[] args) {
		
		Graph graph = new UndirectedGraph(3);
		
		Vertex v1 = new Node();
		Vertex v2 = new Node();
		Vertex v3 = new Node();
		
		graph.addEdge(v1, v2);
		graph.addEdge(v1, v3, 4);
		
		/*v1.addEdge(v2, 2);
		v2.addEdge(v1, 2);
		v3.addEdge(v1, 4);
		v1.addEdge(v3, 4);*/
		
		System.out.println(v1.print());
		System.out.println(v2.print());
		System.out.println(v3.print());
		
		/*v1.removeEdge(v3);
		v3.removeEdge(v1);
		
		System.out.println(v1.print());
		System.out.println(v2.print());
		System.out.println(v3.print());*/
		
		System.out.println(graph);
		System.out.println(graph);


	}

}
