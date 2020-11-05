package com.company.nico;


import java.util.*;


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


    public void afficheEcole() {
        System.out.println("Voici la liste des villes possédant une école :");
        for (Ville ville2 : ecole.keySet()) {
            System.out.print(ville2.getNom()+" \t");
        }

        System.out.println("liste de voisin :");
        System.out.println();
        for (Ville v: voisin.keySet()) {
            System.out.print(v);
        }

        System.out.println();
        for (Set<Ville> v: voisin.values()) {
            System.out.print(v);
        }
    }

    Ville getVille (String name) {
        Ville ville2 = new Ville(name);
        for (Ville ville : voisin.keySet()) {
            if (ville.getNom().equals(name)) {
                ville2 = (Ville) ville.clone();
            }
        }
        if (ville2==null)
            System.out.println("La ville"+name+"n'existe pas !");
        return ville2;
    }

    /**
     * La méthode getVillesVoisinnes permet d'obtenir les villes
     * voisinnes à une ville particulière
     * c'est-à-dire les sommets adjacents d'un sommet particulier.
     * @param nom: Nom de la ville dans laquel il faut rechercher les voisins
     * @return  voisin.get(nom):
     */
    public Set<Ville> getVillesVoisinnes(Ville nom) { return voisin.get(nom); }

    public Set<Ville> getEcolesDependante(Ville nom) { return voisin.get(nom);}

    /**
     * Méthode SupprimerEcole permet de supprimer une ecole si celle-ci existe
     * @param nomVilleDeEcole: nom de la ville dans laquel supprimer l'école.
     */
    public void supprimerEcole(String nomVilleDeEcole) {
       Ville ville2 = getVille(nomVilleDeEcole);
       if (ecole.containsKey(ville2)) { /* Si l'école existe dans la ville */
            if (!getVillesVoisinnes(ville2).isEmpty()) { /* Si la ville a un voisin qui depend de son école */
                if (ecole.get(ville2).isEmpty()) {
                    System.out.println("On peut la supprimer car aucune autre ville ne depend de l'ecole");
                    ecole.remove(ville2); /* On la supprime */
                } else {
                    for (Ville v: getEcolesDependante(ville2)) {
                        System.out.println("boucle");
                        if (ecole.get(v)==null && ecole.get(v).size() == 1) {
                            System.out.println("Pas possible car ");
                            break;
                        } else {
                            System.out.println("Possible");
                            for (Ville ville: ecole.get(v))
                            ecole.remove(ville2);
                        }
                    }
                }
            } else System.out.println("pas possible car pas de voisins");
        } else System.out.println("pas possible");
    }
    /**
     * La méthode ajouterVille permet d'ajouter une ville dans la collection de
     * données rappel une ville est équivalente au sommet d'un graphe
     * 
     * @param nom
     */


    public void ajouterVille(String nom) {
        Ville v = getVille(nom);
         
        if (voisin.containsKey(new Ville(nom))){ //
            System.out.println("Cette ville existe déjà !");
        }
        voisin.putIfAbsent(v,new HashSet<>());
        ecole.putIfAbsent(v, new HashSet<>());
    }


    /**
     * La méthode supprimerVille permet de supprimer une ville de la collection
     * de données
     * @param nom
     */
    public void supprimerVille(String nom){
        Ville v = new Ville(nom);
        
        voisin.values().forEach(e -> e.remove(v));
        voisin.remove(new Ville(nom));
    }
    /**
     * La méthode ajouterRoute permet d'ajouter une route (Arète)
     * entre 2 ville. 
     * Cette méthode crée une nouvelle arête et met à jour la carte des
     * sommets adjacents. 
     * 
     * @param nomVille
     * @param nomVoisinne
     */
    public boolean ajouterRoute(String nomVille,String nomVoisinne) {
        /* Variables locales */
        Ville villeTmpA = new Ville(nomVille);
        Ville villeTmpB = new Ville(nomVoisinne);

        if(voisin.containsKey(villeTmpA)){
            if(voisin.containsKey(villeTmpB)){
                if(!villeTmpA.equals(villeTmpB)){
                    voisin.get(villeTmpA).add(villeTmpB);
                    voisin.get(villeTmpB).add(villeTmpA);
                    ecole.get(villeTmpA).add(villeTmpB);
                    ecole.get(villeTmpB).add(villeTmpB);
                }
            } else System.out.println("la ville voisinne entrer n'existe pas");

        } else System.out.println("la ville entrer n'existe pas");
       return voisin.containsKey(villeTmpA)&&voisin.containsKey(villeTmpB);
    }

    /**
     * La méthode supprimer Route permet de supprimer une route (Arète)
     * entre 2 ville
     * @param nomVille
     * @param nomVoisinne
     */

    public void supprimerRoute(String nomVille,String nomVoisinne ){
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
    
 
    /**
     * Méthode AjouterEcole permet d'ajouter une et une seule école à une ville
     * si la ville existe 
     * @param nomVille
     * @return
     */
    public boolean ajouterEcole(String nomVille){
        Ville v = getVille(nomVille);
        if(voisin.containsKey(v)) {
            ecole.putIfAbsent(v, getVillesVoisinnes(v));
            return true;
        }
        else{
             System.out.println("la ville n'existe pas");
             return false;
        }
    }
    /**
     * méthode d'affichage
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

    /**
     * compteur 
     * compte le nombre de ville voision à une ville 
     * @param m
     */
    public void CompteVoisin(Ville m){
            m.setNombreDeVoisin(voisin.get(m).size());
            System.out.println(m.toString()+" = "+m.getNombreDeVoisin()); 
    }

    /**
     * getter de CA
     * @return
     */

    public Map<Ville, Set<Ville>> getVoisin() {
        return voisin;
    }

}
