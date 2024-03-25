package fr.insarouen.iti.prog.itiaventure.elements.structure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.TestObjet;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPiece {

   private Monde monde;
   private Piece piece;

   @Before
   public void setUp() throws NomDEntiteDejaUtiliseDansLeMondeException {
      this.monde = new Monde("monde");
      this.piece = new Piece("piece", this.monde);
   }

   @Test
   public void testDeposer() throws NomDEntiteDejaUtiliseDansLeMondeException {
      Objet objet = new TestObjet.ObjetTestDeplacable("objet", this.monde);
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
      Objet objet = new TestObjet.ObjetTestDeplacable("objet", this.monde);
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

   @Test
   public void testAjouterPorte() throws ITIAventureException {
      Piece pieceB = new Piece("pieceB", this.monde);
      Porte porte = new Porte("porte", this.monde, this.piece, pieceB);
      this.piece.addPorte(porte);
      assertThat(this.piece.aLaPorte(porte), is(true));
      assertThat(pieceB.aLaPorte(porte.getNom()), is(true));
      assertThat(this.piece.getPorte(porte.getNom()), is(porte));
   }

   @After
   public void apresTest() {
      this.monde = null;
      this.piece = null;
   }

}