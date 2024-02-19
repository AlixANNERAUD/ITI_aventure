package fr.insarouen.iti.prog.itiaventure.elements.structure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.TestObjet;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

import org.hamcrest.core.IsEqual;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPiece {

   class ObjetTest extends Objet {
      ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
         super(nom, monde);
      }

      @Override
      public boolean estDeplacable() {
         return true;
      }
   }

   private Monde monde;
   private Piece piece;

   @Before
   public void setUp() throws NomDEntiteDejaUtiliseDansLeMondeException {
      this.monde = new Monde("monde");
      this.piece = new Piece("piece", this.monde);
   }

   @Test
   public void testDeposer() throws NomDEntiteDejaUtiliseDansLeMondeException {
      Objet objet = new ObjetTest("objet", this.monde);
      this.piece.deposer(objet);
      assertThat(this.piece.contientObjet(objet), is(true));

   }

   @Test
   public void testEntrer() throws ITIAventureException {
      Vivant vivant = new Vivant("vivant", this.monde, 2, 3, this.piece);
      this.piece.entrer(vivant);
      assertThat(this.piece.contientVivant(vivant), is(true));
   }

   @Test
   public void testRetirer() throws ITIAventureException {
      Objet objet = new ObjetTest("objet", this.monde);
      this.piece.deposer(objet);
      this.piece.retirer(objet);
      assertThat(this.piece.contientObjet(objet), is(false));
   }

   @Test
   public void testSortir() throws ITIAventureException {
      Vivant vivant = new Vivant("vivant", this.monde, 2, 3, this.piece);
      this.piece.entrer(vivant);
      this.piece.sortir(vivant);
      assertThat(this.piece.contientVivant(vivant), is(false));
   }

   @After
    public void apresTest() {
        this.monde = null;
        this.piece = null;
    }

}