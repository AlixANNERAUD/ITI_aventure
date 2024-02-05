package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class Piece extends ElementStructurel {

    private Objet[] objets = new Objet[0];
    private Vivant[] vivants = new Vivant[0];

    public Piece(String nom, Monde monde) {
        super(nom, monde);
    }

    public boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    public boolean contientObjet(String nomObjet) {
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet)) {
                return true;
            }
        }
        return false;

    }

    public boolean contientVivant(String nomVivant) {
        for (int i = 0; i < this.vivants.length; i++) {
            if (this.vivants[i].getNom().equals(nomVivant)) {
                return true;
            }
        }
        return false;

    }

    public boolean contientVivant(Vivant vivant) {
        return this.contientVivant(vivant.getNom());
    }

    public void deposer(Objet objet) {
        Objet[] objets = new Objet[this.objets.length + 1];

        for (int i = 0; i < this.objets.length; i++) {
            objets[i] = this.objets[i];
        }

        objets[this.objets.length] = objet;

        this.objets = objets;
    }

    public void entrer(Vivant vivant) {
        Vivant[] vivants = new Vivant[this.vivants.length + 1];

        for (int i = 0; i < this.vivants.length; i++) {
            vivants[i] = this.vivants[i];
        }

        vivants[this.vivants.length] = vivant;

        this.vivants = vivants;
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

        if (objet != null) {
            this.objets = objets;
        }
        return objet;

    }

    
    public Objet[] getObjets() {
        return this.objets.clone();
    }

    /**
     * Cette fonction retourne le tableau contenant les vivants de la pièce.
     * @return
     */
    public Vivant[] getVivants() {
        return this.vivants.clone();
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

    /**
     * Cette méthode sort un vivant de la pièce.
     * @param vivant Vivant à sortir
     * @return Le vivant qui à été sorti, null si non trouvé.
     */
    public Vivant sortir(Vivant vivant) {
        Vivant[] vivants = new Vivant[this.vivants.length - 1];
        Vivant vivantSorti = null;

        int j = 0;
        for (int i = 0; i < this.vivants.length; i++) {
            if (this.vivants[i].getNom().equals(vivant.getNom()) && vivantSorti == null) {
                vivantSorti = this.vivants[i];
            } else {
                vivants[j] = this.vivants[i];
                j++;
            }
        }

        if (vivantSorti != null) {
            this.vivants = vivants;
        }
        return vivantSorti;
    }
}
