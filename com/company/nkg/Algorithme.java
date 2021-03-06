package com.company.nkg;

import com.company.nkg.Utils.UtilSaisie;

import java.util.*;

public class Algorithme {


    public static void choixAlgo(Scanner scanner,CA ca) {
        int choix;
        System.out.println((ca.nombreVille()<=4)?"Il n'y a pas beaucoup de ville dans votre communauté " +
                "Nous vous conseillons d'utiliser l'algorithme naïf qui fera l'affaire.":"Votre communauté " +
                "d'agglomération a un nombre conséquent de ville l'algorithme optimisé sera plus approprié.");
        do {
            System.out.println("Quelle algorithme voulez vous utilisé ?\n1 - Algorithme naïf\n2 - Algorithme optimisé");
            choix = UtilSaisie.saisieEntier(scanner);
            if (choix < 1 || choix > 2)
                System.out.println("Choix incorrecte !");
        } while (choix < 1 || choix > 2);
        Solution(ca,choix);

    }

    public static void Solution(CA ca,int nb) {
        if (nb==1){
            algoNaif(ca,ca.nombreVille());
        } else if (nb==2){
            algorithmeOptimiser(ca);
            precision(ca);
        }
    }

   /* public static void algoApproximation(CA ca, int k) {

        int i = 0;
        int scoreCourant = ca.score();
        Map<Ville, Boolean> ecole = new HashMap<>();
        Ville ville = ca.getRandomVille();
        while (i < k) {

            if (ca.villeExist(ville.toString())) {
                if (ca.villePossedeEcole(ville.toString())) {
                    ca.supprimerEcole(ville.toString());
                } else {
                    ca.ajouterEcole(ville.toString());
                }
                if (ca.score() < scoreCourant) {
                    for (Map.Entry<String, Ville> e : ca.getEcole().entrySet()) {
                        if (ca.testSuppressionEcole(e.getKey())) {
                            ecole.put(e.getValue(), true);
                        }
                    }
                    break;
                } else
                    i++;
            }
        }
        for (Map.Entry<Ville, Boolean> e : ecole.entrySet()) {
            System.out.println("" + ca.ecoleToString());
            ca.supprimerEcole(e.getKey().toString());
        }
        System.out.println(ca.getEcoleList().toString());
    }
*/
   /*
    public static void algoOptimiser(CA ca) {
        List<Integer> list = new ArrayList<>();

        for (Map.Entry<Ville, Set<Ville>> e : ca.getVoisin().entrySet()) {
            list.add(e.getValue().size());
        }


        int scoreCourant = ca.score();
        while (ca.score() >= scoreCourant) {
            algoApproximation(ca, ca.nombreVille());
            scoreCourant = majorant(list);
            if (ca.score() <= scoreCourant) {
                if (ca.score() < scoreCourant) {
                    System.out.println(scoreCourant + "<" + ca.ecoleToString());
                    break;
                } else if (ca.score() == scoreCourant) {
                    System.out.println(scoreCourant + "==" + ca.ecoleToString());
                    break;
                }
            }
        }
        System.out.println("La meilleur solution est: " + ca.getEcoleList());
    }
*/

    /*  public static int majorant(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return Integer.MIN_VALUE;
        }
        List<Integer> listTrie = new ArrayList<>(list);
        Collections.sort(listTrie);
        return listTrie.get(listTrie.size() - 1);
    }*/

    public static void algoNaif(CA ca, int k) {
        int i = 0;

        while (i < k) {
            Ville ville = ca.getRandomVille();

            if (ca.villeExist(ville.toString())) {
                if (ca.villePossedeEcole(ville.toString())) {
                    ca.supprimerEcole(ville.toString());
                } else {
                    ca.ajouterEcole(ville.toString());
                }
                i++;
            }
        }
    }

    public static CA algorithme(CA ca, int k) {
        int i = 0;
        int scoreCourant = ca.score() ;
        Map<Ville, Boolean> ecole = new HashMap<>();

        while (i < k) {
            Ville ville = ca.getRandomVille();

            if (ca.villeExist(ville.toString())) {
                if (ca.villePossedeEcole(ville.toString())) {
                    ca.supprimerEcole(ville.toString());

                } else {
                    ca.ajouterEcole(ville.toString());
                }
                if (ca.score() < scoreCourant) {
                    for (Map.Entry<String, Ville> e : ca.getEcole().entrySet()) {
                        if (ca.testSuppressionEcole(e.getKey())) {
                            ecole.put(e.getValue(), true);
                        }
                    }

                    break;
                } else
                    i++;
            }
        }
        for (Map.Entry<Ville, Boolean> e : ecole.entrySet()) {
            ca.supprimerEcole(e.getKey().toString());
        }
        //affiche(ca.getEcoleList().toString());
        return ca;
    }

    public static CA algorithmeOptimiser(CA ca){
        int scoreCourant=ca.score();
        

       /* if(ca.getEcole().size()<ca.nombreVille()/3){
            ca.initEcole();
            scoreCourant=ca.score();
        }else scoreCourant=ca.score();

*/       
            while (ca.score() >= scoreCourant) {
                algorithme(ca, ca.nombreVille()).getEcoleList();
                if (ca.score() <= scoreCourant) {
                    if (ca.score() < scoreCourant) {
                        break;
                    } else if (ca.score() == scoreCourant) {
                        break;
                    }
                }

            }
    
            
        
        affiche("La meilleur solution est: "+ca.getEcoleList());
        return ca;

    }

    public static CA precision(CA ca) {
        Map<Ville, Boolean> ecole = new HashMap<>();
        Map<Ville, String> verification = new LinkedHashMap<>();
        for (Map.Entry<String, Ville> e : ca.getEcole().entrySet()) {
            if(ca.getVillesVoisinnes(e.getValue()).size()==1)
            verification.put(e.getValue(), ca.getVillesVoisinnes(e.getValue()).toString());
        }

        if(!verification.isEmpty()){
                Queue<Ville> cles= new LinkedList<>();
                Queue<String> values= new LinkedList<>();
                cles.addAll(verification.keySet());
                values.addAll(verification.values());
                for (Map.Entry<Ville, String> e : verification.entrySet()) {
                    if(cles.contains(e.getKey())&&values.contains(e.getValue())){
                        Ville maville = new Ville(e.getValue());
                        StringTokenizer str = new StringTokenizer(maville.toString(),"[]");
                        String s = str.nextToken().toString();
                        
                        ca.ajouterEcole(s);
                        ca.supprimerEcole(e.getKey().toString());
                    }
                }
            affiche(ca.ecoleToString());

        }
        return ca;
    }
    public static void affiche(String s){
        System.out.println(s);
    }

}
