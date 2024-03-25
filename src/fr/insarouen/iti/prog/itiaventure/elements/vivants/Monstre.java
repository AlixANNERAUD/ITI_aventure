package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeException;

public class Monstre extends Vivant implements Executable {

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
        List<Porte> portes = this.getPiece().getPortes().stream().filter(p -> {
            return p.getEtat() == Etat.OUVERT; // On ne peut pas franchir une porte fermée
        })
                .collect(Collectors.toList());
        if (portes.isEmpty()) {
            return;
        }
        Collections.shuffle(portes); // Mélanger les portes
        Porte porte = portes.getFirst(); // Prendre la première porte

        this.franchir(porte);
        this.setPointVie(this.getPointVie() - 1);

        // Récupérer les objets déplacables de la pièce
        List<Objet> objets_piece = this.getPiece().getObjets().stream().filter(Objet::estDeplacable)
                .collect(Collectors.toList());

        // Deposer les objets de l'inventaire
        ArrayList<Objet> objetsMonstre = new ArrayList<Objet>(this.getObjets());
        for (Objet objet : objetsMonstre) {
            this.deposer(objet);
        }

        // Prendre les objets déplacables de la pièce
        for (Objet objet : objets_piece) {
            this.prendre(objet);
        }

    }

}
