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
        if (nom.equalsIgnoreCase("default")) throw new Exception("No puedes crear otra plantilla default.");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");
        else {
            listaPlantillas.add(nom);
            mapPlantillas.put(nom, new Plantilla(nom));
        }
    }
    
    public static void borrarPlantilla(String nom) throws FileNotFoundException, Exception{
        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else if(nom.equalsIgnoreCase("default")) throw new Exception("La plantilla default no se puede borrar");
        else {
            //CtrlPersistenciaPlantilla.borrarPlantilla(nom);
            listaPlantillas.remove(nom); 
            mapPlantillas.remove(nom);
        }
    }
    
    
    public static void guardarPlantillas() throws IOException{
        ArrayList<ArrayList<String>> list = new ArrayList();
        ArrayList<String> plantilla;
        for (String clave : mapPlantillas.keySet()) { 
            plantilla = new ArrayList();
            Plantilla valor = mapPlantillas.get(clave);
            Object[] ob = valor.getPond();
            Map<String, Integer> mp = (Map<String, Integer>) ob[1];
            plantilla.add((String) ob[0]);
            for (int i : mp.values()){
                plantilla.add(String.valueOf(i));
            }
            list.add(plantilla);
        }
        CtrlPersistenciaPlantilla.guardarPlantillas(list);
    }
    
    public static HashMap<String, Plantilla> cargarPlantillas() {
        ArrayList<ArrayList<String>> list = CtrlPersistenciaPlantilla.cargarPlantillas();
        HashMap<String, Plantilla> map = new HashMap();
        ListIterator l1 = list.listIterator();
        ListIterator l2;
        int[] pond = new int[9];
        String nom;
        int n;
        Plantilla p;
        while (l1.hasNext()){
            l2 = ((ArrayList<String>) l1.next()).listIterator();
            n = 0;
            nom = (String) l2.next();
            while (l2.hasNext()){
                pond[n] = Integer.valueOf((String) l2.next());
                n++;
            }
            ponder(pond);
            p = new Plantilla(nom,pond);
            map.put(nom, p);
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
    
    
    private static void ponder(int[] pond) {
        int aux = pond[6];
        pond[6] = pond[1];
        pond[1] = pond[4];
        pond[4] = pond[3];
        pond[3] = pond[2];
        pond[2] = pond[5];
        pond[5] = pond[8];
        pond[8] = pond[7];
        pond[7] = aux;
    }
    
    public static Map<String,Plantilla> mostarMapaPlantillas(){
        return mapPlantillas;
    }
    
    
    public static ArrayList<String> mostrarListaPlantillas(){
        return listaPlantillas;
    }
    
    //Pre: la llista conte les coincidencies en cada event, entre dos diputats
    //Post: retorna l’afinitat entre dip1 i dip2
    public static int calculAfinitat(int[] lista, String nom){
        Plantilla p = mapPlantillas.get(nom);
        int ret = p.calculAfinitat(lista);
        return ret;
    }
    
    public static Object[] getPond(String nom){
        Plantilla p = mapPlantillas.get(nom);
        Object[] ret = p.getPond();
        return ret;
    }
    
    public static void modAll(int[] listaPond, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
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
        else {
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpVotacio(p);
        }
    }
    
    public static void modVotacioDif(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpVotacioDif(p);
        }
    }
    
    public static void modReunio(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpReunio(p);
        }
    }
    
    public static void modConf(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpConf(p);
        }
    }
    
    public static void modDinar(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpDinar(p);
        }
    }
    
    public static void modLleure(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpLleure(p);
        }
    }
    
    public static void modPartit(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpPartit(p);
        }
    }
    
    public static void modEdat(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            Plantilla pl = mapPlantillas.get(nom);
            pl.modpEdat(p);
        }
    }
    
    public static void modReligio(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
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
