package com.company.ghandi;


public class Ville2 {
    private String nom;
    private int nombreDeVoisin=0;
    public Ville2(String nom2){
        this.nom = nom2;
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
        Ville2 other = (Ville2) obj;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        return true;
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
