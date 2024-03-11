package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import java.util.HashMap;
import java.util.Map;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationException;

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
    private Map<String, Objet> objets = new HashMap<String, Objet>();

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
        for (Objet objet : objets) {
            this.objets.put(objet.getNom(), objet);
        }
    }

    public void activerActivable(Activable activable) throws ActivationException {
        activable.activer();
    }

    public void activerActivableAvecObjet(Activable activable, Objet objet) throws ActivationException {
        activable.activerAvec(objet);
    }

    /**
     * Depose un objet du vivant dans la piece à partir de l'objet
     * 
     * @param objet
     */
    public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException {
        this.deposer(objet.getNom());
    }

    /**
     * Depose un objet du vivant dans la piece à partir du nom de l'objet
     * 
     * @param nomObjet
     */
    public void deposer(String nomObjet) throws ObjetNonPossedeParLeVivantException {

        if (!this.possede(nomObjet)) {
            throw new ObjetNonPossedeParLeVivantException(
                    String.format("Le vivant %s ne possède pas l'objet %s pour le déposer dans la pièce.",
                            this.getNom(), nomObjet));
        }

        this.piece.deposer(this.objets.remove(nomObjet));
    }

    /**
     * Permet de donner tous les objets appartenant à un vivant.
     * 
     * @return Objet[], un tableau contenant les objets du vivants
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
     * Indique si le vivant est mort.
     * @return Boolean
     */
    public boolean estMort() {
        return this.pointVie <= 0;
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
        return this.objets.containsKey(nomObjet);
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

        this.objets.put(nomObjet, objet);
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

    /**
     * Cette méthode permet à un vivant de franchir une porte (passer d'une pièce à
     * une autre)
     * 
     * @param nomPorte Nom de la porte à franchir
     * @throws PorteFermeException
     * @throws PorteInexistanteDansLaPieceException
     */
    public void franchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
        Porte porte = this.piece.getPorte(nomPorte);
        if (porte == null) {
            throw new PorteInexistanteDansLaPieceException(
                    String.format("La porte %s n'existe pas dans la pièce %s", nomPorte, this.piece.getNom()));
        }
        Etat etat = porte.getEtat();

        switch (etat) {
            case Etat.VERROUILLE:
                throw new PorteFermeException("La porte est verrouillée");
            case Etat.FERME:
                throw new PorteFermeException("La porte est fermée");
            case Etat.OUVERT:
                try {
                    this.piece.sortir(this);
                } catch (VivantAbsentDeLaPieceException e) {
                    // Exception qui ne devrait jamais arriver
                    e.printStackTrace();
                }

                this.piece = porte.getPieceAutreCote(this.piece);

                this.piece.entrer(this);
                break;
            default:
                break;
        }

    }

    /**
     * Cette méthode permet à un vivant de franchir une porte (passer d'une pièce à une autre)
     * @param porte Nom de la porte à franchir.
     * @throws PorteFermeException Porte fermée.
     * @throws PorteInexistanteDansLaPieceException Porte inexistante dans la pièce.
     */
    public void franchir(Porte porte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
        this.franchir(porte.getNom());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Vivant: %s\n", this.getNom()));
        stringBuilder.append(String.format("\t- Point de vie : %s\n", this.pointVie));
        stringBuilder.append(String.format("\t- Point de force : %s\n", this.pointForce));
        for (Objet objet : this.objets.values()) {
            stringBuilder.append(String.format("\t- %s\n", objet.toString()));
        }
        return stringBuilder.toString();
    }

}
