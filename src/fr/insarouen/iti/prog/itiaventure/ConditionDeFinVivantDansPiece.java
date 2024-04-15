package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantDansPiece extends ConditionDeFin {

    private final Vivant vivant;
    private final Piece piece;

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

    
}
