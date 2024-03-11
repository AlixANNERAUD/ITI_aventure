package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;

public class TestVivant {

    class ObjetTest extends Objet {
        public ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
            super(nom, monde);
        }

        @Override
        public boolean estDeplacable() {
            return true;
        }
    }

    private Monde monde;
    private Piece piece, pieceB;
    private ObjetTest[] objets;
    private Porte porte;

    @Before
    public void avantTest() throws Exception {
        this.monde = new Monde("monde");
        this.piece = new Piece("piece", this.monde);
        this.pieceB = new Piece("pieceB", this.monde);
        this.objets = new ObjetTest[10];
        for (int i = 0; i < 10; i++) {
            this.objets[i] = new ObjetTest("objet" + i, this.monde);
        }
        this.porte = new Porte("porte", this.monde, this.piece, this.pieceB);
    }

    @Test
    public void testConstructeur() throws NomDEntiteDejaUtiliseDansLeMondeException {
        Vivant vivant = new Vivant("vivant", this.monde, 2, 3, this.piece, this.objets[0], this.objets[1],
                this.objets[2],
                this.objets[3], this.objets[4], this.objets[5], this.objets[6], this.objets[7], this.objets[8],
                this.objets[9]);

        
        assertThat(vivant.getNom(), is("vivant"));
        assertThat(vivant.getMonde(), is(this.monde));
        assertThat(vivant.getPiece(), is(this.piece));
        assertThat(vivant.getPointVie(), is(2));
        assertThat(vivant.getPointForce(), is(3));

        
    }

    @Test
    public void testDeposer() throws ITIAventureException {
        Vivant vivant = new Vivant("vivant", this.monde, 2, 3, this.piece, this.objets);
        for (int i = 0; i < 10; i++) {
            vivant.deposer(this.objets[i]);
            assertThat(vivant.possede(this.objets[i]), is(false));
            assertThat(piece.contientObjet(this.objets[i]), is(true));
        }
        assertThrows(ObjetNonPossedeParLeVivantException.class, () -> vivant.deposer(this.objets[0]));

    }

    @Test
    public void testPrendre() throws ITIAventureException {
        Vivant vivant = new Vivant("vivant", this.monde, 2, 3, this.piece, this.objets);
        for (int i = 0; i < 10; i++) {
            this.piece.deposer(this.objets[i]);
            vivant.prendre(this.objets[i]);
            assertThat(vivant.possede(this.objets[i]), is(true));
        }
    }

    @Test 
    public void testFranchir() throws ITIAventureException {
        Vivant vivant = new Vivant("vivant", this.monde, 2, 3, this.piece, this.objets);
        assertThat(vivant.getPiece(), is(piece));
        assertThat(this.piece.contientVivant(vivant), is(true));

        vivant.activerActivable(this.porte);
        assertThat(this.porte.getEtat(), is(Etat.OUVERT));

        vivant.franchir(porte);
        assertThat(vivant.getPiece().equals(this.pieceB), is(true));
        assertThat(this.piece.contientVivant(vivant), is(false));
    }    

    @Test
    public void testEstMort() throws ITIAventureException {
        Vivant vivantA = new Vivant("vivantA", this.monde, 2, 3, this.piece, this.objets);
        assertThat(vivantA.estMort(), is(false));
        Vivant vivantB = new Vivant("vivantB", this.monde, 0, 3, this.piece, this.objets);
        assertThat(vivantB.estMort(), is(true));
    }

    @After
    public void apresTest() {
        this.monde = null;
        this.piece = null;
        this.objets = null;
        this.porte = null;
        this.pieceB = null;
    }

}
