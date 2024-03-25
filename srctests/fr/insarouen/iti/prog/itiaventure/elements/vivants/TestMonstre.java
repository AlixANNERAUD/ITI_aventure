package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.TestObjet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;

public class TestMonstre {

    private Monde monde;
    private Piece piece1, piece2;
    private Objet[] objets;
    private Porte porte;

    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException {
        this.monde = new Monde("monde");
        this.piece1 = new Piece("piece1", monde);
        this.piece2 = new Piece("piece2", monde);
        this.porte = new Porte("porte", monde, piece1, piece2);
        this.porte.activer();

        this.objets = new Objet[10];
        for (int i = 0; i < 10; i++) {
            this.objets[i] = new TestObjet.ObjetTestDeplacable("objet" + i, monde);
        }

        for (int i = 10; i < 20; i++) {
            this.piece1.deposer(new TestObjet.ObjetTestDeplacable("objet" + i, monde));
        }

        for (int i = 20; i < 30; i++) {
            this.piece2.deposer(new TestObjet.ObjetTestDeplacable("objet" + i, monde));
        }
    }

    @Test
    public void testMonstreVivant() throws ITIAventureException {
        Monstre monstre = new Monstre("monstre1", this.monde, 69, 0, this.piece1, this.objets);

        assertThat(monstre.getPiece().equals(piece1), is(true));

        ArrayList<Objet> anciensObjetsMonstre = new ArrayList<Objet>(monstre.getObjets());
        ArrayList<Objet> anciensObjetsPiece2 = new ArrayList<Objet>(this.piece2.getObjets());

        int ancienPointdeVie = monstre.getPointVie();

        monstre.executer();

        assertThat(monstre.getPiece().equals(piece2), is(true)); // monstre a bien changé de pièce
        // assertThat(this.piece2.getObjets(),
        // containsInAnyOrder(anciensObjetsMonstre)); // monstre a bien
        // déposé ses objets
        // assertThat(monstre.getObjets(), containsInAnyOrder(anciensObjetsPiece2)); //
        // monstre a bien pris les
        // objets de la piece

        assertThat(monstre.getPointVie(), is(ancienPointdeVie - 1)); // monstre a bien perdu un point de vie
    }

    @Test
    public void testMonstreMort() throws ITIAventureException {
        Monstre monstre = new Monstre("monstre", this.monde, 0, 0, this.piece1, this.objets);

        Collection<Objet> anciensObjetsMonstre = monstre.getObjets();
        Collection<Objet> anciensObjetsPiece2 = this.piece2.getObjets();

        assertThat(monstre.estMort(), is(true));

        monstre.executer();

    //    assertThat(this.piece2.getObjets(), containsInAnyOrder(anciensObjetsPiece2)); // monstre n'a pas pris les objets
        // assertThat(monstre.getObjets(), containsInAnyOrder(anciensObjetsMonstre)); //
        // monstre n'a pas déposé ses objets

    }

    @After
    public void apresTest() {
        this.monde = null;
        this.piece1 = null;
        this.piece2 = null;
        this.objets = null;
        this.porte = null;
    }
}
