package com.company.ghandi;

import java.util.*;
import java.util.concurrent.RecursiveAction;


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
            
            if(test(ville))
            { ecole.remove(ville.getEcole());/** Si les test de la méthode test renvoie vrai on supprime l'école */
              System.out.println("Vous avez supprimez l'école dans la ville " + ville.toString() + ".");
            }else {
                System.out.println(ville.getNom()+" n'a pas de voisin. Vous ne pouvrez pas supprimez l'école "+ville.getEcole()+" dans cette ville.");
            }
           
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

    public boolean villePossedeEcole(Ville2 ville){
        return ecole.containsKey(ville.getEcole());
    }

    public boolean test(Ville2 ville){
        boolean rep = true;
        if (villePossedeEcole(ville))
            if(!getVillesVoisinnes(ville).isEmpty())rep=supprimerSiVoisin(getVillesVoisinnes(ville));
            else rep=false;
        else {
            rep = false;
            System.out.println("La ville "+ ville.toString() + " ne possédent pas d'école.");
        }
        return rep;
    }
    private boolean supprimerSiVoisin(Set<Ville2> listVoisin){
        boolean rep = true;
        Set<Ville2> listeDesVillesProblematique= new HashSet<Ville2>();
        Map<Ville2,Boolean> testSiVpossedeE= new HashMap<Ville2,Boolean>();
        for (Ville2 ville : listVoisin) {
            testSiVpossedeE.putIfAbsent(ville, villePossedeEcole(ville));
        }
        for(Map.Entry<Ville2,Boolean> valeurTest: testSiVpossedeE.entrySet()){
            rep= rep && valeurTest.getValue();
            if(!rep){
                listeDesVillesProblematique.add(valeurTest.getKey());
            }
        }

        if(rep) return true;
        else {
            if(!listeDesVillesProblematique.isEmpty()) return false;
            else return false;
        }
    }


    
    public boolean iteration(Ville2 ville) {
        
        System.out.println("************|Vous voulais supprimer la ville "+ville+" |*************");
       
        boolean reponseFinal = true;
        Ville2 villeVoisin; 
        Ville2 villeVoisin2;
        Set<Ville2>clone= new HashSet<Ville2>();
        Set<Ville2>clone0= new HashSet<Ville2>();
        System.out.println("la ville "+ville.getNom()+" possède l'ecole (nom de l'école: |"+ville.getEcole()+"|) elle a pour voisin :");
        clone0= getVillesVoisinnes(ville);
        Iterator it = getVillesVoisinnes(ville).iterator(); /** liste des villes voisinnes de la ville à supprimer */
        while(it.hasNext()){ /** on parcourt la liste des voisins de la ville à supprimer  */
            villeVoisin=contientVille(it.next().toString()); /** correspond au voisin selectionner dans la liste */
            System.out.print("\t- La ville "+villeVoisin+" qui est liée à lui ("+ville+"-->"+villeVoisin+")");
            if(ecole.containsKey(villeVoisin.getEcole())) { /** Si la ville voisinne possède une ecole */
                clone.add(villeVoisin);
                System.out.println(" et possède l'ecole "+ villeVoisin.getEcole()+" dont la ville "+ville+" dépendra");
                
            }
            else{ /** Si la ville voisinne ne possède pas d'ecole */
                System.out.println(" dépend de lui ");
                
                Set<Ville2> clone2 = getVillesVoisinnes(villeVoisin);
                it = getVillesVoisinnes(villeVoisin).iterator(); /** Pour chacun des voisins de la ville voisinne  */
                
                System.out.println("*************************************************************************");
                System.out.println("parcourt des voisins "+villeVoisin+clone2.contains(villeVoisin)+villeVoisin+" et contient ville "+villeVoisin+clone2.contains(ville)+ville+
                                    " et la liste est (clone2) "+clone2.toString()+" plus le clone"+clone.toString()+
                                    " clone0 est"+clone0.toString()+" j'enleve J dans clone0");
                System.out.println("*************************************************************************");
                while(it.hasNext()){ /** on parcourt la liste des voisins de la ville voisinne */
                    villeVoisin2=contientVille(it.next().toString()); /** correspond au voisin selectionner dans la liste */
                    
                  if(ecole.containsValue(ville)&&ecole.containsKey(villeVoisin2.getEcole())){/** si la ville que l'on doit supprimer contient bien une ecole  */
                        System.out.println("la ville "+villeVoisin2+" est voisin de "+villeVoisin);
                        //**continue */
                        reponseFinal=false;
                    }
                    else{
                        
                        System.out.println(" et aucune de ses villes voisinnes en possède.");
                        reponseFinal=false;
                    }
                }   
            }
        }
        return reponseFinal;
    }
    public Ville2 contientVille(String s){
        int e=0;
       for (Ville2 v : voisin.keySet()) {
           if(v.getNom().equals(s)) return v;
           else e=v.getEcole()+1;
       }

       return new Ville2(s,e);
    }

    public void initRoute(){
        ajouterRoute(contientVille("A"), contientVille("B"));
        ajouterRoute(contientVille("A"), contientVille("D"));
        ajouterRoute(contientVille("B"), contientVille("C"));
        ajouterRoute(contientVille("B"), contientVille("H"));
        ajouterRoute(contientVille("C"), contientVille("I"));
        ajouterRoute(contientVille("C"), contientVille("D"));
        ajouterRoute(contientVille("D"), contientVille("E"));
        ajouterRoute(contientVille("E"), contientVille("F"));
        ajouterRoute(contientVille("E"), contientVille("G"));
        ajouterRoute(contientVille("H"), contientVille("K"));
        ajouterRoute(contientVille("H"), contientVille("J"));
        ajouterRoute(contientVille("H"), contientVille("I"));

        
    }

    public void initSuppression(){
        
        
        supprimerEcole(contientVille("G"));
        supprimerEcole(contientVille("F"));
        supprimerEcole(contientVille("J"));
        supprimerEcole(contientVille("K"));
        supprimerEcole(contientVille("H"));
        supprimerEcole(contientVille("A"));
        supprimerEcole(contientVille("I"));
        supprimerEcole(contientVille("C"));
        supprimerEcole(contientVille("B"));
        supprimerEcole(contientVille("D"));
        supprimerEcole(contientVille("E"));
        
    }

    public static Set<Ville2> parcourtEnprofondeur(CA graph, Ville2 ville) {
        Set<Ville2> visited = new LinkedHashSet<Ville2>();
        Stack<Ville2> stack = new Stack<Ville2>();
        stack.push(ville);
        while (!stack.isEmpty()) {
            Ville2 vertex = stack.pop();
            if (!visited.contains(vertex)) {
                  visited.add(vertex);
                for (Ville2 v : graph.getVillesVoisinnes(ville)) {
                    stack.push(v);
                    System.out.println(ville+ "-> "+graph.getVillesVoisinnes(v).toString());  
                      
                    
                }
                
            }
        }
        return visited;
    }


    
   

}
