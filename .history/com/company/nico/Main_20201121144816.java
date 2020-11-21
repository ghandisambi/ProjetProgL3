package com.company.nico;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;


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
        CA ca = new CA();/** Création de la communauté d'agglomération */
        boolean modeAutomatique = true;
        int n;
        do {
            System.out.println("Afficher le menu");
            break;

        } while (true);
         

        String fichierVille="com" + File.separator + "company" + File.separator + "nico" + File.separator + "fichier"
        + File.separator + "ville.txt";/** Lien fichier des Villes et Routes */

        String fichierEcole="com" + File.separator + "company" + File.separator + "nico" + File.separator + "fichier"
        + File.separator + "exemple.ca";/** Lien fichier des ecoles */

        LinkedList<String> donneefichier = new LinkedList<>(); /** liste qui stock les données de plusieur fichier peu importe la taille  */

        
        donneefichier.addAll(UtilFile.lire(fichierVille));/**On ajoute les données du fichier des villes  */
        donneefichier.addAll(UtilFile.lire(fichierEcole));/**On ajoute les données du fichier des ecoles  */
        
        if(modeAutomatique)Automatique.Solution(ca,donneefichier);/** On fait appelle à la méthode static (Solution) de la classe Automatique pour résoudre le problème en fonction du choix */
        else Manuelle.Affichage(ca);/**Sinon On fait appelle à la méthode static (Affichage) de la classe Manuelle pour nous permetre de résoudre le problème */
    
    
    }
}
