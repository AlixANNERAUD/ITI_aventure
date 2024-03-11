package fr.insarouen.iti.prog.itiaventure.elements.structure;

import java.util.Collection;
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
    final private Map<String, Objet> objets = new HashMap<String, Objet>();

    /**
     * Tableau contenant les vivants de la pièce.
     */
    final private Map<String, Vivant> vivants = new HashMap<String, Vivant>();

    /**
     * Tableau contenant les portes de la pièce.
     */
    final private Map<String, Porte> portes = new HashMap<String, Porte>();

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
        return this.objets.containsKey(nomObjet);
    }

    /**
     * Cette méthode vérifie si la pièce contient un vivant.
     * 
     * @param nomVivant Nom du vivant à vérifier.
     * @return true si la pièce contient le vivant, false sinon.
     */
    public boolean contientVivant(String nomVivant) {
        return this.vivants.containsKey(nomVivant);
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
        Objet objet = this.objets.get(nomObjet);

        if (objet == null) {
            throw new ObjetAbsentDeLaPieceException(
                    String.format("L'objet %s n'est pas dans la piece", nomObjet));
        }

        if (!objet.estDeplacable()) { // Evite une allocation inutile qui
                                      // pourrait être plus couteuse que la
                                      // vérification
            throw new ObjetNonDeplacableException(
                    String.format("L'objet %s n'est pas déplaçable", nomObjet));
        }

        return this.objets.remove(nomObjet);
    }

    /**
     * Cette fonction retourne le tableau contenant les objets de la pièce.
     * 
     * @return
     */
    public Collection<Objet> getObjets() {
        return this.objets.values();
    }

    /**
     * Cette fonction retourne le tableau contenant les vivants de la pièce.
     * 
     * @return
     */
    public Collection<Vivant> getVivants() {
        return this.vivants.values();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Piece: %s\n%s\n%s\n%s", this.getNom(), this.objets.toString(), this.vivants.toString(), this.portes.toString()));
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
        Vivant vivant = this.vivants.remove(nomVivant);

        if (vivant == null) {
            throw new VivantAbsentDeLaPieceException(
                    String.format("Le vivant %s n'est pas dans la piece %s", nomVivant, this.getNom()));
        }

        return vivant;
    }

    /**
     * Cette méthode ajoute une porte à la pièce.
     * 
     * @param porte Porte à ajouter.
     */
    protected void addPorte(Porte porte) {
        this.portes.put(porte.getNom(), porte);
    }

    /**
     * Cette méthode vérifie si la pièce contient une porte.
     * 
     * @param porte Porte à vérifier.
     * @return true si la pièce contient la porte, false sinon.
     */
    public boolean aLaPorte(Porte porte) {
        return this.aLaPorte(porte.getNom());
    }

    /**
     * Cette méthode vérifie si la pièce contient une porte.
     * 
     * @param nomPorte Nom de la porte à vérifier.
     * @return true si la pièce contient la porte, false sinon.
     */
    public boolean aLaPorte(String nomPorte) {
        return this.portes.containsKey(nomPorte);
    }

    /**
     * Cette méthode retourne une porte de la pièce.
     * 
     * @param nomPorte Nom de la porte à retourner.
     * @return La porte correspondante, null si non trouvée.
     */
    public Porte getPorte(String nomPorte) {
        return this.portes.get(nomPorte);
    }
}
