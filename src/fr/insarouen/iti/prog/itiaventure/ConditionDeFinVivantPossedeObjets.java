package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin {
    
    private final Objet[] objets;
    private final Vivant vivant;

    public ConditionDeFinVivantPossedeObjets(EtatDuJeu etat, Vivant vivant, Objet... objets)
    {
        super(etat);
        this.objets = objets;
        this.vivant = vivant;
    }

    @Override
    public EtatDuJeu verifierEtatDuJeu()
    {
        for (Objet objet : this.objets)
        {
            if (!this.vivant.possede(objet))
            {
                return EtatDuJeu.ENCOURS;
            }
        }
        return this.getEtatConditionVerifiee();
    }
}
