package dominio.controlador;
import dominio.*;
import java.util.*;

import persistencia.*;



public class crlsolucio {
	private HashMap<Integer, Diputat> Diputats = new HashMap<Integer, Diputat>();
	private Plantilla plantillaActual;
	private int n_solucions = 0;
	private HashMap<Integer, Graph<Node>> grafos = new HashMap<Integer, Graph<Node>>();
	private HashMap<Integer, Solution> solucions = new HashMap<Integer, Solution>();
	private Clique C = new Clique();
	private GirvanNewman GW = new GirvanNewman();
	private Ctrl_diputat d;
	
	//Pre: cert
	//Post: crea nova instancia de ctrl solucio e inicialitza la plantilla per defecte
	public crlsolucio(Ctrl_diputat d){
		plantillaActual = new Plantilla("standart");
		plantillaActual.modpVotacio(5);
		plantillaActual.modpVotacioDif(-4);
		plantillaActual.modpPartit(1);
		plantillaActual.modpConf(3);
		plantillaActual.modpDinar(2);
		plantillaActual.modpReunio(3);
		plantillaActual.modpEdat(2);
		plantillaActual.modpLleure(2);
		plantillaActual.modpReligio(1);
		this.d = d;
	}
	
	//Pre: cert
	//Post: retorna les id dels diputats enregistrats al parametre implicit
	public Set<Integer> GetDiputatIds(){
		Vector<Integer> Dip = d.getIdsDiputats();
		Set<Integer> res = new HashSet();
		for(Integer dip : Dip){
			res.add(dip);
		}
		return res;
	}
	
	//Pre: cert
	//Post: pla plantilla que utilitza el parametre implicit passa a ser "p"
	public void SetPlantilla(Plantilla p){
		plantillaActual = p;
	}
	
	//Pre: cert
	//Post: retorna la plantilla que estem utilitzant
	public Plantilla GetPlantilla(){
		return plantillaActual;
	}
	
	//Pre: cert
	//Post: actualitza els diputats actuals
	public void actDiputats(Vector<Diputat> D){
		Diputats = new HashMap();
		for(Diputat d : D){
			Diputats.put(d.GetId(), d);
		}
	}
	
	//Pre: cert
	//Post: retorna si existeix la solucio amb id "s"
	public boolean ExistsSol(int s){
		return solucions.containsKey(s);
	}
	
	//Pre: cert
	//Post: retorna si existeix la solucio amb id "s"
	public boolean ExistsGraph(int s){
		return grafos.containsKey(s);
	}
	
	//Pre: el graf amb id "descripcio" no existeix
	//Post: crea el graf amb id "descripcio"
	public Graph<Node> creagraph(int descripcio){
		actDiputats(d.GetDiputats(d.getIdsDiputats()));
		Graph<Node> g = new Graph<Node>();
		Set<Integer> d = Diputats.keySet();
		for(int i : d){
			Node n = new Node();
			n.SetId(String.valueOf(i));
			g.addNode(n);
		}
		Set<Node> s = g.getAllNodes();
		for(Node i: s){
			for(Node j: s){
				if(i.GetId() < j.GetId()){
					int[] lista = new int[9];
					lista[0] = Diputats.get(i.GetId()).GetRelacio(j.GetId(), 2);
					lista[1] = Diputats.get(i.GetId()).GetRelacio(j.GetId(), 5);
					lista[2] = Diputats.get(i.GetId()).GetRelacio(j.GetId(), 0);
					lista[3] = Diputats.get(i.GetId()).GetRelacio(j.GetId(), 4);
					lista[4] = Diputats.get(i.GetId()).GetRelacio(j.GetId(), 3);
					lista[5] = Diputats.get(i.GetId()).GetRelacio(j.GetId(), 1);
					if(Diputats.get(i.GetId()).consultarPartit().equals(Diputats.get(j.GetId()).consultarPartit())) lista[6] = 1;
					else lista[6] = 0;
					if(Diputats.get(i.GetId()).consultarEdat() == Diputats.get(j.GetId()).consultarEdat()) lista[7] = 1;
					else lista[7] = 0;
					if(Diputats.get(i.GetId()).consultarReligio().equals(Diputats.get(j.GetId()).consultarReligio())) lista[8] = 1;
					else lista[8] = 0;
					Edge e = new Edge(); 
					e.setWeight(plantillaActual.calculAfinitat(lista));
					g.addEdge(i, j, e);
				}
			}
		}
		grafos.put(descripcio,g);
		return g;
	}
	
