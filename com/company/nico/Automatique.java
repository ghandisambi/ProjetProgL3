package com.company.nico;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Automatique {

    public static void Solution(CA ca,LinkedList<String> l){
        
        /** Initalisation des villes */
        while (l.contains("ville")) {
            l.remove("ville");
            ca.ajouterVille(l.poll());
        }
        /** Initalisation des routes */
        while (l.contains("route")) {
            l.remove("route");
            ca.ajouterRoute(l.pop(), l.pop()); 
        }
        /** Initalisation des ecoles*/
        while (l.contains("ecole")) {
            l.remove("ecole");
            ca.ajouterEcole(l.poll());
        }

        
        algorithmeOptimiser(ca);
        
        
        
        
        
        
        
    }

    public static CA algorithmeNaif(CA ca,int k){
        int i=0;
        int scoreCourant = ca.score();
        while(i<k){
            Ville ville = ca.getRandomVille();

           if(ca.villeExist(ville.toString())){
            if(ca.villePossedeEcole(ville.toString())){
                
                affiche("Supprime "+ville.toString());
                ca.supprimerEcole(ville.toString());
                affiche("cette ville ne peut pas être supprimer car certainnes villes dépendes d'elle car il ne possède pas d'école");
                
            }else{
                
                affiche("Ajoute "+ville.toString());
                
                ca.ajouterEcole(ville.toString());
                
            }   
                i++;
            }
            else affiche("la ville n'existe pas");
        }
        
        return ca;
    }


    public static CA algorithme(CA ca, int k) {
        

        int i = 0;
        int scoreCourant = ca.score() ;

        
        
        Map<Ville, Boolean> ecole = new HashMap<>();

        
        while (i < k) {
            Ville ville = ca.getRandomVille();

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
            ca.supprimerEcole(e.getKey().toString());
        }
        affiche(ca.getEcoleList().toString());
        return ca;
    }

    public static CA algorithmeOptimiser(CA ca){
        int scoreCourant=ca.score();
        if(ca.getEcole().size()<ca.nombreVille()/3){
            ca.initEcole();
            scoreCourant=ca.score();
        }else scoreCourant=ca.score();   
        
            while (ca.score()>=scoreCourant) {
                algorithme(ca, ca.nombreVille()).getEcoleList();
                if(ca.score()<=scoreCourant){
                    if(ca.score()<scoreCourant){
                        break;
                    }else if(ca.score()==scoreCourant){
                        if(precision(ca).score()<scoreCourant)
                        break;
                        else break;
                    }
                }
            }
            affiche("La meilleur solution est: "+ca.getEcoleList());
        return ca;
    }
    public static CA precision(CA ca) {
        algorithme(ca, ca.nombreVille());
       return ca; 
    }

    public static void affiche(String s){
        System.out.println(s);
    }
    
}
