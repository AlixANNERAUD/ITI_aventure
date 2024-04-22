package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantDansPiece extends ConditionDeFin {

    /**
     * Vivant à vérifier
     */
    private final Vivant vivant;

    /**
     * Pièce à vérifier
     */
    private final Piece piece;

    /**
     * Constructeur ConditionDeFinVivantDansPiece
     * 
     * @param etat   État du jeu lorsque la condition est vérifiée
     * @param vivant vivant a verifier
     * @param piece  piece a verifier
     */
    public ConditionDeFinVivantDansPiece(EtatDuJeu etat, Vivant vivant, Piece piece) {
        super(etat);
        this.vivant = vivant;
        this.piece = piece;
    }

    @Override
    public EtatDuJeu verifierEtatDuJeu() {
        if (this.vivant.getPiece().equals(this.piece)) {
            return this.getEtatConditionVerifiee();
        }
        return EtatDuJeu.ENCOURS;
    }

    @Override
    public String toString() {
        return String.format("Vivant dans piece: %s", this.piece);
    }
}
