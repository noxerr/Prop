/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dani__000
 */
public class Estadistica {
    //el int tiene: T total : integer, n veces repe : integer, sumaTiCuadrado : integer
    private HashMap<Integer, int[]> mapaNewman, mapaClicke, mapaLouvain;
    
    //Pre: cert
    //Post: crea una estadistica
    public Estadistica(){
        mapaNewman = new HashMap();
        mapaClicke = new HashMap();
        mapaLouvain = new HashMap();
    }
    
    public Estadistica(ArrayList<Integer>[] sizes, ArrayList<Integer>[] times){
        mapaNewman = new HashMap();
        mapaClicke = new HashMap();
        mapaLouvain = new HashMap(); 
        //ObjectOutputStream oos = new ObjectOutputStream(new OutputStream(String auxi));
        
        //oos.writeObject(hashmap);
    }

    //Pre: cert
    //Post: entra la mida i el temps de la solució pels tres algorismes
    public void afegeix_mida_temps(int[] mida, int[] temps){
        //newman
        int[] sd = new int[3];
        if (!mapaNewman.isEmpty() && mapaNewman.containsKey(mida[0])) {
            sd = mapaNewman.get(mida[0]);
            sd[0] += temps[0];
            sd[1]++;
            sd[2] = (temps[0]*temps[0]);
        }
        else{
            sd[0] = temps[0];
            sd[1] = 1;
            sd[2] = (temps[0]*temps[0]);
            mapaNewman.put(mida[0], sd);
            System.out.println("sd 0: " + sd[0]);
        }
        
        //clicke
        sd = new int[3];
        if (!mapaClicke.isEmpty() && mapaClicke.containsKey(mida[0])) {
            sd = mapaClicke.get(mida[0]);
            sd[0] += temps[0];
            sd[1]++;
            sd[2] = (temps[0]*temps[0]);
        }
        else{
            sd[0] = temps[0];
            sd[1] = 1;
            sd[2] = (temps[0]*temps[0]);
            mapaClicke.put(mida[0], sd);
        }
        
        //louvain
        sd = new int[3];
        if (!mapaLouvain.isEmpty() && mapaLouvain.containsKey(mida[0])) {
            sd = mapaLouvain.get(mida[0]);
            sd[0] += temps[0];
            sd[1]++;
            sd[2] = (temps[0]*temps[0]);
        }
        else{
            sd[0] = temps[0];
            sd[1] = 1;
            sd[2] = (temps[0]*temps[0]);
            mapaLouvain.put(mida[0], sd);
        }
    }

    //Pre: cert
    //Post: retorna la mitjana dels temps de girvan Newman guardats
    public int mitj_temps_GN(){
        /*Iterator it = temps_g.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (temps_g.size() > 0) ret = suma/(temps_g.size());*/
        return 0;
    }


    //Pre: cert
    //Post: retorna la mitjana dels temps de clicke guardats
    public int mitj_temps_Clicke(){
        /*Iterator it = temps_c.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (temps_c.size() > 0) ret = suma/(temps_c.size());*/
        return 0;
    }

    
    //Pre: cert
    //Post: retorna la mitjana dels temps de louvain guardats
    public int mitj_temps_Louvain(){
        /*Iterator it = temps_l.iterator();
        int suma = 0;
        int ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (temps_l.size() > 0) ret = suma/(temps_l.size());*/
        return 0;
    }
    
    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de girvan Newman guardats
    public double mitj_mida_GN(){
        /*Iterator it = mida_girvan_newman.iterator();
        double suma = 0;
        double ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (mida_girvan_newman.size() > 0) ret = suma/(mida_girvan_newman.size());*/
        return 0;
    }

    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de clicke guardats
    public double mitj_mida_Clicke(){
        /*Iterator it = mida_clicke.iterator();
        double suma = 0;
        double ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (mida_clicke.size() > 0) ret = suma/(mida_clicke.size());*/
        return 0;
    }    
    
    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de louvain guardats
    public double mitj_mida_Louvain(){
        /*Iterator it = mida_louvain.iterator();
        double suma = 0;
        double ret = 0;
        while (it.hasNext()){
            suma += (int) it.next();
        }
        if (mida_louvain.size() > 0) ret = suma/(mida_louvain.size());*/
        return 0;
    }


