/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controlador;

import dominio.Plantilla;
import dominio.controlador.CtrlEstadistica;
import dominio.controlador.CtrlPlantilla;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import persistencia.CtrlPersistenciaPlantilla;

/**
 *
 * @author Dani
 */
public class CtrlPresentacionPlantilla {
    public static void crearPlantilla(String nom) throws Exception, IOException{
        CtrlPlantilla.crearPlantilla(nom);
    }
    
    public static void crearPlantilla(String nom, int[] lPonds) throws Exception, IOException{
        CtrlPlantilla.crearPlantilla(nom, lPonds);
    }
    
    public static void borrarPlantilla(String nom) throws FileNotFoundException, Exception{
        CtrlPlantilla.borrarPlantilla(nom);
    }
    
    public static void guardarPlantillas() throws IOException{
        CtrlPlantilla.guardarPlantillas();
    }
    
    public static void cargarPlantillas() {
        CtrlPlantilla.cargarPlantillas();
    }
    
    public static ArrayList<Integer> getListaPond(String nom) throws FileNotFoundException{
        return CtrlPlantilla.getListaPond(nom);
    }
    
    
    public static Map<String,Plantilla> mostarMapaPlantillas(){
        return CtrlPlantilla.mostarMapaPlantillas();
    }
    
    
    public static ArrayList<String> mostrarListaPlantillas(){
        return CtrlPlantilla.mostrarListaPlantillas();
    }
    
    //Pre: la llista conte les coincidencies en cada event, entre dos diputats
    //Post: retorna l’afinitat entre dip1 i dip2
    public static int calculAfinitat(int[] lista, String nom) throws Exception{
        return CtrlPlantilla.calculAfinitat(lista, nom);
    }
    
    public static Object[] getPond(String nom) throws Exception{
        return CtrlPlantilla.getPond(nom);
    }
    
    public static void modAll(int[] listaPond, String nom) throws Exception{
        CtrlPlantilla.modAll(listaPond, nom);
    }
    
    public static void modVotacio(int p, String nom) throws Exception{
        CtrlPlantilla.modVotacio(p, nom);
    }
    
    public static void modVotacioDif(int p, String nom) throws Exception{
        CtrlPlantilla.modVotacioDif(p, nom);
    }
    
    public static void modReunio(int p, String nom) throws Exception{
        CtrlPlantilla.modReunio(p, nom);
    }
    
    public static void modConf(int p, String nom) throws Exception{
        CtrlPlantilla.modConf(p, nom);
    }
    
    public static void modDinar(int p, String nom) throws Exception{
        CtrlPlantilla.modDinar(p, nom);
    }
    
    public static void modLleure(int p, String nom) throws Exception{
        CtrlPlantilla.modLleure(p, nom);
    }
    
    public static void modPartit(int p, String nom) throws Exception{
        CtrlPlantilla.modPartit(p, nom);
    }
    
    public static void modEdat(int p, String nom) throws Exception{
        CtrlPlantilla.modEdat(p, nom);
    }
    
    public static void modReligio(int p, String nom) throws Exception{
        CtrlPlantilla.modReligio(p, nom);
    }
    
    public static void modNom(String nom, String oldNom) throws Exception{
        CtrlPlantilla.modNom(nom, oldNom);
    }
}
