package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.Monde;

public class PiedDeBiche extends Objet {

    public PiedDeBiche(Monde monde) {
        super("Pied de biche", monde);
    }

    @Override
    public boolean estDeplacable() {
        return true;
    }

}
