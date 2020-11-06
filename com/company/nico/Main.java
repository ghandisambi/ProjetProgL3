package com.company.nico;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Main et la classe principale contenant l'ensemble des méthodes et déclaration permettant d'utiliser
 * l'interface utilisateur.
 */
public class Main {

    /**
     * Affiche le menu correspondant et retourne le choix de l'utilisateur
     * @return
     *  menu d'affichage
     */
    public static int menu(int etape,Scanner scanner){
        System.out.println("Quelle opération souhaitez-vous effectuer ?");
        int n = 0; /* Choix de l'utilisateur a retourner */
        switch (etape){
            case 1:
                do{
                    System.out.println("1 - Ajouter une route\n2 - Fin");
                    n = saisieEntier(scanner); //On demande une saisie d'entier.
                } while (n < 1 || n > 2); //Tant que l'utilisateur ne choisit pas la bonne option.
                break;
            case 2:
                do{
                    System.out.println("1 - Ajouter une école\n2 - Retirer une école\n3 - Fin / Quitter");
                    n = saisieEntier(scanner); //On demande une saisie d'entier.
                }while (n < 0 || n > 3); //Tant que l'utilisateur ne choisit pas la bonne option.
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
     * @param nCa
     *  Communauter d'aglomeration
     */
    public static void Affichage(CA nCa){
        int n = 0 ;
        String s;
        Scanner scanner = new Scanner(System.in);

        /* On demande a l'utilisateur le nombre de villes qu'il souhaite. */
        do {
            System.out.println("Entrez le nombre de Ville souhaiter entre 1 et 26 : ");
            n = saisieEntier(scanner);
        } while (n < 1 || n > 26); /* Tant que le nombre de ville n'est pas entre 1 et 26 */

        /* On crée n ville */
        for (int i = 0 ;i < n; i++){
            s = Character.toString(i+65); /* On rajoute 65 pour avoir la representation d'une lettre majuscule dans le code ASCII */
            nCa.ajouterVille(s); /* On ajoute la ville créé a la CA */
        }

        /* Etape 1 */
        int option = 0;
        do {
            System.out.println("__________________________________________________________________________");
            System.out.println("****************************  Etape 1   ***************************");
            option = menu(1,scanner); /* L'utilisateur choisit parmit les options de l'étape 1 */

            switch(option) {

                case 1: /* Ajouter une route */
                    String v1,v2;
                    do{
                        System.out.println("Nom de la ville : ");
                        v1 = saisieVille(new Scanner(System.in),nCa);
                        System.out.println("Nom de la ville voisine :");
                        v2 = saisieVille(new Scanner(System.in),nCa);
                    } while(!nCa.ajouterRoute(v1, v2));
                    System.out.println("\n********  Voici la liste des villes et des routes entrer  *************** ");
                    System.out.println(nCa.toString());
                break;

                case 2: /* Fin , on poursuit le programme */
                    nCa.initRoute();
                    System.out.println(nCa.toString());
                    do {
                        System.out.println("__________________________________________________________________________");
                        System.out.println("****************************  Etape 2   ***************************");
                        option=menu(2,scanner);
                        nCa.afficheEcole();
                        switch (option) {
                            case 1:
                                do {
                                    System.out.println("************************** << Etape 2 ajout  >> *****************************");
                                    System.out.println("Dans quelle ville voulez-vous ajouter l'école => ");
                                    s = saisieVille(new Scanner(System.in),nCa);
                                } while (!nCa.ajouterEcole(s));
                                System.out.println(nCa.toString());
                                nCa.afficheEcole();
                                break;

                            case 2:
                                System.out.println("**************************** << Etape 2 suppression >> ***************************");
                                System.out.println("Dans quelle ville voulez-vous supprimer l'école => ");
                                s = saisieVille(new Scanner(System.in),nCa);
                                nCa.supprimerEcole(s);
                                nCa.afficheEcole();
                                break;

                            case 3:
                                System.out.println("Fin programme");
                                option = 0;
                                break;

                            default:
                                break;
                        }
                    } while (option != 0); /* Tant qu'on ne demande pas la fin du programme on reste a l'étape 2 */
                    break;
                default:
                break;
            }
        } while(option != 0);
        scanner.close();
    }


    /**
     * Lancement du programme.
     */
    public static void main(String[] args) {
        CA nCa = new CA();
        Affichage(nCa);
    }
}