	//Pre: no hi ha solucio amb id descripcio
	//Post: genera un graph amb els diputats actuas, i una solucio amb l'algorisme de louvain
	//i els guarda amb identificador descripcio
	public void SolLouvain(int descripcio) throws Exception{
		Graph <Node> g = creagraph(descripcio);
		Louvain t = new Louvain();
		solucions.put(descripcio, t.getSolution(g));
		n_solucions++;
	}
	
	//Pre: no hi ha solucio amb id descripcio
	//Post: genera un graph amb els diputats actuas, i una solucio amb l'algorisme de clique
	//i els guarda amb identificador descripcio	
	public void SolClique(int n, int descripcio) throws Exception{
		Graph <Node>g = creagraph(descripcio);
		Solution s = C.getSolution(g);
		s.setAlg('C');
		solucions.put(descripcio, s);
		n_solucions++;
	}

	//Pre: no hi ha solucio amb id descripcio
	//Post: genera un graph amb els diputats actuas, i una solucio amb l'algorisme de Grivan-Newman
	//i els guarda amb identificador descripcio
	public void SolGW(int k, int descripcio){
		Graph <Node>g = creagraph(descripcio);
		solucions.put(descripcio, GW.getSolution(g));
		solucions.get(descripcio).setAlg('G');
		n_solucions++;
	}
	
	//Pre: desc es un id de graf
	//Post: retorna el graf amb id desc
	public Graph<Node> GetGraph(int desc){
		return grafos.get(desc);
	}
	
	
	//Pre: desc es un id de graf
	//Post: retorna un graf transformat en estructures basiques de java
	public HashMap<Integer, HashMap<Integer, Float>> RetGraph(int desc){
		HashMap<Integer, HashMap<Integer, Float>> Grafo = new HashMap<Integer, HashMap<Integer, Float>>();
		Set<Node> n = grafos.get(desc).getAllNodes();
		for(Node i : n){
			Grafo.put(i.GetId(), new HashMap<Integer, Float>());
			Set<Node> m = grafos.get(desc).getAdjacentNodesTo(i);
			for(Node j : m){
				Grafo.get(i.GetId()).put(j.GetId(), grafos.get(desc).getEdge(i, j).getWeight());
			}
		}
		return Grafo;
	}
	
	//Pre: cert
	//Post: retorna els identificadors de les solucions
	public Set<Integer> descripcions(){
		return solucions.keySet();
	}
	
	//Pre: cert
	//Post: retorna el nombre de solucions
	public int nombre_solucions(){
		return n_solucions;
	}
	
	//Pre: existeix una solucio amb id "desc"
	//Post: retorna una solucio en estructures de dades basiques de java
	public List<List<Integer>> RetSol(int desc){
		List<List<Integer>> sol = new ArrayList<List<Integer>>();
		List<Community> com = solucions.get(desc).getCommunities();
		for(Community c: com){
			List<Integer> l = new ArrayList<Integer>();
			List<Node> n = c.getCommunity();
			for(Node i: n){
				l.add(i.GetId());
			}
			sol.add(l);
		}
		return sol;
	}
	
	//Pre: existeix una solucio amb id "desc"
	//Post: retorna el temps que ha trigat en generarse una solucio
	public double GetTime(int desc){
		return solucions.get(desc).getTime();
	}
	
	//Pre: existeix una solucio amb id "desc"
	//Post: retorna la solucio identificada per "desc"
	public Solution GetSolucio(int desc){
		return solucions.get(desc);
	}
	
	//Pre: existeix una solucio amb id "desc"
	//Post: elimina la solucio i el graf identificats per "desc"
	public void EliminarSolucio(int desc){
		System.out.println("");
		solucions.remove(desc);
		grafos.remove(desc);
		--n_solucions;
	}
		
	//Pre: existeix una solucio amb id "desc"
	//Post: retorna l'espai de memoria que ocupa la solucio "desc"
	public int GetMemory(int desc){
		return solucions.get(desc).getMemory();
	}
	
	//Pre: existeix una solucio amb id "desc"
	//Post: retorna el nombre de comunitats de la solucio "desc"
	public int GetNumCommunities(int desc){
		return solucions.get(desc).getNumCommunities();
	}

