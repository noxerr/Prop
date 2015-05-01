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

    public void afegeix_mida_alg(int[] mida){
        
    }
    
    public void afegeix_temps_alg(int[] g){
        
    }
    
    public int mitj_temps_GN(){
        return estadistica.mitj_temps_GN();
    }
    
    public int mitj_temps_Clicke(){
        return estadistica.mitj_temps_Clicke();
    }
    
    public int mitj_temps_Louvain(){
        return estadistica.mitj_temps_Louvain();
    }
    
    public int mitj_mida_GN(){
        return estadistica.mitj_mida_GN();
    }
    
    public int mitj_mida_Clicke(){
        return estadistica.mitj_mida_Clicke();
    }
    
    public int mitj_mida_Louvain(){
        return estadistica.mitj_mida_Louvain();
    }
    
    public String rapid(){
        return estadistica.rapid();
    }
    
    public String petit(){
        return estadistica.petit();
    }
}
