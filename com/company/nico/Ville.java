package com.company.nico;

/**
 * Ville est la classe représentant l'ensemble des variables et des méthodes nécessaires à la création et à la modification
 * d'une ville.
 */
public class Ville implements Cloneable {

    /* Le nom de la ville, unique pour chaque ville. */
    private final String nom;

    /**
     * Constructeur de la classe CA.
     * Il permet de construire et initialise une nouvelle ville.
     * @param nomVille
     *  Nom de la ville à construire.
     */
    public Ville(String nomVille) {
        this.nom = nomVille;
    }

    /**
     * Getter de la variable nom.
     * @return nom
     *  Le nom de la ville.
     */
    public String getNom() {
        return nom;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ville other = (Ville) obj;
        if (nom == null) {
            return other.nom == null;
        } else return nom.equals(other.nom);
    }

   @Override
    public Object clone(){
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e){
            throw new InternalError();
        }
    }

    /**
     *
     * Redéfinition de la classe toString.
     * @return
     *  Une chaine de caractère contenant les informations de la ville.
     */
    @Override
    public String toString() {
        return nom ;
    }
}
