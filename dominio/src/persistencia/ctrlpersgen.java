package persistencia;

import java.io.IOException;
import java.util.List;

public class ctrlpersgen {

	public static boolean createDir(String dir){
		return FileManager.createDir(dir);
	}
	
	
	public static boolean createFile(String f) throws IOException{
		return FileManager.createFile(f);
	}
	
	public static boolean removeFile(String f){
		return FileManager.removeFile(f);
	}
	
	public static boolean removeDir(String dir){
		return FileManager.removeDir(dir);
	}
		
	 public static List<List<String>> read(String fileName, String objecte) {
	      return FileManager.read(fileName, objecte);
	   }
	 
	 public static void buidaficher(String fn){
		 FileManager.buidaficher(fn);
	 }
	 
	 public static void WriteFile(List<List<String>> L, String fn, String objecte){
	    FileManager.WriteFile(L, fn, objecte);
	 }
	
}
