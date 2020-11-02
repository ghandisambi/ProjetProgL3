package com.company;


import java.util.InputMismatchException;
import java.util.Scanner;



public class TestMain {
    
    /**
     * affiche le premier menu 
     * @return menu d'affichage
     */
    public static int menu(){
        int n = 0;
        System.out.println("Quelle opération souhaitez vous effectuer?");
        do{ 
         n = saisieEntier(new Scanner(System.in),  "1 - Ajouter une route \n"+"2 - Fin\n"+"4 - Quitter\n");
        }while(!( n>=0 && n<=4));
        return n;
    }

    /**
     * affiche le 2ème menu 
     *  
     * @param v
     * @return
     */
    public static int menu(int v){
        int n = 0;
        System.out.println("Quelle opération souhaitez vous effectuer?");
        do{ 
         
         n = saisieEntier(new Scanner(System.in),  "1 - Ajouter une ecole \n"+"2 - supprimer une route\n"+"3 - Fin\n"+"4 - Quitter\n");
        }while((!( n>=0 && n<=4))||n==4);
        return n;
    }
    /**
     * permet de simplifier la saisie de chaîne de caractères
     * @param sc
     * @param tag
     * @return
     */
    public static String  saisie(Scanner sc,String tag){
        
        String s = new String();
        
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
     * affiche le menu 2
     * @param option
     * @param nCa
     */
    public static void choix(int option,CA nCa){
        switch(option){
            case 1:
            String v;
            do{ System.out.println("************************** <<Etape 2 ajout >> *****************************");
                v = saisie(new Scanner(System.in), "Dans quelle ville voulez-vous ajouter l'école:");
                
            }while(!nCa.ajouterEcole(v));
            System.out.println(nCa.toString());
            break;
            case 2:
            System.out.println("**************************** <<Etape 2 suppression >> ***************************");
            v = saisie(new Scanner(System.in), "Dans quelle ville voulez-vous supprimer l'école:");
            
            nCa.supprimerEcole(v);
            System.out.println("\n");
            break;

            case 3:
            System.out.println(nCa.toString());
            System.out.println("Fin programme");
            break;
            case 4:
            break;
            
            default:

            break;
        }

    }
    /**
     * affichage du programme
     * @param nCa
     */
    public static void Affichage(CA nCa){
        int nbVille = 0 ;
        
        String s = new String();
        char ville = 'A';
        --ville;
        do{
            
            nbVille= saisieEntier(new Scanner(System.in), "Entrez le nombre de Ville entre(1/26):");
        }
        while(!( nbVille>0 && nbVille<=26 ));
        
        for (int i=0;i<nbVille;i++){
            s=Character.toString(++ville);
            nCa.ajouterVille(s);
        }
        System.out.println(nCa.toString());
        
        
        int option = 0;
        do
        {   
            option = menu();
            switch(option)
            {
            case 1: 
            String v1,v2;
            do{
             v1 = saisie(new Scanner(System.in), "Nom de la ville:");
             v2 = saisie(new Scanner(System.in), "Nom de la ville voisinne:");
            }while(!nCa.ajouterRoute(v1, v2));
            System.out.println("\n********Voici la liste des villes et des routes entrez*************** ");
            System.out.println(nCa.toString());
            break;
                
            case 2:
            System.out.println("***************************Nombre de voisin****************************");
            for(Ville2 m:nCa.getVoisin().keySet()){
                nCa.CompteVoisin(m);
            }
            System.out.println("__________________________________________________________________________");
            System.out.println("****************************  Etape 2   ***************************");
            choix(menu(1), nCa);            
            break;
            
            default: 
            /*
            v1 = saisie(new Scanner(System.in), "Nom de la ville:");
            v2 = saisie(new Scanner(System.in), "Nom de la ville voisinne:");
            nCa.supprimerRoute(v1, v2);
            System.out.println("Entrer la bonne option svp\n");
            System.out.println("Entrer la bonne option svp\n");
            */
            System.out.println("Selectionner la bonne option");
            break;


        }

        }while(option!=4);
         
    }

    /**
     * creation de la communauter d'agglomeration par défaut
     * @param nCa
     */
    public static void CAPardefault(CA nCa){
        nCa=nCa.creerCA();
        System.out.println(nCa.toString());
        for(Ville2 m:nCa.getVoisin().keySet()){
            nCa.CompteVoisin(m);
        }
        
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
