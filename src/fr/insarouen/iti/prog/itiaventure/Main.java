package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;

public class Main{ 

    public static void main(String[] args){
        Entite entite = new Entite("e1");
        Entite entite3 = new Entite("e1");
        System.out.println(entite.toString());

        Entite entite2 = new Entite("e2");
        System.out.println(entite == entite2);
        System.out.println(entite == entite);
        System.out.println(entite == entite3);
    }
}