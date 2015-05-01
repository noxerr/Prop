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
        ArrayList<Integer> sizes = new ArrayList<>(
            Arrays.asList(1, 2, 3));
        ArrayList<Integer> times = new ArrayList<>(
            Arrays.asList(10, 20, 30));
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
