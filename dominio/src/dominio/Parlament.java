
package dominio;

import java.util.*;



public class Parlament {
	
	/* ATRIBUTS */
	
	// indica si esta present al parlament o no
	private Map<Integer,Diputat> diputats;
	private int maxNumDip;

	//private String nom;
	
	// FUNCIONS
	
	public Parlament(int maxNumDip){
		// el tamano inicial es 0
		diputats = new HashMap<Integer,Diputat> (0);
		this.maxNumDip = maxNumDip;
		//this.nom = nom;
	}
	
	public boolean afegirDiputat(Diputat dip) {
		// directamente cambia de int a integer y viceversa
		if (this.maxNumDip == diputats.size() || diputats.containsKey(dip.consultarDni())) {
			return false;
		}
		else {
			// inserto el diputado
			diputats.put(dip.consultarDni(), dip);
			return true;
		}
	}
	
	public void elminiarDiputatDeParlament(int id) {
		diputats.remove(id);
	}
	
	public int numDiputats() {
		return diputats.size();
	}
	
	public int numDiputatsMax() {
		return maxNumDip;
	}
	
	public Diputat consultarDiputat(int dni) {
		return (Diputat) diputats.get(dni);
	}
	
	public boolean existeixDiputat(int dni) {
		return diputats.containsKey(diputats.get(dni).consultarDni());
	}
	
	public List<Diputat> getParlament() {
		List<Diputat> list = new ArrayList<Diputat>();
		//Iterator it = diputats.entrySet().iterator();
		for (Map.Entry<Integer, Diputat> entry : diputats.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
}
	
	
	


