package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;

public abstract class Objet extends Entite {

    public Objet(String nom, Monde monde) {
        super(nom, monde);
    }

    public abstract boolean estDeplacable();
    

    public String toString() {
        return String.format("Objet: %s", this.getNom());
    }
}
