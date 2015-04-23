/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio.controlador;


/**
 *
 * @author dani__000
 */
public class CtrlEstadistica {
    public static Object[] cargarEstadistica(){
        Object pair[] = new Object[2];
        int[] listaSizes = new int[3];
        double[] listaTimes = new double[3];
        pair[0] = listaSizes;
        pair[1] = listaTimes;
        return pair;
    }
    
    public static void actualizarEstadistica(Object[] ob){
        int[] sizes = (int[]) ob[0];
        double[] times = (double[]) ob[1];
    }

}
