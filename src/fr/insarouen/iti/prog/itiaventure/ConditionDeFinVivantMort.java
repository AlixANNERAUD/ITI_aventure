package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantMort extends ConditionDeFin {

    private final Vivant vivant;

    /**
     * Constructeur ConditionDeFinVivantMort
     * 
     * @param etat   État du jeu lorsque la condition est vérifiée
     * @param vivant vivant a verifier
     */
    public ConditionDeFinVivantMort(EtatDuJeu etat, Vivant vivant) {
        super(etat);
        this.vivant = vivant;
    }

    @Override
    public EtatDuJeu verifierEtatDuJeu() {
        if (this.vivant.estMort()) {
            return this.getEtatConditionVerifiee();
        }
        return EtatDuJeu.ENCOURS;
    }

    @Override
    public String toString() {
        return String.format("Vivant mort: %s", this.vivant.getNom());
    }
}
