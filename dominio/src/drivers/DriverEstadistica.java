/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package drivers;

import dominio.controlador.CtrlEstadistica;

/**
 *
 * @author dani__000
 */
public class DriverEstadistica {
    public static void main(String[] args) throws Exception {
        CtrlEstadistica.initEstadistica();
        System.out.println(CtrlEstadistica.rapid());
        System.out.println(CtrlEstadistica.petit());
        
        int[] mida = new int[3];
        int[] g = new int [3];
        mida[0] = 10; mida[1] = 15; mida[2] = 8;
        g[0] = 5; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        
        System.out.println(CtrlEstadistica.rapid());
        System.out.println(CtrlEstadistica.petit());
        
        System.out.println("mida mitja GN: " + CtrlEstadistica.mitj_mida_GN());
        System.out.println("mida mitja Clicke: " + CtrlEstadistica.mitj_mida_Clicke());
        System.out.println("mida mitja Clicke: " + CtrlEstadistica.mitj_mida_Clicke());
        System.out.println("mida mitja Louvain: " + CtrlEstadistica.mitj_mida_Louvain());
        
        CtrlEstadistica.guardarEstadistica();
    }
}
