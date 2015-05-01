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
        CtrlEstadistica.resetearEstadisticas();
        String a = CtrlEstadistica.rapid();
    }
}
