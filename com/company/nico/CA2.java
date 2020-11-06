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
    }

    public void afficheEcole() {
        System.out.println("Voici la liste des villes possédant une école :");
        for (Ville ville2 : ecole.keySet()) {
            System.out.print(ville2.getNom()+" \t");
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
            if (!ecole.containsKey(voisin))
                bool= false;
        }
        return bool;
    }
    public boolean voisinPossedeEcoleDifferente(Ville nom,Ville nom2){
        for (Ville ville:getVillesVoisinnes(nom)) {
            if (ecole.containsKey(nom) && ville!=nom2){
                return true;
            }
        }
        return false;
    }
    /**
     * Méthode SupprimerEcole permet de supprimer une ecole si celle-ci existe
     * @param nomVilleDeEcole: nom de la ville dans laquel supprimer l'école.
     */
    /*
     * On regarder si tous les voisins de la villes ont une école
     *   Si oui
     *       on supprime
     *   Sinon On regarde dans les voisins des voisins si il possede une ecole differente de celle qu'on veut supprimer
     * Si Oui
     *   on remove
     * Sinon
     *   Pas possible
     * */
    public void supprimerEcole(String nomVilleDeEcole) {
       Ville ville = getVille(nomVilleDeEcole); //Ville de l'école

        boolean b=false;
        if (tousVoisinsOntEcole(ville))
            b = true;


        for (Ville voisin:getVillesVoisinnes(ville)){
            if(voisinPossedeEcoleDifferente(voisin,ville))
                b=true;
            System.out.println(b);
        }
        System.out.println(b);
        if (b) {
            Set<Ville> eV1;
            for (Ville ko : getVillesVoisinnes(ville)) {
                eV1=ecole.get(ko);
                if (eV1!=null) {
                    System.out.println(ville +" va être supprimer de la liste de dependance de "+ko);
                    eV1.remove(ville);
                }
            }
            ecole.remove(ville);
        } else System.out.println("Pas possible 2");
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
            } else System.out.println("la ville voisinne entrer n'existe pas");

        } else System.out.println("la ville entrer n'existe pas");
        afficheEcole();
       return voisin.containsKey(villeTmpA)&&voisin.containsKey(villeTmpB);
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
            for (Ville ville:getVillesVoisinnes(v)) {
                ecole.get(ville).add(v); // On ajoute dans les dependance a l'ecole
            }
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
