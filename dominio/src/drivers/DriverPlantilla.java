/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package drivers;

import dominio.Plantilla;
import dominio.controlador.CtrlEstadistica;
import dominio.controlador.CtrlPlantilla;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import persistencia.Persistencia;
import presentacion.NewJFrame;

/**
 *
 * @author dani__000
 */
public class DriverPlantilla {
    public static void main(String[] args) throws Exception {
        CtrlPlantilla.cargarPlantillas();
        Object[] ob = CtrlPlantilla.getPond("Default");
        Map<String,Integer> mapa = (Map) ob[1];
        String ret = (String) ob[0];
        System.out.println("Valores default: nom - " + ret + ".\n");
        
        
        //PROBANDO FUNCION GETPOND
        for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }
        
        
        
        try{
            //PROBANDO MODNOM ESTANDO EN LA PLANTILLA DEFAULT
            CtrlPlantilla.modNom("nueva","this");
        }
        catch(Exception e){
            //e.printStackTrace();
            System.out.println("No puede repetir el nombre default");
            //System.exit(17);
            //return;
        }
        System.out.println("\nmuletilla 1");
        
        
        
        //PROBANDO DE CREAR UNA PLANTILLA NUEVA
        CtrlPlantilla.crearPlantilla("nueva");
        System.out.println("muletilla 2");
        
        try{
            //MODNOM CON PLANTILLA DIFERENTE A DEFAULT
            CtrlPlantilla.modNom("primera", "this");
        }
        catch(Exception e){
            //e.printStackTrace();
            System.out.println("No puede repetir el nombre default..");
            //System.exit(17);
            //return;
        }
        
        
        int[] listaPond = new int[9];
        for (int i = 0; i<9; i++){
            listaPond[i] = i;
        }
        
        
        //COMPROBANDO FUNCION MODALL CON PLANTILLA DIF DE DEFAULT
        //CtrlPlantilla.modAll(listaPond);
        
        
        //FUNCION GETPOND
        ob = CtrlPlantilla.getPond("Default");
        ret = (String) ob[0];
        mapa = (Map) ob[1];
        System.out.println("Valores modificados: nom - " + ret + ".\n");
        for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }
        
        
        //CALCUL AFINITAT
        int retur = CtrlPlantilla.calculAfinitat(listaPond, "Default");
        //deberia dar 204 (0*0 + 1*1 + 2*2 + 3*3.. +8*8 xk las ponderaciones las he puesto
        //de 0 a 8 y las coincidencias de eventos tambien
        System.out.println("La afinitat dels dos diputats es de: " + retur);
        
        //probando de crear otra plantilla con un nombre ya existente
        try{
            CtrlPlantilla.crearPlantilla("Default");
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("entra aki1");
        }
        
        try{
            CtrlPlantilla.crearPlantilla("primera");
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("entra aki2");
        }
        
        //probando que el modnom haya cambiado el nombre en el vector de strings 
        //y por lo tanto me deje crearla
        try{
            CtrlPlantilla.crearPlantilla("nueva");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("entra aki3");
        }
        
        //comprobando mostrarListaPlantillas
        System.out.println(CtrlPlantilla.mostrarListaPlantillas().toString());
        
        //FUNCION GETPOND antes de cargar plantilla
        ob = CtrlPlantilla.getPond("Default");
        ret = (String) ob[0];
        mapa = (Map) ob[1];
        System.out.println("\n\nValores modificados: nom - " + ret + ".\n");
        for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }
        
        
        
        //cargarplantilla
        //CtrlPlantilla.cargarPlantilla("primera");
        //FUNCION GETPOND despues de cargar
        ob = CtrlPlantilla.getPond("primera");
        ret = (String) ob[0];
        mapa = (Map) ob[1];
        System.out.println("\n\nValores modificados: nom - " + ret + ".\n");
        for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }
        
        //cargando plantilla que no existe
       /* try{
            CtrlPlantilla.cargarPlantilla("noExisto");
        }catch(FileNotFoundException f){
            System.out.println("\n" + f.toString() + ". No existe la plantilla");
        }*/
        
        
        //borrando plantilla
        CtrlPlantilla.borrarPlantilla("nueva");
        
        //comprobando mostrarListaPlantillas despues de borrar plantilla
        System.out.println(CtrlPlantilla.mostrarListaPlantillas().toString());
        
        
        //borrando plantilla que no existe
        try{
            CtrlPlantilla.borrarPlantilla("noExisto");
        }catch(FileNotFoundException f){
            System.out.println("\n" + f.toString() + ". No existe la plantilla a borrar");
        }
        
        Scanner input = new Scanner(System.in);
        /*while(true){
            int a = input.nextInt();
        }*/
        
        ArrayList<String> ar = new ArrayList();
        Persistencia.guardarDisco(ar, "hola2.txt", ret);
        Persistencia.cargarDisco("hola2.txt", ret);
        CtrlEstadistica.initEstadistica();
        new NewJFrame().setVisible(true);
        
        Map<String,Plantilla> mapaPlantillas = CtrlPlantilla.mostarMapaPlantillas();
        Plantilla p = mapaPlantillas.get("una");
        int n = 0;
        Object[] a = p.getPond();
        int[] i = new int[9];
        int k = 0;
        HashMap<String, Integer> mapita = (HashMap<String, Integer>) a[1];
        for (String s : mapita.keySet()){
            i[k] = mapita.get(s);
            k++;
        }
        while(n < 9){
            System.out.println(i[n]);
            n++;
        }
        
    }
}
