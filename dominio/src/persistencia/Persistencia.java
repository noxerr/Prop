/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author daniel.roca.lopez
 */
public class Persistencia {
    public static void guardarDisco(ArrayList<String> al, String filename, String type) throws FileNotFoundException, IOException{

        BufferedWriter out =
            new BufferedWriter(new FileWriter(filename));
        out.append("El adri es un cabezon");
        out.flush();
        //out.writeBytes("\nEl adri es otro cabezon");
        
    }
    
    public static Object[] cargarDisco(String filename, String type) throws FileNotFoundException, IOException{
        BufferedReader in =
                new BufferedReader(new FileReader(filename));
        String ret = in.readLine();
        System.out.println(ret);
        String reto = in.readLine();
        System.out.println(reto);
        return null;
    }
}
