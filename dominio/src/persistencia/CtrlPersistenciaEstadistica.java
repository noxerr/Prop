/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author dani__000
 */
public class CtrlPersistenciaEstadistica {
    public static Object[] cargarEstadistica() throws IOException{
        Object[] ob = new Object[2];
        ArrayList<Integer>[] sizes = new ArrayList[3];
        sizes[0] = new ArrayList<>(Arrays.asList(2, 2, 5));
        sizes[1] = new ArrayList<>(Arrays.asList(4, 1, 3));
        sizes[2] = new ArrayList<>(Arrays.asList(1, 52, 83));
        
        ArrayList<Integer>[] times = new ArrayList[3];
        times[0] = new ArrayList<>(Arrays.asList(10, 20, 30));
        times[1] = new ArrayList<>(Arrays.asList(10, 20, 30));
        times[2] = new ArrayList<>(Arrays.asList(10, 20, 30));
        
        ob[0] = sizes;
        ob[1]= times;
        return ob;
        //llamarFuncionCargarCosasDeDisco
    }
    
    public static void resetearEstadistica(){
        //llamarFuncionBorrarCosasDeDisco
    }
    
    public static void guardarEstadistica(Object[] ob) throws IOException{
        //llamarFuncionModificarCosasDeDisco
    }
    
}
