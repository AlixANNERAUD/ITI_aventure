package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ElementStructurel;
public class Main{ 

    public static void main(String[] args){
        Monde monde = new Monde("my little pony");
        System.out.println(monde.toString());

        Entite entite = new Entite("e1", monde);
        Entite entite3 = new Entite("e1", monde);
        System.out.println(entite.toString());

        Entite entite2 = new Entite("e2", monde);
        System.out.println(entite == entite2);
        System.out.println(entite == entite);
        System.out.println(entite == entite3);

        ElementStructurel elementStructurel = new ElementStructurel("e3", monde);

        System.out.println(elementStructurel.toString());
    }
}