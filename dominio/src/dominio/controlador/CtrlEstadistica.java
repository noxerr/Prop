/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio.controlador;

import java.util.ArrayList;


/**
 *
 * @author dani__000
 */
public class CtrlEstadistica {
    public static Object[] cargarEstadistica(){
        Object pair[] = new Object[2];
        ArrayList[] listaSizes = new ArrayList[3];
        ArrayList[] listaTimes = new ArrayList[3];
        pair[0] = listaSizes;
        pair[1] = listaTimes;
        return pair;
    }
    
    public static void actualizarEstadistica(Object[] ob){
        ArrayList[] sizes = (ArrayList[]) ob[0];
        ArrayList[] times = (ArrayList[]) ob[1];
    }

}
