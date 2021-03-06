/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio.controlador;

import dominio.Plantilla;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import persistencia.CtrlPersistenciaPlantilla;


/**
 *
 * @author dani__000
 */
public class CtrlPlantilla {
    private static ArrayList<String> listaPlantillas = new ArrayList();// = cargarListaPlantillas();
    private static ListIterator it;
    private static Map<String,Plantilla> mapPlantillas = cargarPlantillas();
    private static String oldName;
    
    public static void crearPlantilla(String nom) throws Exception, IOException{
        if (nom.equalsIgnoreCase("default")) throw new Exception("No pots crear un altre plantilla default.");
        else if (nom.isEmpty()) throw new Exception("El nom no pot estar buit.");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ja existeix la plantilla "+ nom + ".");
        else {
            listaPlantillas.add(nom);
            mapPlantillas.put(nom, new Plantilla(nom));
        }
    }
    
    
    public static void crearPlantilla(String nom, int[] lPonds) throws Exception, IOException{
        if (nom.equalsIgnoreCase("default")) throw new Exception("No pots crear un altre plantilla default.");
        else if (nom.isEmpty()) throw new Exception("El nom no pot estar buit.");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ja existeix la plantilla "+ nom + ".");
        else {
            boolean mal = false;
            for (int i = 0; (i < 9) && !mal; i++){
                if (lPonds[i] < 0){
                    mal = true;
                    throw new Exception("Les ponderacions no poden ser negatives");
                }
            }
            listaPlantillas.add(nom);
            mapPlantillas.put(nom, new Plantilla(nom, lPonds));
        }
    }
    
    
    public static void borrarPlantilla(String nom) throws FileNotFoundException, Exception{
        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else if(nom.equalsIgnoreCase("default")) throw new Exception("La plantilla default no es pot esborrar");
        else {
            listaPlantillas.remove(nom); 
            mapPlantillas.remove(nom);
        }
    }
    
    
    public static void guardarPlantillas() throws IOException{
        List<List<String>> list = new ArrayList();
        List<String> plantilla;
        for (String clave : mapPlantillas.keySet()) { 
            plantilla = new ArrayList();
            Plantilla valor = mapPlantillas.get(clave);
            Object[] ob = valor.getPond();
            List<Integer> aux = (List<Integer>) ob[1];
            plantilla.add((String) ob[0]);
            for (int i : aux){
                plantilla.add(String.valueOf(i));
            }
            list.add(plantilla);
        }
        CtrlPersistenciaPlantilla.guardarPlantillas(list);
    }
    
    
    public static ArrayList<Integer> getListaPond(String nom) throws FileNotFoundException{
        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else {
            return mapPlantillas.get(nom).getListaPond();
        }
    }
    
    public static HashMap<String, Plantilla> cargarPlantillas() {
        List<List<String>> list = CtrlPersistenciaPlantilla.cargarPlantillas();
        HashMap<String, Plantilla> map = new HashMap();
        ListIterator l1 = list.listIterator();
        ListIterator l2;
        int[] pond = new int[9];
        String nom;
        int n;
        while (l1.hasNext()){
            l2 = ((ArrayList<String>) l1.next()).listIterator();
            n = 0;
            nom = (String) l2.next();
            while (l2.hasNext()){
                pond[n] = Integer.valueOf((String) l2.next());
                n++;
            } 
            //ponder(pond);
            map.put(nom, new Plantilla(nom,pond));
        }
        for (String v : map.keySet()){
            if (!listaPlantillas.contains(v)) listaPlantillas.add(v);
        }
        if (!listaPlantillas.contains("Default")) {
            listaPlantillas.add("Default");
            map.put("Default", new Plantilla("Default", new int[9]));
        }
        return map;
    }
    
    
    public static Map<String,Plantilla> mostarMapaPlantillas(){
        return mapPlantillas;
    }
    
    
    public static ArrayList<String> mostrarListaPlantillas(){
        return listaPlantillas;
    }
    
    //Pre: la llista conte les coincidencies en cada event, entre dos diputats
    //Post: retorna l’afinitat entre dip1 i dip2
    public static int calculAfinitat(int[] lista, String nom) throws Exception{
        if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        Plantilla p = mapPlantillas.get(nom);
        int ret = p.calculAfinitat(lista);
        return ret;
    }
    
    public static Object[] getPond(String nom) throws Exception{
        if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        Plantilla p = mapPlantillas.get(nom);
        Object[] ret = p.getPond();
        return ret;
    }
    
    public static void modAll(int[] listaPond, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            boolean mal = false;
            for (int i = 0; (i < 9) && !mal; i++){
                if (listaPond[i] < 0){
                    mal = true;
                    throw new Exception("Les ponderacions no poden ser negatives");
                }
            }
            Plantilla p = mapPlantillas.get(nom);
            p.modpVotacio(listaPond[0]);
            p.modpVotacioDif(listaPond[1]);
            p.modpReunio(listaPond[2]);
            p.modpConf(listaPond[3]);
            p.modpDinar(listaPond[4]);
            p.modpLleure(listaPond[5]);
            p.modpPartit(listaPond[6]);
            p.modpEdat(listaPond[7]);
            p.modpReligio(listaPond[8]);
        }
    }
    
    public static void modVotacio(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpVotacio(p);
        }
    }
    
    public static void modVotacioDif(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpVotacioDif(p);
        }
    }
    
    public static void modReunio(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpReunio(p);
        }
    }
    
    public static void modConf(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpConf(p);
        }
    }
    
    public static void modDinar(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpDinar(p);
        }
    }
    
    public static void modLleure(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpLleure(p);
        }
    }
    
    public static void modPartit(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpPartit(p);
        }
    }
    
    public static void modEdat(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpEdat(p);
        }
    }
    
    public static void modReligio(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else if (!mapPlantillas.containsKey(nom)) throw new Exception("La plantilla no existe");
        else {
            if (p < 0) 
                throw new Exception("Les ponderacions no poden ser negatives");
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpReligio(p);
        }
    }
    
    public static void modNom(String nom, String oldNom) throws Exception{
        if (nom.isEmpty()) throw new Exception ("El nom no pot estar buit");
        if ("default".equalsIgnoreCase(oldNom)) throw new Exception("La plantilla default no se puede modificar");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");    
        else {
            String auxi;
            it = listaPlantillas.listIterator();
            while (it.hasNext()){
                auxi = (String) it.next();
                if (auxi.equals(oldNom)) {
                    it.set(nom);
                }
            }
            mapPlantillas.put(nom, mapPlantillas.get(oldNom));
            mapPlantillas.remove(oldNom);
        }
    }

    
}
