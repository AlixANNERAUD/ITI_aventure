package fr.insarouen.iti.prog.itiaventure;

public class ConditionDeFinConjonctionDeConditionDeFin extends ConditionDeFin {
    
    private final ConditionDeFin[] conditions;

    public ConditionDeFinConjonctionDeConditionDeFin(EtatDuJeu etat, ConditionDeFin... conditions)
    {
        super(etat);
        this.conditions = conditions;
    }

    @Override   
    public EtatDuJeu verifierEtatDuJeu()
    {
        for (ConditionDeFin c : this.conditions)
        {
            if (c.verifierEtatDuJeu() != this.getEtatConditionVerifiee())
            {
                return EtatDuJeu.ENCOURS;
            }
        }
        return this.getEtatConditionVerifiee();
    }

}
