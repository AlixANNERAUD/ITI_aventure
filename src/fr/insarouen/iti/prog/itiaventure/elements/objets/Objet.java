package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;

/**
 * Classe générique pour les objets.
 */
public abstract class Objet extends Entite {

    /**
     * Constructeur Objet.
     * @param nom Nom de l'objet.
     * @param monde Monde dans lequel se trouve l'objet.
     */
    public Objet(String nom, Monde monde) {
        super(nom, monde);
    }

    /**
     * Indique si l'objet est déplaçable.
     * @return true si l'objet est déplaçable, false sinon.
     */
    public abstract boolean estDeplacable();
    
    public String toString() {
        return String.format("Objet: %s", this.getNom());
    }
}
