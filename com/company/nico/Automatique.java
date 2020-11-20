package com.company.nico;

import java.util.LinkedList;
import java.util.List;

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

        ca.afficheRoute();
        algorithmeNaif(ca, ca.nombreVille());
        ca.afficheEcole();
        affiche("\n__________________________________________________________________________\n");
        affiche("\n__________________________________________________________________________\n");
        algorithmeNaif(ca, ca.nombreVille());
        ca.afficheEcole();
        
        
        
        
        
        
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
    public static CA algorithme(CA ca,int k){
        affiche("alorithme optimiser");
        int i=0;
        int scoreCourant = ca.score();
        List<Ville> list = new LinkedList<>();
        while(i<k){
            Ville ville = ca.getRandomVille();


           if(!list.contains(ville)){
               list.add(ville);
               if(ca.villeExist(ville.toString())){
                if(ca.villePossedeEcole(ville.toString())){
                
                    affiche("Supprime "+ville.toString());
                    ca.supprimerEcole(ville.toString());
                    affiche("cette ville ne peut pas être supprimer car certainnes villes dépendes d'elle car elle ne possèdent pas d'écoles le nombre d'école entrez est "+scoreCourant+" et le nombre d'école total est "+ca.score());  
                }else{
                 affiche("Ajoute "+ville.toString());
                    
                    ca.ajouterEcole(ville.toString());
                    affiche("le nombre d'école entrez est "+scoreCourant+" et le nombre d'école total est "+ca.score());
                
                }   
                if(ca.score()<scoreCourant){
                  
                  i=0;
                  scoreCourant = ca.score();  
                  affiche("---------------->le score est : "+scoreCourant);

                }else i++;
            }
            
            }else affiche("la ville n'existe pas");
        
        }
        
        return ca;
    }

    public static void affiche(String s){
        System.out.println(s);
    }
    
}
