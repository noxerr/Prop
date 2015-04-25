/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio.controlador;

import dominio.Plantilla;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author dani__000
 */
public class CtrlPlantilla {
    private static Plantilla plantilla;
    
    public static void cargarPlantilla(String nom){
        int[] nueva = new int[9];
        nueva[0] = 7;
        nueva[1] = 9;
        nueva[2] = 0;
        nueva[3] = 1;
        nueva[4] = 2;
        nueva[5] = 4;
        nueva[6] = 8;
        nueva[7] = 3; 
        nueva[8] = 13;
        //int[] nueva = CtrlPersistencia.cargarPlantilla(nom);
        try {
            if (nueva == null) throw new Exception();
            else {
                System.out.println("Existe y se ha cargado satisfactoriamente");
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
        } catch (Exception e) {
            System.out.println("No existe la plantilla");
        }
        
    }
    
    public static void guardarPlantilla(){
        Object[] ob;
        ob = getPond();
        /*for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }*/
        //CtrlPersistencia.guardarPlantilla(ob);
    }
    
    public static String[] cargarListaPlantillas(){
        String[] ret = null;
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
}
