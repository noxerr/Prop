/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package drivers;

import dominio.controlador.CtrlPlantilla;
import java.util.Map;

/**
 *
 * @author dani__000
 */
public class DriverPlantilla {
    public static void main(String[] args) throws Exception {
        Object[] ob = CtrlPlantilla.getPond();
        Map<String,Integer> mapa = (Map) ob[1];
        String ret = (String) ob[0];
        System.out.println("Valores default: nom - " + ret + ".\n");
        for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }
        
        try{
            CtrlPlantilla.modNom("nueva");
        }
        catch(Exception e){
            e.printStackTrace();
            //System.out.println("No puede repetir el nombre default");
            //return;
        }
        int[] listaPond = new int[9];
        for (int i = 0; i<9; i++){
            listaPond[i] = i;
        }
        CtrlPlantilla.modAll(listaPond);
        ob = CtrlPlantilla.getPond();
        ret = (String) ob[0];
        mapa = (Map) ob[1];
        System.err.println("Valores modificados: nom - " + ret + ".\n");
        for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }
        
        
    }
}
