package fr.insarouen.iti.prog.itiaventure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.JoueurHumain;

/**
 * Classe représentant un monde.
 */
public class Monde implements Serializable {

   /**
    * Tableau des entités du monde.
    */
   private Map<String, Entite> entites = new HashMap<String, Entite>();

   /**
    * Nom du monde.
    */
   private final String nom;

   /**
    * Constructeur Monde.
    * 
    * @param nom Nom du monde.
    */
   public Monde(String nom) {
      this.nom = nom;
   }

   public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Monde : ");
      stringBuilder.append(this.nom);
      stringBuilder.append("\n");
      for (Entite entite : this.entites.values()) {
         stringBuilder.append(entite.toString());
         stringBuilder.append("\n");
      }
      return stringBuilder.toString();
   }

   /**
    * Retourne le nom du monde.
    * 
    * @return Nom du monde.
    */
   public String getNom() {
      return this.nom;
   }

   /**
    * Retourne l'entité du monde correspondant au nom passé en paramètre.
    * 
    * @param nomEntite Nom de l'entité.
    * @return Entité correspondant au nom passé en paramètre.
    */
   public Entite getEntite(String nomEntite) {
      return this.entites.get(nomEntite);
   }

   /**
    * Retourne les exécutables du monde.
    *
    * @return Exécutables du monde.
    */
   public Collection<Executable> getExecutables() {
      return this.entites
            // On récupère les valeurs de la map (les entités)
            .values()
            // On transforme les entités en stream (permet de les manipuler plus facilement)
            .stream()
            // On filtre les entités qui sont des exécutables (utilisation de fonction
            // lambda)
            .filter(entite -> entite instanceof Executable)
            // On transtype les entités en exécutables
            .map(entite -> (Executable) entite)
            // On collecte les exécutables dans une liste
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
   }

   /**
    * Ajoute une entité au monde.
    * 
    * @param entite Entité à ajouter.
    */
   public void ajouter(Entite entite)
         throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {

      if (entite.getMonde() != this) {
         throw new EntiteDejaDansUnAutreMondeException(
               String.format("L'entité %s est déjà dans un autre monde", entite.getNom()));
      }

      if (this.getEntite(entite.getNom()) != null) {
         throw new NomDEntiteDejaUtiliseDansLeMondeException(
               String.format("L'entité %s est déjà dans le monde", entite.getNom()));
      }

      this.entites.put(entite.getNom(), entite);
   }
}