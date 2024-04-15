package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFinConjonctionDeConditionDeFin {

    public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etat, Vivant vivant, Piece piece, Objet... objets) {
        super(etat, new ConditionDeFinVivantDansPiece(etat, vivant, piece), new ConditionDeFinVivantPossedeObjets(etat, vivant, objets));
    }
}
