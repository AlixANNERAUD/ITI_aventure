package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin {

    /**
     * Objet à vérifier
     */
    private final Objet[] objets;

    /**
     * Vivant à vérifier
     */
    private final Vivant vivant;

    /**
     * Constructeur ConditionDeFinVivantPossedeObjets
     * 
     * @param etat   État du jeu lorsque la condition est vérifiée
     * @param vivant vivant a verifier
     * @param objets objets a verifier
     */
    public ConditionDeFinVivantPossedeObjets(EtatDuJeu etat, Vivant vivant, Objet... objets) {
        super(etat);
        this.objets = objets;
        this.vivant = vivant;
    }

    @Override
    public EtatDuJeu verifierEtatDuJeu() {
        for (Objet objet : this.objets) {
            if (!this.vivant.possede(objet)) {
                return EtatDuJeu.ENCOURS;
            }
        }
        return this.getEtatConditionVerifiee();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Objet objet : this.objets) {
            sb.append(objet.getNom());
            sb.append(", ");
        }

        return String.format("Vivant possede objets: %s", sb.toString());
    }
}
