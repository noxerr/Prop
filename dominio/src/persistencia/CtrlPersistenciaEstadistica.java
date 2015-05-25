/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;


/**
 *
 * @author dani__000
 */
public class CtrlPersistenciaEstadistica {
    public static List<List<String>>[] cargarEstadistica() throws IOException{  
        List<List<String>>[] ret = new ArrayList[3];
        ret[0] = FileManager.read("EstadisticasN", "estadistica");
        ret[1] = FileManager.read("EstadisticasC", "estadistica");
        ret[2] = FileManager.read("EstadisticasL", "estadistica");
        return ret;
    }
    
    public static void resetearEstadistica(){
        //llamarFuncionBorrarCosasDeDisco
    }
    
    public static void guardarEstadistica(List<List<String>>[] ob) throws IOException{
        FileManager.WriteFile(ob[0], "EstadisticasN", "estadistica");
        FileManager.WriteFile(ob[1], "EstadisticasC", "estadistica");
        FileManager.WriteFile(ob[2], "EstadisticasL", "estadistica");
    }
    
}
