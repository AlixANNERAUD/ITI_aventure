package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;

/**
 * Classe générique pour les éléments structurels.
 */
public abstract class ElementStructurel extends Entite {

    /**
     * Constructeur ElementStructurel.
     * 
     * @param nom   Nom de l'élément structurel.
     * @param monde Monde dans lequel se trouve l'élément structurel.
     */
    public ElementStructurel(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    public String toString() {
        return String.format("ElementStructurel: %s", this.getNom());
    }
}
