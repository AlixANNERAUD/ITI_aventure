package fr.insarouen.iti.prog.itiaventure.elements.structure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.itiaventure.elements.objets.TestObjet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Serrure;

public class TestPorte {

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
    public void testActivation() throws NomDEntiteDejaUtiliseDansLeMondeException {
        Porte porte = new Porte("porte", this.monde, this.pieceA, this.pieceB);
        Objet objet = new TestObjet.ObjetTestDeplacable("objet", this.monde);
        assertThat(porte.activableAvec(objet), is(false));
        PiedDeBiche piedDeBiche = new PiedDeBiche(this.monde);
        assertThat(porte.activableAvec(piedDeBiche), is(true));

        Serrure serrure = new Serrure("serrure", this.monde);
        Clef clef = serrure.creerClef();
        Porte porteAvecSerrure = new Porte("porteAvecSerrure", this.monde, serrure, this.pieceA, this.pieceB);
        assertThat(porteAvecSerrure.activableAvec(objet), is(false));
        assertThat(porteAvecSerrure.activableAvec(piedDeBiche), is(true));
        assertThat(porteAvecSerrure.activableAvec(clef), is(true));
    }

    @After
    public void tearDown() {
        this.monde = null;
        this.pieceA = null;
        this.pieceB = null;
    }
}
