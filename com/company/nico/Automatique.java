package com.company.nico;

import java.util.LinkedList;

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

        algorithme(ca,ca.nombreVille());
        ca.afficheEcole();
        algorithmeNaif(ca, ca.nombreVille());
        
        ca.afficheEcole();
        
        
        
        
        
    }

    public static CA algorithmeNaif(CA ca,int k){
        int i=0;
        int scoreCourant = ca.score();
        
        
        while(i<k){
            Ville ville = ca.getRandomVille();
            if(ca.dependance(ville.toString())){
                affiche("Supprime "+ville.toString());
                ca.supprimerEcole(ville.toString());
                
            }else{
                affiche("Ajoute "+ville.toString());
                ca.ajouterEcole(ville.toString());
                
            }
            i++;
        }
        
        return ca;
    }
    public static CA algorithme(CA ca,int k){
        int i=0;
        int scoreCourant = ca.score();

        
        
        while(i<k){
            Ville ville = ca.getRandomVille();
            if(ca.dependance(ville.toString())){
                affiche("Supprime "+ville.toString());
                ca.supprimerEcole(ville.toString());
                
            }else{
                affiche("Ajoute "+ville.toString());
                ca.ajouterEcole(ville.toString());
                
            }
            if(ca.score()<scoreCourant){
                i=0;
                scoreCourant = ca.score();
            }else i++;
            
        }
        
        return ca;
    }

    public static void affiche(String s){
        System.out.println(s);
    }
    
}
