package dominio;
import java.util.*;

public class Diputat{
	private int dni;
	private String nom;
	private String religio;
	private String partit;
	private String sexe;
	private int edat;
	private HashMap<Integer, Integer> votacio =  new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> reunio =  new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> conferencia =  new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> dinar =  new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> esport =  new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> difvotacio =  new HashMap<Integer, Integer>();
	
	
	//creadores
	
	//Pre: cert
	//Post: crea un diputat buit
	public Diputat(){
	}
	
	//Pre: el diputat amb dni dni no existia abans, a mes dni i edat han de ser nombres positius
	//Post: es crea un diputat amb dni = "dni", nom = "nom", religio = "religio", partit = "partit", sexe = "sexe", edat = "edat"
	public Diputat(int dni, String nom, String religio, String partit, String sexe, int edat){
		this.dni = dni;
		this.nom = nom;
		this.religio = religio;
		this.partit = partit;
		this.sexe = sexe;
		this.edat = edat;
	}
	
	//consultres
	
	//Pre: cert
	//Post: retorna el dni del parametre implicit
	public int consultarDni(){
		return dni;
	}
	
	//Pre: cert
	//Post: retorna el dni del parametre implicit
	public int GetId(){
		return dni;
	}
	
	//Pre: cert
	//Post: retorna el nom del parametre implicit
	public String consultarNom(){
		return nom;
	}
	
	//Pre: cert
	//Post: retorna la religio del parametre implicit
	public String consultarReligio(){
		return religio;
	}
	
	//Pre: cert
	//Post: retorna el partit del parametre implicit
	public String consultarPartit(){
		return partit;
	}
	
	//Pre: cert
	//Post: retorna l'edat del parametre implicit
	public int consultarEdat(){
		return edat;
	}
	
	//Pre: cert
	//Post: retorna el sexe del parametre implicit
	public String consultarSexe(){
		return sexe;
	}
	
	//Pre: event es un numero entre 0 i 5
	/*Post: retorna el nombre de incidencies entre el parametre implicit i
	 * i el diputat de dni = "id" en l'event identificat per "event", a saber:
	 * 0 = cops que han assistit a la mateixa reunio
	 * 1 = cops que han practicat esport junts 
	 * 2 = cops que han votat el mateix
	 * 3 = cops que han dinat junts
	 * 4 = cops que han assistit a la mateixa conferencia
	 * 5 = cops que han votat diferent
	 */
	public int GetRelacio(int id, int Event){
		if(Event == 0){
			if(reunio.containsKey(id)) return reunio.get(id);
			else return 0;
		}
		if(Event == 1){
			if(esport.containsKey(id)) return esport.get(id);
			else return 0;
		}
		if(Event == 2){
			if(votacio.containsKey(id)) return votacio.get(id);
			else return 0;
		}
		if(Event == 3){
			if(dinar.containsKey(id)) return dinar.get(id);
			else return 0;
		}
		if(Event == 4){
			if(conferencia.containsKey(id)) return conferencia.get(id);
			else return 0;
		}
		if(Event == 5){
			if(difvotacio.containsKey(id)) return difvotacio.get(id);
			else return 0;
		}
		return -1;
	}
	
	
	//modificadores
	
	//Pre: cert
	//Post: la religio del parametre implicit passa a ser "religio"
	public void modReligio(String religio){
		this.religio = religio;
	}
	
	//Pre: cert
	//Post: el partit del parametre implicit passa a ser "partit"
	public void modPartit(String partit){
		this.partit = partit;
	}
	
	//Pre: cert
	//Post: l'edat del parametre implicit passa a ser "edat"
	public void modEdat(int edat){
		this.edat = edat;
	}
        
        public void modSexe(String edat){
		this.sexe = edat;
	}
	
	//Pre: evnt es un nombre entre 0 i 5 i fact un nombre entre 1 i -1,
	//a m�s "id"
	/*Post: el nombre de coincidencies en un event "event" entre el parametre implicit i
	 * el diputat de dni "id" es modifica en fact
	 * els event poden ser:
	 * 0 = cops que han assistit a la mateixa reunio
	 * 1 = cops que han practicat esport junts 
	 * 2 = cops que han votat el mateix
	 * 3 = cops que han dinat junts
	 * 4 = cops que han assistit a la mateixa conferencia
	 * 5 = cops que han votat diferent 
	 */
	public void SetRelevent(int Event, int id, int fact){
		if(Event == 0){
			if(reunio.containsKey(id)) reunio.put(id, reunio.get(id)+fact);
			else if(fact == 1) reunio.put(id, 1);
		}
		if(Event == 1){
			if(esport.containsKey(id)) esport.put(id, esport.get(id)+fact);
			else if(fact == 1) esport.put(id, 1);
		}
		if(Event == 2){
			if(votacio.containsKey(id)) votacio.put(id, votacio.get(id)+fact);
			else if(fact == 1) votacio.put(id, 1);
		}
		if(Event == 3){
			if(dinar.containsKey(id)) dinar.put(id, dinar.get(id)+fact);
			else if(fact == 1) dinar.put(id, 1);
		}
		if(Event == 4){
			if(conferencia.containsKey(id)) conferencia.put(id, conferencia.get(id)+fact);
			else if(fact == 1) conferencia.put(id, 1);
		}
		if(Event == 5){
			if(difvotacio.containsKey(id)) difvotacio.put(id, difvotacio.get(id)+fact);
			else if(fact == 1) difvotacio.put(id, 1);
		}
	}
}
