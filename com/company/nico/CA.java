package com.company.nico;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * CA est la classe contenant l'ensemble des variables et des méthodes nécessaires à la création et à la modification
 * d'une communauté d'agglomérations.
 */
public class CA {

    /* Association représentant une ville comme clé et sa liste d'adjacence comme valeur. */
    private final Map<Ville, Set<Ville>> voisin;
    /* Association représentant le nom d'une ville comme clé la ville dans laquelle elle se situe comme valeur. */
    private final Map<String, Ville> ecole;

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
     * @param nomVille Le nom de la ville à retourner.
     * @return villeTmp
     * La ville correspondante.
     */
    Ville getVille(String nomVille) {
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
     *
     * @param ville Nom de la ville dans laquel il faut rechercher les voisins
     * @return voisin.get(nom):
     */
    public Set<Ville> getVillesVoisinnes(Ville ville) {
        return voisin.get(ville);
    }


    /**
     * Permets d'initialiser automatiquement le graphe proposé dans le sujet.
     * */
    public void initRoute() {
        ajouterRoute("F", "E");
        ajouterRoute("E", "G");
        ajouterRoute("E", "D");
        ajouterRoute("D", "A");
        ajouterRoute("D", "C");
        ajouterRoute("A", "B");
        ajouterRoute("B", "C");
        ajouterRoute("C", "I");
        ajouterRoute("B", "H");
        ajouterRoute("H", "I");
        ajouterRoute("H", "J");
        ajouterRoute("H", "K");
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
            System.out.print(getVille(nomEcole)+"\t");
        }
        System.out.println("\n");
    }


    /**
     * Ajouter une ville si elle n'existe pas déja.
     *
     * @param nomVille Nom de la ville a ajouté.
     */
    public void ajouterVille(String nomVille) {
        Ville ville = getVille(nomVille);
        if (voisin.containsKey(new Ville(nomVille))) { /* Si la ville existe déja on s'arrête ici. */
            System.out.println("Cette ville existe déjà !");
        } else {
            /* On insère la relation de la nouvelle ville dans l'ensemble de voisins. */
            voisin.putIfAbsent(ville, new HashSet<>());
            /* On crée une école dans la nouvelle ville et on insère la relation de cette nouvelle école dans l'ensemble d'écoles. */
            
        }
    }
    public void ajouterVille(String nomVille,boolean avecEcole){
        Ville ville = getVille(nomVille);

        if (voisin.containsKey(new Ville(nomVille))) { /* Si la ville existe déja on s'arrête ici. */
            System.out.println("Cette ville existe déjà !");
        } else {
            /* On insère la relation de la nouvelle ville dans l'ensemble de voisins. */
            voisin.putIfAbsent(ville, new HashSet<>());
            /* On crée une école dans la nouvelle ville et on insère la relation de cette nouvelle école dans l'ensemble d'écoles. */
            if(avecEcole) ecole.putIfAbsent(ville.getNom(), ville);
        }
    }

    public void afficheRoute(){
        StringBuilder sb = new StringBuilder();
        for (Ville v : voisin.keySet()) {
            sb.append(v).append("->");
            sb.append(voisin.get(v)).append("\n");
        }
        System.out.println(sb.toString());
    }


    /**
     * À partir d'un sommet de référence et d'un sommet voisin du sommet de référence on vérifie si ce sommet voisin
     * peut dépendre d'une autre école différente de l'école du sommet de référence.
     *
     * @param sommetVoisin
     *  Nom du sommet voisin au sommet de référence à parcourir.
     * @param sommetRef
     *  Nom du sommet de référence.
     * @return
     *  true : si le sommet de référence peut dépendre d'une autre école.
     * @return
     * false : si le sommet de référence ne peut dépendre d'une autre école.
     */
    public boolean parcourtSommet(String sommetVoisin, String sommetRef) {

        if (!ecole.containsKey(sommetVoisin)) {
            if (getVillesVoisinnes(getVille(sommetVoisin)).size() != 1) {
                for (Ville voisin : getVillesVoisinnes(getVille(sommetVoisin))) {
                    if (ecole.containsKey(voisin.getNom()) && !(voisin.getNom().equals(sommetRef))) {
                        return true;
                    }
                }
                return false;
            } else return false;
        } else {
            return true;
        }
    }


