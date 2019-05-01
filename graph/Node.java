package graph;
import java.util.LinkedList;
import java.util.ListIterator;

public class Node<T> {
	
	private T data_node;
	private static int cnt = 1;
	int id;
	
	class Edge{
		Node<T> node;
		int weight;
		
		Edge(Node<T> n, int w){
			node = n;
			weight = w;
		}
		
		void changeWeight(int w) {
			weight = w;
		}
		
	}
	
	LinkedList<Edge> adjacent = new LinkedList<Edge>();
	
	Node(){
		id = cnt;
		cnt++;
	}
	
	Node(int _id){
		id = _id;
	}

	Node(int _id, T data){
		id = _id;
		data_node = data;
	}

	void addData(T data){
		data_node = data;
	}
	
	T getData() {
		return data_node;
	}
	
	void addAdjacent(Node<T> adj, int weight) {
		
		if(adj.equals(this))
			return;
		
		if(!adjacent.isEmpty()) {
			
			ListIterator<Edge> list_iter = adjacent.listIterator();
			Edge nxt;
					
			while(list_iter.hasNext()){
				
				nxt = list_iter.next();
				if(adj.equals(nxt.node))
					return;
			}
		
		}
		
		adjacent.add(new Edge(adj,weight));
		adj.adjacent.add(new Edge(this,weight));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Node<T> other = (Node<T>) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		ListIterator<Edge> list_iter = adjacent.listIterator();
		int adj[][] = new int[adjacent.size()][2];
		int cnt = 0;
		
		if(!adjacent.isEmpty()) {
			
			Edge nxt;			
			while(list_iter.hasNext()) {
				
				nxt = list_iter.next();
				adj[cnt][0] = nxt.node.id;
				adj[cnt++][1] = nxt.weight;
			}
		}
		
		String str = "";
		for(int j=0; j<adjacent.size(); j++) {
			str += "  Node " + adj[j][0] + ", weight " + adj[j][1] + "\n";
		}
		
		return "Node " + id + "\n" + str;
	}

}
