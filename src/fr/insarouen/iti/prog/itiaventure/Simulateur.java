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
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.JoueurHumain;

public class Simulateur {

    /**
     * Monde.
     */
    private Monde monde;

    /**
     * Conditions de fin.
     */
    private List<ConditionDeFin> conditionsDeFin;

    /**
     * Etat du jeu.
     */
    private EtatDuJeu etat;

    /**
     * Constructeur Simulateur.
     * 
     * @param monde           Monde.
     * @param conditionsDeFin Conditions de fin.
     */
    public Simulateur(Monde monde, ConditionDeFin... conditionsDeFin) {
        this.monde = monde;
        this.conditionsDeFin = new ArrayList<ConditionDeFin>();
        for (ConditionDeFin conditionDeFin : conditionsDeFin) {
            this.conditionsDeFin.add(conditionDeFin);
        }
    }

    /**
     * Constructeur Simulateur.
     * 
     * @param reader Reader.
     */
    public Simulateur(Reader reader) {
        this.conditionsDeFin = new ArrayList<ConditionDeFin>();

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
                    case "ConditionDeFinVivantDansPiece":
                        this.creerConditionDeFinVivantDansPiece(scanner);
                        break;
                    default:
                        System.out.println("Classe non reconnue : " + classe);
                        break;
                }
            } catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
                System.out.println("Erreur lors de la création de l'entité : " + e.getMessage());

            }
        }
    }

    /**
     * Crée une condition de fin pour un vivant dans une pièce.
     * 
     * @param scanner Scanner.
     */
    private void creerConditionDeFinVivantDansPiece(Scanner scanner) {
        EtatDuJeu etat = EtatDuJeu.valueOf(scanner.next());
        JoueurHumain joueur = (JoueurHumain) this.monde.getEntite(scanner.next());
        Piece piece = (Piece) this.monde.getEntite(scanner.next());
        ConditionDeFinVivantDansPiece condition = new ConditionDeFinVivantDansPiece(etat, joueur, piece);
        this.ajouterConditionDeFin(condition);
    }

    /**
     * Crée un monde.
     * 
     * @param scanner Scanner.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Erreur lors de la création
     *                                                   du monde.
     */
    private void creerMonde(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = scanner.next();
        this.monde = new Monde(nom);
    }

    /**
     * Crée une pièce.
     * 
     * @param scanner Scanner.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Erreur lors de la création
     *                                                   de la pièce.
     */
    private void creerPiece(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nomPiece = scanner.next();
        new Piece(nomPiece, this.monde);
    }

    /**
     * Crée une porte.
     * 
     * @param scanner     Scanner.
     * @param avecSerrure Si la porte a une serrure.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Erreur lors de la création
     *                                                   de la porte.
     */
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

    /**
     * Crée une clef.
     * 
     * @param scanner Scanner.
     */
    private void creerClef(Scanner scanner) {
        Porte porte = (Porte) this.monde.getEntite(scanner.next());

        Clef clef = porte.getSerrure().creerClef();

        Piece piece = (Piece) this.monde.getEntite(scanner.next());

        piece.deposer(clef);
    }

    /**
     * Crée un joueur humain.
     * 
     * @param scanner Scanner.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException Erreur lors de la création
     *                                                   du joueur.
     */
    private void creerJoueurHumain(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = scanner.next();
        int pointVie = scanner.nextInt();
        int pointForce = scanner.nextInt();
        Piece piece = (Piece) this.monde.getEntite(scanner.next());

        new JoueurHumain(nom, this.monde, pointVie, pointForce, piece);
    }

    /**
     * Constructeur Simulateur.
     * 
     * @param flux Flux dans lequel lire le simulateur.
     * @throws IOException            Erreur lors de la lecture.
     * @throws ClassNotFoundException Erreur lors de la lecture.
     */
    public Simulateur(ObjectInputStream flux) throws IOException, ClassNotFoundException {
        this.monde = (Monde) flux.readObject();
        this.conditionsDeFin = (List<ConditionDeFin>) flux.readObject();
    }

    /**
     * Enregistre le simulateur dans un fichier.
     * 
     * @param flux Flux dans lequel enregistrer le simulateur.
     * @throws IOException Erreur lors de l'enregistrement.
     */
    public void enregistrer(ObjectOutputStream flux) throws IOException {
        flux.writeObject(this.monde);
        flux.writeObject(this.conditionsDeFin);
    }

    /**
     * Ajoute une condition de fin.
     * 
     * @param conditionDeFin Condition de fin à ajouter.
     */
    public void ajouterConditionDeFin(ConditionDeFin conditionDeFin) {
        this.conditionsDeFin.add(conditionDeFin);
    }

    /**
     * Ajoute des conditions de fin.
     * 
     * @param conditionsDeFin Conditions de fin à ajouter.
     */
    public void ajouterConditionsDeFin(Collection<ConditionDeFin> conditionsDeFin) {
        this.conditionsDeFin.addAll(conditionsDeFin);
    }

    /**
     * Retourne l'état du jeu.
     * 
     * @return Etat du jeu (gagné, perdu ou en cours).
     */
    public EtatDuJeu getEtatDuJeu() {
        // On vérifie les conditions de fin
        for (ConditionDeFin conditionDeFin : this.conditionsDeFin) {
            EtatDuJeu etat = conditionDeFin.verifierEtatDuJeu();
            // Si une condition de fin est vérifiée (!= en cours), on retourne l'état du jeu
            // (gagné ou perdu)
            if (etat != EtatDuJeu.ENCOURS) {
                System.out.println("Etat du jeu : " + etat);
                System.out.println("Cause : " + conditionDeFin);
                return etat;

            }
        }
        // Sinon, on retourne que le jeu est en cours
        return EtatDuJeu.ENCOURS;
    }

    /**
     * Exécute un tour.
     * 
     * @return Etat du jeu.
     * @throws Throwable Erreur lors de l'exécution.
     */
    public EtatDuJeu executerUnTour() throws Throwable {
        // On récupère les exécutables
        Collection<Executable> executables = monde.getExecutables();

        // On demande les ordres aux joueurs
        for (Executable executable : executables) {
            // On vérifie si l'entité est un joueur humain
            if (executable instanceof JoueurHumain) {
                // On transtype l'entité en joueur humain
                JoueurHumain joueur = (JoueurHumain) executable;
                // Affichage des informations du joueur
                StringBuilder objetsJoueur = new StringBuilder();
                joueur.getObjets().forEach(objet -> objetsJoueur.append(objet.getNom()).append(", "));

                System.out
                        .println(String.format(
                                "👨 %s - 💓 %d - 💪 %d \n - 📥 : %s",
                                joueur.getNom(),
                                joueur.getPointVie(), joueur.getPointForce(), objetsJoueur.toString()));

                // Affichage de la pièce
                StringBuilder objetsPiece = new StringBuilder();
                joueur.getPiece().getObjets().forEach(objet -> objetsPiece.append(objet.getNom()).append(", "));

                StringBuilder portesPiece = new StringBuilder();
                joueur.getPiece().getPortes()
                        .forEach(porte -> portesPiece.append(String.format("%s (%s), ", porte.getNom(),
                                porte.getEtat().toString())));

                System.out.println(String.format(" - 🗺️  %s\n  - 📥 : %s\n  - 🚪 : %s",
                        joueur.getPiece().getNom(), objetsPiece.toString(),
                        portesPiece.toString()));

                // Demande de la commande
                System.out.print("❓ Entrez une commande : ");

                // On lit la commande de l'utilisateur et on la (readln et decompose)
                Scanner scanner = new Scanner(System.in);

                // On récupère la commande et on l'envoie au joueur
                joueur.setOrdre(scanner.nextLine());
            }
        }

        // On exécute les entités
        for (Executable executable : executables) {
            executable.executer();
        }

        return this.getEtatDuJeu();
    }

    /**
     * Exécute un nombre de tours.
     * 
     * @param nbTours Nombre de tours à exécuter.
     * @return Etat du jeu.
     * @throws Throwable Erreur lors de l'exécution.
     */
    public EtatDuJeu executerNbTour(int nbTours) throws Throwable {
        for (int i = 0; i < nbTours; i++) {
            EtatDuJeu etatDuJeu = this.executerUnTour();
            if (etatDuJeu != EtatDuJeu.ENCOURS) {
                return etatDuJeu;
            }
        }
        return EtatDuJeu.ENCOURS;
    }

    /**
     * Exécute jusqu'à la fin.
     * 
     * @return Etat du jeu.
     * @throws Throwable Erreur lors de l'exécution.
     */
    public EtatDuJeu executerJusquaFin() throws Throwable {
        EtatDuJeu etatDuJeu = this.getEtatDuJeu();
        while (etatDuJeu == EtatDuJeu.ENCOURS) {
            // On exécute un tour
            etatDuJeu = this.executerUnTour();
        }
        return etatDuJeu;
    }

    @Override
    public String toString() {
        return String.format("Simulateur : %s\n%s", monde, conditionsDeFin.toString());
    }
}
