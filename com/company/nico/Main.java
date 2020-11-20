package com.company.nico;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.company.nico.fichier.UtilFile;

/**
 * Main et la classe principale contenant l'ensemble des méthodes et déclaration permettant d'utiliser
 * l'interface utilisateur.
 */
public class Main {
    /**
     * Lancement du programme.
     */
    public static void main(String[] args) {
        CA ca = new CA();
        String fichierVille="com" + File.separator + "company" + File.separator + "nico" + File.separator + "fichier"
        + File.separator + "ville.txt";
        String fichierEcole="com" + File.separator + "company" + File.separator + "nico" + File.separator + "fichier"
        + File.separator + "exemple.ca";

        LinkedList<String> donneefichier = new LinkedList<>(); 
        
        donneefichier.addAll(UtilFile.lire(fichierVille));
        donneefichier.addAll(UtilFile.lire(fichierEcole));
       
        

        
        Automatique.Solution(ca,donneefichier);
    }
}
