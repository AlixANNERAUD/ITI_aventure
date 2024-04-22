package fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.TestObjet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.TestObjet.ObjetTestDeplacable;

public class TestSerrure {

    private Monde monde;
    private Serrure serrure;

    @Before
    public void setUp() throws ITIAventureException {
        this.monde = new Monde("monde");
        this.serrure = new Serrure(monde);
    }

    @Test
    public void testCreerClef() {
        assertThat(this.serrure.creerClef().getClass(), is(Clef.class));
        assertThat(this.serrure.creerClef() == null, is(true));
    }

    @Test
    public void testActiver() {
        assertThrows(ActivationImpossibleException.class, () -> {
            this.serrure.activer();
        });
    }

    @Test
    public void testActivableAvec() throws ITIAventureException {
        assertThat(this.serrure.activableAvec(this.serrure.creerClef()), is(true));
        assertThat(this.serrure.activableAvec(new ObjetTestDeplacable("o", this.monde)), is(false));
    }

    @Test
    public void testEstDeplacable() {
        assertThat(this.serrure.estDeplacable(), is(false));
    }

    @After
    public void tearDown() {
        this.monde = null;
        this.serrure = null;
    }

}
