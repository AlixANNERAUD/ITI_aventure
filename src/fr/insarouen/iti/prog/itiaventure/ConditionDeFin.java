package fr.insarouen.iti.prog.itiaventure;

import java.io.Serializable;

public abstract class ConditionDeFin implements Serializable {

    /**
     * État du jeu lorsque la condition est vérifiée.
     */
    private final EtatDuJeu etat;

    /**
     * Constructeur ConditionDeFin.
     * 
     * @param etat État du jeu lorsque la condition est vérifiée
     */
    public ConditionDeFin(EtatDuJeu etat) {
        this.etat = etat;
    }

    /**
     * Retourne l'état du jeu lorsque la condition est vérifiée.
     * 
     * @return l'état du jeu lorsque la condition est vérifiée
     */
    public EtatDuJeu getEtatConditionVerifiee() {
        return this.etat;
    }

    /**
     * Vérifie si la condition est vérifiée.
     * 
     * @return l'état du jeu lorsque la condition est vérifiée
     */
    public abstract EtatDuJeu verifierEtatDuJeu();
}
