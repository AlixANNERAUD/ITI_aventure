package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;

public class Vivant extends Entite {

    private int pointVie, pointForce;
    private Piece piece;
    private Objet[] objets;

    public Vivant(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) {
        super(nom, monde);
        this.pointVie = pointVie;
        this.pointForce = pointForce;
        this.piece = piece;
        this.objets = objets;
    }

    public void deposer(Objet objet) {
        this.deposer(objet.getNom());
    }

    public void deposer(String nomObjet) {
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

    public Objet[] getObjets() {
        return this.objets.clone();
    }

    public Piece getPieces() {
        return this.piece;
    }

    public int getPointVie() {
        return this.pointVie;
    }

    public int getPointForce() {
        return this.pointForce;
    }

    public Boolean possede(Objet objet) {
        return this.possede(objet.getNom());
    }

    public Boolean possede(String nomObjet) {
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet)) {
                return true;
            }
        }
        return false;
    }

    public void prendre(String nomObjet) {
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

    public void prendre(Objet objet) {
        this.prendre(objet.getNom());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Vivant: %s\n", this.getNom()));
        stringBuilder.append(String.format("\t- Point de vie : %s\n", this.pointVie));
        stringBuilder.append(String.format("\t- Point de force : %s\n", this.pointForce));
        stringBuilder.append(String.format("\t- Piece : %s\n", piece.toString()));
        for (int i = 0; i < this.objets.length; i++) {
            stringBuilder.append("\n\t- ");
            stringBuilder.append(this.objets[i].toString());
        }
        return stringBuilder.toString();
    }

}
