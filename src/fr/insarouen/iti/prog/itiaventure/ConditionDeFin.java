package fr.insarouen.iti.prog.itiaventure;

public abstract class ConditionDeFin {

    private final EtatDuJeu etat;

    public ConditionDeFin(EtatDuJeu etat) {
        this.etat = etat;
    }

    public EtatDuJeu getEtatConditionVerifiee() {
        return this.etat;
    }

    public abstract EtatDuJeu verifierEtatDuJeu();

}
