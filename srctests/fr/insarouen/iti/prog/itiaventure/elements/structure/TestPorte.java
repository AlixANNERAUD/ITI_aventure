package fr.insarouen.iti.prog.itiaventure.elements.structure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;

public class TestPorte {

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
    private Piece pieceA;
    private Piece pieceB;

    @Before
    public void setUp() throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = new Monde("monde");
        this.pieceA = new Piece("pieceA", this.monde);
        this.pieceB = new Piece("pieceB", this.monde);
    }

    @Test
    public void testConstructeur() throws NomDEntiteDejaUtiliseDansLeMondeException {
        Porte porte = new Porte("porte", this.monde, this.pieceA, this.pieceB);
        assertThat(porte.getNom(), is("porte"));
        assertThat(porte.getMonde(), is(this.monde));
        assertThat(porte.getEtat(), is(Etat.FERME));
        assertThat(porte.getPieceAutreCote(pieceA), is(this.pieceB));
        assertThat(porte.getPieceAutreCote(pieceB), is(this.pieceA));
    }


    @Test
    public void testActiver() throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException {
        Porte porte = new Porte("porte", this.monde, this.pieceA, this.pieceB);
        assertThat(porte.getEtat(), is(Etat.FERME));
        porte.activer();
        assertThat(porte.getEtat(), is(Etat.OUVERT));
    }

    @Test
    public void testActivableAvec() throws NomDEntiteDejaUtiliseDansLeMondeException {
        Porte porte = new Porte("porte", this.monde, this.pieceA, this.pieceB);
        Objet objet = new ObjetTest("objet", this.monde);
        assertThat(porte.activableAvec(objet), is(true));
    }

    @Test
    public void testActiverAvec() throws ITIAventureException {
        // TODO
        // Objet objet = new ObjetTest("objet", this.monde);
        // this.porte.activerAvec(objet);
        // assertThat(this.porte.getEtat(), is(Etat.OUVERT));

    }

    @After
    public void tearDown() {
        this.monde = null;
        this.pieceA = null;
        this.pieceB = null;
    }
}
