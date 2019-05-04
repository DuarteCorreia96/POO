package graph;

public class Main {

	public static void main(String[] args) {
		
		Graph<Integer,String> g = new Graph<Integer,String>(3,4);
		g.addConnection(1,2,3);
		g.addConnection(2,4,5);
		g.addConnection(3,4,1);
		System.out.println(g);
		

	}

}
