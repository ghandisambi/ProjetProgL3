package com.company.nico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Automatique {

    
    public static void Solution(CA ca){
        algorithmeOptimiser(ca);   
    }

    public static void algorithmeNaif(CA ca,int k){
        affiche("Algorithme naif");
        int i=0;
        int scoreCourant = ca.score();
        while(i<k){
            Ville ville = ca.getRandomVille();
           if(ca.villeExist(ville.toString())){
            if(ca.villePossedeEcole(ville.toString())){ 
                //affiche("Supprime "+ville.toString());
                ca.supprimerEcole(ville.toString());
                //affiche("cette ville ne peut pas être supprimer car certainnes villes dépendes d'elle car il ne possède pas d'école");
                
            }else{
                
                //affiche("Ajoute "+ville.toString());
                ca.ajouterEcole(ville.toString());
            }   
                i++;
            }
            //else affiche("la ville n'existe pas");
           
        }
    }


    public static CA algorithme(CA ca, int k) {
        int i = 0;
        int scoreCourant = ca.score() ;
        Map<Ville, Boolean> ecole = new HashMap<>();
        Ville ville = ca.getRandomVille();
        while (i < k) {
            
            if (ca.villeExist(ville.toString())) {
                if (ca.villePossedeEcole(ville.toString())) {
                    ca.supprimerEcole(ville.toString());
                } else {
                    ca.ajouterEcole(ville.toString());
                }
                if (ca.score() < scoreCourant) {
                    for (Map.Entry<String, Ville> e : ca.getEcole().entrySet()) {
                        if (ca.testSuppressionEcole(e.getKey())) {
                            ecole.put(e.getValue(), true);
                            
                        }
                    }
                    break;
                } else
                    i++;
            }
        }
        for (Map.Entry<Ville, Boolean> e : ecole.entrySet()) {
            affiche(""+ca.ecoleToString());
            ca.supprimerEcole(e.getKey().toString());
        }
        return ca;
    }


    public static int minorant(List<Integer>list){
            if (list == null || list.size() == 0) { 
                return Integer.MIN_VALUE; 
            }  
            List<Integer> listTrie = new ArrayList<>(list); 
            Collections.sort(listTrie); 
            return listTrie.get(0); 
    }

    public static int majorant(List<Integer>list){
        if (list == null || list.size() == 0) { 
            return Integer.MIN_VALUE; 
        }  
        List<Integer> listTrie = new ArrayList<>(list); 
        Collections.sort(listTrie); 
        return listTrie.get(listTrie.size() - 1); 
    }

    

    public static void algorithmeOptimiser(CA ca){
        List<Integer> list = new ArrayList<>();

        for (Map.Entry<Ville, Set<Ville>> e : ca.getVoisin().entrySet()) {
              list.add(e.getValue().size());
        }
        
        
        int scoreCourant=ca.score();
            while (ca.score()>=scoreCourant) {
                algorithme(ca, ca.nombreVille());
                scoreCourant=majorant(list);
                if(ca.score()<=scoreCourant){
                    if(ca.score()<scoreCourant){
                        affiche(scoreCourant+"<"+ca.ecoleToString());
                        break;
                    }else if(ca.score()==scoreCourant){
                        affiche(scoreCourant+"=="+ca.ecoleToString());
                        break;
                    }
                }
            }
            affiche("La meilleur solution est: "+ca.getEcoleList());
    }

    public static void affiche(String s){
        System.out.println(s);
    }
    
}
