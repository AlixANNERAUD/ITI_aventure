package fr.insarouen.iti.prog.itiaventure.elements;

import javax.swing.text.StyledEditorKit.BoldAction;

import fr.insarouen.iti.prog.itiaventure.Monde;

public class Entite {
    
    private final Monde monde;
    private final String nom;

    public Entite(String nom){
        this.monde = new Monde();
        this.nom = nom;
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

    public boolean equals(Object o) {
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Entite e = (Entite)o;
        return this.nom == e.nom && this.monde == e.monde;
    }

    public int hashCode() {
        return 13 * this.nom.hashCode() + 17 * this.monde.hashCode();
    }
}