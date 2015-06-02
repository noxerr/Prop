package dominio.controlador;
import java.util.*;

import dominio.Data;
import dominio.Diputat;
import dominio.Event;
import dominio.EventVotacio;
import dominio.Pair;
import persistencia.Ctrl_pers;

public class Ctrl_event {
    private Map<String, Event> registre;
    private Ctrl_diputat ctrl_diputat;
    private int num_vot;

    private boolean votacio (String nomE) {
            return (registre.get(nomE).consultarTipus() == 2);
    }
    public Ctrl_event(Ctrl_diputat ctrl_diputat) {
            // preserva el orden de entrada
            registre = new LinkedHashMap<String, Event> (0);
            this.ctrl_diputat = ctrl_diputat;
            num_vot = 0; // nomes per al driver
    }
    /*private boolean checkEvent(Event e) {
            return registre.contains(e);
    }*/

    private boolean checkEvent(String s) {
            return registre.containsKey(s);
    }

    public boolean crearEvent (int tipusEvent,Data dataEvent,String nomEvent) {
            Event aux;
            // COMPRUEBO QUE EL EVENTO NO EXISTA
            if (tipusEvent != 2) aux = new Event(tipusEvent, dataEvent,nomEvent);
            else {
                    aux = new EventVotacio(dataEvent, nomEvent);
                    num_vot += 1;
            }
            if (! checkEvent(nomEvent)) {
                    registre.put(nomEvent, aux); // si ya esta no lo modifica, retornara falso
                    return true;
            }
            return false;
    }

    // clase nomes per a provar el driver
    public int getnumvot() {
            return num_vot;
    }

    public boolean cambiaRelacioDiputats(String nomEvent, Vector<Integer>idsDiputats, int modif, boolean borrar_Ev) {
        if (! checkEvent(nomEvent) || votacio (nomEvent))  return false; // si no existeix o de tipus votacio
        Event aux = registre.get(nomEvent);
        Vector<Integer> dipA = aux.consultarAssociats();
        Vector<Diputat> dip = ctrl_diputat.GetDiputats(dipA);
        boolean nou = false;
        // si es biut son tots nous. Primerament els he d'afegir. Un event no buit al que se
        // l'afegeixen nous diputats ha de mantenir la relacio amb els que ja tenia i cambiarla
        // respecte els nous
        if (dipA.size() == 0) {
                // esta vacio, s'afegeixen nous
                nou = true;
                if (modif == 1) {
                        if (!aux.associarDiputats(idsDiputats)) return false;
        }

        }
        /*System.out.println(dipA);
        System.out.println(idsDiputats);*/
        for(int i:dipA) {
                // per a tots els de l'event
                for (int j : idsDiputats) {
                        // modifico la relacio amb els nous
                        if (i != j) { // no es ell mateix
                                // he de combprovar que existeixin (pot ser que sigui un antic que ara no esta
                                // al parlament
                                if (ctrl_diputat.checkDiputat(i) && ctrl_diputat.checkDiputat(j)) {
                                        ctrl_diputat.GetDiputat(i).SetRelevent(registre.get(nomEvent).consultarTipus(), j, modif);
                                        if (!nou && !borrar_Ev) {
                                                // abans he fet antic respecte nou
                                                ctrl_diputat.GetDiputat(j).SetRelevent(registre.get(nomEvent).consultarTipus(), i, modif);
                                                // ara faig nou respecte antic
                                        }
                                }
                        }

                }
        }
        if (!nou && modif == 1) {
                aux.associarDiputats(idsDiputats);
        }
        if (modif == -1) {
                aux.eliminarDiputats(idsDiputats);
        }
        return true;
    }

