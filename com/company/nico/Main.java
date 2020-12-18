package com.company.nico;

import java.io.*;
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
        /* Création de la communauté d'agglomération */
        if (args.length > 0) {
            CA ca = null;
            ca = UtilFile.loadDataFile(args[0]);
            if (ca!=null){
            if (!ca.respectContrainte()) {
                for (String ville : ca.getListVille()) {
                    ca.ajouterEcole(ville);
                }
            }

            System.out.println(ca.toString());
            Commande.commande(ca);
            } else System.out.println("Ce repertoire n'existe pas !");
        } else {
            System.out.println("Veuillez specifier le nom du repertoire !");
        }
    }
}
