package fr.insarouen.iti.prog.itiaventure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.JoueurHumain;

public class Simulateur {

    private Monde monde;
    private List<ConditionDeFin> conditionsDeFin = new ArrayList<ConditionDeFin>();
    private boolean continuer;

    public Simulateur(Monde monde, ConditionDeFin... conditionsDeFin) {
        this.monde = monde;
        for (ConditionDeFin conditionDeFin : conditionsDeFin) {
            this.conditionsDeFin.add(conditionDeFin);
        }
    }

    public Simulateur(Reader reader) {

        Scanner scanner = new Scanner(reader);

        while (scanner.hasNext()) {
            String classe = scanner.next();

            try {
                switch (classe) {
                    case "Monde":
                        this.creerMonde(scanner);
                        break;
                    case "Piece":
                        this.creerPiece(scanner);
                        break;
                    case "PorteSerrure":
                        this.creerPorte(scanner, true);
                        break;
                    case "Porte":
                        this.creerPorte(scanner, false);
                        break;
                    case "Clef":
                        this.creerClef(scanner);
                        break;
                    case "JoueurHumain":
                        this.creerJoueurHumain(scanner);
                        break;
                    case "ConditionDeFin":
                        // TODO
                        break;
                    default:
                        System.out.println("Classe non reconnue");
                        break;
                }
            } catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
                System.out.println("Erreur lors de la création de l'entité : " + e.getMessage());

            }
        }
    }

    private void creerMonde(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = scanner.next();
        this.monde = new Monde(nom);
    }

    private void creerPiece(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nomPiece = scanner.next();
        new Piece(nomPiece, this.monde);
    }

    private void creerPorte(Scanner scanner, boolean avecSerrure) throws NomDEntiteDejaUtiliseDansLeMondeException {
        Serrure serrure = null;
        if (avecSerrure) {
            serrure = new Serrure(this.monde);
        }

        String nom = scanner.next();
        String piece1String = scanner.next();
        String piece2String = scanner.next();

        Piece piece1 = (Piece) this.monde.getEntite(piece1String);
        Piece piece2 = (Piece) this.monde.getEntite(piece2String);

        if (avecSerrure) {
            new Porte(nom, this.monde, serrure, piece1, piece2);
        } else {
            new Porte(nom, this.monde, piece1, piece2);
        }
    }

    private void creerClef(Scanner scanner) {
        Porte porte = (Porte) this.monde.getEntite(scanner.next());

        Clef clef = porte.getSerrure().creerClef();

        Piece piece = (Piece) this.monde.getEntite(scanner.next());

        piece.deposer(clef);
    }

    private void creerJoueurHumain(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = scanner.next();
        int pointVie = scanner.nextInt();
        int pointForce = scanner.nextInt();
        Piece piece = (Piece) this.monde.getEntite(scanner.next());

        new JoueurHumain(nom, this.monde, pointVie, pointForce, piece);
    }

    public Simulateur(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.monde = (Monde) ois.readObject();
        this.conditionsDeFin = (List<ConditionDeFin>) ois.readObject();
    }

    public void enregistrer(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.monde);
        oos.writeObject(this.conditionsDeFin);
    }

    public void ajouterConditionDeFin(ConditionDeFin conditionDeFin) {
        this.conditionsDeFin.add(conditionDeFin);
    }

    public void ajouterConditionsDeFin(Collection<ConditionDeFin> conditionsDeFin) {
        this.conditionsDeFin.addAll(conditionsDeFin);
    }

    public EtatDuJeu getEtatDuJeu() {
        for (ConditionDeFin conditionDeFin : this.conditionsDeFin) {
            // TODO
        }
        return EtatDuJeu.ENCOURS;
    }

    public EtatDuJeu executerUnTour() throws Throwable {
        // TODO
        return this.getEtatDuJeu();
    }

    public EtatDuJeu executerNbTour(int nbTours) throws Throwable {
        for (int i = 0; i < nbTours; i++) {
            EtatDuJeu etatDuJeu = this.executerUnTour();
            if (etatDuJeu != EtatDuJeu.ENCOURS) {
                return etatDuJeu;
            }
        }
        return EtatDuJeu.ENCOURS;
    }

    public EtatDuJeu executerJusquaFin() throws Throwable {
        this.continuer = true;
        EtatDuJeu etatDuJeu = EtatDuJeu.ENCOURS;
        while (etatDuJeu == EtatDuJeu.ENCOURS && this.continuer) {
            etatDuJeu = this.executerUnTour();
        }
        return etatDuJeu;
    }

    public void stopper() {
        this.continuer = false;
    }

    @Override
    public String toString() {
        return String.format("Simulateur : %s\n%s", monde, conditionsDeFin.toString());
    }
}
