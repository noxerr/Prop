

package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dani__000
 */
public class Plantilla {

    private String nom;
    private int votacioEq, votacioDif, reunio, conferencia, dinar, lleure, partit, edat, religio;
    
    // CREADORA
    // Pre: no hi ha cap plantilla amb nom “nom”
    // Post: crea una plantilla amb nom “nom”
    public Plantilla(String nom){
        this.nom = nom;
    }
    
    public Plantilla(String nom, int[] listaPond){
        this.nom = nom;
        this.votacioEq = listaPond[0];
        this.votacioDif = listaPond[1];
        this.reunio = listaPond[2];
        this.conferencia = listaPond[3];
        this.dinar = listaPond[4];
        this.lleure = listaPond[5];
        this.partit = listaPond[6];
        this.edat = listaPond[7];
        this.religio = listaPond[8];
    }
    

    // MODIFICADORA
    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de votacioEq val ‘p’
    public void modpVotacio(int p){
        this.votacioEq = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de votacioDif val ‘p’
    public void modpVotacioDif(int p){
        this.votacioDif = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de reunio val ‘p’
    public void modpReunio(int p){
        this.reunio = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de conferencia val ‘p’
    public void modpConf(int p){
        this.conferencia = p;
    }


    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de dinar val ‘p’
    public void modpDinar(int p){
        this.dinar = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de Lleure val ‘p’
    public void modpLleure(int p){
        this.lleure = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de partit val ‘p’
    public void modpPartit(int p){
        this.partit = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de edat val ‘p’
    public void modpEdat(int p){
        this.edat = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de reunio val ‘p’
    public void modpReligio(int p){
        this.religio = p;
    }
    
    // Pre: plantilla amb nom diferent de “default”
    // Post: ara el nom de la plantilla val “nom”
    public void modNom(String nom){
        this.nom = nom;
    }

    // CONSULTORA
    //Pre: la llista conte les coincidencies en cada event, entre dos diputats
    //Post: retorna l’afinitat entre dip1 i dip2
    public int calculAfinitat(int[] lista){
        int total = 0;
        Object[] a = getPond();
        Map<String,Integer> mapa = (Map) a[1];
        int i = 0;
        /*for (String clave : mapa.keySet()) {   
            int valor = mapa.get(clave);
            total += (valor*lista[i]);
            if (i<9)i++; //para evitar errores
        }*/
        total += (lista[0] * mapa.get("votacioEq"));
        total += (lista[1] * mapa.get("votacioDif"));
        total += (lista[2] * mapa.get("reunio"));
        total += (lista[3] * mapa.get("conferencia"));
        total += (lista[4] * mapa.get("dinar"));
        total += (lista[5] * mapa.get("lleure"));
        total += (lista[6] * mapa.get("partit"));
        total += (lista[7] * mapa.get("edat"));
        total += (lista[8] * mapa.get("religio"));
        
        return total;
    }
    
    public ArrayList<Integer> getListaPond(){
        ArrayList<Integer> ret = new ArrayList();
        ret.add(votacioEq); ret.add(votacioDif);
        ret.add(reunio); ret.add(conferencia);
        ret.add(dinar); ret.add(lleure);
        ret.add(partit); ret.add(edat);
        ret.add(religio);
        return ret;
    }

    // Pre: existeix la plantilla
    // Post: retorna dos object, el [0] es el string del nom y el [1] un hashmap
    // amb la llista de atributs - ponderacio
    public Object[] getPond(){
        Object[] retorno = new Object[2];
        retorno[0] = this.nom;
        Map<String, Integer> Pair = new HashMap();
        Pair.put("votacioEq", this.votacioEq);
        Pair.put("votacioDif", this.votacioDif);
        Pair.put("reunio", this.reunio);
        Pair.put("conferencia", this.conferencia);
        Pair.put("dinar", this.dinar);
        Pair.put("lleure", this.lleure);
        Pair.put("partit", this.partit);
        Pair.put("edat", this.edat);
        Pair.put("religio", this.religio);
        retorno[1] = Pair;
        return retorno;        
        //PARA OBTENER EL RESULTADO, CREAIS OTRO OBJECT COMO LA 1A LINEA DE LA FUNCION
        //LO IGUALAIS A GETPOND Y LUEGO HACEIS STRING NOMBRE = (STRING) OBJECTO[0] Y
        //MAP ... TAL = NEW HASHMAP TAL.. ; TAL = (MAP<TAL,TAL>) OBJETO[1].
    }
    
    // Pre: existeix la plantilla
    //Post: retorna el nom de la plantilla actual
    public String getNom(){
        return this.nom;
    }
    
}
