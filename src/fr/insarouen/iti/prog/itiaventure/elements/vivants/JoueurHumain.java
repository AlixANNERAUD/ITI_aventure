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

    public JoueurHumain(String nom, Monde monde, int pointVie, int pointForce, Piece piece)
            throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointVie, pointForce, piece);
    }

    private String obtenirCommande(Scanner scanner) {
        return "commande" + scanner.next();
    }

    private ArrayList<String> obtenirParametre(Scanner scanner) {
        ArrayList<String> parametres = new ArrayList<String>();
        while (scanner.hasNext()) {
            parametres.add(scanner.next());
        }
        return parametres;
    }

    private Class<?>[] obtenirTypes(ArrayList<String> parametres) {
        List<Class<?>> types = parametres.stream().map(String::getClass).collect(Collectors.toList());
        return types.toArray(new Class<?>[types.size()]);
    }

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
            throw new CommandeImpossiblePourLeVivantException("Commande inconnue : " + commande + " : " + e.getMessage());
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

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    private void commandePrendre(String nom)
            throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        this.prendre(nom);
    }

    private void commandePoser(String nomObjet)
            throws ObjetNonPossedeParLeVivantException {
        this.deposer(nomObjet);
    }

    private void commandeFranchir(String nomPorte)
            throws PorteFermeException, PorteInexistanteDansLaPieceException {
        this.franchir(nomPorte);
    }

    private void commandeOuvrirPorte(String nomPorte)
            throws ActivationException, PorteInexistanteDansLaPieceException {
        this.activerActivable((Porte) this.getMonde().getEntite(nomPorte));
    }

    private void commandeOuvrirPorte(String nomPorte, String nomObjet)
            throws ActivationException, PorteInexistanteDansLaPieceException,
            ObjetNonPossedeParLeVivantException {
        this.activerActivableAvecObjet((Porte) this.getMonde().getEntite(nomPorte),
                (Objet) this.getMonde().getEntite(nomObjet));
    }

}
