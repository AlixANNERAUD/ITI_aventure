package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationException;
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;

public class JoueurHumain extends Vivant implements Executable {
    private String ordre = null;

    /**
     * Constructeur JoueurHumain
     * @param nom 
     * @param monde 
     * @param pointVie
     * @param pointForce
     * @param piece
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    public JoueurHumain(String nom, Monde monde, int pointVie, int pointForce, Piece piece)
            throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointVie, pointForce, piece);
    }

    /**
     * Obtenir la commande de l'utilisateur
     * @param scanner Scanner
     * @return Commande de l'utilisateur
     */
    private String obtenirCommande(Scanner scanner) {
        return "commande" + scanner.next();
    }

    /**
     * Récupère les paramètres de la commande
     * @param scanner Scanner
     * @return Paramètres de la commande
     */
    private ArrayList<String> obtenirParametre(Scanner scanner) {
        ArrayList<String> parametres = new ArrayList<String>();
        while (scanner.hasNext()) {
            parametres.add(scanner.next());
        }
        return parametres;
    }

    /**
     * Récupère les types des paramètres de la commande de l'utilisateur
     * @param parametres Paramètres
     * @return Types des paramètres
     */
    private Class<?>[] obtenirTypes(ArrayList<String> parametres) {
        List<Class<?>> types = parametres.stream().map(String::getClass).collect(Collectors.toList());
        return types.toArray(new Class<?>[types.size()]);
    }

    /**
     * Exécute une commande
     * 
     * @param commande   Commande à exécuter
     * @param parametres Paramètres de la commande
     * @throws CommandeImpossiblePourLeVivantException Si la commande est impossible
     * @throws Throwable                               Si une erreur survient
     */
    private void executerCommande(String commande, ArrayList<String> parametres)
            throws CommandeImpossiblePourLeVivantException, Throwable {
        try {
            // On récupère les types des paramètres
            Class<?>[] types = this.obtenirTypes(parametres);

            // On récupère la méthode correspondant à la commande (introspection)
            Method methode = this.getClass().getDeclaredMethod(commande, types);

            // On appelle la méthode
            methode.invoke(this, parametres.toArray());

        } catch (NoSuchMethodException e) {
            throw new CommandeImpossiblePourLeVivantException("Commande inconnue !");
        } catch (InvocationTargetException e) {
            throw new CommandeImpossiblePourLeVivantException(
                    String.format("Commande impossible : %s", e.getCause().getMessage()));
        } catch (IllegalAccessException e) {
            throw new CommandeImpossiblePourLeVivantException(
                    String.format("Commande impossible : %s", e.getMessage()));
        }
    }

    @Override
    public void executer() throws CommandeImpossiblePourLeVivantException, Throwable {
        // Si l'ordre est null, on ne fait rien
        if (this.ordre == null) {
            return;
        }

        // On découpe l'ordre en commande et paramètres
        Scanner scanner = new Scanner(ordre);
        // On réinitialise l'ordre
        ordre = null;

        String commande = this.obtenirCommande(scanner); // On récupère la commande
        ArrayList<String> parametres = this.obtenirParametre(scanner); // On récupère les paramètres

        // On exécute la commande
        this.executerCommande(commande, parametres);
    }

    /**
     * Définit l'ordre de l'utilisateur
     * 
     * @param ordre Ordre de l'utilisateur
     */
    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    /**
     * Commande de l'utilisateur pour prendre un objet
     * 
     * @param nom Nom de l'objet
     * @throws ObjetAbsentDeLaPieceException
     * @throws ObjetNonDeplacableException
     */
    private void commandePrendre(String nom)
            throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        this.prendre(nom);
    }

    /**
     * Commande de l'utilisateur pour déposer un objet
     * 
     * @param nomObjet Nom de l'objet
     * @throws ObjetNonPossedeParLeVivantException
     */
    private void commandePoser(String nomObjet)
            throws ObjetNonPossedeParLeVivantException {
        this.deposer(nomObjet);
    }

    /**
     * Commande de l'utilisateur pour franchir une porte
     * 
     * @param nomPorte Nom de la porte
     * @throws PorteFermeException
     * @throws PorteInexistanteDansLaPieceException
     */
    private void commandeFranchir(String nomPorte)
            throws PorteFermeException, PorteInexistanteDansLaPieceException {
        this.franchir(nomPorte);
    }

    /**
     * Commande de l'utilisateur pour ouvrir une porte
     * 
     * @param nomPorte Nom de la porte
     * @throws ActivationException
     * @throws PorteInexistanteDansLaPieceException
     */
    private void commandeOuvrirPorte(String nomPorte)
            throws ActivationException, PorteInexistanteDansLaPieceException {
        this.activerActivable((Porte) this.getMonde().getEntite(nomPorte));
    }

    /**
     * Commande de l'utilisateur pour ouvrir une porte avec un objet
     * 
     * @param nomPorte Nom de la porte
     * @param nomObjet Nom de l'objet
     * @throws ActivationException
     * @throws PorteInexistanteDansLaPieceException
     * @throws ObjetNonPossedeParLeVivantException
     */
    private void commandeOuvrirPorte(String nomPorte, String nomObjet)
            throws ActivationException, PorteInexistanteDansLaPieceException,
            ObjetNonPossedeParLeVivantException {
        this.activerActivableAvecObjet((Porte) this.getMonde().getEntite(nomPorte),
                (Objet) this.getMonde().getEntite(nomObjet));
    }

}
