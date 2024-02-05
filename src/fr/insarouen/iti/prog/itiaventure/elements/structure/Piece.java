package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;

public class Piece extends ElementStructurel {

    private Objet[] objets;

    public Piece(String nom, Monde monde) {
        super(nom, monde);
        this.objets = new Objet[0];
    }

    public boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    public boolean contientObjet(String nom) {
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nom)) {
                return true;
            }
        }
        return false;

    }

    public void deposer(Objet objet) {
        Objet[] objets = new Objet[this.objets.length + 1];

        for (int i = 0; i < this.objets.length; i++) {
            objets[i] = this.objets[i];
        }

        objets[this.objets.length] = objet;

        this.objets = objets;
    }

    public Objet retirer(Objet objet) {
        return this.retirer(objet.getNom());
    }

    public Objet retirer(String nomObjet) {
        Objet[] objets = new Objet[this.objets.length - 1];
        Objet objet = null;

        int j = 0;
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet) && objet == null) {
                objet = this.objets[i];
            } else {
                objets[j] = this.objets[i];
                j++;
            }
        }

        this.objets = objets;
        return objet;

    }

    public Objet[] getObjets() {
        return this.objets.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Piece: %s", this.getNom()));
        for (int i = 0; i < this.objets.length; i++) {
            stringBuilder.append("\n\t-> ");
            stringBuilder.append(this.objets[i].toString());
        }
        return stringBuilder.toString();
    }

}
