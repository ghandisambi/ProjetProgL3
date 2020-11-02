package com.company.nico;

import java.util.ArrayList;

/**
 * Classe Ville : 
 * il se comporte comme un noeud
 *  @author  Nico 
 * @version 1.0
 * 
 */

public class Ville {
    private char name ;
    private ArrayList<Ville> villeVoisines;

    public Ville(char name, ArrayList<Ville> villeVoisines) {
        this.name = name;
        this.villeVoisines = villeVoisines;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public ArrayList<Ville> getVilleVoisines() {
        return villeVoisines;
    }

    public void setVilleVoisines(ArrayList<Ville> villeVoisines) {
        this.villeVoisines = villeVoisines;
    }

    public void addVilleVoisine(Ville ville){
        this.villeVoisines.add(ville);
    }

}
