package com.company.nkg;

import java.io.*;
import java.util.Scanner;

import com.company.nkg.Utils.UtilFile;
import com.company.nkg.Utils.UtilSaisie;

/**
 * Main et la classe principale contenant l'ensemble des méthodes et déclaration
 * permettant d'utiliser l'interface utilisateur.
 */
public class Main {
    /**
     * Lancement du programme.
     */
    public static void main(String[] args) throws IOException {
        int choix;
        /* Création de la communauté d'agglomération */
        do {
            if (args.length > 0) {
                CA ca = null;
                ca = UtilFile.chargement(args[0]);
                if (ca != null) {
                    if (!ca.respectContrainte()) {
                        for (String ville : ca.getListVille()) {
                            ca.ajouterEcole(ville);
                        }
                    }
                    System.out.println(ca.toString());
                    Commande.menu(ca);
                } else System.out.println("Ce repertoire n'existe pas !");
            } else {
                System.out.println("Veuillez specifier le nom du repertoire !");
            }
            System.out.println("Voulez- vous réessayer avec une autre communauter d'agglomération ?\nOui - 1\nNon - 2");
            do {
                choix = UtilSaisie.saisieEntier(new Scanner(System.in));
            } while (choix < 1 || choix > 2);
        } while (choix != 2);
        System.out.println("Au revoir !");
    }
}
