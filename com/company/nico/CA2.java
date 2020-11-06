package com.company.nico;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * La classe CA(communauté d'agglomération) représente un graphe
 * et donc possède des sommets représenter par des villes et 
 * des arètes représenter par des routes.  
 */
public class CA2 {
    
    private Map<Ville,Set<Ville>> voisin;
    private Map<Ville,Set<Ville>> ecole;

    /**
     * Constructeur de La classe CA(communauté d'agglomération)
     * il initialise 2 associations d'objets dont:
     * - Objet 1 : composer d'une ville et d'une liste de ville sans doublon qui sont des objets du package
     * - Objet 2 : a pour clé un object ecole et pour valeur un objet ville2
     *
     */

    public CA2() {
        this.voisin = new HashMap<Ville, Set<Ville>>();
        this.ecole = new HashMap<Ville, Set<Ville>>();
    }

    public void initRoute(){
        ajouterRoute("F", "E");
        ajouterRoute("E", "G");
        ajouterRoute("E","D");
        ajouterRoute("D", "A");
        ajouterRoute("D", "C");
        ajouterRoute("A", "B");
        ajouterRoute("B", "C");
        ajouterRoute("C", "I");
        ajouterRoute("B", "H");
        ajouterRoute("H", "I");
        ajouterRoute("H","J");
        ajouterRoute("H","K");
        supprimerEcole("F");
        supprimerEcole("G");
        supprimerEcole("A");
        supprimerEcole("D");
        supprimerEcole("C");
        supprimerEcole("I");
        supprimerEcole("J");
        supprimerEcole("K");
    }

    public void afficheEcole() {
        System.out.println("Voici la liste des villes possédant une école :");
        for (Ville ville2 : ecole.keySet()) {
            System.out.print(ville2.getNom()+" \t");
        }
    }

    Ville getVille (String name) {
        Ville villeTmp = new Ville(name);
        for (Ville ville : voisin.keySet()) {
            if (ville.getNom().equals(name)) {
                villeTmp = (Ville) ville.clone();
            }
        }
        if (villeTmp == null) {
            System.out.println("La ville" + name + "n'existe pas !");
            return null;
        }
        return villeTmp;
    }

    /**
     * La méthode getVillesVoisinnes permet d'obtenir les villes
     * voisinnes à une ville particulière
     * c'est-à-dire les sommets adjacents d'un sommet particulier.
     * @param nom: Nom de la ville dans laquel il faut rechercher les voisins
     * @return  voisin.get(nom):
     */
    public Set<Ville> getVillesVoisinnes(Ville nom) {
        return voisin.get(nom);
    }

    public Set<Ville> getEcolesDependante(Ville nom) {
        return ecole.get(nom);
    }

    public int getTaille(Ville nom) {
        if (getEcolesDependante(nom)!=null) {
            return getEcolesDependante(nom).size();
        }else
            return 0;
    }

    /**
     * La méthode supprimer Route permet de supprimer une route (Arète)
     * entre 2 ville
     * @param nomVille
     * @param nomVoisinne
     */

    public void supprimerRoute(String nomVille,String nomVoisinne) {
        Ville v1= new Ville(nomVille);
        Ville v2= new Ville(nomVoisinne);
        Set<Ville> eV1 = voisin.get(v1);
        Set<Ville> eV2 = voisin.get(v2);
        if (eV1 !=null){
            eV1.remove(v2);
        }
        if (eV2 !=null){
            eV2.remove(v1);
        }
    }


    public boolean tousVoisinsOntEcole(Ville ville) {
        boolean bool = true;
        for (Ville voisin:getVillesVoisinnes(ville)){
            if (!ecole.containsKey(voisin)) {
                bool = false;
                break;
            }
            System.out.println(voisin+" possède ecole");
        }
        return bool;
    }

    public boolean parcourtVoisin(Ville nom, Ville nom2) {
        if (!ecole.containsKey(nom)) {
            if (getVillesVoisinnes(nom).size() != 1) {
                for (Ville voisin : getVillesVoisinnes(nom)) {
                    if (ecole.containsKey(voisin) && !(voisin.equals(nom2))){
                        return true;
                    }
                }
                return false;
            } else return false;
        } else {
            System.out.println(nom+" contient une ecole");
            return true;
        }
    }

    /**
     * Supprime une école si cela est possible.
     *
     * @param nomEcole
     *  Le nom de l'école a supprimé.
     */
    public void supprimerEcole(String nomEcole) {
       Ville ville = getVille(nomEcole);
        boolean isPossible = false; // Au départ la suppression est impossible
        if (tousVoisinsOntEcole(ville))// Si tous les voisins possédent une école il n'y pas de problème.
            isPossible = true;

            for (Ville voisin : getVillesVoisinnes(ville)) {
                isPossible = parcourtVoisin(voisin, ville);
                if (!isPossible) break;
            }

        if (isPossible) {
            Set<Ville> eV1;
            for (Ville ko : getVillesVoisinnes(ville)) {
                eV1=ecole.get(ko);
                if (eV1!=null) {
                    eV1.remove(ville);
                }
            }
            ecole.remove(ville); // Si le retrait de l'école ne viole pas la contrainte d'accessibilité.
        } else System.out.println("La suppression de " + nomEcole + " est impossible");
    }


   public boolean villeExist(String nomVille) {
        Ville villeTmp = new Ville(nomVille);
       return voisin.containsKey(villeTmp);
    }

    /**
     * Ajoute une ville a la communauter d'aglomération si elle n'existe pas déja.
     *
     * @param nomVille
     *  Nom de la ville a ajouter
     */
    public void ajouterVille(String nomVille) {
        Ville v = getVille(nomVille);
         
        if (voisin.containsKey(new Ville(nomVille))){
            System.out.println("Cette ville existe déjà !");
        }
        voisin.putIfAbsent(v,new HashSet<>());
        ecole.putIfAbsent(v, new HashSet<>());
    }



    /**
     * Ajoute une route entre deux villes et met à jour leurs liste de voisins.
     * 
     * @param nomVilleA
     *  Nom de la ville A a relier avec B.
     * @param nomVilleB
     *  Nom de la ville B a relier avec A
     */
    public boolean ajouterRoute(String nomVilleA,String nomVilleB) {
        /* Variables locales */
        Ville villeTmpA = new Ville(nomVilleA);
        Ville villeTmpB = new Ville(nomVilleB);

        if(voisin.containsKey(villeTmpA)){ // Si la ville A existe
            if(voisin.containsKey(villeTmpB)){ // Si la ville B existe
                if(!villeTmpA.equals(villeTmpB)){ // Si les deux ville ne sont pas les mêmes
                    voisin.get(villeTmpA).add(villeTmpB); // On ajoute la ville A comme voisine de B
                    voisin.get(villeTmpB).add(villeTmpA); // On ajoute la ville B comme voisine de A
                    System.out.println("On ajouter " + villeTmpA + "au dependance de "+villeTmpB );
                    ecole.get(villeTmpA).add(villeTmpB); // On ajoute dans les dependance a l'ecole
                    System.out.println("On ajouter " + villeTmpB + "au dependance de "+villeTmpA );
                    ecole.get(villeTmpB).add(villeTmpA); // On ajoute dans les dependance a l'ecole
                }
            } else System.out.println("la ville de départ n'existe pas");
        } else System.out.println("la ville d'arriver n'existe pas");
       return voisin.containsKey(villeTmpA)&&voisin.containsKey(villeTmpB);
    }


    
 
    /**
     * Méthode AjouterEcole permet d'ajouter une et une seule école à une ville
     * si la ville existe 
     * @param nomVille
     *  Nom de la ville dans laquelle ajouter l'école.
     * @return
     *  Un booléen indiquant si la ville a bien était ajouter.
     */
    public boolean ajouterEcole(String nomVille) {
        Ville v = getVille(nomVille);
        if (voisin.containsKey(v)) {
            ecole.putIfAbsent(v, getVillesVoisinnes(v));
            for (Ville ville : getVillesVoisinnes(v)) {
                ecole.get(ville).add(v); // On ajoute dans les dependance a l'ecole
            }
            return true;
        } else {
            System.out.println("la ville n'existe pas");
            return false;
        }
    }

    /**
     * Redéfinition de la classe toString.
     *
     * @return
     *  Une chaine de caractère contenant les informations de la communauter d'aglomeration
     */
    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        for(Ville v : voisin.keySet()){
            sb.append(v).append("->");
            sb.append(voisin.get(v)).append("\n");
        }
        sb.append("Ecole présent\n").append(ecole.keySet());
        return sb.toString();
    }

}
