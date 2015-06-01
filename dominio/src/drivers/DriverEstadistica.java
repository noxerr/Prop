/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package drivers;

import dominio.controlador.CtrlEstadistica;
import presentacion.NewJFrame;

/**
 *
 * @author dani__000
 */
public class DriverEstadistica {
    public static void main(String[] args) throws Exception {
        CtrlEstadistica.initEstadistica();
        
        int[] mida = new int[3];
        int[] g = new int [3];
        mida[0] = 10; mida[1] = 15; mida[2] = 10;
        g[0] = 50; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 15;
        g[0] = 55; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 15;
        g[0] = 55; g[1] = 7; g[2] = 158;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 15;
        g[0] = 55; g[1] = 17; g[2] = 154;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 20;
        g[0] = 60; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        /*
        mida[0] = 10; mida[1] = 15; mida[2] = 8;
        g[0] = 50; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 9;
        g[0] = 45; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 19; mida[2] = 9;
        g[0] = 60; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        
        mida[0] = 10; mida[1] = 19; mida[2] = 7;
        g[0] = 50; g[1] = 4; g[2] = 140;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 20; mida[2] = 7;
        g[0] = 75; g[1] = 45; g[2] = 137;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 7;
        g[0] = 40; g[1] = 23; g[2] = 142;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        
        mida[0] = 10; mida[1] = 15; mida[2] = 8;
        g[0] = 50; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 9;
        g[0] = 55; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 9;
        g[0] = 60; g[1] = 4; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        
        mida[0] = 10; mida[1] = 15; mida[2] = 14;
        g[0] = 50; g[1] = 54; g[2] = 150;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 13; mida[1] = 12; mida[2] = 19;
        g[0] = 45; g[1] = 17; g[2] = 120;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 13; mida[1] = 12; mida[2] = 19;
        g[0] = 70; g[1] = 24; g[2] = 137;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        
        mida[0] = 10; mida[1] = 12; mida[2] = 14;
        g[0] = 20; g[1] = 30; g[2] = 100;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 17; mida[1] = 20; mida[2] = 17;
        g[0] = 75; g[1] = 45; g[2] = 137;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 15; mida[1] = 17; mida[2] = 7;
        g[0] = 90; g[1] = 23; g[2] = 112;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        
        mida[0] = 100; mida[1] = 120; mida[2] = 140;
        g[0] = 200; g[1] = 300; g[2] = 1000;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 170; mida[1] = 200; mida[2] = 170;
        g[0] = 750; g[1] = 450; g[2] = 1370;
        CtrlEstadistica.afegeix_mida_temps(mida, g);
        mida[0] = 150; mida[1] = 170; mida[2] = 70;
        g[0] = 900; g[1] = 230; g[2] = 1120;
        CtrlEstadistica.afegeix_mida_temps(mida, g);*/
        
        System.out.println("temps mitg GN: " + CtrlEstadistica.mitj_temps_GN());
        System.out.println("mida mitja Clicke: " + CtrlEstadistica.mitj_mida_Clicke());
        System.out.println("mida mitja Louvain: " + CtrlEstadistica.mitj_mida_Louvain());
        
        CtrlEstadistica.guardarEstadistica();
        CtrlEstadistica.mitj_mida_Louvain();
        CtrlEstadistica.getXYAlg(0);
        new NewJFrame().setVisible(true);
    }
}
