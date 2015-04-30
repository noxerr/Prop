/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import dominio.controlador.CtrlEstadistica;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author dani__000
 */
public class Estadistica {
    
    private ArrayList<Integer> mida_girvan_newman, mida_clicke, mida_louvain;//integers
    private ArrayList<Integer> temps_g, temps_c, temps_s;//doubles
    
    //Pre: cert
    //Post: crea una estadistica
    public Estadistica(){
        mida_girvan_newman = new ArrayList(); 
        mida_clicke = new ArrayList(); 
        mida_louvain = new ArrayList();
        temps_g = new ArrayList();
        temps_c = new ArrayList();
        temps_s = new ArrayList();
    }

    //Pre: cert
    //Post: entra la mida de la solució pels tres algorismes
    public void afegeix_mida_alg(int[] mida){
        mida_girvan_newman.add(mida[0]);
        mida_clicke.add(mida[1]);
        mida_louvain.add(mida[2]);
    }

    //Pre: cert
    //Post: entra els temps que es tarda en calcular la solució pels tres algorismes
    public void afegeix_temps_alg(int[] g){
        temps_g.add(g[0]);
        temps_c.add(g[1]);
        temps_s.add(g[2]);
    }

    //Pre: cert
    //Post: retorna la mitjana dels temps de girvan Newman guardats
    public int mitj_temps_GN(){
        Iterator it = temps_g.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        ret = suma/(temps_g.size());
        return ret;
    }


    //Pre: cert
    //Post: retorna la mitjana dels temps de clicke guardats
    public int mitj_temps_Clicke(){
        Iterator it = temps_c.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        ret = suma/(temps_g.size());
        return ret;
    }

    
    //Pre: cert
    //Post: retorna la mitjana dels temps de louvain guardats
    public int mitj_temps_Louvain(){
        Iterator it = temps_s.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        ret = suma/(temps_g.size());
        return ret;
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
    
    public Object[] convertToArray(){
        Object[] ob = new Object[2];
        ArrayList<Integer>[] listaSizes = new ArrayList[3];
        ArrayList<Integer>[] listaTimes = new ArrayList[3];
        
        listaSizes[0] = mida_girvan_newman;
        listaSizes[1] = mida_clicke;
        listaSizes[2] = mida_louvain;
        
        listaTimes[0] = temps_g;
        listaTimes[1] = temps_c;
        listaTimes[2] = temps_s;
        
        ob[0] = listaSizes;
        ob[1] = listaTimes;
        return ob;
    }
    
    public void actualizarEstadistica(){
        Object[] ob = convertToArray();
        CtrlEstadistica.actualizarEstadistica(ob);
    }
    
    public void cargarEstadistica(){
        Object[] ret = CtrlEstadistica.cargarEstadistica();
        ArrayList<Integer>[] sizes = (ArrayList<Integer>[]) ret[0];
        ArrayList<Integer>[] times = (ArrayList<Integer>[]) ret[1];
        mida_girvan_newman = sizes[0];
        mida_clicke = sizes[1];
        mida_louvain = sizes[2];
        temps_g = times[0];
        temps_c = times[1]; 
        temps_s = times[2];
        
    }

}
