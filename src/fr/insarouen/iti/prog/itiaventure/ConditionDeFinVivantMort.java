package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantMort extends ConditionDeFin {

    private final Vivant vivant;

    public ConditionDeFinVivantMort(EtatDuJeu etat, Vivant vivant) {
        super(etat);
        this.vivant = vivant;
    }

    @Override
    public EtatDuJeu verifierEtatDuJeu() {
        if (this.vivant.estMort()){
            return this.getEtatConditionVerifiee();
        }
        return EtatDuJeu.ENCOURS;
    }
    
}
