package com.company.ghandi;



import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;


/**
 * La classe CA(communauté d'agglomération) représente un graphe
 * et donc possède des sommets représenter par des villes et 
 * des arètes représenter par des routes.  
 */
public class CA {
    
    private Map<Ville2,Set<Ville2>> voisin;
    private HashMap<String,Ville2>ecole;
    
    
    
    /**
     * Constructeur de La classe CA(communauté d'agglomération)
     * il initialise 2 associations d'objets dont:
     * - Objet 1 : composer d'une ville et d'une liste de ville sans doublon qui sont des objets du package
     * - Objet 2 : a pour clé un object ecole et pour valeur un objet ville2
     * 
     */
   
    
    public CA() {
        this.voisin = new HashMap<Ville2, Set<Ville2>>();
        this.ecole = new HashMap<String,Ville2>();
        
    }

    /**
     * La méthode ajouterVille permet d'ajouter une ville dans la collection de
     * données rappel une ville est équivalente au sommet d'un graphe
     * 
     * @param nom
     */
    public void ajouterVille(String nom){
        Ville2 v = new Ville2(nom);
         
        if (voisin.containsKey(new Ville2(nom))){
            System.out.println("Cette ville existe déjà !");
        }
        
        voisin.putIfAbsent(v,new HashSet<>());
        ecole.putIfAbsent(nom, v);
        
    }
    /**
     * La méthode supprimerVille permet de supprimer une ville de la collection
     * de données
     * @param nom
     */
    public void supprimerVille(String nom){
        Ville2 v = new Ville2(nom);
        
        voisin.values().forEach(e -> e.remove(v));
        voisin.remove(new Ville2(nom));
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
    public boolean ajouterRoute(String nomVille,String nomVoisinne){
        Ville2 v1= new Ville2(nomVille);
        Ville2 v2= new Ville2(nomVoisinne);
        if(voisin.containsKey(v1)){
            if(voisin.containsKey(v2)){
                if(!v1.equals(v2)){
                
                    voisin.get(v1).add(v2);
                    voisin.get(v2).add(v1);
                }
                
            }else System.out.println("la ville voisinne entrer n'existe pas");

        }else System.out.println("la ville entrer n'existe pas");
       
       return voisin.containsKey(v1)&&voisin.containsKey(v2);
    }
    /**
     * La méthode supprimerRoute permet de supprimer une route (Arète)
     * entre 2 ville
     * @param nomVille
     * @param nomVoisinne
     */

    public void supprimerRoute(String nomVille,String nomVoisinne ){
        Ville2 v1= new Ville2(nomVille);
        Ville2 v2= new Ville2(nomVoisinne);
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
    public boolean ajouterEcole(String nomVille){
        Ville2 v = new Ville2(nomVille);
        
        
        if(voisin.containsKey(v)){
            ecole.putIfAbsent(nomVille, v);
            return true;
        }
        else{
             System.out.println("la ville n'existe pas");
             return false;
        }
        
    }
    
    /**
     * Méthode SupprimerEcole permet de supprimer une ecole si celle-ci existe 
     * @param nomVille
     */
    public void supprimerEcole(String e){
            if(ecole.containsKey(e)){
                System.out.println("oui");
                ecole.remove(e);
                System.out.println("suppression effectuer");
                System.out.println(ecole.keySet());
            }
            else System.out.println("non");
            
            
       
    }
    /**
     * La méthode getVillesVoisinnes permet d'obtenir les villes 
     * voisinnes à une ville particulière
     * c'est-à-dire les sommets adjacents d'un sommet particulier.
     * @param nom
     * @return
     */
    public Set<Ville2> getVillesVoisinnes(String nom) {
        return voisin.get(new Ville2(nom));
 
    }
    
    /**
     * méthode d'affichage
     */
    @Override
    public String toString() {
        StringBuffer sb =new StringBuffer();
        for(Ville2 v : voisin.keySet()){
            sb.append(v+"->");
            sb.append(voisin.get(v)+"\n");
        }
        sb.append("Ecole présent\n"+ecole.keySet());
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

    /**
     * getter de CA
     * @return
     */

    public Map<Ville2, Set<Ville2>> getVoisin() {
        return voisin;
    }
   



    

   
    
    
}