    /**
     * Supprime une école si cela est possible.
     *
     * @param nomEcole Le nom de l'école a supprimé.
     */
    public void supprimerEcole(String nomEcole) {
        Ville ville = getVille(nomEcole);
        boolean isPossible = false;
        boolean dependance = false;

        /* Pour chaque ville voisine de la ville qui possède l'école que l'on veut supprimer. */
        for (Ville voisin : getVillesVoisinnes(getVille(nomEcole))) {
            /* On regarde si son voisin possède une école. */
            if (dependance(voisin.getNom())) {
                dependance = true;
                //break;
            }
        }
        /* Si tous les voisins possédent une école.  */
        if (!dependance) {
            //System.out.println("Erreur - La ville n'est relié a aucune autre école.");
            System.out.println("La suppression de " + nomEcole + " est impossible.");
            return;
        }

        /* Pour chaque ville voisine de la ville qui possède l'école que l'on veut supprimer. */
        for (Ville voisin : getVillesVoisinnes(ville)) {
            /* On regarde en parcourant les villes voisines du voisin si il a la possibilité de dépendre d'une autre école. */
            isPossible = parcourtSommet(voisin.getNom(), ville.getNom());
            if (!isPossible) break;
        }

        if (isPossible) {
            System.out.println("La suppression de " + nomEcole + " est un succès !");
            ecole.remove(ville.getNom()); // Si le retrait de l'école ne viole pas la contrainte d'accessibilité.
        } else System.out.println("La suppression de " + nomEcole + " est impossible.");
    }


    /**
     * Test si la ville donnée en argument possède une école.
     *
     * @param nomVille Nom de la ville tester.
     * @return true : si la ville possède une école.
     * false : si la ville ne possède pas d'école.
     */
    public boolean dependance(String nomVille) {
        boolean y = false;
        if (ecole.containsKey(nomVille)) {
            y = true;
            return y;
        } else {
            System.out.println("Erreur - impossible de supprimer la ville , car la ville "+nomVille+" ne dépendra plus d'aucune école.");
            return y;
        }
    }
    public boolean villePossedeEcole(String nomVille){
        if(ecole.containsValue(getVille(nomVille))){
            Automatique.affiche("Cette ville possède une école");
            return true;
        }
        Automatique.affiche("Cette ville ne possède pas d'école");
        return false;
    }


    /**
     * Vérifie si une ville existe.
     *
     * @param nomVille Nom de la ville à laquelle on cherche a vérifier l'existence.
     * @return true si la ville existe.
     * false si la ville n'existe pas.
     */
    public boolean villeExist(String nomVille) {
        Ville villeTmp = new Ville(nomVille);
        return voisin.containsKey(villeTmp);
    }


    /**
     * Ajoute une route entre deux villes et met à jour leurs liste de voisins.
     *
     * @param nomVilleA Nom de la ville A a relier avec B.
     * @param nomVilleB Nom de la ville B a relier avec A
     */
    public boolean ajouterRoute(String nomVilleA, String nomVilleB) {
        /* Variables locales */
        Ville villeTmpA = new Ville(nomVilleA);
        Ville villeTmpB = new Ville(nomVilleB);

        if (voisin.containsKey(villeTmpA)) { // Si la ville A existe
            if (voisin.containsKey(villeTmpB)) { // Si la ville B existe
                if (!villeTmpA.equals(villeTmpB)) { // Si les deux ville ne sont pas les mêmes
                    voisin.get(villeTmpA).add(villeTmpB); // On ajoute la ville A comme voisine de B
                    voisin.get(villeTmpB).add(villeTmpA); // On ajoute la ville B comme voisine de A
                }
            } else System.out.println("la ville de départ n'existe pas");
        } else System.out.println("la ville d'arriver n'existe pas");
        return voisin.containsKey(villeTmpA) && voisin.containsKey(villeTmpB);
    }


    /**
     * Méthode AjouterEcole permet d'ajouter une et une seule école à une ville
     * si la ville existe
     *
     * @param nomVille Nom de la ville dans laquelle ajouter l'école.
     * @return Un booléen indiquant si la ville a bien était ajouter.
     */
    public boolean ajouterEcole(String nomVille) {
        Ville villeTMp = getVille(nomVille); /* On récupere la ville de 'nomVille'. */
        if (voisin.containsKey(villeTMp)) { /* Si la ville n'existe pas. */
            ecole.putIfAbsent(villeTMp.getNom(), villeTMp); /* On ajoute une école a la ville si cel ci n'en possède pas. */
            System.out.println("Une école vient d'être construite dans la ville " + villeTMp.getNom() + ".");
            return true;
        }
        System.out.println("la ville n'existe pas");
        return false;
    }

    /**
     * Permet de connaître le nombre d'école
     * @return nombre d'école
     */
    public int score(){

        return ecole.size();
    }

    public Ville getRandomVille(){
        Random rnd = new Random();
        char c = (char)('A'+rnd.nextInt(voisin.keySet().size()));
        return getVille(Character.toString(c));
    }
    public int nombreVille(){
        return voisin.keySet().size();
    }
    


    /**
     * Redéfinition de la classe toString.
     *
     * @return Une chaine de caractère contenant les informations de la communauter d'aglomeration
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Ville v : voisin.keySet()) {
            sb.append(v).append("->");
            sb.append(voisin.get(v)).append("\n");
        
        }
        sb.append("Ecole présent\n").append(ecole.keySet());
        return sb.toString();
    }

    public Map<Ville, Set<Ville>> getVoisin() {
        return voisin;
    }
    


}