	//Pre: existeix una solucio amb id "desc"
	//Post: retorna el nombre de nodes de la solucio "desc"
	public int GetNumNodes(int desc){
		return solucions.get(desc).getNumNodes();
	}
	
	//Pre: existeix una solucio amb id "desc"
	//Post: afegeix una comunitat a la solucio "desc"
	public void addCommunity(int desc){
		solucions.get(desc).addCommunity(new Community());
	}
	
	//Pre: existeix una solucio amb id "desc", a mes "i" es un enter entre 0 i el
	//nombre de comunitats de la solucio
	//Post: elimina la comunitat numero "i" de la solucio "desc"
	public void removeCommunity(int desc, int i){
		List<Community> c = solucions.get(desc).getCommunities();
		Community com = c.get(i);
		solucions.get(desc).removeCommunity(com);
	}
	
	//Pre: existeix una solucio amb id "desc", a mes "i" es un enter entre 0 i el
	//nombre de comunitats de la solucio, i "id" es positiu
	//Post: afegeix el node "id" a la comunitat numero "i" de la solucio "desc"
	public boolean addNode(int desc, int i, String id){
		List<Community> c = solucions.get(desc).getCommunities();
		Community com = c.get(i);
		boolean is = com.checkNode(id);
		if(is) return false;
		else{
			Node n = new Node(String.valueOf(id));
			com.addNode(n);
			return true;
		}
	}
	
	//Pre: existeix una solucio amb id "desc", a mes "i" es un enter entre 0 i el
	//nombre de comunitats de la solucio, i "id" es positiu
	//Post: afegeix el node "id" a la comunitat numero "i" de la solucio "desc"
	public boolean removeNode(int desc, int i, String id){
		List<Community> c = solucions.get(desc).getCommunities();
		Community com = c.get(i);
		boolean is = com.checkNode(id);
		if(!is) return false;
		else{
			com.deleteNode(id);
			return true;
		}
	}
	
	private List<List<String>> graphpers(){
		List<List<String>> L = new ArrayList<List<String>>();
		Set<Integer> S = grafos.keySet();
		for(Integer i : S){
			List<String> l1 = new ArrayList<String>();
			L.add(l1);
			l1.add(i.toString());
			Set<Node> S2 = grafos.get(i).getAllNodes();
			for(Node n : S2){
				l1.add(n.getId());
			}
			for(Node n : S2){
				l1.add("adjacents");
				l1.add(n.getId());
				Set<Node> S3 = grafos.get(i).getAdjacentNodesTo(n);
				for(Node n2 : S3){
					l1.add(n2.getId());
					Float f = grafos.get(i).getEdge(n, n2).getWeight();
					l1.add(f.toString());
				}
			}
		}
		return L;
	}	
	
	private List<List<String>> solpers(){
		List<List<String>> L = new ArrayList<List<String>>();
		Set<Integer> S = solucions.keySet();
		for(Integer i : S){
			List<String> l1 = new ArrayList<>();
			L.add(l1);
			l1.add(i.toString());
			l1.add(solucions.get(i).getId());
			Integer mem = solucions.get(i).getMemory();
			l1.add(mem.toString());
			Character alg = solucions.get(i).getAlg();
			l1.add(alg.toString());
			Double time = solucions.get(i).getTime();
			l1.add(time.toString());
			List<Community> l2 = solucions.get(i).getCommunities();
			for(Community c : l2){
				l1.add("community");
				List<Node> l3 = c.getCommunity();
				for(Node n : l3){
					l1.add(n.getId());
				}
			}
		}
		return L;
	}
	
	
	//Pre: existeix la solucio "id"
	//Post: guarda la solucio "id" a la capa de persistencia
	public void guardaSol(){
		ctrlpersol.escriuGraph(graphpers(), "C:/Users/David/Desktop/proves/grafs");
		ctrlpersol.escriuSolucio(solpers(), "C:/Users/David/Desktop/proves/solucions");
	}
	
	
	private void lecSolucio(List<List<String>> L, List<HashMap<String, Node>> Nodes){
		for(List<String> s : L){
			Solution sol = new Solution();
			solucions.put(Integer.valueOf(s.get(0)), sol);
			sol.setId(s.get(1));
			sol.setMemory(Integer.valueOf(s.get(2)));
			sol.setAlg(s.get(3).charAt(0));
			sol.setTime(Double.valueOf(s.get(4)));
			int i = 5;
			Community c = new Community();
			while(i < s.size()){
				if(s.get(i).equals("community")){
					c = new Community();
					sol.addCommunity(c);
				}
				else{
					c.addNode(Nodes.get(L.indexOf(s)).get(s.get(i)));
				}
				i++;
			}
		}
	}
	
	
	
