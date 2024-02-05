package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ElementStructurel;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;

public class Main {

    public static class EntiteTest extends Entite {
        public EntiteTest(String nom, Monde monde) {
            super(nom, monde);
        }
    }

    public static class ElementStructurelTest extends ElementStructurel {
        public ElementStructurelTest(String nom, Monde monde) {
            super(nom, monde);
        }
    }

    private static class ObjetTest extends Objet {
        public ObjetTest(String nom, Monde monde) {
            super(nom, monde);
        }

        @Override
        public boolean estDeplacable() {
            return true;
        }
    }

    public static void main(String[] args) {
        Monde monde = new Monde("my little pony");
        System.out.println(monde.toString());

        Entite entite = new EntiteTest("e1", monde);

        System.out.println(entite.toString());

        Objet objet = new ObjetTest("o2", monde);

        System.out.println(objet.toString());

        ElementStructurel elementStructurel = new ElementStructurelTest("e3", monde);

        System.out.println(elementStructurel.toString());
   
        Piece piece = new Piece("p4", monde);
        piece.deposer(objet);

        System.out.println(piece.toString());

        Vivant vivant = new Vivant("Jean-Jacques", monde, 2,3, piece, objet);

        System.out.println(vivant.toString());        
    }
}