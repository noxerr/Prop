package persistencia;

import java.util.*;

public class ctrlpersol {
	public static void escriuGraph(List<List<String>> Graph, String path){
		FileManager.WriteFile(Graph, path, "Graph");
	}
	
	
	public static void escriuSolucio(List<List<String>> Sol, String path){
		FileManager.WriteFile(Sol, path, "Solucio");
	}
	
	public static List<List<String>> lecGraph(String path){
		return FileManager.read(path, "Graph");
	}
	
	public static List<List<String>> lecSolucio(String path){
		return FileManager.read(path, "Solucio");
	}
} 
