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


public static void loadDataFile(String[] args,CA ca) {
    if (args.length==0)
        System.out.println("Un argument est attendu !");
    else {
        lireLignes(args[0],ca);
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
        String argument;
        String substring = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
        if (Pattern.matches("ville(.*)", line)) {
            argument = substring;
            if (argument.length() == 1) {
                ca.ajouterVille(argument);
            } else System.out.println("Erreur création de ville - Un argument est incorrect !");
        }
        if (Pattern.matches("route(.*)", line)){
            argument = substring;
                if (argument.contains(",") && argument.length() == 3) {
                    ca.ajouterRoute(argument.substring(0,argument.indexOf(',')),argument.substring(argument.indexOf(',')+1,3));
                } else System.out.println("Erreur création de route - Un argument est incorrect !");
            }
        }

    public static void sauvegarde(CA ca,File file)throws IOException {

        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(ca.ecoleToString());
        bw.newLine();
        bw.close();

    }

    /* public static void file(String[] args) {
         try {
             File fichier = new File(args[0]);
             Scanner myReader = new Scanner(fichier);
          while (myReader.hasNextLine()) {
            String ligne = myReader.nextLine();
            System.out.println(ligne);
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("Le fichier n'existe pas("+e.getMessage()+")");
        }
    }
*/
    /* public static LinkedList<String> lire(String chemin) {
        
        LinkedList<String> l = new LinkedList<>();
        try {
            
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();
            File fichier = new File(chemin); 
            Scanner sc = new Scanner(fichier);
          while (sc.hasNextLine()) {

              sb.append(sc.nextLine());
          }
          sc.close();
          st = new StringTokenizer(sb.toString(),"("+"."+","+")");

          while (st.hasMoreTokens()) {
              l.add(st.nextToken());
          }
        } catch (FileNotFoundException e) {
          System.out.println("Le fichier n'existe pas("+e.getMessage()+")");
        }
        
        return l;
    }
*/
    /*
    public static CA lecture(CA ca,LinkedList<String> l) {

      while (l.contains("ville")) {
            l.remove("ville");
            ca.ajouterVille(l.poll());
      }

      while (l.contains("route")) {
            l.remove("route");
            ca.ajouterRoute(l.pop(), l.pop()); 
      }

      while (l.contains("ecole")) {
            l.remove("ecole");
            ca.ajouterEcole(l.poll());
      }      
      return ca;
    }
*/

    
    
}
