package fr.insarouen.iti.prog.itiaventure;

import java.util.HashMap;
import java.util.Map;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;

/**
 * Classe représentant un monde.
 */
public class Monde {

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