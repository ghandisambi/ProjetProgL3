package com.company.nico;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void menu(){
        System.out.println("1 - Ajouter une route");
        System.out.println("2 - Fin");
    }

    public static int indexOfVille(char c, ArrayList<Ville> array) {
        int tmp = 0;
        for (Ville ville:array){
            if (!(ville.getName()==c)) {
                tmp ++;
            } else break;
        }
        return tmp;
    }
    public static boolean villeExist(char name,ArrayList<Ville> villes){
        boolean exist = false;
        for (Ville ville:villes){
            if(ville.getName() == name){
                exist=true;
                break;
            }
        }
        return exist;
    }

    public static void main(String[] args) {
        ArrayList<Ville> villes = new ArrayList<Ville>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre de ville ?");
        int nbVille = scanner.nextInt();
        for (int i=0;i<nbVille;i++){
            villes.add(new Ville((char) (i+65), new ArrayList<>()));
        }

        int option = 0;
        while(option != 2){
            menu();
            option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Entre quelle villes voulez-vous ajoutez une route ?");
                  char tmpA = scanner.next().charAt(0);
                  while (!villeExist(tmpA,villes)){
                      System.out.println("Cette ville n'existe pas !");
                     tmpA = scanner.next().charAt(0);
                }
                  char tmpB = scanner.next().charAt(0);
                    while (!villeExist(tmpB,villes)){
                        System.out.println("Cette ville n'existe pas !");
                        tmpB = scanner.next().charAt(0);
                    }
                    villes.get(indexOfVille(tmpA,villes)).addVilleVoisine(villes.get(indexOfVille(tmpB,villes)));
                    villes.get(indexOfVille(tmpB,villes)).addVilleVoisine(villes.get(indexOfVille(tmpA,villes)));
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Mauvaise commande");
            }
        } scanner.close();
    }

}

