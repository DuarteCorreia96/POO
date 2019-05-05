package graph;

public class Link implements Edge{
	
	private int weight;
	private Vertex v1;
	private Vertex v2;
	
	public Link(Vertex v1, Vertex v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}


	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setStartVertex(Vertex v) {
		v1 = v;
	}

	@Override
	public void setFinishVertex(Vertex v) {
		v2 = v;
		
	}

	@Override
	public Vertex getStartVertex() {
		return v1;
	}

	@Override
	public Vertex getFinishVertex() {
		return v2;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
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
		Link other = (Link) obj;
		if (v1 == null) {
			if (other.v1 != null)
				return false;
		} else if (!v1.equals(other.v1))
			return false;
		if (v2 == null) {
			if (other.v2 != null)
				return false;
		} else if (!v2.equals(other.v2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node " + v1.getId() + " → Node " + v2.getId() + "(" + weight + ")";
	}
	

}
