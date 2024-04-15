package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Coffre;

import fr.insarouen.iti.prog.itiaventure.elements.vivants.Monstre;

public class Main {

    public static void main(String[] args) {
        // On prend un scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        Simulateur simulateur = null;

        // On itère jusqu'a ce que l'utilisateur entre 5
        boolean continuer = true;
        while (continuer) {

            System.out.println("1/ Jouer");
            System.out.println("2/ Charger un fichier de description");
            System.out.println("3/ Sauver la partie actuelle");
            System.out.println("4/ Charger une partie sauvegardée");
            System.out.println("5/ Quitter");

            switch (scanner.nextInt()) {
                case 1:
                    // TODO
                    break;
                case 2:
                    try {
                        FileReader file = new FileReader("./description.txt");
                        simulateur = new Simulateur(file);
                        file.close();
                        simulateur.executerJusquaFin();
                    } catch (FileNotFoundException e) {
                        System.out.println("Erreur lors de la lecture du fichier de description : " + e.getMessage());
                    } catch (Throwable e) {
                        System.out.println("Erreur lors de l'exécution de la partie : " + e.getMessage());
                    }

                    break;
                case 3:
                    try {
                        FileOutputStream file = new FileOutputStream("./sauvegarde.txt");
                        simulateur.enregistrer(new ObjectOutputStream(file));
                        file.close();
                    } catch (Exception e) {
                        System.out.println("Erreur lors de la sauvegarde de la partie : " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        FileInputStream file = new FileInputStream("./sauvegarde.txt");
                        simulateur = new Simulateur(new ObjectInputStream(file));
                        file.close();
                        simulateur.executerJusquaFin();
                    } catch (Throwable e) {
                        System.out.println("Erreur lors du chargement de la partie : " + e.getMessage());
                    }
                    break;
                case 5:
                    continuer = false;
                    break;
                default:
                    System.out.println("Veuillez entrer un nombre entre 1 et 5");
                    break;
            }

        }
    }

}