package com.company.nico.fichier;

import java.io.*;
import java.util.LinkedList;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.company.nico.CA;

/**
 * UtilFile
 */
public class UtilFile {


public static CA loadDataFile(String[] args) {
    CA ca =null;

    if (args.length==0) {
        System.out.println("Vous n'avez pas indiqué de fichier !");
        return ca;
    } else {
        ca = new CA();
        lireLignes(args[0],ca);
        System.out.println(ca.toString());
        return ca;
    }
}


public static void lireLignes(String chemin,CA ca) {
    BufferedReader bufferedreader = null;
    FileReader filereader = null;

    try {
        filereader = new FileReader("com/company/nico/fichier/"+chemin);
        bufferedreader = new BufferedReader(filereader);

        String strCurrentLine;
        while ((strCurrentLine = bufferedreader.readLine()) != null) {
            traitementLigne(strCurrentLine,ca);}
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
     * @param ca La communauté d'agglomérations sur laquelle appliquer des changements.
     */
    public static void traitementLigne(String line,CA ca) {
        String argument = line.substring(line.indexOf('(') + 1, line.indexOf(')'));;
        if (Pattern.matches("ville(.*)", line)) {
            if (argument.length() == 1) {
                ca.ajouterVille(argument);
            } else System.out.println("Erreur création de ville - Un argument est incorrect !");
        }
        if (Pattern.matches("route(.*)", line)) {
                if (argument.contains(",") && argument.length() == 3) {
                    ca.ajouterRoute(argument.substring(0,argument.indexOf(',')),argument.substring(argument.indexOf(',')+1,3));
                } else System.out.println("Erreur création de route - Un argument est incorrect !");
        }

        if (Pattern.matches("ecole(.*)",line)) {
                if (argument.length() == 1) {
                    ca.ajouterEcole(argument);
                }
        }
    }


    public static void sauvegarde(CA ca)throws IOException {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try {
            String fichierSolution = "com" + File.separator + "company" + File.separator + "nico" + File.separator + "fichier" + File.separator + "solution.txt";
            File file = new File(fichierSolution);
            fileWriter = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write(ca.ecoleToString());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
