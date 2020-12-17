package com.company.nico;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

import com.company.nico.fichier.UtilFile;

import javax.swing.*;

/**
 * Main et la classe principale contenant l'ensemble des méthodes et déclaration
 * permettant d'utiliser l'interface utilisateur.
 */
public class Main {
    /**
     * Lancement du programme.
     */
    public static void main(String[] args) throws IOException {
        /* Création de la communauté d'agglomération */

        CA ca = null;
        ca = UtilFile.loadDataFile(args);
        if(ca.getEcoleList().isEmpty()) {
            for (String ville : ca.ajouteEcoleDansVilles()) {
                ca.ajouterEcole(ville);
            }
        }

        int n;
        do {
            System.out.println(
                    "1) résoudre manuellement.\n" + "2) résoudre automatiquement.\n" + "3) sauvegarder.\n" + "4) fin.");
            do {
                n = Manuelle.saisieEntier(new Scanner(System.in));
            } while (n < 1 || n > 4);
            switch (n) {
                case 1:
                    System.out.println("====== Résolution manuelle ======");
                    Manuelle.Affichage(ca);
                    break;

                case 2:
                    System.out.println("====== Résolution automatique ======");
                    /////////////////////////Attention erreur si le Ca na pas de ville.
                    if (ca.getEcoleList().isEmpty()){
                        Algorithme.algoNaif(ca,ca.nombreVille());
                        System.out.println("apre naif : "+ca.toString());
                    }
                    Algorithme.Solution(ca);

                    break;
                case 3:
                    System.out.println("====== Sauvegarde ======");
                    try {
                        UtilFile.sauvegarde(ca);
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
