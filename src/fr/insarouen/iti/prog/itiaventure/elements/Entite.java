package fr.insarouen.iti.prog.itiaventure.elements;

import fr.insarouen.iti.prog.itiaventure.Monde;

/**
 * Classe générique pour les entités.
 */
public abstract class Entite {

    /**
     * Monde dans lequel se trouve l'entité.
     */
    private final Monde monde;

    /**
     * Nom de l'entité.
     */
    private final String nom;

    /**
     * Constructeur Entite.
     * @param nom Nom de l'entité.
     * @param monde Monde dans lequel se trouve l'entité.
     */
    public Entite(String nom, Monde monde) {
        this.monde = monde;
        this.nom = nom;

        monde.ajouter(this);
    }

    /**
     * Retourne le nom de l'entité.
     * @return Nom de l'entité.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne le monde dans lequel se trouve l'entité.
     * @return Monde dans lequel se trouve l'entité.
     */
    public Monde getMonde() {
        return this.monde;
    }

    public String toString() {
        return String.format("Entite : %s", nom);
    }

    public boolean equals(Object o) {
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Entite e = (Entite) o;
        return this.nom == e.nom && this.monde == e.monde;
    }

    public int hashCode() {
        return 13 * this.nom.hashCode() + 17 * this.monde.hashCode();
    }
}