/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import dominio.controlador.CtrlEstadistica;
import java.util.ArrayList;

/**
 *
 * @author dani__000
 */
public class Estadistica {
    
    private ArrayList<Integer> mida_girvan_newman, mida_clicke, mida_louvain;
    private ArrayList<Double> temps_g, temps_c, temps_s;
    
    //Pre: cert
    //Post: crea una estadistica
    public Estadistica(){
        
    }

    //Pre: cert
    //Post: entra la mida de la solució pels tres algorismes
    public void afegeix_mida_alg(Graph g){
        
    }

    //Pre: cert
    //Post: entra els temps que es tarda en calcular la solució pels tres algorismes
    public void afegeix_temps_alg(Graph g){
        
    }

    //Pre: cert
    //Post: retorna la mitjana dels temps de girvan Newman guardats
    public int mitj_temps_GN(){
        return 0;
        
    }

    //Pre: cert
    //Post: retorna la mitjana dels temps de louvain guardats
    public int mitj_temps_Louvain(){
        int a = 1;
        return a; 
    }

    //Pre: cert
    //Post: retorna la mitjana dels temps de clicke guardats
    public int mitj_temps_Clicke(){
        return 0;
        
    }

    //Pre: cert
    //Post: retorna la mitjana dels tamanys de girvan Newman guardats
    public int mitj_mida_GN(){
        return 0;
        
    }

    //Pre: cert
    //Post: retorna la mitjana dels tamanys de louvain guardats
    public int mitj_mida_Louvain(){
        return 0;
        
    }


    //Pre: cert
    //Post: retorna la mitjana dels tamanys de clicke guardats
    int mitj_mida_Clicke(){
        return 0;
        
    }

    //Pre: cert
    //Post: retorna el nom de l’algorisme més rapid per la última solució
    String rapid(){
        return null;
        
    }

    //Pre: cert
    //Post: retorna el nom de l’algorisme la solució del cual ocupa menys espai per la última //solució
    String petit(){
        return null;
        
    }


    //Pre: cert
    //Post: retorna estadístiques
    Estadistica ret_estadistiques(){
        return null;
    }
    
    public static void actualizarEstadistica(Object[] ob){
        CtrlEstadistica.actualizarEstadistica(ob);
    }
    
    public static Object[] cargarEstadistica(){
        Object[] ret = CtrlEstadistica.cargarEstadistica();
        return ret;
    }

}
