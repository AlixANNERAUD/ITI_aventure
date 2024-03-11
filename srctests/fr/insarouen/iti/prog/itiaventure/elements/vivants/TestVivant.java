package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;

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
    private Piece piece;
    private ObjetTest[] objets;

    @Before
    public void avantTest() throws Exception {
        this.monde = new Monde("monde");
        this.piece = new Piece("piece", this.monde);
        this.objets = new ObjetTest[10];
        for (int i = 0; i < 10; i++) {
            this.objets[i] = new ObjetTest("objet" + i, this.monde);
        }
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

        assertThat(Arrays.asList(this.objets).containsAll(Arrays.asList(vivant.getObjets())), is(true));        
        assertThat(Arrays.asList(vivant.getObjets()).containsAll(Arrays.asList(this.objets)), is(true));
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

    @After
    public void apresTest() {
        this.monde = null;
        this.piece = null;
        this.objets = null;
    }

}
