package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

/**
 * Classe représentant une pièce.
 */
public class Piece extends ElementStructurel {

    /**
     * Tableau contenant les objets de la pièce.
     */
    private Objet[] objets = new Objet[0];

    /**
     * Tableau contenant les vivants de la pièce.
     */
    private Vivant[] vivants = new Vivant[0];

    /**
     * Constructeur Piece.
     * @param nom Nom de la pièce.
     * @param monde Monde dans lequel se trouve la pièce.
     */
    public Piece(String nom, Monde monde) {
        super(nom, monde);
    }

    /**
     * Cette méthode vérifie si la pièce contient un objet.
     * @param objet Objet à vérifier.
     * @return true si la pièce contient l'objet, false sinon.
     */
    public boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    /**
     * Cette méthode vérifie si la pièce contient un objet.
     * @param nomObjet Nom de l'objet à vérifier.
     * @return true si la pièce contient l'objet, false sinon.
     */
    public boolean contientObjet(String nomObjet) {
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Cette méthode vérifie si la pièce contient un vivant.
     * @param nomVivant Nom du vivant à vérifier.
     * @return true si la pièce contient le vivant, false sinon.
     */
    public boolean contientVivant(String nomVivant) {
        for (int i = 0; i < this.vivants.length; i++) {
            if (this.vivants[i].getNom().equals(nomVivant)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Cette méthode vérifie si la pièce contient un vivant.
     * @param vivant Vivant à vérifier.
     * @return true si la pièce contient le vivant, false sinon.
     */
    public boolean contientVivant(Vivant vivant) {
        return this.contientVivant(vivant.getNom());
    }

    /**
     * Cette méthode dépose un objet dans la pièce.
     * @param objet Objet à déposer.
     */
    public void deposer(Objet objet) {
        Objet[] objets = new Objet[this.objets.length + 1];

        for (int i = 0; i < this.objets.length; i++) {
            objets[i] = this.objets[i];
        }

        objets[this.objets.length] = objet;

        this.objets = objets;
    }

    /**
     * Cette méthode fait entrer un vivant dans la pièce.
     * @param vivant Vivant à faire entrer.
     */
    public void entrer(Vivant vivant) {
        Vivant[] vivants = new Vivant[this.vivants.length + 1];

        for (int i = 0; i < this.vivants.length; i++) {
            vivants[i] = this.vivants[i];
        }

        vivants[this.vivants.length] = vivant;

        this.vivants = vivants;
    }

    /**
     * Cette méthode retire un objet de la pièce.
     * @param objet Objet à retirer.
     * @return L'objet retiré, null si non trouvé.
     */
    public Objet retirer(Objet objet) {
        return this.retirer(objet.getNom());
    }

    /**
     * Cette méthode retire un objet de la pièce.
     * @param nomObjet Nom de l'objet à retirer.
     * @return L'objet retiré, null si non trouvé.
     */
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

    /**
     * Cette fonction retourne le tableau contenant les objets de la pièce.
     * @return
     */
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
        for (int i = 0; i < this.vivants.length; i++) {
            stringBuilder.append("\n\t-> ");
            stringBuilder.append(this.vivants[i].toString());
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
