package com.company.ghandi;

import java.util.*;


/**
 * La classe CA(communauté d'agglomération) représente un graphe
 * et donc possède des sommets représenter par des villes et 
 * des arètes représenter par des routes.  
 */
public class CA {
    
    private Map<Ville2,Set<Ville2>> voisin;
    private HashMap<Integer,Ville2>ecole;
    
    
    
    /**
     * Constructeur de La classe CA(communauté d'agglomération)
     * il initialise 2 associations d'objets dont:
     * - Objet 1 : composer d'une ville et d'une liste de ville sans doublon qui sont des objets du package
     * - Objet 2 : a pour clé un object ecole et pour valeur un objet ville2
     * 
     */
   
    
    public CA() {
        this.voisin = new HashMap<Ville2, Set<Ville2>>();
        this.ecole = new HashMap<Integer,Ville2>();
    }

    /**
     * La méthode ajouterVille permet d'ajouter une ville dans la collection de
     * données rappel une ville est équivalente au sommet d'un graphe
     * 
     * @param nom
     */
    public void ajouterVille(Ville2 v,int e ) {
        
        
        if (voisin.containsKey(v)){ //
            System.out.println("Cette ville existe déjà !");
        }
        voisin.putIfAbsent(v,new HashSet<>());
        ecole.putIfAbsent(e,v );
        
    }
    /**
     * La méthode supprimerVille permet de supprimer une ville de la collection
     * de données
     * @param nom
     */
    public void supprimerVille(Ville2 nom){
        
        
        voisin.values().forEach(e -> e.remove(nom));
        voisin.remove(nom);
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
    public boolean ajouterRoute(Ville2 nomVille,Ville2 nomVoisinne) {
        /* Variables locales */
        Ville2 villeTmpA= nomVille;
        Ville2 villeTmpB= nomVoisinne;

        if(voisin.containsKey(villeTmpA)){
            if(voisin.containsKey(villeTmpB)){
                if(!villeTmpA.equals(villeTmpB)){
                    voisin.get(villeTmpA).add(villeTmpB);
                    voisin.get(villeTmpB).add(villeTmpA);
                }
            }else System.out.println("la ville voisinne entrer n'existe pas");

        } else System.out.println("la ville entrer n'existe pas");
       return voisin.containsKey(villeTmpA)&&voisin.containsKey(villeTmpB);
    }

    /**
     * La méthode supprimer Route permet de supprimer une route (Arète)
     * entre 2 ville
     * @param nomVille
     * @param nomVoisinne
     */

    public void supprimerRoute(Ville2 nomVille,Ville2 nomVoisinne ){
        Ville2 v1= nomVille;
        Ville2 v2= nomVoisinne;
        Set<Ville2> eV1 = voisin.get(v1);
        Set<Ville2> eV2 = voisin.get(v2);
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
    public boolean ajouterEcole(Ville2 nomVille){
        Ville2 v = nomVille;
        if(voisin.containsKey(v)){
            ecole.putIfAbsent(v.getEcole(), v);
            return true;
        }
        else{
             System.out.println("la ville n'existe pas");
             return false;
        }
    }
    
    /**
     * Méthode SupprimerEcole permet de supprimer une ecole si celle-ci existe 
     * @param ville
     */
    public void supprimerEcole(Ville2 ville){
           
            
            if (ecole.containsValue(ville))   { /* Si l'école existe */
                
                if(!getVillesVoisinnes(ville).isEmpty()) {
                if(iteration(ville)){
                ecole.remove(ville.getEcole());
                System.out.println("Vous avez supprimez l'école dans la ville " + ville.toString() + ".");
                }
                }else{
                System.out.println(ville.getNom()+" n'a pas de voisin. Vous ne pouvrez pas supprimez l'école "+ville.getEcole()+" dans cette ville."); 
                }
             }
            else System.out.println("La ville "+ ville.toString() + " ne possédent pas d'école.");
    }

    /**
     * La méthode getVillesVoisinnes permet d'obtenir les villes 
     * voisinnes à une ville particulière
     * c'est-à-dire les sommets adjacents d'un sommet particulier.
     * @param nom
     * @return
     */
    public Set<Ville2> getVillesVoisinnes(Ville2 nom) {
        return voisin.get(nom);
 
    }

    public void afficheEcole() {
        System.out.println("Voici la liste des villes possédant une école :");
        System.out.println(String.valueOf(ecole.values()));
    }
    
    /**
     * méthode d'affichage
     */
    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        for(Ville2 v : voisin.keySet()){
            sb.append(v).append("->");
            sb.append(voisin.get(v)).append("\n");
        }
        sb.append("Ecole présent\n").append(ecole.values());
        return sb.toString();
    }



    /**
     * compteur 
     * compte le nombre de ville voision à une ville 
     * @param m
     */
    public void CompteVoisin(Ville2 m){
            m.setNombreDeVoisin(voisin.get(m).size());
            System.out.println(m.toString()+" = "+m.getNombreDeVoisin()); 
    }
    public HashMap<Integer, Ville2> getEcole() {
        return ecole;
    }

    /**
     * getter de CA
     * @return
     */

    public Map<Ville2, Set<Ville2>> getVoisin() {
        return voisin;
    }

   
    public boolean iteration(Ville2 ville) {
        Ville2 villeVoisin;
        System.out.println("la ville "+ville.getNom()+" possède l'ecole "+ville.getEcole()+" elle a pour voisin ");
        Iterator it = getVillesVoisinnes(ville).iterator();
        while(it.hasNext()){
            villeVoisin=contientVille(it.next().toString());
            System.out.print("La ville "+villeVoisin+" qui est liée à la ville "+ville);
            if(ecole.containsKey(villeVoisin.getEcole())) System.out.println(" et possède l'ecole "+ villeVoisin.getEcole()+" dont la ville "+ville+" dépendra");
            else{
                System.out.println(" ne possède pas d'école");
                it = getVillesVoisinnes(villeVoisin).iterator();
                while(it.hasNext()){
                    ville=contientVille(it.next().toString());
                    if(ecole.containsKey(ville.getEcole())){
                        System.out.println(". Mais un de ses voisins possède une école donc, il est liée à l'école("+ville.getEcole()+") de la ville "+ville);
                    }else{
                        System.out.println(" et aucune de ses villes voisionnes en possède.");
                        return false;
                    }
                }   
            }
        }
        return true;
    }
    public Ville2 contientVille(String s){
        int e=0;
       for (Ville2 v : voisin.keySet()) {
           if(v.getNom().equals(s)) return v;
           else e=v.getEcole()+1;
       }

       return new Ville2(s,e);
    }

    
   

}
