package com.company;

public class Ecole {
 private String nom;
 private Ville2 v;



    public Ecole(Ville2 v) {
        this.v = v;
        this.nom = v.toString();
    }

	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

	public Object add(Ville2 v) {
		return null;
    }

    @Override
    public String toString() {
        return  nom ;
    }

    public Ville2 getV() {
        return v;
    }

    public void setV(Ville2 v) {
        this.v = v;
    }
    
    
}
