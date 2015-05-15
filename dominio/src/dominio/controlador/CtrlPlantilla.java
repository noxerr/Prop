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
    private static final int[] aux = new int[9];
    private static final Plantilla plantilla = new Plantilla("Default", aux);
    private static ArrayList<String> listaPlantillas = cargarListaPlantillas();
    private static ListIterator it;
    public static Map<String,Plantilla> mapPlantillas = cargarPlantillas();
    private static String oldName;
    
    public static void crearPlantilla(String nom) throws Exception, IOException{
        if (nom.equalsIgnoreCase("default")) throw new Exception("No puedes crear otra plantilla default.");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");
        else {
            CtrlPersistenciaPlantilla.crearPlantilla(nom);
            listaPlantillas.add(nom);
            mapPlantillas.put(nom, new Plantilla(nom));
        }
    }
    
    public static void borrarPlantilla(String nom) throws FileNotFoundException, Exception{
        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else if(nom.equalsIgnoreCase("default")) throw new Exception("La plantilla default no se puede borrar");
        else {
            CtrlPersistenciaPlantilla.borrarPlantilla(nom);
            listaPlantillas.remove(nom); 
            mapPlantillas.remove(nom);
        }
    }
    
    /*public static void cargarPlantilla(String nom) throws IOException, FileNotFoundException{
        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else {
            int[] nueva = CtrlPersistenciaPlantilla.cargarPlantilla(nom);
            plantilla.modpVotacio(nueva[0]);
            plantilla.modpVotacioDif(nueva[1]);
            plantilla.modpReunio(nueva[2]);
            plantilla.modpConf(nueva[3]);
            plantilla.modpDinar(nueva[4]);
            plantilla.modpLleure(nueva[5]);
            plantilla.modpPartit(nueva[6]);
            plantilla.modpEdat(nueva[7]);
            plantilla.modpReligio(nueva[8]);
            plantilla.modNom(nom);
        }

        
    }*/
    
    public static void guardarPlantilla(String nom) throws Exception, IOException{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede guardar la plantilla por defecto");
        else {
            Object[] ob;
            ob = getPond(nom);
            CtrlPersistenciaPlantilla.guardarPlantilla(ob, oldName);
        }
        /*for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }*/
    }
    
    public static ArrayList<String> cargarListaPlantillas(){
        //HashSet<String> ret = new HashSet<>();
        ArrayList<String> ret = CtrlPersistenciaPlantilla.cargarListaPlantillas();
        return ret;
    }
    
    public static ArrayList<String> mostrarListaPlantillas(){
        return listaPlantillas;
    }
    
    //Pre: la llista conte les coincidencies en cada event, entre dos diputats
    //Post: retorna l’afinitat entre dip1 i dip2
    public static int calculAfinitat(int[] lista){
        int ret = plantilla.calculAfinitat(lista);
        return ret;
    }
    
    public static Object[] getPond(String nom){
        Object[] ret = plantilla.getPond();
        return ret;
    }
    
    public static void modAll(int[] listaPond, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpVotacio(listaPond[0]);
            plantilla.modpVotacioDif(listaPond[1]);
            plantilla.modpReunio(listaPond[2]);
            plantilla.modpConf(listaPond[3]);
            plantilla.modpDinar(listaPond[4]);
            plantilla.modpLleure(listaPond[5]);
            plantilla.modpPartit(listaPond[6]);
            plantilla.modpEdat(listaPond[7]);
            plantilla.modpReligio(listaPond[8]);
        }
    }
    
    public static void modVotacio(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpVotacio(p);
        }
    }
    
    public static void modVotacioDif(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpVotacioDif(p);
        }
    }
    
    public static void modReunio(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpReunio(p);
        }
    }
    
    public static void modConf(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpConf(p);
        }
    }
    
    public static void modDinar(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpDinar(p);
        }
    }
    
    public static void modLleure(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpLleure(p);
        }
    }
    
    public static void modPartit(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpPartit(p);
        }
    }
    
    public static void modEdat(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpEdat(p);
        }
    }
    
    public static void modReligio(int p, String nom) throws Exception{
        if ("default".equalsIgnoreCase(nom)) throw new Exception("No se puede modificar la plantilla default");
        else {
            plantilla.modpReligio(p);
        }
    }
    
    public static void modNom(String nom, String oldNom) throws Exception{
        if (nom.isEmpty()) throw new Exception ("El nom no pot estar buit");
        if ("default".equalsIgnoreCase(oldNom)) throw new Exception("La plantilla default no se puede modificar");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");    
        else {
            String aux;
            it = listaPlantillas.listIterator();
            while (it.hasNext()){
                aux = (String) it.next();
                if (aux.equals(oldNom)) {
                    it.set(nom);
                }
            }
            mapPlantillas.put(nom, mapPlantillas.get(oldNom));
            mapPlantillas.remove(oldNom);
        }
    }

    public static HashMap<String, Plantilla> cargarPlantillas() {
        ArrayList<ArrayList<String>> list = CtrlPersistenciaPlantilla.cargarPlantillas();
        HashMap<String, Plantilla> map = new HashMap();
        ListIterator l1 = list.listIterator();
        ListIterator l2;
        int[] pond = new int[9];
        String nom;
        int n = 0;
        Plantilla p;
        while (l1.hasNext()){
            l2 = ((ArrayList<String>) l1.next()).listIterator();
            n = 0;
            nom = (String) l2.next();
            while (l2.hasNext()){
                pond[n] = Integer.valueOf((String) l2.next());
                n++;
            }
            p = new Plantilla(nom,pond);
            map.put(nom, p);
        }
        return map;
    }
    
}
