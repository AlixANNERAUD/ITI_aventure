package fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;

public final class Clef extends Objet {
    
    /**
     * Constructeur Clef
     * @param nom 
     * @param monde
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    protected Clef(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    @Override
    public boolean estDeplacable() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("Clef: %s", this.getNom());
    }
}
