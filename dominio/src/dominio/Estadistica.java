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
    
    private ArrayList<Integer> mida_girvan_newman, mida_clicke, mida_louvain, temps_g, temps_c, temps_s;
    
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
    
    public Estadistica(ArrayList<Integer>[] sizes, ArrayList<Integer>[] times){
        this.mida_girvan_newman = sizes[0];
        this.mida_clicke = sizes[1];
        this.mida_louvain = sizes[2];
        
        this.temps_g = times[0];
        this.temps_c = times[1];
        this.temps_s = times[2];
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
        if (temps_g.size() > 0) ret = suma/(temps_g.size());
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
        if (temps_c.size() > 0) ret = suma/(temps_c.size());
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
        if (temps_s.size() > 0) ret = suma/(temps_s.size());
        return ret;
    }
    
    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de girvan Newman guardats
    public int mitj_mida_GN(){
        Iterator it = mida_girvan_newman.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (mida_girvan_newman.size() > 0) ret = suma/(mida_girvan_newman.size());
        return ret;
    }

    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de clicke guardats
    public int mitj_mida_Clicke(){
        Iterator it = mida_clicke.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (mida_clicke.size() > 0) ret = suma/(mida_clicke.size());
        return ret;
    }    
    
    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de louvain guardats
    public int mitj_mida_Louvain(){
        Iterator it = mida_louvain.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (mida_louvain.size() > 0) ret = suma/(mida_louvain.size());
        return ret;
    }


    //Pre: cert
    //Post: retorna el nom de l’algorisme més rapid per la última solució
    public String rapid(){
        if (mida_girvan_newman.size()>0) 
            System.out.println(mida_girvan_newman.get(mida_girvan_newman.size()-1));
        return "Hola";
    }

    //Pre: cert
    //Post: retorna el nom de l’algorisme la solució del cual ocupa menys espai per la última solució
    public String petit(){
        return null;   
    }

    
    
    public Object[] getSizesTimes(){
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
    
    
    public void setSizesTimes(ArrayList<Integer>[] sizes, ArrayList<Integer>[] times){
        this.mida_girvan_newman = sizes[0];
        this.mida_clicke = sizes[1];
        this.mida_louvain = sizes[2];
        
        this.temps_g = times[0];
        this.temps_c = times[1];
        this.temps_s = times[2];
    }
    

}
