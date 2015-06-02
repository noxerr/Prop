/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controlador;

import dominio.controlador.Ctrl_diputat;
import dominio.controlador.Ctrl_event;
import java.util.Vector;
import dominio.Data;
import dominio.Pair;
import java.util.ArrayList;
import java.util.List;
import presentacion.VistaEvent;

/**
 *
 * @author adrian
 */

// IMPORTANTE, SE TIENE QUE INSTANCIAR DESPUES DE CTRL_VISTADIPUTAT
public class Ctrl_VistaEvent {
    
    private Ctrl_event ctrlev;
    private VistaEvent vE;
    private Ctrl_diputat cP;
    
    public Ctrl_VistaEvent(VistaEvent instancia, Ctrl_VistaParlament inst) {
        ctrlev = new Ctrl_event(inst.getCtrl_dip());
        cP = inst.getCtrl_dip();
        vE = instancia;
    }
    
    
   // de momento solo el nombre
    public void guarda_events_fitxer(String fitxer) {
         ctrlev.guardaEvents(fitxer);
    }
    
    public void carrega_events_fitxer(String fitxer) {
        ctrlev.carregaEvents(fitxer);
        List< List<String>> aux = ctrlev.getHistorialCarrega();
        Vector<String> v;
        for (List<String> it: aux) {
            v = new Vector<>();
            v.add(it.get(0));
            v.add(it.get(1));
            v.add(it.get(2));
            vE.afegir_dades_taula(v);
        }
        
        // dejo este error para seguir mañana, tengo que hacer que se carguen los datos
    }
    
    // falta implementarlas
    private int TipusToInt(String tipus) {
        if ("Reunio".equals(tipus)) return 0;
        else if ("Esport".equals(tipus)) return 1;
        else if ("Votacio".equals(tipus)) return 2;
        else if ("Dinar".equals(tipus)) return 3;
        else return 4; // conferencia
   }
    
    
    public void afegir_event(Vector<String> info) {
        String nom = info.get(0);
        String tipus = info.get(1);
        String data = info.get(2);
        ctrlev.crearEvent(TipusToInt(tipus), Data.StringToData(data), nom);
    }
    
    public void modifica_tipus_event(Vector<String> info) {
        // codi de modificar diputat
    }
    
    public void eliminaEvent(String nomE) {
       ctrlev.eliminaEvent(nomE);
    }
    
    private List<List<String>> getInfoDips(Vector<Integer> v) {
        List<List<String>> ret = new ArrayList<>();
        for (int i: v) {
            ret.add(cP.atributs_diputat(i));
        }
        return ret;
    }
    public  List<List<String>> consultaAssociats(String nomEvent) {
        
       return getInfoDips(ctrlev.consultarAssociats(nomEvent));
    }
    
     
    private String IntToVot(int vot) {
        if (vot == 0) return "Si";
        else if (vot == 1) return "No";
        else if (vot == 2) return "Blanc";
        else return "No assistit";
    }
    
    public List<List<String>> consultaAssociatsVotacio(String nomEvent) {
        ArrayList<ArrayList<Integer>> aux = ctrlev.consultaVotacions(nomEvent);
        List<List<String>> ret = new ArrayList<>();
          int i = 0;
        for (ArrayList<Integer> it: aux) {
            System.out.println(it);
            for (int n: it) {
                List<String> dip = new ArrayList<>();
                dip.addAll(cP.atributs_diputat(n));
                dip.add(IntToVot(i));
                ret.add(dip);
                System.out.println(dip);
            }
            ++i;
        }
        
        return ret;
    }
    
    public List<List<String>> consultaPotencialsAssociar(String nomEvent) {
        Vector<Integer> aux = ctrlev.consultarAssociats(nomEvent);
        Vector<Integer> ret = cP.getIdsDiputats();
        ret.removeAll(aux);
        return getInfoDips(ret);
    }
    
    public void associarAEvent(String nomE, Vector<Integer> dip) {
        ctrlev.cambiaRelacioDiputats(nomE, dip, 1, false);
    }
    
    public void desassEvent(String nomE, Vector<Integer> dip) {
        ctrlev.cambiaRelacioDiputats(nomE, dip, -1, false);
    }

    public void eliminaEventVotacio(String nomEv) {
        ctrlev.eliminaEventVotacio(nomEv);
    }
    
    public void assignaVotacio(String nomEv, Vector<Pair<Integer,Integer>> id_dip) {
       ctrlev.modificaRelacioVotacio(nomEv, id_dip, 1);
    }
    
    public boolean permetAssignarVotacio(String nomEv) {
        ArrayList<ArrayList<Integer>> s = ctrlev.consultaVotacions(nomEv);
        int r = 0;
        for (ArrayList<Integer> t: s) {
           for (int i: t) {
               ++r;
           }
           if (r > 0) return false;
        }
        return true;
    }
  
}
