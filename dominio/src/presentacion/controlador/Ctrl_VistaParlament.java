/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import dominio.controlador.Ctrl_diputat;
import java.lang.reflect.Array;
import presentacion.VistaParlament;

/**
 *
 * @author adrian
 */
public class Ctrl_VistaParlament {
    
    /* informacio codificada de la seguent manera:
        vector de tots els diputats que han estat al parlament
        informacio de cada diputat: 
    */
    
    private Ctrl_diputat ctrldip;
    private VistaParlament vP;
    
    public Ctrl_VistaParlament(VistaParlament instancia) {
        ctrldip = new Ctrl_diputat();
        vP = instancia;
    }
     
     public void guarda_diputats_fitxer(String fitxer) {
         ctrldip.guardaParlament(fitxer);
    }
    
    public void carrega_diputats_fitxer(String fitxer) {
        ctrldip.carregaParlamentDeFitxer(fitxer);
        vP.carrega_dades(ctrldip.getParlament());
        // dejo este error para seguir mañana, tengo que hacer que se carguen los datos
    }
    
    public void afegir_diputat(Vector<String> info) {
        Integer id = Integer.parseInt(info.get(0));
        String nom = info.get(1);
        Integer edat = Integer.parseInt(info.get(2));
        String sexe = info.get(3);
        String partit = info.get(4);
        String religio = info.get(5);
        if (ctrldip.creaDiputat(id, nom, religio, partit, sexe, edat)) {
            System.out.println("correcte");
        }
    }
    
    public void modifica_diputat(Vector<String> info) {
        // codi de modificar diputat
        Integer id = Integer.parseInt(info.get(0));
        Integer edat = Integer.parseInt(info.get(2));
        String sexe = info.get(3);
        String partit = info.get(4);
        String religio = info.get(5);
        ctrldip.nouPartit(partit, id);
        ctrldip.novaReligio(religio, id);
        ctrldip.modEdat(edat, id);
        ctrldip.modSexe(sexe, id);
        
    }
    
    public void eliminaDiputat(int dip) {
       ctrldip.eliminarDiputat(dip);
    }
    
    public Ctrl_diputat getCtrl_diputat() {
        return this.ctrldip;
    }
    
    private int TipusToInt(String tipus) {
        if ("Reunio".equals(tipus)) return 0;
        else if ("Esport".equals(tipus)) return 1;
        else if ("Reunio".equals(tipus)) return 2;
        else if ("Dinar".equals(tipus)) return 3;
        else return 4; // conferencia
   }
   
    // Pre: dip1, dip2 existeixen
    // Post: retorna nombre: {0,1,2,3,4,5} = {Reunio, Esport,Votacio =, Dinar,
    // Conferencia, Votacions !=}
    public Vector<String> relacio_diputats(int dip1, int dip2) {
       Vector<String> ret = new Vector<>();
        for (int i = 0; i < 6; ++i) {
            ret.add(String.valueOf(ctrldip.relacio(dip1, dip2, i)));
        }
        return ret;
        
    }
    
    public Vector<Vector<String>> getDiputats(Vector<Integer> id) {
        Vector<Vector<String>> ret = new Vector<Vector<String>>();
        for (int i: id) {
            Vector<String> v = new Vector<>();
            List<String> r = ctrldip.atributs_diputat(i);
            for (String aux: r) {
                v.add(aux);
            }
            ret.add(v);
        }
        return ret;
    }

    Ctrl_diputat getCtrl_dip() {
       return ctrldip;
    }
}
