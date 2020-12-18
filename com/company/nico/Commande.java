package com.company.nico;

import com.company.nico.fichier.UtilFile;

import java.io.IOException;
import java.util.Scanner;

public class Commande {
    public static void commande(CA ca) {
        System.out.println("Commande ca");
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
