package fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleAvecObjet;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;

public class Serrure extends Objet implements Activable {

    private Clef clef;
    private Etat etat;
    private static int compteur = 0;

    public static String getIdentifiant() {
        return String.format("%d", compteur++);
    }

    public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super("serrure " + getIdentifiant(), monde);
        this.clef = null;
        this.etat = Etat.VERROUILLE;
    }

    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.clef = null;
        this.etat = Etat.VERROUILLE;
    }

    public final Clef creerClef() {
        if (this.clef == null) {
            try {
                this.clef = new Clef(String.format("clef de ", getIdentifiant()) , this.getMonde());
                return this.clef;
            } catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
                throw new Error(e.toString()); // ne doit pas arriver
            }
        }
        return null;
    }

    public boolean activableAvec(Objet objet) {
        return this.clef != null && this.clef.equals(objet);
    }

    public void activer() throws ActivationException {
        throw new ActivationImpossibleException();
    }

    public void activerAvec(Objet objet) throws ActivationException {
        switch (this.etat) {
            case Etat.VERROUILLE:
                if (this.activableAvec(objet)) {
                    this.etat = Etat.DEVEROUILLE;
                 } else {
                    throw new ActivationImpossibleAvecObjet();
                }
                break;
            case Etat.DEVEROUILLE:
                if (this.activableAvec(objet)) {
                    this.etat = Etat.VERROUILLE;
                } else {
                    throw new ActivationImpossibleAvecObjet();
                }
                break;
            default:
                throw new ActivationImpossibleException(); // Ne doit pas arriver
        }
    }

    @Override
    public boolean estDeplacable() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Serrure: %s - %s", this.getNom(), this.etat);
    }
}
