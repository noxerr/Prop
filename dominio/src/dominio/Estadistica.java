/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

/**
 *
 * @author dani__000
 */
public class Estadistica {
    //el int tiene: T total, n veces repe, sumaTiCuadrado : integer
    private HashMap<Integer, int[]> mapaNewman, mapaClicke, mapaLouvain;
    
    //Pre: cert
    //Post: crea una estadistica
    public Estadistica(){
        mapaNewman = new HashMap();
        mapaClicke = new HashMap();
        mapaLouvain = new HashMap();
    }
    
    public Estadistica(ArrayList<ArrayList<String>>[] all) throws IOException{
        HashMap<Integer, int[]>[] maps = convertFromString(all);
        mapaNewman = maps[0];
        mapaClicke = maps[1];
        mapaLouvain = maps[2];
    }

    //Pre: cert
    //Post: entra la mida i el temps de la solució pels tres algorismes
    public void afegeix_mida_temps(int[] mida, int[] temps){
        //newman
        int[] sd = new int[3];
        if (mapaNewman.containsKey(mida[0])) {
            sd = mapaNewman.get(mida[0]);
            sd[0] += temps[0];
            sd[1]++;
            sd[2] += (temps[0]*temps[0]);
        }
        else{
            sd[0] = temps[0];
            sd[1] = 1;
            sd[2] = (temps[0]*temps[0]);
            mapaNewman.put(mida[0], sd);
        }
        
        //clicke
        sd = new int[3];
        if (mapaClicke.containsKey(mida[1])) {
            sd = mapaClicke.get(mida[1]);
            sd[0] += temps[1];
            sd[1]++;
            sd[2] += (temps[1]*temps[1]);
        }
        else{
            sd[0] = temps[1];
            sd[1] = 1;
            sd[2] = (temps[1]*temps[1]);
            mapaClicke.put(mida[1], sd);
        }
        
        //louvain
        sd = new int[3];
        if (mapaLouvain.containsKey(mida[2])) {
            sd = mapaLouvain.get(mida[2]);
            sd[0] += temps[2];
            sd[1]++;
            sd[2] += (temps[2]*temps[2]);
        }
        else{
            sd[0] = temps[2];
            sd[1] = 1;
            sd[2] = (temps[2]*temps[2]);
            mapaLouvain.put(mida[2], sd);
        }
    }

    //Pre: cert
    //Post: retorna la mitjana dels temps de girvan Newman guardats
    public int mitj_temps_GN(){
        int n = 0, TTotal = 0;
        int[] vec;
        for (int v : mapaNewman.keySet()){
            vec = mapaNewman.get(v);
            n += vec[1];
            TTotal += vec[0];
        }
        return (TTotal/n);
    }


    //Pre: cert
    //Post: retorna la mitjana dels temps de clicke guardats
    public int mitj_temps_Clicke(){
        int n = 0, TTotal = 0;
        int[] vec;
        for (int v : mapaClicke.keySet()){
            vec = mapaClicke.get(v);
            n += vec[1];
            TTotal += vec[0];
        }
        return (TTotal/n);
    }

    
    //Pre: cert
    //Post: retorna la mitjana dels temps de louvain guardats
    public int mitj_temps_Louvain(){
        int n = 0, TTotal = 0;
        int[] vec;
        for (int v : mapaLouvain.keySet()){
            vec = mapaLouvain.get(v);
            n += vec[1];
            TTotal += vec[0];
        }
        return (TTotal/n);
    }
    
    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de girvan Newman guardats
    public double mitj_mida_GN(){
        int n = 0;
        double n2 = 0, TamanyTotal = 0;
        int[] vec;
        for (int v : mapaLouvain.keySet()){
            vec = mapaLouvain.get(v);
            n += vec[1];
            TamanyTotal += v;
        }
        n2 = n;
        return (TamanyTotal/n2);
    }

    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de clicke guardats
    public double mitj_mida_Clicke(){
        int n = 0;
        double n2 = 0, TamanyTotal = 0;
        int[] vec;
        for (int v : mapaClicke.keySet()){
            vec = mapaClicke.get(v);
            n += vec[1];
            TamanyTotal += v;
        }
        n2 = n;
        return (TamanyTotal/n2);
    }    
    
    
    //Pre: cert
    //Post: retorna la mitjana dels tamanys de louvain guardats
    public double mitj_mida_Louvain(){
        int n = 0;
        double n2 = 0, TamanyTotal = 0;
        int[] vec;
        for (int v : mapaLouvain.keySet()){
            vec = mapaLouvain.get(v);
            n += vec[1];
            TamanyTotal += v;
        }
        n2 = n;
        return (TamanyTotal/n2);
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
        ArrayList<String> fila;
        System.out.println("filas: ");
        for (int i : object.keySet()){
            fila = new ArrayList();
            fila.add(String.valueOf(i));
            for (int j : object.get(i)){
                fila.add(String.valueOf(j));
            }
            ret.add(fila);
            System.out.println(fila);
        }
        return ret;
    }
    
