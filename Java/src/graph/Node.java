package graph;
import java.util.LinkedList;
import java.util.ListIterator;

public class Node implements Vertex{
	
	private final int id;
	private static int cnt = 1;
	private LinkedList<Link> links = new LinkedList<Link>();
	
	public Node() {
		id = cnt++;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public boolean addEdge(Vertex v, int weight) {
		
		if(v.equals(this))
			return false;
		
		if(this.containsEdge(v))
			return false;
		
		links.add(new Link(this,v,weight));
		return true;
		
	}
	
	public boolean removeEdge(Vertex v) {
		
		int i = 0;
		
		if(!links.isEmpty()) {
			ListIterator<Link> list_iter = links.listIterator();
			Edge nxt;
			
			while(list_iter.hasNext()){
				nxt = list_iter.next();
				if(((Node)v).equals((Node)nxt.getFinishVertex())) {
					links.remove(i);
					return true;
				}
				i++;
			}
		}
			
		return false;
	}
	
	@Override
	public boolean containsEdge(Vertex v) {
		
		Edge edge = findEdge(v);
		if(edge == null)
			return false;
				
		if(edge.getFinishVertex() == v)
			return true;
		
		return false;
	}
	
	@Override
	public Edge findEdge(Vertex v) {
		
		if(!links.isEmpty()) {
			ListIterator<Link> list_iter = links.listIterator();
			Edge nxt;
			
			while(list_iter.hasNext()){
				nxt = list_iter.next();
				if(((Node)v).equals((Node)nxt.getFinishVertex()))
					return nxt;
			}
		}
		
		return null;
	}
	
	public Edge[] getAllEdges() {
				
		if(links.isEmpty())
			return new Edge[0];
		
		int n_edges = degreeOf();
		Edge[] edges = new Edge[n_edges];
		
		ListIterator<Link> list_iter = links.listIterator();
		Edge nxt;
		int i = 0;
		
		while(list_iter.hasNext()){
			nxt = list_iter.next();
			edges[i++] = nxt;
		}
		
		return edges;
	}
	
	@Override
	public int degreeOf() {		
		return links.size();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String print() {
		
		ListIterator<Link> list_iter = links.listIterator();
		int links_m[][] = new int[links.size()][2];
		int cnt = 0;
		
		if(!links.isEmpty()) {
			
			Link nxt;			
			while(list_iter.hasNext()) {
				
				nxt = list_iter.next();
				links_m[cnt][0] = nxt.getFinishVertex().getId();
				links_m[cnt++][1] = nxt.getWeight();
			}
		}
		
		String str = "";
		for(int j=0; j<links.size(); j++) {
			str += "  Node " + links_m[j][0] + ", weight " + links_m[j][1] + "\n";
		}
		
		return "Node " + id + "\n" + str;
	}

}
