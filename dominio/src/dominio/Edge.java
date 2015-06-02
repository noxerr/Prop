package dominio;

public class Edge {
	private float weight;
	
	public Edge(){
	}
	
	public Edge(Float i) {
		weight = i;
	}

	public float getWeight(){
		return weight;
	}
	
	public void setWeight(float weight){
		this.weight = weight;
	}
}
