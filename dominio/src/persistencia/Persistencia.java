/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author daniel.roca.lopez
 */
public class Persistencia {
    public static void guardarDisco(Object[] ob, String filename, String type) throws FileNotFoundException, IOException{
        DataOutputStream out = new DataOutputStream
            (new FileOutputStream(filename));
        out.writeChars("El adri es un cabezon");
        out.writeBytes("\nEl adri es otro cabezon");
        
    }
    
    public static Object[] cargarDisco(String filename, String type) throws FileNotFoundException, IOException{
        DataInputStream in = new DataInputStream
            (new FileInputStream(filename));
        String ret = in.readLine();
        System.out.println(ret);
        String reto = in.readLine();
        System.out.println(reto);
        return null;
    }
}
