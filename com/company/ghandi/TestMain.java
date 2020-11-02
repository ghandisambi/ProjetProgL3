package com.company.ghandi;


import java.util.InputMismatchException;
import java.util.Scanner;



public class TestMain {

    /**
     * Affiche le menu correspondant et retourne le choix de l'utilisateur
     * @return menu d'affichage
     */
    public static int menu(int etape){
        System.out.println("Quelle opération souhaitez-vous effectuer ?");
        int n = 0; /* Choix de l'utilisateur a retourner */
        switch (etape){
            case 1:
                do{
                    n = saisieEntier(new Scanner(System.in),
                            """
                                    1 - Ajouter une route\s
                                    2 - Fin
                                    """);
                } while(n < 1 || n > 2);
                break;
            case 2:
                do{
                    n = saisieEntier(new Scanner(System.in),
                            """
                                1 - Ajouter une école\s
                                2 - Retirer une école
                                3 - Fin / Quitter
                                """);
                }while(n < 0 || n > 3);
            default:
                break;
        }
        return n;
    }

    /**
     * permet de simplifier la saisie de chaîne de caractères
     * @param sc
     * @param tag
     * @return
     */
    public static String  saisie(Scanner sc,String tag){
        
        String s = "";
        
        System.out.print(tag);
        s = sc.nextLine();
        
        return s ;
    }
    /**
     * permet de simplifier la saisie des entiers
     * 
     * @param sc
     * @param tag
     * @return
     */
    public static int saisieEntier(Scanner sc,String tag){
       int entier = 0;
          
            try {
                System.out.print(tag);
                entier= sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        return entier ;
    }

    /**
     * affichage du programme
     * @param nCa
     */
    public static void Affichage(CA nCa){
        int n = 0 ;
        String s;

        do {
            n = saisieEntier(new Scanner(System.in), "Entrez le nombre de Ville entre (1/26):");
        }
        while(n < 0 || n >= 26); /* Tant que le nombre de ville n'a pas le bon format */

        for (int i = 0 ;i < n; i++){ /* On créé n ville */
            s = Character.toString(i+65); /* On rajoute 65 pour avoir la representation d'une lettre majuscule dans le code ASCII */
            nCa.ajouterVille(s); /* On ajoute la ville créé a la CA */
        }
        System.out.println(nCa.toString()); /* Test a supprimer */

        int option = 0;
        do {
            System.out.println("__________________________________________________________________________");
            System.out.println("****************************  Etape 1   ***************************");
            option = menu(1);

            switch(option) {

                case 1: /* Ajouter une route */
                    String v1,v2;
                    do{
                        v1 = saisie(new Scanner(System.in), "Nom de la ville:");
                        v2 = saisie(new Scanner(System.in), "Nom de la ville voisinne:");
                    } while(!nCa.ajouterRoute(v1, v2));
                    System.out.println("\n********Voici la liste des villes et des routes entrez*************** ");
                    System.out.println(nCa.toString());
                break;

                case 2: /* Fin , on poursuit le programme */
                    System.out.println("***************************Nombre de voisin****************************");
                    for(Ville2 m:nCa.getVoisin().keySet()){
                        nCa.CompteVoisin(m);
                    }
                    do {
                        System.out.println("__________________________________________________________________________");
                        System.out.println("****************************  Etape 2   ***************************");
                        option=menu(2);
                        switch (option) {
                            case 1:
                                do {
                                    System.out.println("************************** << Etape 2 ajout  >> *****************************");
                                    s = saisie(new Scanner(System.in), "Dans quelle ville voulez-vous ajouter l'école =>");
                                } while (!nCa.ajouterEcole(s));
                                System.out.println(nCa.toString());
                                break;

                            case 2:
                                System.out.println("**************************** << Etape 2 suppression >> ***************************");
                                s = saisie(new Scanner(System.in), "Dans quelle ville voulez-vous supprimer l'école => ");
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
        } while(option!=0);
    }

    /**
     * Teste du programme
     * @param args
     */
    public static void main(String[] args) {
        CA nCa = new CA();
        Affichage(nCa);
    }
}
