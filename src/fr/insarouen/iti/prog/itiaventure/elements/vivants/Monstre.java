package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeException;

public class Monstre extends Vivant implements Executable {

    static final Random random = new Random();

    public Monstre(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets)
            throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointVie, pointForce, piece, objets);
    }

    public void executer() throws ITIAventureException {
        // Si le monstre est mort, il ne peut pas se déplacer
        if (this.estMort()) {
            return;
        }

        // Choisir une porte aléatoirement
        Collection<Porte> portes = this.getPiece().getPortes();
        int nombre = random.nextInt(portes.size());
        for (int i = 0; i < nombre; i++) {
            portes.iterator().next();
        }
        Porte porte = portes.iterator().next();

        this.franchir(porte);
        this.setPointVie(this.getPointVie() - 1);

        // Récupérer les objets déplacables de la pièce
        Collection<Objet> objets_piece = this.getPiece().getObjets().stream().filter(Objet::estDeplacable)
                .collect(Collectors.toList());

        // Deposer les objets de l'inventaire
        for (Objet objet : this.getObjets()) {
            this.deposer(objet);
        }

        // Prendre les objets déplacables de la pièce
        for (Objet objet : objets_piece) {
            this.prendre(objet);
        }

    }

}
