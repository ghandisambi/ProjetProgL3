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

         

        String fichierVille="com" + File.separator + "company" + File.separator + "nico" + File.separator + "fichier"
        + File.separator + "ville.txt";/** Lien fichier des Villes et Routes */

        String fichierEcole="com" + File.separator + "company" + File.separator + "nico" + File.separator + "fichier"
        + File.separator + "exemple.ca";/** Lien fichier des ecoles */

        LinkedList<String> donneefichier = new LinkedList<>(); /** liste qui stock les données de plusieur fichier peu importe la taille  */

        
        donneefichier.addAll(UtilFile.lire(fichierVille));/**On ajoute les données du fichier des villes  */
        donneefichier.addAll(UtilFile.lire(fichierEcole));/**On ajoute les données du fichier des ecoles  */
        
        int n;
        do {
            System.out.println("1) résoudre manuellement.\n" +
                    "2) résoudre automatiquement.\n" +
                    "3) sauvegarder.\n" +
                    "4) fin.");
            do {
                n = Manuelle.saisieEntier(new Scanner(System.in));
            } while (n<1||n>4);
            switch (n) {
               case 1:
                    System.out.println("Mode manuel !");
                    Manuelle.Affichage(ca);
               break;
               
               case 2:
                    System.out.println("Mode automatique !");
                    Automatique.Solution(ca, donneefichier);
               break;
               case 3:
                    System.out.println("Sauvegarde");
               break;
               case 4:
                    n = 0;
                    System.out.println("Au revoir !");
               break;
            }
        } while (n!=0);
    }
}