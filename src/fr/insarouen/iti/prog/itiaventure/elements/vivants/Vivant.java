package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;

public class Vivant extends Entite {

    /**
     * Points de vie du vivant.
     */
    private int pointVie;

    /**
     * Points de force du vivant.
     */
    private int pointForce;

    /**
     * Pièce dans laquelle se trouve le vivant.
     */
    private Piece piece;

    /**
     * Tableau contenant les objets du vivant.
     */
    private Objet[] objets;

    /**
     * Constructeur Vivant.
     * 
     * @param nom        Nom du vivant.
     * @param monde      Monde dans lequel se trouve le vivant.
     * @param pointVie   Points de vie du vivant.
     * @param pointForce Points de force du vivant.
     * @param piece      Pièce dans laquelle se trouve le vivant.
     * @param objets     Objets du vivant.
     */
    public Vivant(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets)
            throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.pointVie = pointVie;
        this.pointForce = pointForce;
        this.piece = piece;
        this.objets = objets;
    }

    /**
     * Depose un objet dans le vivant à partir d'un objet
     * 
     * @param objet
     */
    public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException {
        this.deposer(objet.getNom());
    }

    /**
     * Depose un objet dans le vivant à partir du nom de l'objet
     * 
     * @param nomObjet
     */
    public void deposer(String nomObjet) throws ObjetNonPossedeParLeVivantException {

        if (!this.possede(nomObjet)) {
            throw new ObjetNonPossedeParLeVivantException(
                    String.format("Le vivant %s ne possède pas l'objet %s pour le déposer dans la pièce.",
                            this.getNom(), nomObjet));
        }
        Objet[] objets = new Objet[this.objets.length - 1];
        boolean trouve = false;

        int j = 0;
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet) && !trouve) {
                this.piece.deposer(objets[i]);
            } else {
                objets[j] = this.objets[i];
                j++;
            }
        }

        if (trouve) {
            this.objets = objets;
        }
    }

    /**
     * Permet de donner tous les objets appartenant à un vivant.
     * 
     * @return Objet[], un tableau contenant les objets du vivants
     */
    public Objet[] getObjets() {
        return this.objets.clone();
    }

    /**
     * Renvoi donne la piece dans laquelle se trouve le vivant
     * 
     * @return Piece
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Renvoi les points de vie du vivant.
     * 
     * @return int
     */
    public int getPointVie() {
        return this.pointVie;
    }

    /**
     * Renvoi les points de force du vivant
     * 
     * @return int
     */
    public int getPointForce() {
        return this.pointForce;
    }

    /**
     * Indique si l'objet est contenu dans le vivant à partir de l'objet.
     * 
     * @param objet
     * @return Boolean
     */
    public Boolean possede(Objet objet) {
        return this.possede(objet.getNom());
    }

    /**
     * Indique si l'objet est contenu dans le vivant à partir du nom de l'objet
     * 
     * @param nomObjet
     * @return Boolean
     */
    public Boolean possede(String nomObjet) {
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet de prendre un objet d'une piece et l'assimilé au vivant à partir de
     * son nom.
     * 
     * @param nomObjet
     */
    public void prendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        if (this.piece.contientObjet(nomObjet)) {
            return;
        }

        Objet objet = this.piece.retirer(nomObjet);

        Objet[] objets = new Objet[this.objets.length + 1];

        for (int i = 0; i < this.objets.length; i++) {
            objets[i] = this.objets[i];
        }

        objets[this.objets.length] = objet;

        this.objets = objets;
    }

    /**
     * Permet de prendre un objet d'une piece et l'assimilé au vivant à partir de
     * l'objet.
     * 
     * @param objet
     */
    public void prendre(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        this.prendre(objet.getNom());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Vivant: %s\n", this.getNom()));
        stringBuilder.append(String.format("\t- Point de vie : %s\n", this.pointVie));
        stringBuilder.append(String.format("\t- Point de force : %s\n", this.pointForce));
        for (int i = 0; i < this.objets.length; i++) {
            stringBuilder.append("\n\t- ");
            stringBuilder.append(this.objets[i].toString());
        }
        return stringBuilder.toString();
    }

}
