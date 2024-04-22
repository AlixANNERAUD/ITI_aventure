package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFinConjonctionDeConditionDeFin {

    /**
     * Constructeur ConditionDeFinVivantDansPieceEtPossedeObjets.
     * 
     * @param etat   Etat du jeu lorsque la condition est vérifiée
     * @param vivant Vivant à vérifier
     * @param piece  Pièce à vérifier
     * @param objets Objets à vérifier
     */
    public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etat, Vivant vivant, Piece piece, Objet... objets) {
        super(etat, new ConditionDeFinVivantDansPiece(etat, vivant, piece),
                new ConditionDeFinVivantPossedeObjets(etat, vivant, objets));
    }

    @Override
    public String toString() {
        return String.format("Vivant dans piece et possede objets: %s", super.toString());
    }
}
