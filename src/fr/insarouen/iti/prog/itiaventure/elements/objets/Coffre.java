package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;

public class Coffre extends Objet implements Activable {

    private Etat etat = Etat.FERME;

    public Coffre(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    @Override
    public boolean activableAvec(Objet objet) {
        return false;
    }

    @Override
    public void activer() throws ActivationException {
        switch (etat) {
            case OUVERT:
                etat = Etat.FERME;
                break;
            case FERME:
                etat = Etat.OUVERT;
                break;
            default:
                throw new ActivationException("Le coffre est dans un état inconsistant");
        }
    }

    @Override
    public void activerAvec(Objet obj) throws ActivationException {
        throw new ActivationException("Impossible d'activer le coffre avec un objet");
    }

    @Override
    public boolean estDeplacable() {
        return false;
    }

    /**
     * Retourne l'état du coffre
     * @return l'état du coffre
     */
    public Etat getEtat() {
        return etat;
    }

    @Override
    public String toString() {
        return "Coffre " + getNom() + " " + etat;
    }
}
