package dominio.controlador;
import dominio.Diputat;
import presentacion.controlador.Ctrl_VistaParlament;

import java.util.*;
import persistencia.Ctrl_pers;

public class Ctrl_diputat {
	//private int maxNumDip;
    
        private Ctrl_VistaParlament vistaParl;
	private Map<Integer,Diputat> diputats;
	// FUNCIONS
	
        
        
	public boolean  checkDiputat(int id) {
		return diputats.containsKey(id);
	}
	
        public Ctrl_diputat() {
            diputats = new HashMap<Integer,Diputat> (0);
        }
        
        // no lo necesito
	/*public Ctrl_diputat(Ctrl_VistaParlament instancia) {
                vistaParl = instancia;
		diputats = new HashMap<Integer,Diputat> (0);
		//this.maxNumDip = maxNumDip;
	}*/

	public boolean creaDiputat(Integer dni, String nom, String religio, 
			String partit, String sexe, int edat) {
		if (checkDiputat(dni)) return false;
		// instancio diputat
		Diputat aux = new Diputat(dni,nom,religio,partit,sexe,edat);
		// l'afegeixo
		diputats.put(dni, aux);
		return true;
	}
	
	
	public boolean eliminarDiputat(int id) {
		if (diputats.remove(id) == null) {
			return false;
		}
		return true;
	}
	
	public boolean novaReligio (String novaReligio, int id) {
		if (checkDiputat(id)) {
			diputats.get(id).modReligio(novaReligio);	
			return true;
		}
		return false;
	}
		
	public boolean nouPartit(String nouPartit, int id) {
		if (checkDiputat(id)) {
			diputats.get(id).modPartit(nouPartit);
			return true;
		}
		return false;
	}
	
	public boolean modEdat(int novaEdat, int id) {
		if (checkDiputat(id)){
			diputats.get(id).modEdat(novaEdat);
			return true;
		}
		return false;
	}
        
        public boolean modSexe(String nouSexe, int id) {
            if (checkDiputat(id)){
			diputats.get(id).modSexe(nouSexe);
			return true;
		}
		return false;
	}
        
	
	public ArrayList<String> atributs(int id) {
		List<String> list = new ArrayList<String> ();
		if (checkDiputat(id)){
			Diputat aux = diputats.get(id);
			list.add(String.valueOf(id));
			list.add(aux.consultarNom());
			list.add(aux.consultarReligio());
			list.add(aux.consultarPartit());
			list.add(aux.consultarSexe());
			list.add(String.valueOf(aux.consultarEdat()));
		}
		return (ArrayList<String>) list;
	}
	
	
	public int relacio(int id, int id2, int tipusEvent) {
		if (checkDiputat(id)) {
			return diputats.get(id).GetRelacio(id2,tipusEvent);
		}
		return -1; // may ha de passar
	}
	
	
	
	//public 
	public String atributs_S(int id) {
		String ret = "";
		if (checkDiputat(id)) {
			Diputat aux = diputats.get(id);
			ret += String.valueOf(id) + " ";
			ret += aux.consultarNom() + " ";
			ret += aux.consultarReligio() + " ";
			ret += aux.consultarPartit() + " ";
			ret += aux.consultarSexe() + " ";
			ret += (String.valueOf(aux.consultarEdat()));
		}
		return ret;
	}
        
        // id, nom, edat, sexe, partit, religio
        public List<String> atributs_diputat(int id) {
            List<String> ret = new ArrayList<>();
            if (checkDiputat(id)) {
			Diputat aux = diputats.get(id);
			ret.add(String.valueOf(id));
			ret.add(aux.consultarNom());
                        ret.add(String.valueOf(aux.consultarEdat()));
			ret.add(aux.consultarSexe());
                        ret.add(aux.consultarPartit());
                        ret.add(aux.consultarReligio());
		}
		return ret;
        }
	
	public Vector<String> getInfoDiputats() {
		Vector<String> info = new Vector<String>(0);
		for (Map.Entry<Integer, Diputat> entry:diputats.entrySet()) {
			info.add(atributs_S(entry.getKey()));
		}
		return  info;
	}
	
	public  Vector<Integer> getIdsDiputats() {
		Vector<Integer> ret = new Vector<Integer> (0);
		for (Map.Entry<Integer, Diputat> entry:diputats.entrySet()) {
			ret.add(entry.getKey());
		}
		return ret;
	}
	
	public Vector<Diputat> GetDiputats(Vector<Integer> ids) {
		Vector<Diputat> ret = new Vector<Diputat>(0);
		for (Map.Entry<Integer, Diputat> entry: diputats.entrySet()) {
			ret.add(entry.getValue());
		}
		return ret;
	}
	
	public Diputat GetDiputat(int id) {
		return diputats.get(id);
	}
        
        public List< List<String> > getParlament() {
            List <List <String>> ret;
            ret = new ArrayList<>();
            for (Map.Entry<Integer, Diputat> entry: diputats.entrySet()) {
                    ret.add(atributs_diputat(entry.getKey()));
		}
            return ret;
        }
        
        public void carregaParlamentDeFitxer(String nomFitxer) {
            carregaParlament(Ctrl_pers.carrega(nomFitxer));
        }
        // id, nom, edat, sexe, partit, religio
        public void carregaParlament(List <List<String> > info) {
            diputats = new HashMap<>();
            for (List<String> aux: info)
            {
                Integer id = Integer.parseInt(aux.get(0));
                String nom = aux.get(1);
                Integer edat = Integer.parseInt(aux.get(2));
                String sexe = aux.get(3);
                String partit = aux.get(4);
                String religio = aux.get(5);
                creaDiputat(id, nom, religio, partit, sexe, edat);
            }
        }
	
/*	public boolean Carregar(Vector<String> atributs) {
		
	}*/

    public void guardaParlament(String fitxer) {
        Ctrl_pers.guarda(fitxer, getParlament());
    }
	
	
	
}

