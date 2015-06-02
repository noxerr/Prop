/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import persistencia.FileManager;
/**
 *
 * @author adrian
 */
public class Ctrl_pers {
    
    private static String separador = "objecte";
    
    public static void setNomSeparador(String nom) {
        separador = nom;
    }
     
    public static List <List <String> > carrega(String nomF) {
        separador = "diputat";
        return FileManager.read(nomF, separador);
        
    }
    
    public static void guarda(String nomF,List<List<String>> info) {
        separador = "diputat";
        FileManager.WriteFile(info, nomF, separador);
    }
    
    
    
}


