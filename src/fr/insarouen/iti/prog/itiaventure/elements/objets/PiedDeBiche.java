package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Classe représentant un pied de biche.
 */
public class PiedDeBiche extends Objet {

    /**
     * Constructeur PiedDeBiche.
     * 
     * @param monde Monde dans lequel se trouve le pied de biche.
     */
    public PiedDeBiche(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super("Pied de biche", monde);
    }

    @Override
    public boolean estDeplacable() {
        return true;
    }

    public String toString() {
        return String.format("PiedDeBiche: %s", this.getNom());
    }
}