    // diff sempre hauria de valer 1 si es per afegir votacions
    public void modificaRelacioVotacio(String nomV, Vector<Pair<Integer,Integer>> id_vot, int diff) {
            if (! checkEvent(nomV) || !votacio (nomV)) return;
            //System.out.println("tiene tamano " + id_vot.size());
            for (Pair<Integer,Integer> i: id_vot) {
                    if (diff == 1) introduirVotDiputat(nomV, i);
                    for (Pair<Integer, Integer> j: id_vot) {
                            // afegeixo els diputats a l'event

                            if (i.getFirst() != j.getFirst()) {
                                    if (ctrl_diputat.checkDiputat(i.getFirst()) && ctrl_diputat.checkDiputat(j.getFirst())) {
                                            Diputat aux = ctrl_diputat.GetDiputat(i.getFirst());
                                            int factor;
                                            if (i.getSecond() != j.getSecond()) {
                                                    factor = 5;
                                            }
                                            else factor = 2;
                                            // afegeixo o trec (diff = 1, -1 respectivament)amb el diputat aux que ha votat igual o diferent amb el j 
                                            aux.SetRelevent(factor, j.getFirst(), diff);
                                    }
                            }
                    }
            }
            // no hace falta eliminarlo, ya lo hace la otra funcion
    }

    public void eliminaEvent(String nomE) {
            if (! checkEvent(nomE) || votacio (nomE)) return; // si no existeix o es una votacio
            Event aux = registre.get(nomE);
            Vector<Integer> dips = aux.consultarAssociats();
            // s'ha de borrar
            cambiaRelacioDiputats(nomE, dips, -1,true);
            registre.remove(nomE);
    }

    public void eliminaEventVotacio(String nomE) {
            if (! checkEvent(nomE) || !votacio (nomE)) return; // si no existeix o no es una votacio
            EventVotacio aux = (EventVotacio) registre.get(nomE);
            // Vector<Integer> dips = aux.consultarAssociats();
            ArrayList<ArrayList<Integer>> al = aux.consultaVotacions();
            Integer index = 0;
            Vector<Pair<Integer, Integer>> parells = new Vector<Pair<Integer,Integer>>(0);
            // 4 veces
            //System.out.println(al);
            for (ArrayList<Integer> it: al) {
                    //System.out.println(al);
                    // 1 vez
                    for (Integer j: it) {
                            Pair<Integer,Integer> par = new Pair<Integer,Integer>(j,index);
                            parells.add(par);
                    }
                    // tipo de votacion
                    index += 1;
            }
            modificaRelacioVotacio(nomE, parells, -1);
            // falta deshacerlo
            num_vot -= 1; // nomes per al driver
            registre.remove(nomE);
    }


    public Vector<Integer> consultarAssociats (String nomEvent) {
            return registre.get(nomEvent).consultarAssociats();
    }

    private Data consultarData(String nomEvent) {
            return registre.get(nomEvent).consultarData();
    }

    private int consultarTipus(String nomEvent) {
            return registre.get(nomEvent).consultarTipus();
    }


    public void modificarData(String nomEvent, Data data) {
            if (! checkEvent(nomEvent)) return;
            registre.get(nomEvent).modificarData(data);
    }

    /*
    public void modificarNom(String nomEventOld, String nomEventNew) {
            Event aux = registre.get(nomEventOld);
            registre.remove(nomEventOld);
            aux.modificarNom(nomEventNew);
            registre.put(nomEventNew, aux);
    }
    */ 


/*	// tipus votacio???
    public boolean novaVotacio(String nomV, Data data, int tipus) {
            EventVotacio event = new EventVotacio(tipus,data,nomV);
            if (!checkEvent(nomV)) {
                    registre.put(nomV, event);
                    return true;
            }
            return false;
    }*/

  /*  public Vector<String> getHistorial() {
            Vector<String> ret = new Vector<String> (0);
            String aux = "";
            for (Map.Entry<String, Event> entry: registre.entrySet()) {
                    aux = entry.getKey();
                    aux += " " + entry.getValue().consultarTipus();
                    Data d = entry.getValue().consultarData();
                    aux += " " + d.getDia();
                    aux += "/" + d.getMes();
                    aux += "/" + d.getAny();
                    ret.add(aux);
            }
            return ret;

    }*/
    
    public List< List<String>> getHistorialCarrega() {
        List< List<String>> ret;
        ret = new ArrayList< > ();
        for (Map.Entry<String, Event> entry: registre.entrySet()) {
            String aux = entry.getKey();
            ret.add(infoEvent(aux));
        }
        return ret;
    }
    
