package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ElementStructurel;
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
    }
}