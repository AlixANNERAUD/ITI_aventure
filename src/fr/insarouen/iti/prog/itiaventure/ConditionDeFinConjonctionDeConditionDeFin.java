package fr.insarouen.iti.prog.itiaventure;

public class ConditionDeFinConjonctionDeConditionDeFin extends ConditionDeFin {

    /**
     * Conditions à vérifier.
     */
    private final ConditionDeFin[] conditions;

    /**
     * Constructeur ConditionDeFinConjonctionDeConditionDeFin.
     * 
     * @param etat       État du jeu lorsque la condition est vérifiée
     * @param conditions conditions à vérifier
     */
    public ConditionDeFinConjonctionDeConditionDeFin(EtatDuJeu etat, ConditionDeFin... conditions) {
        super(etat);
        this.conditions = conditions;
    }

    @Override
    public EtatDuJeu verifierEtatDuJeu() {
        for (ConditionDeFin c : this.conditions) {
            if (c.verifierEtatDuJeu() != this.getEtatConditionVerifiee()) {
                return EtatDuJeu.ENCOURS;
            }
        }
        return this.getEtatConditionVerifiee();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ConditionDeFin c : this.conditions) {
            sb.append(c.toString());
            sb.append("\n");
        }
        return String.format("Conjonction de conditions de fin: %s", sb.toString());
    }
}
