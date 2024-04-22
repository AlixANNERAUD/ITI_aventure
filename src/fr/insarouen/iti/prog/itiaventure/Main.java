package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Coffre;

import fr.insarouen.iti.prog.itiaventure.elements.vivants.Monstre;

public class Main {

    static Simulateur simulateur = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // On itère jusqu'a ce que l'utilisateur entre 5
        boolean continuer = true;
        while (continuer) {
            System.out.println("- - - 🍆 ITI Aventure ⭐ - - -");
            System.out.println("1️⃣ Jouer");
            System.out.println("2️⃣ Charger un fichier de description");
            System.out.println("3️⃣ Sauvegarder la partie actuelle");
            System.out.println("4️⃣ Charger une partie sauvegardée");
            System.out.println("5️⃣ Quitter");

            System.out.print("❓ Entrez votre choix : ");

            int choix = 0;

            try {
                choix = scanner.nextInt();
            } catch (Exception e) {
                scanner.next();
            }

            switch (choix) {
                case 1:
                    Main.jouer();
                    break;
                case 2:
                    Main.chargerDescription("./description.txt");
                    break;
                case 3:
                    Main.sauvegarder("./sauvegarde.txt");
                    break;
                case 4:
                    Main.chargerSauvegarde("./sauvegarde.txt");
                    break;
                case 5:
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide !");
                    break;
            }

        }
    }

    /**
     * Lance le jeu
     */
    private static void jouer() {
        if (Main.simulateur == null) {
            System.out.println("❌ Veuillez charger une description avant de jouer.");
            return;
        }

        EtatDuJeu etat = EtatDuJeu.ENCOURS;
        do {
            try {
                etat = Main.simulateur.executerUnTour();
            } catch (Throwable e) {
                System.out.println(String.format("❌ %s", e.getMessage()));
            }

        } while (Main.demandeContinuer() && etat.equals(EtatDuJeu.ENCOURS));

        if (!etat.equals(EtatDuJeu.ENCOURS)) {
            Main.simulateur = null;
        }
    }

    /**
     * Charge un fichier de description
     * 
     * @param fichier Chemin du fichier
     */
    private static void chargerDescription(String fichier) {
        try {
            FileReader file = new FileReader(fichier);
            Main.simulateur = new Simulateur(file);
            file.close();
            System.out.println("✅ Description chargée.");
        } catch (FileNotFoundException e) {
            System.out.println("❌ Fichier de description introuvable.");
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la lecture du fichier de description.");
        }
    }

    /**
     * Sauvegarde la partie actuelle
     * 
     * @param fichier Chemin du fichier
     */
    private static void sauvegarder(String fichier) {
        if (Main.simulateur == null) {
            System.out.println("❌ Aucune partie en cours.");
            return;
        }
        
        try {
            FileOutputStream file = new FileOutputStream(fichier);
            Main.simulateur.enregistrer(new ObjectOutputStream(file));
            file.close();
            System.out.println("✅ Partie sauvegardée");
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la sauvegarde de la partie : " + e.toString());
        }
    }

    /**
     * Charge une partie sauvegardée
     * 
     * @param fichier Chemin du fichier
     */
    private static void chargerSauvegarde(String fichier) {
        try {
            FileInputStream file = new FileInputStream(fichier);
            Main.simulateur = new Simulateur(new ObjectInputStream(file));
            file.close();
            System.out.println("✅ Partie chargée.");
        } catch (FileNotFoundException e) {
            System.out.println("❌ Fichier de sauvegarde introuvable.");
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la lecture du fichier de sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Demande à l'utilisateur s'il veut continuer
     * 
     * @return true si l'utilisateur veut continuer, false sinon
     */
    private static boolean demandeContinuer() {
        System.out.print("❓ Voulez-vous continuer (O/N) : ");
        while (true) {
            String reponse = Main.scanner.next().toUpperCase();
            if (reponse.equals("O")) {
                return true;
            } else if (reponse.equals("N")) {
                return false;
            } else {
                System.out.println("❌ Veuillez entrer O ou N.");
            }
        }
    }
}