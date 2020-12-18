package com.company.nkg;

import com.company.nkg.Utils.UtilFile;
import com.company.nkg.Utils.UtilSaisie;

import java.io.IOException;
import java.util.Scanner;

public class Commande {

    public static int choixAlgo(Scanner scanner) {
        int choix;
        do {
            System.out.println("Quelle algorithme voulez vous utilisé ?\n1 - Algorithme naïf\n2 - Algorithme optimisé");
            choix = UtilSaisie.saisieEntier(scanner);
            if (choix < 1 || choix > 2)
                System.out.println("Choix incorrecte !");
        } while (choix < 1 || choix > 2);
        return choix;
    }

    public static void menu(CA ca) {
        System.out.println("Commande ca");
        int n;
        int algo;

        do {
            System.out.println(
                    "1) résoudre manuellement.\n" + "2) résoudre automatiquement.\n" + "3) sauvegarder.\n" + "4) fin.");
            do {
                n = UtilSaisie.saisieEntier(new Scanner(System.in));
            } while (n < 1 || n > 4);
            switch (n) {
                case 1:
                    System.out.println("====== Résolution manuelle ======");
                    manuelle(ca);
                    break;

                case 2:
                    System.out.println("====== Résolution automatique ======");
                    algo = choixAlgo(new Scanner(System.in));
                    /////////////////////////Attention erreur si le Ca na pas de ville.
                    switch (algo) {
                        case 1:
                            Algorithme.Solution(ca,1);
                            break;
                        case 2:
                            Algorithme.Solution(ca,2);
                            break;
                        default:
                            break;
                    }
                    /*if (ca.getEcoleList().isEmpty()) {
                        Algorithme.algoNaif(ca, ca.nombreVille());
                        System.out.println("apre naif : " + ca.toString());
                    }*/
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
        } while (n != 0);
    }


    public static void manuelle(CA ca) {

        String s;
        Scanner scanner = new Scanner(System.in);


        int option = 0;
        System.out.println(ca.toString());
        do {
            System.out.println("********************************  Etape 2   *******************************");
            option = menu(2, scanner);
            ca.afficheEcole();
            switch (option) {
                case 1:
                    System.out.println("************************** << Etape 2 ajout  >> *****************************");
                    System.out.println("Dans quelle ville voulez-vous ajouter l'école => ");
                    s = saisieVille(new Scanner(System.in), ca);
                    if(!ca.ajouterEcole(s)){
                        System.out.println("Impossible de rajouter une école dans la ville !");
                    }
                    System.out.println(ca.toString());
                    ca.afficheEcole();
                    break;

                case 2:

                    System.out.println("**************************** << Etape 2 suppression >> ***************************");
                    System.out.println("Dans quelle ville voulez-vous supprimer l'école => ");
                    s = saisieVille(new Scanner(System.in), ca);
                    ca.supprimerEcole(s);
                    ca.afficheEcole();
                    break;

                case 3:
                    System.out.println("Fin du programme");
                    option = 0;
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }

    /**
     * Permet la saisie d'une chaine de caractère.
     *
     * @return La chaîne de caractère saisie.
     */
    public static String saisieVille(Scanner scanner, CA ca) {
        String s = "";
        s = scanner.nextLine();
        if (ca.villeExist(s))
            return s;
        else return null;
    }

    public static int menu(int etape, Scanner scanner) {
        System.out.println("Quelle opération souhaitez-vous effectuer ?");
        int n = 0; /* Choix de l'utilisateur a retourner */
        switch (etape) {
            case 1:
                do {
                    System.out.println("1 - Ajouter une route\n2 - Fin");
                    n = UtilSaisie.saisieEntier(scanner); //On demande une saisie d'entier.
                } while (n < 1 || n > 2); //Tant que l'utilisateur ne choisit pas la bonne option.
                break;
            case 2:
                do {
                    System.out.println("1 - Ajouter une école\n2 - Retirer une école\n3 - Fin / Quitter");
                    n = UtilSaisie.saisieEntier(scanner); //On demande une saisie d'entier.
                } while (n < 1 || n > 3); //Tant que l'utilisateur ne choisit pas la bonne option.
            default:
                break;
        }
        return n;
    }


}
