package com.company.nico;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Manuelle {
    public static int menu(int etape,Scanner scanner){
        System.out.println("Quelle opération souhaitez-vous effectuer ?");
        int n = 0; /* Choix de l'utilisateur a retourner */
        switch (etape) {
            case 1:
                do {
                    System.out.println("1 - Ajouter une route\n2 - Fin");
                    n = saisieEntier(scanner); //On demande une saisie d'entier.
                } while (n < 1 || n > 2); //Tant que l'utilisateur ne choisit pas la bonne option.
                break;
            case 2:
                do {
                    System.out.println("1 - Ajouter une école\n2 - Retirer une école\n3 - Fin / Quitter");
                    n = saisieEntier(scanner); //On demande une saisie d'entier.
                } while (n < 1 || n > 3); //Tant que l'utilisateur ne choisit pas la bonne option.
            default:
                break;
        }
        return n;
    }

    /**
     * Permet la saisie d'une chaine de caractère.
     * @return
     *  La chaîne de caractère saisie.
     */
    public static String saisieVille(Scanner scanner, CA ca){
            String s = "";
            s = scanner.nextLine();
            if (ca.villeExist(s))
            return s;
            else return null;
    }


    /**
     * Permet la saisie sécuriser d'un entier.
     * @return
     *  L'entier saisie.
     */
    public static int saisieEntier(Scanner scanner){
        int entier = 0;

        try {
            entier= scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erreur -> Votre saisie ne correspond pas à la valeur attendue");
            scanner.next();
        }
        return entier ;
    }

    /**
     * Affichage du programme.
     *
     *  Communauter d'aglomeration
     */
    public static void Affichage(CA ca){
        
        String s;
        Scanner scanner = new Scanner(System.in);
        

        int option = 0;
        System.out.println(ca.toString());
        do {
            System.out.println("********************************  Etape 2   *******************************");
            option=menu(2,scanner);
            ca.afficheEcole();
            switch (option) {
                case 1:
                    do {
                            System.out.println("************************** << Etape 2 ajout  >> *****************************");
                            System.out.println("Dans quelle ville voulez-vous ajouter l'école => ");
                            s = saisieVille(new Scanner(System.in),ca);
                        } while (!ca.ajouterEcole(s));
                                
                        System.out.println(ca.toString());
                        ca.afficheEcole();
                break;

                case 2:

                    System.out.println("**************************** << Etape 2 suppression >> ***************************");
                    System.out.println("Dans quelle ville voulez-vous supprimer l'école => ");
                    s = saisieVille(new Scanner(System.in),ca);
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

}
