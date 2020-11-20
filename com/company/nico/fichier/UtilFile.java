package com.company.nico.fichier;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.LinkedList;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * UtilFile
 */
public class UtilFile {

   
   
    public static void file(String[] args) {
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
    public static LinkedList<String> lire(String chemin) {
        
        LinkedList<String> l = new LinkedList<>();
        try {
            
            StringTokenizer st;
            StringBuffer sb=new StringBuffer();
            File fichier = new File(chemin); 
          Scanner sc = new Scanner(fichier);
          while (sc.hasNextLine()) {
             sb.append(sc.nextLine());
          }
          sc.close();
          st=new StringTokenizer(sb.toString(),"("+"."+","+")");
          while (st.hasMoreTokens()) {
              l.add(st.nextToken());
          }
        } catch (FileNotFoundException e) {
          System.out.println("Le fichier n'existe pas("+e.getMessage()+")");
        }
        
        return l;
    }
    
    
}