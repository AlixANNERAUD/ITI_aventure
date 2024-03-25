package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Serrure;

public class Porte extends ElementStructurel implements Activable {

    Etat etat;
    Piece pieceA, pieceB;
    Serrure serrure;

    /**
     * Constructeur de Porte
     * 
     * @param nom    Nom de la porte
     * @param monde  Monde dans lequel se trouve la porte
     * @param pieceA Piece A
     * @param pieceB Piece B
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        pieceA.addPorte(this);
        pieceB.addPorte(this);
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.serrure = null;
        this.etat = Etat.FERME;
    }

    /**
     * Constructeur de Porte
     * 
     * @param nom     Nom de la porte
     * @param monde   Monde dans lequel se trouve la porte
     * @param serrure Serrure de la porte
     * @param pieceA  Piece A
     * @param pieceB  Piece B
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB)
            throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        pieceA.addPorte(this);
        pieceB.addPorte(this);
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.serrure = serrure;
        this.etat = Etat.VERROUILLE;
    }

    /**
     * Activer la porte
     */
    @Override
    public void activer() throws ActivationImpossibleException {
        switch (this.getEtat()) {
            case Etat.FERME:
                this.etat = Etat.OUVERT;
                break;
            case Etat.OUVERT:
                this.etat = Etat.FERME;
                break;
            default:
                throw new ActivationImpossibleException("La porte ne peut pas être activée");
        }
    }

    /**
     * Activer la porte avec un objet
     */
    @Override
    public void activerAvec(Objet objet) throws ActivationException {
        switch (this.getEtat()) {
            case VERROUILLE:
                if (objet.getClass() == PiedDeBiche.class) {
                    this.etat = Etat.CASSE;
                    break;
                }
                this.serrure.activerAvec(objet);
                this.etat = Etat.OUVERT;
                break;
            case FERME:
                if (objet.getClass() == PiedDeBiche.class) {
                    this.etat = Etat.CASSE;
                    break; // Si ça ne break pas ici ça continue sur le suivant
                }
            case OUVERT:
                this.serrure.activerAvec(objet);
                this.etat = Etat.VERROUILLE;
                break;
            default:
                break;

        }
    }

    /**
     * Vérifie si la porte est activable avec un objet
     */
    @Override
    public boolean activableAvec(Objet objet) {
        return (objet.getClass() == PiedDeBiche.class)
                || (this.getSerrure() != null && (this.serrure.activableAvec(objet)));

    }

    /**
     * Vérifie si la porte est activable
     */
    public Etat getEtat() {
        return this.etat;
    }

    /**
     * Récupère la serrure de la porte
     * 
     * @return Serrure
     */
    public Serrure getSerrure() {
        return this.serrure;
    }

    /**
     * Récupère la porte de l'autre côté
     * 
     * @param piece
     * @return La porte de l'autre côté
     */
    public Piece getPieceAutreCote(Piece piece) {
        if (piece.equals(this.pieceA)) {
            return this.pieceB;
        } else if (piece.equals(this.pieceB)) {
            return this.pieceA;
        } else {
            return null;
        }
    }

    public String toString() {
        return String.format("Porte: %s - %s", this.getNom(), this.etat);
    }
}