     // quan es carrega
     private int TipusToInt(String tipus) {
        if ("Reunio".equals(tipus)) return 0;
        else if ("Esport".equals(tipus)) return 1;
        else if ("Votacio".equals(tipus)) return 2;
        else if ("Dinar".equals(tipus)) return 3;
        else return 4; // conferencia
    }
    
    // quan es carrega
    private void afegir_event(List<String> info) {
        String nom = info.get(0);
        String tipus = info.get(1);
        String data = info.get(2);
        crearEvent(TipusToInt(tipus), Data.StringToData(data), nom);
    }
    
    public void guardaEvents(String nomF) {
        // falta que anada los asociados
        Ctrl_pers.guarda(nomF, getHistorialCarrega());
    }
    
    public void carregaEvents(String nomF) {
        registre = new LinkedHashMap<>();
        List<List<String>> aux = Ctrl_pers.carrega(nomF);
        for (List<String> it: aux) {
            System.out.println(it + " -> " + it.size());
            List <String> info = new ArrayList<>();
            info.add(it.get(0)); // nom
            info.add(it.get(1));
            info.add(it.get(2));
            afegir_event(info);
            // lo que queda son los diputados asociados, los tengo que dar de alta
            if (consultarTipus(it.get(0)) != 2) { // no es una votacion
                Vector<Integer> v = new Vector<>();
                // pongo los diputados en un vector
                for (int i = 3; i < it.size(); ++i) {
                    int asd = Integer.parseInt(it.get(i));
                    v.add(Integer.parseInt(it.get(i)));
                }
            // se puede optimizar si directamente cargo en los diputados sus relaciones
                cambiaRelacioDiputats(it.get(0), v, 1, false);
            }
            else { // es una votacion
                System.out.println("Me estoy ejecutando");
                Vector<Pair<Integer, Integer>> v = new Vector<Pair<Integer,Integer>>();
                for (int i = 3; i < it.size(); i+=2) {
                    int d = Integer.parseInt(it.get(i));
                    int r = Integer.parseInt(it.get(i+1));
                    Pair<Integer,Integer> p = new Pair<Integer,Integer>();
                    p.setFirst(d);
                    p.setSecond(r);
                    v.add(p);
                }
                System.out.println(v);
                modificaRelacioVotacio(it.get(0), v, 1);
            }
        }
    }
    
    private String TipusToString(int tipus) {
        if (tipus == 0) return "Reunio";
        else if (tipus == 1) return "Esport";
        else if (tipus == 2) return "Votacio";
        else if (tipus == 3) return "Dinar";
        else return "Conferencia";
   }
     
        public List<String> infoEvent (String nomEvent) {
        List<String> ret = new ArrayList<String>();
        Event aux  = registre.get(nomEvent);
        ret.add(nomEvent);
        ret.add(TipusToString(aux.consultarTipus()));
        ret.add(Data.DataToString(aux.consultarData()));
        if (aux.consultarTipus() != 2) { // es normal
            Vector<Integer> a = aux.consultarAssociats();
            for (int i: a) {
                ret.add(String.valueOf(i));
            }
        }
        else { // votacion
            // consultar los asociados a la votacio
            // para cada vector anadir el par id, {resultado}
            ArrayList<ArrayList<Integer> > votantes = consultaVotacions(nomEvent);
            int i = 0;
            for (ArrayList<Integer> v: votantes) {
                for (int it: v) {
                    // los del 0 son los del si
                    ret.add(String.valueOf(it));
                    ret.add(String.valueOf(i));
                }
                ++i;
            }
        }
        return ret;
    }
    // vector<Integer, Integer> vots (id, vot);
    private void introduirVotDiputat(String nomV, Pair<Integer,Integer> id_vot) {
            ((EventVotacio) registre.get(nomV)).introduirDades(id_vot.getFirst(), id_vot.getSecond());
    }

    // diff indica is s'afegeix o es treu.


    // no seria mejor vector de Pair?
    public ArrayList<ArrayList<Integer>> consultaVotacions(String nomV) {
            return ((EventVotacio) registre.get(nomV)).consultaVotacions();
    }


    // DIPUTATS





}
