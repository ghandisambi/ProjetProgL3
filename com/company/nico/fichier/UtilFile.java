package com.company.nico.fichier;

import com.company.nico.CA;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * UtilFile
 */
public class UtilFile {

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
            return fichier;
        }
        return fichier;
    }

    public static CA loadDataFile(String chemin) {
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
    }


    public static void sauvegarde(CA ca) throws IOException {
        FileWriter fileWriter;

        try {
            String fichierSolution = "historique" + File.separator + "solution.ca";
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
