package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
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
     * 
     * @param nom   Nom de la pièce.
     * @param monde Monde dans lequel se trouve la pièce.
     */
    public Piece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    /**
     * Cette méthode vérifie si la pièce contient un objet.
     * 
     * @param objet Objet à vérifier.
     * @return true si la pièce contient l'objet, false sinon.
     */
    public boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    /**
     * Cette méthode vérifie si la pièce contient un objet.
     * 
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
     * 
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
     * 
     * @param vivant Vivant à vérifier.
     * @return true si la pièce contient le vivant, false sinon.
     */
    public boolean contientVivant(Vivant vivant) {
        return this.contientVivant(vivant.getNom());
    }

    /**
     * Cette méthode dépose un objet dans la pièce.
     * 
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
     * 
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
     * 
     * @param objet Objet à retirer.
     * @return L'objet retiré, null si non trouvé.
     */
    public Objet retirer(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        return this.retirer(objet.getNom());
    }

    /**
     * Cette méthode retire un objet de la pièce.
     * 
     * @param nomObjet Nom de l'objet à retirer.
     * @return L'objet retiré, null si non trouvé.
     */
    public Objet retirer(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {

        if (!this.contientObjet(nomObjet)) {
            throw new ObjetAbsentDeLaPieceException(
                    String.format("L'objet %s n'est pas dans la piece", nomObjet));
        }

        if (((Objet) this.getMonde().getEntite(nomObjet)).estDeplacable()) { // Evite une allocation inutile qui
                                                                             // pourrait être plus couteuse que la
                                                                             // vérification
            throw new ObjetNonDeplacableException(
                    String.format("L'objet %s n'est pas déplaçable", nomObjet));
        }

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

    /**
     * Cette fonction retourne le tableau contenant les objets de la pièce.
     * 
     * @return
     */
    public Objet[] getObjets() {
        return this.objets.clone();
    }

    /**
     * Cette fonction retourne le tableau contenant les vivants de la pièce.
     * 
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
     * 
     * @param vivant Vivant à sortir
     * @return Le vivant qui à été sorti, null si non trouvé.
     */
    public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException {
        return this.sortir(vivant.getNom());
    }

    public Vivant sortir(String nomVivant) throws VivantAbsentDeLaPieceException {

        if (!this.contientVivant(nomVivant)) {
            throw new VivantAbsentDeLaPieceException(
                    String.format("Le vivant %s n'est pas dans la piece %s", nomVivant, this.getNom()));
        }

        Vivant[] vivants = new Vivant[this.vivants.length - 1];
        Vivant vivantSorti = null;

        int j = 0;
        for (int i = 0; i < this.vivants.length; i++) {
            if (this.vivants[i].getNom().equals(nomVivant) && vivantSorti == null) {
                vivantSorti = this.vivants[i];
            } else {
                vivants[j] = this.vivants[i];
                j++;
            }
        }

        this.vivants = vivants;
        return vivantSorti;
    }
}
