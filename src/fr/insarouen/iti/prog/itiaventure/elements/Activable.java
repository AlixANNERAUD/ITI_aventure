package fr.insarouen.iti.prog.itiaventure.elements;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;

/**
 * Activable est une interface représentant un élément activable.
 * 
 * @see Objet
 * @see ActivationException
 */
public interface Activable {

    /**
     * Méthode permettant d'activer l'élément.
     * 
     * @throws ActivationException
     */
    public boolean activableAvec(Objet objet);

    /**
     * Méthode permettant d'activer l'élément avec un objet.
     * 
     * @param obj Objet avec lequel activer l'élément.
     * @throws ActivationException
     */
    public void activer() throws ActivationException;

    /**
     * Méthode permettant d'activer l'élément avec un objet.
     * 
     * @param obj Objet avec lequel activer l'élément.
     * @throws ActivationException
     */
    public void activerAvec(Objet obj) throws ActivationException;
}
