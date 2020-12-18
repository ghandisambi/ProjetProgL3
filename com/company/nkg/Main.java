package com.company.nkg;

import java.io.*;

import com.company.nkg.Utils.UtilFile;

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
            ca = UtilFile.chargement(args[0]);
            if (ca!=null){
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
    }
}
