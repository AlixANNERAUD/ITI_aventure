package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;

public class Objet extends Entite {

    public Objet(String nom, Monde monde) {
        super(nom, monde);
    }

    public boolean estDeplacable() {
        return false;
    }

    public String toString() {
        return String.format("Objet: %s\n%s", this.getNom(), this.getMonde().toString());
    }
}
