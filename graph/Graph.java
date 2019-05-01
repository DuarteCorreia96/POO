package graph;

public class Graph<T,E> {
	
	T data;
	int n;		
	Node<?>[] graph_nodes;
		
	public Graph(T _data, int _n) {
		
		data = _data;
		n = _n;
		graph_nodes = new Node<?>[n];
		
		for(int j=0; j<n; j++)
			graph_nodes[j] = new Node<E>();
	}
	
	public void addConnection(int node1, int node2, int weight) {
		
		Node<E> node_a = findNode(node1);
		if(node_a != null)
			node_a.addAdjacent(new Node<E>(node2),weight);
		
		Node<E> node_b = findNode(node2);
		if(node_b != null)
			node_b.addAdjacent(new Node<E>(node1),weight);
	}
	
	@SuppressWarnings("unchecked")
	public Node<E> findNode(int id) {
		
		for(Node<?> j:graph_nodes) {
			if(j.id == id)
				return (Node<E>)j;
		}
		
		return null;	
	}

	@Override
	public String toString() {
		
		String str = "";
		for(int j=0; j<n; j++) {
			str += graph_nodes[j];
		}
		
		return "\n-> Graph \n\n" + str;
	}
	
	
}
