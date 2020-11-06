package com.company.nico;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * CA est la classe contenant l'ensemble des variables et des méthodes nécessaires à la création et à la modification
 * d'une communauté d'agglomérations.
 */
public class CA {

    /* Association représentant une ville comme clé et sa liste d'adjacence comme valeur. */
    private final Map<Ville,Set<Ville>> voisin;
    /* Association représentant le nom d'une ville comme clé la ville dans laquelle elle se situe comme valeur. */
    private final Map<String,Ville> ecole;

    /**
     * Constructeur de la classe CA.
     * Il initialise les variables 'voisins' et 'ecole' en tant que nouveau HashMap initialement.
     */
    public CA() {
        this.voisin = new HashMap<Ville, Set<Ville>>();
        this.ecole = new HashMap<String, Ville>();
    }

    /**
     * Permets de retourner un objet ville identifier par son nom de ville.
     *
     * @param nomVille
     *  Le nom de la ville à retourner.
     * @return villeTmp
     *  La ville correspondante.
     */
    Ville getVille (String nomVille) {
        Ville villeTmp = new Ville(nomVille);
        for (Ville ville : voisin.keySet()) {
            if (ville.getNom().equals(nomVille)) {
                villeTmp = (Ville) ville.clone();
            }
        }
        if (villeTmp == null) {
            System.out.println("La ville" + nomVille + "n'existe pas !");
            return null;
        }
        return villeTmp;
    }

    /**
     * Permets de retourner à partir d'une ville un ensemble de ses voisins.
     * @param ville
     *  Nom de la ville dans laquel il faut rechercher les voisins
     * @return  voisin.get(nom):
     */
    public Set<Ville> getVillesVoisinnes(Ville ville) {
        return voisin.get(ville);
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

    /**
     * Affiche les villes possédant une école.
     */
    public void afficheEcole() {
        System.out.println("Voici la liste des villes possédant une école :");
        for (String nomEcole : ecole.keySet()) {
            System.out.print(getVille(nomEcole));
        }
    }

    /**
     * Ajouter une ville si elle n'existe pas déja.
     * @param nomVille
     *  Nom de la ville a ajouté.
     * */
    public void ajouterVille(String nomVille) {
        Ville ville = getVille(nomVille);
        if (voisin.containsKey(new Ville(nomVille))) { /* Si la ville existe déja on s'arrête ici. */
            System.out.println("Cette ville existe déjà !");
        } else {
            /* On insère la relation de la nouvelle ville dans l'ensemble de voisins. */
            voisin.putIfAbsent(ville,new HashSet<>());
            /* On crée une école dans la nouvelle ville et on insère la relation de cette nouvelle école dans l'ensemble d'écoles. */
            ecole.putIfAbsent(ville.getNom(),ville);
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
    /**
     *
     *
     * */
    public boolean parcourtVoisin(String nom, String nom2) {
        if (!ecole.containsKey(nom)) {
            if (getVillesVoisinnes(getVille(nom)).size() != 1) {
                for (Ville voisin : getVillesVoisinnes(getVille(nom))) {
                    if (ecole.containsKey(voisin.getNom()) && !(voisin.getNom().equals(nom2))){
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

        System.out.println(ville);
        for (Ville voisin : getVillesVoisinnes(ville)) {
            System.out.println("On traite du sommet "+voisin);
            isPossible = parcourtVoisin(voisin.getNom(), ville.getNom());
            System.out.println(isPossible?"Se sera possible":"Se ne sera pas possible");
            if (!isPossible) break;
            }

        if (isPossible) {
            System.out.println("La suppression de "+nomEcole+"est un succsée !");
            ecole.remove(ville.getNom()); // Si le retrait de l'école ne viole pas la contrainte d'accessibilité.
        } else System.out.println("La suppression de " + nomEcole + " est impossible");
    }


    /**
     * Vérifie si une ville existe.
     * @param nomVille
     *  Nom de la ville à laquelle on cherche a vérifier l'existence.
     * @return
     *  true si la ville existe.
     *  false si la ville n'existe pas.
     * */
   public boolean villeExist(String nomVille) {
        Ville villeTmp = new Ville(nomVille);
       return voisin.containsKey(villeTmp);
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
                    /* System.out.println("On ajouter " + villeTmpA + "au dependance de "+villeTmpB );
                    ecole.get(villeTmpA).add(villeTmpB); // On ajoute dans les dependance a l'ecole
                    System.out.println("On ajouter " + villeTmpB + "au dependance de "+villeTmpA );
                    ecole.get(villeTmpB).add(villeTmpA); // On ajoute dans les dependance a l'ecole */
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
        Ville villeTMp = getVille(nomVille); /* On récupere la ville de 'nomVille'. */
        if (voisin.containsKey(villeTMp)) { /* Si la ville n'existe pas. */
            ecole.putIfAbsent(villeTMp.getNom(), villeTMp); /* On ajoute une école a la ville si cel ci n'en possède pas. */
            System.out.println("Une école vient d'être construite dans la ville "+villeTMp.getNom()+".");
            return true;
        }
        System.out.println("la ville n'existe pas");
        return false;
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