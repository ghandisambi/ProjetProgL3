package com.company.nico;

import java.util.LinkedList;

public class Automatique {

    public static void Solution(CA ca,LinkedList<String> l){
        
        
        while (l.contains("ville")) {
            l.remove("ville");
            ca.ajouterVille(l.poll());
        }
        while (l.contains("route")) {
            l.remove("route");
            ca.ajouterRoute(l.pop(), l.pop()); 
        }
        while (l.contains("ecole")) {
            l.remove("ecole");
            ca.ajouterEcole(l.poll());
        }


        ca.afficheRoute();
        ca.afficheEcole();
        affiche(ca.toString());
        
        
        
        
    }

    public static void affiche(String s){
        System.out.println(s);
    }
    
}
