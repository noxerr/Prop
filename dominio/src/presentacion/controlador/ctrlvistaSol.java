/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controlador;

import java.util.*;
 

/**
 *
 * @author David
 */
public class ctrlvistaSol {
    private HashMap<String, String> sols = new HashMap<String, String>();
    private String id;
    
    public String getTime(String id){
        return "2.3";
    }
    
    public List<String> getComunitats(String i){
        List<String> s = new ArrayList<String>();
        int j = Integer.valueOf(i);
        for(Integer k = 0; k < j; k++){
            s.add(k.toString());
        }
        return s;
    }
    
    public List<String> getNodes(String i, String id){
        List<String> s = new ArrayList<String>();
        int j = Integer.valueOf(i);
        for(Integer k = 0; k < j; k++){
            s.add(k.toString());
        }
        return s;
    }
    
    public boolean generaSolucio(String id, String alg, String pl){
        sols.put(id, alg);
        if(alg.equals("Clique")) return false;
        else return true;
    }
    
    public String getAlg(String id){
        return sols.get(id);
    }
    
    public boolean eliminaSolucio(String id){
        if(Integer.valueOf(id) != 5) return true;
        else return false;
    }
    
    public void setId(String id){
        this.id = id;
        System.out.println("m'han cridat");
        System.out.print("tinc l'id " + this.id);
    }
    
    public String getId(){
        System.out.println("m'han cridat");
        System.out.print("tinc l'id " + id);
        return id;
    }
    
    public String getAlgorisme(){
        return "Louvain";
    }

    public String getData() {
        return "2/3/2014";
    }
    
    
    
}
