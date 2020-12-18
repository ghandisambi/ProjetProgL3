package com.company.nkg.Utils;

import com.company.nkg.CA;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * UtilFile : Contient les méthodes de lecture/écriture néccesaire au programme.
 */
public class UtilFile {


    /**
     * Permet a l'utilisateur de choisir un nom de fichier parmis un repertoire spécifier en paramètre.
     *
     * @param rep le repertoire.
     * @return un nom de fichier.
     */
    public static String choixFichier(String rep) {

        File repertoire = new File(rep);
        String[] liste = repertoire.list();
        String extension;
        ArrayList<String> dispo = new ArrayList<>();
        ArrayList<String> indispo = new ArrayList<>();
        String fichier = "";
        Scanner scanner = new Scanner(System.in);

        if (liste != null) {
            for (String s : liste) {
                extension = s.substring(s.indexOf(".") + 1);
                if (extension.equals("ca")) {
                    dispo.add(s);
                } else {
                    indispo.add(s);
                }
            }
            System.out.println("====== Voici la liste des fichiers disponible ! ======");
            for (String sd : dispo) {
                System.out.println(sd);
            }
            System.out.println("====== Voici la liste des fichiers indisponibles ! ======");
            for (String si : indispo) {
                System.out.println(si);
            }

            do {
                System.out.println("Quelle fichier voulez-vous ouvrir ?");
                fichier = scanner.nextLine();
                if (!dispo.contains(fichier))
                    System.out.println("Ce fichier n'existe pas ou n'est pas accessible a l'ouverture !");
            } while (!dispo.contains(fichier));

        } else {
            scanner.close();
            return fichier;
        }
        return fichier;
    }

    public static CA chargement(String chemin) {
        String fichier = choixFichier(chemin);
        CA ca = null;

        if (fichier.isEmpty()) {
            return ca;
        } else {
            System.out.println(fichier);
        }

        ca = new CA();
        lireLignes(chemin, fichier, ca);
        System.out.println(ca.toString());
        return ca;

    }


    /**
     * Lis un fichier ligne par ligne et traite chaque ligne afin de remplir une communauter d'agglomération.
     *
     * @param chemin le chemin du repertoire
     * @param fichier le chemin du fichier
     * @param ca une communauter d'agglomération.
     */
    public static void lireLignes(String chemin, String fichier, CA ca) {
        BufferedReader bufferedreader = null;
        FileReader filereader = null;

        try {
            filereader = new FileReader(chemin + "/" + fichier);
            bufferedreader = new BufferedReader(filereader);

            String strCurrentLine;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                traitementLigne(strCurrentLine, ca);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedreader != null)
                    bufferedreader.close();
                if (filereader != null)
                    filereader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Traite une chaine de caractère et appelle les méthodes de création Ville/Route.
     *
     * @param line Nom de la chaine de caractère a traité.
     * @param ca   La communauté d'agglomérations sur laquelle appliquer des changements.
     */
    public static void traitementLigne(String line, CA ca) {
        StringTokenizer st = new StringTokenizer(line,".,() \n");
        
        LinkedList<String> donneeligne = new LinkedList<>();
        while (st.hasMoreTokens()) {
            donneeligne.add(st.nextToken());
        }
        while (donneeligne.contains("ville")) {
            donneeligne.remove("ville");
            ca.ajouterVille(donneeligne.poll());
        }
        while (donneeligne.contains("route")) {
            donneeligne.remove("route");
            ca.ajouterRoute(donneeligne.pop(), donneeligne.pop()); 
        }
        while (donneeligne.contains("ecole")) {
            donneeligne.remove("ecole");
            ca.ajouterEcole(donneeligne.poll());
        }
        
        
        /*
        String argument = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
        if (Pattern.matches("ville(.*)", line)) {
            if (argument.length() == 1) {
                ca.ajouterVille(argument);
            } else System.out.println("Erreur création de ville - Un argument est incorrect !");
        }
        if (Pattern.matches("route(.*)", line)) {
            if (argument.contains(",") && argument.length() == 3) {
                ca.ajouterRoute(argument.substring(0, argument.indexOf(',')), argument.substring(argument.indexOf(',') + 1, 3));
            } else System.out.println("Erreur création de route - Un argument est incorrect !");
        }

        if (Pattern.matches("ecole(.*)", line)) {
            if (argument.length() == 1) {
                ca.ajouterEcole(argument);
            }
        }
        */
    }


    /**
     * Permet de sauvegarder une communauter d'agglomération dans un fichier.
     *
     * @param ca une communauter d'agglomération.
     */
    public static void sauvegarde(CA ca) throws IOException {
        FileWriter fileWriter;
        Scanner scanner = new Scanner(System.in);
        String nomSauvegarde="";

        do {
            System.out.println("Sous quel nom voulez-vous enregistrer ce fichier ?");
            nomSauvegarde = scanner.nextLine();
            if (nomSauvegarde.isEmpty()){
                System.out.println("Veuillez rentrer un nom de fichier pour la sauvegarde !");
            }
        } while (nomSauvegarde.isEmpty());

        try {
            String fichierSolution = "historique" + File.separator + nomSauvegarde + ".rep";
            File file = new File(fichierSolution);
            fileWriter = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write(ca.ecoleToString());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