    //Pre: cert
    //Post: retorna el nom de l’algorisme més rapid per la última solució
    public String rapid(){
        /*DecimalFormat df = new DecimalFormat("0.00");
        double midaG = 0, midaC = 0, midaL = 0, timeG = 0, timeC = 0, timeL = 0, 
                vG = 0, vC = 0, vL = 0;
        double fastest;
        String ret = "El alg. más rápido es ";
        if (mida_girvan_newman.size()>0){
            midaG = mida_girvan_newman.get(mida_girvan_newman.size()-1);
            timeG = temps_g.get(temps_g.size()-1);
            vG = midaG/timeG;
            //System.out.println(df.format(vG));
            //System.out.println(mida_girvan_newman.get(mida_girvan_newman.size()-1));
        }
        if (mida_clicke.size()>0) {
            midaC = mida_clicke.get(mida_clicke.size()-1);
            timeC = temps_c.get(temps_c.size()-1);
            vC = midaC/timeC;
            //System.out.println(df.format(vC));
        }
        if (mida_louvain.size()>0){
            midaL = mida_louvain.get(mida_louvain.size()-1);
            timeL = temps_l.get(temps_l.size()-1);
            vL = midaL/timeL;
            //System.out.println(df.format(vL));
        }
        
        double aux = Math.max(vG, vC);
        aux = Math.max(aux, vL);
        
        if (aux == vG) ret = ret.concat("Girvan Newman\ncon velocidad: " + df.format(vG) + " nodos/msec");
        else if (aux == vC) ret = ret.concat("Clicke\ncon velocidad: " + df.format(vC) + " nodos/msec");
        else ret = ret.concat("Louvain\ncon velocidad: " + df.format(vL) + " nodos/msec");*/
        
        return null;
    }

    //Pre: cert
    //Post: retorna el nom de l’algorisme la solució del cual ocupa menys espai per la última solució
    public String petit(){
        /*int midaG = 0, midaC = 0, midaL = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        String ret = "L'algorisme que menys ocupa es ";
        
        if (mida_girvan_newman.size()>0){
            midaG = mida_girvan_newman.get(mida_girvan_newman.size()-1);
        }
        if (mida_clicke.size()>0) {
            midaC = mida_clicke.get(mida_clicke.size()-1);
        }
        if (mida_louvain.size()>0){
            midaL = mida_louvain.get(mida_louvain.size()-1);
        }
        
        int aux = Math.min(midaG, midaC);
        aux = Math.min(aux, midaL);

        if (aux == midaG) ret = ret.concat("Girvan Newman\namb: " + df.format(midaG) + " nodes");
        else if (aux == midaC) ret = ret.concat("Clicke\namb: " + df.format(midaC) + " nodes");
        else ret = ret.concat("Louvain\namb: " + df.format(midaL) + " nodes");*/
        
        return null;
    }

    
    
    public ArrayList<ArrayList<String>>[] getMaps() throws IOException{
        ArrayList<ArrayList<String>>[] a0 = new ArrayList[3];
        a0[0] = convertToString(mapaNewman);
        a0[1] = convertToString(mapaClicke);
        a0[2] = convertToString(mapaLouvain);
        
        return a0;
    }
    
    private ArrayList<ArrayList<String>> convertToString(HashMap<Integer, int[]> object) throws IOException {
        ArrayList<ArrayList<String>> ret = new ArrayList();
        for (int i : object.keySet()){
            
        }
        return ret;
    }
    
    private HashMap<Integer, int[]> convertFromString(byte[] value) throws IOException, ClassNotFoundException {
        try (InputStream is = new ByteArrayInputStream(value);
            ObjectInputStream ois = new ObjectInputStream(is)) {
            return (HashMap<Integer, int[]>) ois.readObject();
        }
    }
    
    public void setSizesTimes(ArrayList<Integer>[] sizes, ArrayList<Integer>[] times){
        /*this.mida_girvan_newman = sizes[0];
        this.mida_clicke = sizes[1];
        this.mida_louvain = sizes[2];
        
        this.temps_g = times[0];
        this.temps_c = times[1];
        this.temps_l = times[2];*/
    }
    

}
