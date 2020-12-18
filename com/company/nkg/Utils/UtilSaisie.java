package com.company.nkg.Utils;

import com.company.nkg.CA;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilSaisie {

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

}
