package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.structure.ElementStructurel;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;
public class Main {

    public static void main(String[] args) {
        Monde monde = new Monde("Monde");
        try {
            Piece piece1 = new Piece("Piece 1", monde);
            Piece piece2 = new Piece("Piece 2", monde);
            Vivant vivant = new Vivant("Vivant", monde, 100, 100, piece1);
            PiedDeBiche piedDeBiche = new PiedDeBiche(monde);
            piece1.deposer(piedDeBiche);
            System.out.println(monde);
        } catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
            e.printStackTrace();
        }
    }


}