package dominio;

public class Node {
	private String id;
	
	public Node(){
	}
	
	public Node(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
	public int GetId(){
		return Integer.valueOf(id);
	}
	
	public void SetId(String id){
		this.id = id;
	}
}
