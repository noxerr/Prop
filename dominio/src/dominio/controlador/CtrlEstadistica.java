/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio.controlador;

import dominio.Estadistica;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import persistencia.CtrlPersistenciaEstadistica;


/**
 *
 * @author dani__000
 */
public class CtrlEstadistica {
    private static Estadistica estadistica;
    
    public static void initEstadistica() throws IOException{
        List<List<String>>[] all = CtrlPersistenciaEstadistica.cargarEstadistica();
        estadistica = new Estadistica(all);
    }
    
    
    public static void resetearEstadisticas(){
        CtrlPersistenciaEstadistica.resetearEstadistica();
        estadistica = new Estadistica();
    }
     
    
    public static void guardarEstadistica() throws IOException{
        List<List<String>>[] ob = estadistica.getMaps();
        CtrlPersistenciaEstadistica.guardarEstadistica(ob);
    }

    public static void afegeix_mida_temps(int[] mida, int[] g){
        estadistica.afegeix_mida_temps(mida, g);
    }
    
    public static void afegeix_mida_temps(int mida, int g, int alg){
        estadistica.afegeix_mida_temps(mida, g, alg);
    }
    
    public static double mitj_temps_GN(){
        return estadistica.mitj_temps_GN();
    }
    
    public static double mitj_temps_Clicke(){
        return estadistica.mitj_temps_Clicke();
    }
    
    public static double mitj_temps_Louvain(){
        return estadistica.mitj_temps_Louvain();
    }
    
    public static double mitj_mida_GN(){
        return estadistica.mitj_mida_GN();
    }
    
    public static double mitj_mida_Clicke(){
        return estadistica.mitj_mida_Clicke();
    }
    
    public static double mitj_mida_Louvain(){
        return estadistica.mitj_mida_Louvain();
    }
    
    public static ArrayList<Double>[] getXYAlg(int alg) throws Exception{
        return estadistica.getXYAlg(alg);
    }
}
