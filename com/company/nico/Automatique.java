package com.company.nico;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;

public class Automatique {

    public static CA Solution(CA ca, LinkedList<String> l) {

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
        /** Initalisation des ecoles */
        while (l.contains("ecole")) {
            l.remove("ecole");
            ca.ajouterEcole(l.poll());
        }
        
        try {
            
            sauvegardeConsole(algorithmeOptimiser(ca)).getEcoleList();

           
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        

        return ca;
    }

    public static CA algorithmeNaif(CA ca, int k) {
        int i = 0;
        int scoreCourant = ca.score();
        while (i < k) {
            Ville ville = ca.getRandomVille();

            if (ca.villeExist(ville.toString())) {
                if (ca.villePossedeEcole(ville.toString())) {

                    
                    ca.supprimerEcole(ville.toString());

                } else {

                    

                    ca.ajouterEcole(ville.toString());

                }
                i++;
            } 
                
        }

        return ca;
    }

    public static CA algorithme(CA ca, int k) {
        

        int i = 0;
        int scoreCourant = ca.score();
        
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
        
        for (Entry<Ville, Boolean> e : ecole.entrySet()) {
            ca.supprimerEcole(e.getKey().toString());
        }
        affiche(ca.getEcoleList().toString());
        return ca;
    }

    public static CA algorithmeOptimiser(CA ca){
        int scoreCourant=ca.score();
        Set<Set<String>> ecoles= new HashSet<>();
           
            while (ca.score()>=scoreCourant) {
                
                algorithme(ca, ca.nombreVille()).getEcoleList();
                
            }
       

            
        return ca;
    }

    public static void affiche(String s){
        System.out.println(s);
    }
    
    public static CA sauvegardeConsole(CA ca)throws IOException {
        
        String fichierSolution="com" + File.separator + "company" + File.separator + "nico" + File.separator + "fichier"
        + File.separator + "solution.txt";
        File file = new File(fichierSolution);
        
        PrintStream stream = new PrintStream(new FileOutputStream(file,true), true);
        
        System.setOut(stream);
        return ca;
    }

    public static Map<Integer,List<String>> supprimeDeLaListe(Map<Integer,List<String>> list, List<String> element){
      
            for (int i = 0; i < list.size(); i++) {
                if (Objects.equals(element, list.get(i))) {
                    list.remove(i);
                    i--;
                }
            }
       
        return list;
    }

}
