package com.company.nico;


public class Ville implements Cloneable {

    private final String nom;
    private int nombreDeVoisin=0;

    public Ville(String nom2) {
        this.nom = nom2;
    }

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
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        return true;
    }

    // Méthodes
    public Object clone(){
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e){
            throw new InternalError();
        }
    }


    @Override
    public String toString() {
        return nom ;
    }

    public int getNombreDeVoisin() {
        return nombreDeVoisin;
    }

    public void setNombreDeVoisin(int nombreDeVoisin) {
        this.nombreDeVoisin = nombreDeVoisin;
    }
    
    
    

    
}
