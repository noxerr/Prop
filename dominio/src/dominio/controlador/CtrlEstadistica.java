/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio.controlador;

import dominio.Estadistica;
import java.io.IOException;
import java.util.ArrayList;
import persistencia.CtrlPersistenciaEstadistica;


/**
 *
 * @author dani__000
 */
public class CtrlEstadistica {
    private static Estadistica estadistica;
    
    public static void initEstadistica() throws IOException{
        Object[] ret = CtrlPersistenciaEstadistica.cargarEstadistica();
        ArrayList<Integer>[] sizes = (ArrayList<Integer>[]) ret[0];
        ArrayList<Integer>[] times = (ArrayList<Integer>[]) ret[1];
        estadistica = new Estadistica(sizes, times);
    }
    
    public static void cargarEstadistica() throws IOException{
        Object[] ret = CtrlPersistenciaEstadistica.cargarEstadistica();
        ArrayList<Integer>[] sizes = (ArrayList<Integer>[]) ret[0];
        ArrayList<Integer>[] times = (ArrayList<Integer>[]) ret[1];
        estadistica.setSizesTimes(sizes, times);
    }
    
    
    public static void resetearEstadisticas(){
        CtrlPersistenciaEstadistica.resetearEstadistica();
        estadistica = new Estadistica();
    }
    
    
    public static void guardarEstadistica() throws IOException{
        Object[] ob = estadistica.getSizesTimes();
        CtrlPersistenciaEstadistica.guardarEstadistica(ob);
    }

    public static void afegeix_mida_alg(int[] mida){
        estadistica.afegeix_mida_alg(mida);
    }
    
    public static void afegeix_temps_alg(int[] g){
        estadistica.afegeix_temps_alg(g);
    }
    
    public static int mitj_temps_GN(){
        return estadistica.mitj_temps_GN();
    }
    
    public static int mitj_temps_Clicke(){
        return estadistica.mitj_temps_Clicke();
    }
    
    public static int mitj_temps_Louvain(){
        return estadistica.mitj_temps_Louvain();
    }
    
    public static int mitj_mida_GN(){
        return estadistica.mitj_mida_GN();
    }
    
    public static int mitj_mida_Clicke(){
        return estadistica.mitj_mida_Clicke();
    }
    
    public static int mitj_mida_Louvain(){
        return estadistica.mitj_mida_Louvain();
    }
    
    public static String rapid(){
        return estadistica.rapid();
    }
    
    public static String petit(){
        return estadistica.petit();
    }
}