    private HashMap<Integer, int[]>[] convertFromString(ArrayList<ArrayList<String>>[] value) throws IOException {
        HashMap<Integer, int[]>[] ret = new HashMap[3];
        ret[0] = new HashMap();
        ret[1] = new HashMap();
        ret[2] = new HashMap();
        ArrayList<ArrayList<String>> map0 = value[0];
        ArrayList<ArrayList<String>> map1 = value[1];
        ArrayList<ArrayList<String>> map2 = value[2];
        if ((map0 != null) && (!map0.isEmpty())){
            ListIterator l0 = map0.listIterator();
            ListIterator l1 = map1.listIterator();
            ListIterator l2 = map2.listIterator();
            ListIterator lAux;
            while (l0.hasNext()){
                int[] i = new int[3];
                int iAux;
                ArrayList<String> aux0 = (ArrayList<String>) l0.next();
                lAux = aux0.listIterator();
                iAux = Integer.valueOf((String) lAux.next());
                i[0] = Integer.valueOf((String) lAux.next());
                i[1] = Integer.valueOf((String) lAux.next());
                i[2] = Integer.valueOf((String) lAux.next());
                ret[0].put(iAux, i);
            }
            
            while (l1.hasNext()){
                int[] i = new int[3];
                int iAux;
                ArrayList<String> aux1 = (ArrayList<String>) l1.next();
                lAux = aux1.listIterator();
                iAux = Integer.valueOf((String) lAux.next());
                i[0] = Integer.valueOf((String) lAux.next());
                i[1] = Integer.valueOf((String) lAux.next());
                i[2] = Integer.valueOf((String) lAux.next());
                ret[1].put(iAux, i);
            }
            
            while (l2.hasNext()){
                int[] i = new int[3];
                int iAux;
                ArrayList<String> aux2 = (ArrayList<String>) l2.next();
                lAux = aux2.listIterator();
                iAux = Integer.valueOf((String) lAux.next());
                i[0] = Integer.valueOf((String) lAux.next());
                i[1] = Integer.valueOf((String) lAux.next());
                i[2] = Integer.valueOf((String) lAux.next());
                ret[2].put(iAux, i);
            }
        }
        return ret;
    }
    
    public void setSizesTimes(ArrayList<ArrayList<String>>[] a) throws IOException{
        HashMap<Integer, int[]>[] maps = convertFromString(a);
        mapaNewman = maps[0];
        mapaClicke = maps[1];
        mapaLouvain = maps[2];
    }
    
    //1r array de x, 2o de y, 3o de min y, 4o de max y
    public ArrayList<Double>[] getXYAlg(int alg) throws Exception{
        HashMap<Integer, int[]> inMap;
        ArrayList<Double>[] ret = new ArrayList[4];
        ret[0] = new ArrayList();
        ret[1] = new ArrayList();
        ret[2] = new ArrayList();
        ret[3] = new ArrayList();
        switch(alg){
            case 0: inMap = mapaNewman;
                    break;
            case 1: inMap = mapaClicke;
                    break;
            case 2: inMap = mapaLouvain;
                    break;
            default: throw new Exception("problema en el getXYAlg");
        }
        int[] vec;
        double mean = 0, devMin = 0, devMax, aux = 0, dev, meanSq, aux2;
        for (int a : inMap.keySet()){
            ret[0].add(aux + a);
            vec = inMap.get(a);
            //convertimos los vec a double trampeando
            mean = (aux + vec[0])/(aux + vec[1]);
            ret[1].add(mean);
            meanSq = mean*mean;
            aux2 = (aux + vec[2])/(aux + vec[1]);
            dev = Math.sqrt(aux2-meanSq);
            ret[2].add(mean-dev);
            ret[3].add(mean+dev);
        }
        return ret;
    }
    

}
