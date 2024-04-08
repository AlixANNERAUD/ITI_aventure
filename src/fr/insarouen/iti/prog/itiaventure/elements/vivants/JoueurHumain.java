package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationException;
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;

public class JoueurHumain extends Vivant implements Executable {
    private String ordre;

    public JoueurHumain(String nom, Monde monde, int pointVie, int pointForce, Piece piece) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointVie, pointForce, piece);
    }

    @Override
    public void executer() {
        System.out.println("JoueurHumain");
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    private void commandePrendre(String nom)
            throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        this.prendre(nom);
    }

    private void commandePoser(String nomObjet)
            throws ObjetNonPossedeParLeVivantException {
            this.deposer(nomObjet);
    }

    private void commandeFranchir(String nomPorte)
            throws PorteFermeException, PorteInexistanteDansLaPieceException {
        this.franchir(nomPorte);
    }

    private void commandeOuvrirPorte(String nomPorte)
            throws ActivationException, PorteInexistanteDansLaPieceException {
            this.activerActivable((Porte)this.getMonde().getEntite(nomPorte));
    }

    private void commandeOuvrirPorte(String nomPorte, String nomObjet)
            throws ActivationException, PorteInexistanteDansLaPieceException,
            ObjetNonPossedeParLeVivantException {
        this.activerActivableAvecObjet((Porte)this.getMonde().getEntite(nomPorte), (Objet)this.getMonde().getEntite(nomObjet));
    }

}
