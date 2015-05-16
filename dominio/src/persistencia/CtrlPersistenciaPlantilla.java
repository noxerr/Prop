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
public class CtrlPersistenciaPlantilla {

    public static void guardarPlantillas(ArrayList<ArrayList<String>> list) throws IOException{
        //llamarFuncionAñadirCosasADisco
    }
    
    
    public static ArrayList<ArrayList<String>> cargarPlantillas(){
        ArrayList<ArrayList<String>> list = new ArrayList();
        ArrayList<String> ret = new ArrayList<>(
            Arrays.asList("una", "0", "2", "3", "4", "5", "6", "7", "8", "9"));
        list.add(ret);
        //ret = llamarFuncionCargarCosasDeDisco
        return list;
    }
}
