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
    public static void crearPlantilla(String nom) throws IOException{
        //llamarFuncionAñadirCosasADisco
    }
    
    public static void borrarPlantilla(String nom){
        //llamarFuncionBorrarCosasDeDisco
    }
    
    public static int[] cargarPlantilla(String nom) throws IOException{
        int[] nueva = new int[9];
        nueva[0] = 7; nueva[1] = 9; nueva[2] = 0; nueva[3] = 1;
        nueva[4] = 2; nueva[5] = 4; nueva[6] = 8; nueva[7] = 3; 
        nueva[8] = 13;
        //llamarFuncionCargarCosasDeDisco
        return nueva;
    }
    
    public static void guardarPlantilla(Object[] ob, String oldName) throws IOException{
        //llamarFuncionAñadirCosasADisco
    }
    
    public static ArrayList<String> cargarListaPlantillas(){
        ArrayList<String> ret = new ArrayList<>(
            Arrays.asList("Default", "Pepito Grillo", "La Plata"));
        //ret = llamarFuncionCargarCosasDeDisco
        return ret;
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
