package fr.insarouen.iti.prog.itiaventure.elements;

import fr.insarouen.iti.prog.itiaventure.Monde;

public class Entite {
    
    private final Monde monde;
    private final String nom;

    public Entite(){

    }

    public String getNom(){
        return this.nom;
    }

    public Monde getMonde(){
        return this.monde;
    }

    public String toString() {
        return String.format("Entite : %s \n %s", nom, monde.toString());
    }


}