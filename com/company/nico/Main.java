package com.company.nico;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

import com.company.nico.fichier.UtilFile;

/**
 * Main et la classe principale contenant l'ensemble des méthodes et déclaration
 * permettant d'utiliser l'interface utilisateur.
 */
public class Main {
    /**
     * Lancement du programme.
     */
    public static void main(String[] args) throws IOException {
        CA ca = new CA();/* Création de la communauté d'agglomération */
        UtilFile.loadDataFile(args,ca);
        String fichierSolution = "com" + File.separator + "company" + File.separator + "nico" + File.separator
                + "fichier" + File.separator + "solution.txt";
        File file = new File(fichierSolution);
        Automatique.affiche(ca.toString());

        int n;
        do {
            System.out.println(
                    "1) résoudre manuellement.\n" + "2) résoudre automatiquement.\n" + "3) sauvegarder.\n" + "4) fin.");
            do {
                n = Manuelle.saisieEntier(new Scanner(System.in));
            } while (n < 1 || n > 4);
            switch (n) {
                case 1:
                    if(ca.getEcoleList().isEmpty()){
                        Automatique.algorithmeNaif(ca, ca.nombreVille());
                    }
                    System.out.println("Mode manuel !");
                    Manuelle.Affichage(ca);
                    break;

                case 2:
                    if(ca.getEcoleList().isEmpty()){
                        Automatique.algorithmeNaif(ca, ca.nombreVille());
                    }
                    System.out.println("Mode automatique !");
                    Automatique.Solution(ca);
                    break;
                case 3:
                    System.out.println("Sauvegarde");
                    try {
                        UtilFile.sauvegarde(ca, file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

               break;
               case 4:
                    n = 0;
                    System.out.println("Au revoir !");

               break;
            }
        } while (n!=0);
    }
}
