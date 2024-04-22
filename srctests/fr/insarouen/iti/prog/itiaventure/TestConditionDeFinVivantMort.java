package fr.insarouen.iti.prog.itiaventure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.TestObjet.ObjetTestDeplacable;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.itiaventure.Monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConditionDeFinVivantMort {
    private Monde monde1;
    private Monde monde2;
    private Piece piece1, piece2;
    private Objet[] objets;
    private Porte porte;
    private Vivant vivant1;
    private Vivant vivant2;

    @Before
    public void avantTest() throws ITIAventureException {
        this.monde1 = new Monde("monde1");
        this.monde2 = new Monde("monde2");
        this.piece1 = new Piece("piece1", monde1);
        this.piece2 = new Piece("piece2", monde2);
        this.porte = new Porte("porte", monde1, piece1, piece2);
        this.porte.activer();
        
        
        this.objets = new Objet[10];
        for (int i = 0; i < 10; i++) {
            objets[i] = new ObjetTestDeplacable("objet" + i, monde1);
        }
        
        this.piece2.deposer(objets[2]);
        this.vivant1 = new Vivant("Taoba", monde1, 0, 0, piece1);
        this.vivant2 = new Vivant("Alix", monde2, 3, 2, piece2, objets[1]);
    }

    @Test
    public void testConditionDeFinVivantMort() {
        ConditionDeFin c1 = new ConditionDeFinVivantMort(EtatDuJeu.ECHEC, vivant1);
        assertThat(c1.verifierEtatDuJeu(), is(EtatDuJeu.ECHEC));
        ConditionDeFin c2 = new ConditionDeFinVivantMort(EtatDuJeu.ECHEC, vivant2);
        assertThat(c2.verifierEtatDuJeu(), is(EtatDuJeu.ENCOURS));
    }

    @Test
    public void testConditionDeFinVivantPossedeObjet() throws ITIAventureException {
        ConditionDeFin c = new ConditionDeFinVivantPossedeObjets(EtatDuJeu.ECHEC, vivant2, objets[2]);
        assertThat(c.verifierEtatDuJeu(), is(EtatDuJeu.ENCOURS));
        vivant2.prendre(objets[2]);
        assertThat(c.verifierEtatDuJeu(), is(EtatDuJeu.ECHEC));
    }

    @Test
    public void testConditionDeFinVivantDansPieceEtPossedeObjets() throws ITIAventureException {
        ConditionDeFin c = new ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu.ECHEC, vivant2, piece2, objets[2]);
        assertThat(c.verifierEtatDuJeu(), is(EtatDuJeu.ENCOURS));
        vivant2.prendre(objets[2]);
        assertThat(c.verifierEtatDuJeu(), is(EtatDuJeu.ECHEC));
    }

    @Test
    public void testConditionDeFinConjonctionDeConditionDeFin() throws ITIAventureException {
        ConditionDeFin c1 = new ConditionDeFinVivantMort(EtatDuJeu.ECHEC, vivant1);
        ConditionDeFin c2 = new ConditionDeFinVivantPossedeObjets(EtatDuJeu.ECHEC, vivant2, objets[2]);
        ConditionDeFin c = new ConditionDeFinConjonctionDeConditionDeFin(EtatDuJeu.ECHEC, c1, c2);

        assertThat(c.verifierEtatDuJeu(), is(EtatDuJeu.ENCOURS));
        vivant2.prendre(objets[2]);
        assertThat(c.verifierEtatDuJeu(), is(EtatDuJeu.ECHEC));
    }

    @After
    public void apresTest() {
        this.monde1 = null;
        this.monde2 = null;
        this.piece1 = null;
        this.piece2 = null;
        this.porte = null;
        this.vivant1 = null;
        this.vivant2 = null;
    }

}
