package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;

public class Monde {

   private Entite[] entites;
   private final String nom;

   public Monde(String nom) {
      this.nom = nom;
      this.entites = new Entite[0];
   }

   public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Monde : ");
      stringBuilder.append(this.nom);
      stringBuilder.append("\n");
      for (int i = 0; i < this.entites.length; i++) {
         stringBuilder.append(entites[i].toString());
         stringBuilder.append("\n");
      }
      return stringBuilder.toString();
   }

   public String getNom() {
      return this.nom;
   }

   public Entite getEntite(String nomEntite) {

      for (int i = 0; i < this.entites.length; i++) {
         if (this.entites[i].getNom().equals(nomEntite)) {
            return this.entites[i];
         }
      }
      return null;
   }

   public void ajouter(Entite entite) {
      Entite[] entites = new Entite[this.entites.length + 1];

      for (int i = 0; i < this.entites.length; i++) {
         entites[i] = this.entites[i];
      }

      entites[this.entites.length] = entite;

      this.entites = entites;

   }
}