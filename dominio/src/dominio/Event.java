package dominio;

import java.util.Vector;


	public class Event {
	        private String nom;
	        private Data data;
	        private int tipus;     
	        private Vector<Integer> diputatsAssociats;
	        private boolean trobat(Integer d){
	                int n=diputatsAssociats.size();
	                for(int i=0;i<n;++i){
	                        if(diputatsAssociats.get(i).equals(d)) return true;
	                }
	                return false;
	        }
	        public Event(int tipusEvent,Data dataEvent,String nomEvent){
	                nom=nomEvent;
	                data= dataEvent;
	                this.tipus=tipusEvent;
	                this.diputatsAssociats= new Vector<Integer>();
	        }
	       
	        public void eliminarDiputats(Vector<Integer> diputats){
	                this.diputatsAssociats.removeAll(diputats);
	        }
	       
	        public boolean associarDiputats(Vector<Integer> diputats){
	                for (int d: diputats) {
	                        if (diputatsAssociats.contains(d)) return false;
	                }
	                this.diputatsAssociats.addAll(diputats);
	                return true;
	        }
	               
	        public void modificarData(Data data){
	                this.data=data;
	        }
	        public String consultarNomEvent(){
	                return this.nom;
	        }
	        public Data consultarData(){
	                return this.data;
	        }
	        public Integer consultarTipus(){
	                return (this.tipus);
	        }
	        public Vector<Integer> consultarAssociats(){
	                return this.diputatsAssociats;
	        }
	}

	/* 
	 * if(Event == 0){
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
                }*/