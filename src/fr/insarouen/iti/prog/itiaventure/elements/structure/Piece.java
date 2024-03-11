package fr.insarouen.iti.prog.itiaventure.elements.structure;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Objet> objets = new HashMap<String, Objet>();

    /**
     * Tableau contenant les vivants de la pièce.
     */
    private Map<String, Vivant> vivants = new HashMap<String, Vivant>();

    /**
     * Constructeur Piece.
     * 
     * @param nom   Nom de la pièce.
     * @param monde Monde dans lequel se trouve la pièce.
     */
    public Piece(
            String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
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
        return this.objets.get(nomObjet) != null;
    }

    /**
     * Cette méthode vérifie si la pièce contient un vivant.
     * 
     * @param nomVivant Nom du vivant à vérifier.
     * @return true si la pièce contient le vivant, false sinon.
     */
    public boolean contientVivant(String nomVivant) {
        return this.vivants.get(nomVivant) != null;
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
        this.objets.put(objet.getNom(), objet);
    }

    /**
     * Cette méthode fait entrer un vivant dans la pièce.
     * 
     * @param vivant Vivant à faire entrer.
     */
    public void entrer(Vivant vivant) {
        this.vivants.put(vivant.getNom(), vivant);
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

        if (!((Objet) this.getMonde().getEntite(nomObjet)).estDeplacable()) { // Evite une allocation inutile qui
                                                                              // pourrait être plus couteuse que la
                                                                              // vérification
            throw new ObjetNonDeplacableException(
                    String.format("L'objet %s n'est pas déplaçable", nomObjet));
        }

        Objet o = this.objets.get(nomObjet);
        this.objets.remove(nomObjet);
        return o;
    }

    /**
     * Cette fonction retourne le tableau contenant les objets de la pièce.
     * 
     * @return
     */
    public Objet[] getObjets() {
        Objet[] objets = new Objet[this.objets.size()];
        int i = 0;
        for (Objet objet : this.objets.values()) {
            objets[i] = objet;
            i++;
        }
        return objets;
    }

    /**
     * Cette fonction retourne le tableau contenant les vivants de la pièce.
     * 
     * @return
     */
    public Vivant[] getVivants() {
        Vivant[] vivants = new Vivant[this.vivants.size()];
        int i = 0;
        for (Vivant vivant : this.vivants.values()) {
            vivants[i] = vivant;
            i++;
        }
        return vivants;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Piece: %s", this.getNom()));
        for (Objet objet : this.objets.values()) {
            stringBuilder.append("\n\t-> ");
            stringBuilder.append(objet.toString());
        }

        for (Vivant vivant : this.vivants.values()) {
            stringBuilder.append("\n\t-> ");
            stringBuilder.append(vivant.toString());
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

        Vivant v = this.vivants.get(nomVivant);
        this.vivants.remove(nomVivant);
        return v;
    }
}
