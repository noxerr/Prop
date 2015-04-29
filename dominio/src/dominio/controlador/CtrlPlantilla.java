/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio.controlador;

import dominio.Plantilla;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;


/**
 *
 * @author dani__000
 */
public class CtrlPlantilla {
    private static Plantilla plantilla = new Plantilla("Default");
    private static ArrayList<String> listaPlantillas = cargarListaPlantillas();
    private static ListIterator it = listaPlantillas.listIterator();
    
    public static void crearPlantilla(String nom) throws Exception{
        if (nom.equalsIgnoreCase("default")) throw new Exception("No puedes crear otra plantilla default.");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");
        else {
            plantilla = new Plantilla(nom);
            listaPlantillas.add(nom);
        }
    }
    
    public static void borrarPlantilla(String nom) throws FileNotFoundException{
        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else {
            listaPlantillas.remove(nom);
            //borrar plantilla de disco   
        }
    }
    
    public static void cargarPlantilla(String nom) throws FileNotFoundException{
        int[] nueva = new int[9];
        nueva[0] = 7; nueva[1] = 9; nueva[2] = 0; nueva[3] = 1;
        nueva[4] = 2; nueva[5] = 4; nueva[6] = 8; nueva[7] = 3; 
        nueva[8] = 13;
        //try int[] nueva = CtrlPersistencia.cargarPlantilla(nom);

        if (!listaPlantillas.contains(nom)) throw new FileNotFoundException();
        else {
            //int[] nueva = CtrlPersistencia.cargarPlantilla(nom);
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
    
    public static void guardarPlantilla() throws Exception{
        if ("default".equalsIgnoreCase(plantilla.getNom())) throw new Exception("No se puede guardar la plantilla por defecto");
        //comprobar si existe el nombre---else if (nom.equals(plantilla.getNom())) throw new Exception("Ja hi ha una plantilla amb aquest nom");
        else {
            Object[] ob;
            ob = getPond();
            //CtrlPersistencia.guardarPlantilla(ob);
        }
        /*for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }*/
    }
    
    public static ArrayList<String> cargarListaPlantillas(){
        //HashSet<String> ret = new HashSet<>();
        ArrayList<String> ret = new ArrayList<>();
        //cargar de disco
        return ret;
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
        if ("default".equalsIgnoreCase(plantilla.getNom())) throw new Exception("La plantilla default no se puede modificar");
        else if (listaPlantillas.contains(nom)) throw new Exception("Ya existe la plantilla "+ nom + ".");
        else {           
            String aux;
            if(!listaPlantillas.isEmpty()){
                it = listaPlantillas.listIterator();
                while (it.hasNext()){
                    aux = (String) it.next();
                    if (aux.equals(plantilla.getNom())) {
                        it.set(nom);
                    }
                }
            }
            plantilla.modNom(nom);
        }        
    }
    
}
