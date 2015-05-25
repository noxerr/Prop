package persistencia;

import java.io.*;
import java.util.*;

public class FileManager {
	public static boolean createDir(String dir){
		File directori = new File(dir);
		if(directori.mkdirs()) return true;
		else return false;
	}
	
	
	public static boolean createFile(String f) throws IOException{
		File fitxer = new File(f);
		if(fitxer.createNewFile()) return true;
		else return false;
	}
	
	public static boolean removeFile(String f){
		File fitxer = new File(f);
		if(fitxer.delete()) return true;
		else return false;
	}
	
	private static boolean deleteChildren(File dir) {  
	    File[] children = dir.listFiles();  
	    boolean childrenDeleted = true;  
	    for (int i = 0; children != null && i < children.length; i++) {  
	        File child = children[i];  
	        if (child.isDirectory()) {  
	            childrenDeleted = deleteChildren(child) && childrenDeleted;  
	        }  
	        if (child.exists()) {  
	            childrenDeleted = child.delete() && childrenDeleted;  
	        }  
	    }  
	    return childrenDeleted;  
	}  
	
	public static boolean removeDir(String dir){
		File directori = new File(dir);
		return deleteChildren(directori) && directori.delete();
	}
	
	
	public static void escString(String fileName, String string){
        try{ 
		// Always wrap FileWriter in BufferedWriter.
         BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
         // Note that write() does not automatically
	     // append a newline character.
	     bw.write(string);
	     bw.newLine();
	     // Always close files.
	    bw.close();
        }
	      catch(Exception e){
		         e.printStackTrace();
	      }
	}
		
	 public static List<List<String>> read(String fileName, String objecte) {
	      File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      List<List<String>> L = new ArrayList();
	 
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (fileName);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	 
	         // Lectura del fichero
	         String linea;
	         List<String> l1 = new ArrayList();
	         while((linea=br.readLine())!=null){
	            if(linea.equals(objecte)){
	            	l1 = new ArrayList();
	            	L.add(l1);
	            }
	            else{
	            	l1.add(linea);
	            }
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      return L;
	   }
	 
	 public static void buidaficher(String fn){
		 try{
			 FileWriter fw = new FileWriter(fn);
		 }
	     catch(Exception e){
		     e.printStackTrace();
	     }
	 }
	 
	 public static void WriteFile(List<List<String>> L, String fn, String objecte){
	    buidaficher(fn);
		 for(List<String> l1 : L){
			escString(fn, objecte);
	        for(String s : l1){
	        	escString(fn, s);
	        }
	    }
	 }
	
}