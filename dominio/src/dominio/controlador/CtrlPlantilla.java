<<<<<<< HEAD
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
import java.util.ListIterator;
import persistencia.CtrlPersistenciaPlantilla;


/**
 *
 * @author dani__000
 */
public class CtrlPlantilla {
    private static Plantilla plantilla = new Plantilla("Default");
    private static ArrayList<String> listaPlantillas = cargarListaPlantillas();
    private static ListIterator it = listaPlantillas.listIterator();
    private static String oldName;
    
    public static void crearPlantilla(String nom) throws Exception, IOException{
        if (nom.equalsIgnoreCase("default")) throw new Exception("No puedes crear otra plantilla default.");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");
        else {
            CtrlPersistenciaPlantilla.crearPlantilla(nom);
            plantilla = new Plantilla(nom);
            listaPlantillas.add(nom);
        }
    }
    
    public static void borrarPlantilla(String nom) throws FileNotFoundException, Exception{
        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else if(nom.equalsIgnoreCase("default")) throw new Exception("La plantilla default no se puede borrar");
        else {
            CtrlPersistenciaPlantilla.borrarPlantilla(nom);
            listaPlantillas.remove(nom);  
        }
    }
    
    public static void cargarPlantilla(String nom) throws IOException, FileNotFoundException{
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

        
    }
    
    public static void guardarPlantilla() throws Exception, IOException{
        if ("default".equalsIgnoreCase(plantilla.getNom())) throw new Exception("No se puede guardar la plantilla por defecto");
        else {
            Object[] ob;
            ob = getPond();
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
    
    public static Object[] getPond(){
        Object[] ret = plantilla.getPond();
        return ret;
    }
    
    public static void modAll(int[] listaPond) throws Exception{
        if ("default".equalsIgnoreCase(plantilla.getNom())) throw new Exception("No se puede modificar la plantilla default");
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
    
    public static void modNom(String nom) throws Exception{
        if (nom.isEmpty()) throw new Exception ("El nom no pot estar buit");
        if ("default".equalsIgnoreCase(plantilla.getNom())) throw new Exception("La plantilla default no se puede modificar");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");
        else {           
            String aux;
            if(!listaPlantillas.isEmpty()){
                oldName = plantilla.getNom();
                it = listaPlantillas.listIterator();
                while (it.hasNext()){
                    aux = (String) it.next();
                    if (aux.equals(oldName)) {
                        it.set(nom);
                    }
                }
            }
            plantilla.modNom(nom);
        }        
    }
    
    public static Plantilla getPlantilla(){
        return plantilla;
    }
    
}
=======
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
import java.util.ListIterator;
import persistencia.CtrlPersistenciaPlantilla;


/**
 *
 * @author dani__000
 */
public class CtrlPlantilla {
    private static Plantilla plantilla = new Plantilla("Default");
    private static ArrayList<String> listaPlantillas = cargarListaPlantillas();
    private static ListIterator it = listaPlantillas.listIterator();
    private static String oldName;
    
    public static void crearPlantilla(String nom) throws Exception, IOException{
        if (nom.equalsIgnoreCase("default")) throw new Exception("No puedes crear otra plantilla default.");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");
        else {
            CtrlPersistenciaPlantilla.crearPlantilla(nom);
            plantilla = new Plantilla(nom);
            listaPlantillas.add(nom);
        }
    }
    
    public static void borrarPlantilla(String nom) throws FileNotFoundException, Exception{
        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else if(nom.equalsIgnoreCase("default")) throw new Exception("La plantilla default no se puede borrar");
        else {
            CtrlPersistenciaPlantilla.borrarPlantilla(nom);
            listaPlantillas.remove(nom);  
        }
    }
    
    public static void cargarPlantilla(String nom) throws IOException, FileNotFoundException{
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

        
    }
    
    public static void guardarPlantilla() throws Exception, IOException{
        if ("default".equalsIgnoreCase(plantilla.getNom())) throw new Exception("No se puede guardar la plantilla por defecto");
        else {
            Object[] ob;
            ob = getPond();
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
    
    public static Object[] getPond(){
        Object[] ret = plantilla.getPond();
        return ret;
    }
    
    public static void modAll(int[] listaPond) throws Exception{
        if ("default".equalsIgnoreCase(plantilla.getNom())) throw new Exception("No se puede modificar la plantilla default");
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
    
    public static void modNom(String nom) throws Exception{
        if (nom.isEmpty()) throw new Exception ("El nom no pot estar buit");
        if ("default".equalsIgnoreCase(plantilla.getNom())) throw new Exception("La plantilla default no se puede modificar");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");
        else {           
            String aux;
            if(!listaPlantillas.isEmpty()){
                oldName = plantilla.getNom();
                it = listaPlantillas.listIterator();
                while (it.hasNext()){
                    aux = (String) it.next();
                    if (aux.equals(oldName)) {
                        it.set(nom);
                    }
                }
            }
            plantilla.modNom(nom);
        }        
    }
    
    public static Plantilla getPlantilla(){
        return plantilla;
    }
    
}
>>>>>>> a0459c0d47d5199a82aeba36a50e79f424217c73