	private List<HashMap<String, Node>> lecGraph(List<List<String>> L){
		List<HashMap<String, Node>> Nodes = new ArrayList<HashMap<String, Node>>();
		for(List<String> s : L){
			HashMap<String, Node> Map = new HashMap<String, Node>();
			Nodes.add(Map);
			Graph<Node> G = new Graph<Node>();
			grafos.put(Integer.valueOf(s.get(0)), G);
			int i = 1;
			while(i < s.size() && ! s.get(i).equals("adjacents")){
				Node n = new Node(s.get(i));
				System.out.println(s.get(i));
				Map.put(s.get(i), n);
				G.addNode(n);
				i++;
			}
			System.out.print(Map.get("1").GetId());
			System.out.print(Map.get("2").GetId());
			System.out.print(Map.get("3").GetId());
			System.out.print(Map.get("4").GetId());
			Node n = new Node();
			while(i < s.size()){
				if(s.get(i).equals("adjacents")){
					n = Map.get(s.get(i+1));
				}
				else{
					Node n2 = Map.get(s.get(i));
					G.addEdge(n, n2, new Edge(Float.valueOf(s.get(i+1))));
				}
				i = i + 2;
			}
		}
		return Nodes;
	}
	
	public void carregaSol(String pathsol, String pathg){
		List<HashMap<String, Node>> Nodes = lecGraph(ctrlpersol.lecGraph(pathg));
		lecSolucio(ctrlpersol.lecSolucio(pathsol), Nodes);
	}
	
	//Pre: cert
	//Post: transforma un mapa de mapes d'enters identificats per enters, identificats per enters
	//en un graph
	private Graph<Node> transformaagraph(HashMap<Integer, HashMap<Integer, Float>> a){
		Graph<Node> g = new Graph<Node>();
		Set<Integer> s = a.keySet();
		for(int i : s){
			Node n = new Node(String.valueOf(i));
			g.addNode(n);
		}
		Set<Node> s2  = g.getAllNodes();
		for(Node n1 : s2){
			for(Node n2 : s2){
				if(n1.GetId() < n2.GetId()){
					Edge e = new Edge();
					e.setWeight(a.get(n1.GetId()).get(n2.GetId()));
					g.addEdge(n1, n2, e);
				}
			}
		}
		return g;
	}
	
	//Pre: cert
	//Post: transforma una llista de llistes d'enters en una solucio
	private Solution transformaasolucio(List<List<Integer>> a){
		Solution s = new Solution();
		for(List<Integer> i : a){
			Community c = new Community();
			for(int j : i){
				Node n = new Node(String.valueOf(j));
				c.addNode(n);
			}
			s.addCommunity(c);
		}
		return s;
	}
	

	public List<String> getNodes(String i, String id) {
		List<Community> c = solucions.get(Integer.valueOf(i)).getCommunities();
		List<Node> N = c.get(Integer.valueOf(id)).getCommunity();
		List<String> Nodes = new ArrayList<String>();
		for(Node n : N){
			Nodes.add(n.getId());
		}
		return Nodes;
	}
	
	public boolean canviaCom(String id, String source, String dest, String n){
		List<Community> c = solucions.get(Integer.valueOf(id)).getCommunities();
		if(!c.get(Integer.valueOf(source)).checkNode(n)) return false;
		if(c.get(Integer.valueOf(dest)).checkNode(n)) return false;
		System.out.println("he arrbat");
		List<Node> N = c.get(Integer.valueOf(source)).getCommunity();
		for(Node nd : N){
			if(nd.getId().equals(n)){
				c.get(Integer.valueOf(source)).deleteNode(n);
				c.get(Integer.valueOf(dest)).addNode(nd);
			}
		}
		if(!c.get(Integer.valueOf(dest)).checkNode(n)) return false;
		if(c.get(Integer.valueOf(source)).checkNode(n)) return false;
		return true;
	}
	
}