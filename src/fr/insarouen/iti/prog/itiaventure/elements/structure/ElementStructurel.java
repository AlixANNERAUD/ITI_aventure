package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;

public abstract class ElementStructurel extends Entite {

    public ElementStructurel(String nom, Monde monde) {
        super(nom, monde);
    }

    public String toString() {
        return String.format("ElementStructurel: %s", this.getNom());
    }
}
