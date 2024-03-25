package fr.insarouen.iti.prog.itiaventure.elements.objets;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;

public class TestCoffre {

    private Monde monde;
    private Coffre coffre;

    @Before
    public void setUp() throws ITIAventureException {
        this.monde = new Monde("monde");
        this.coffre = new Coffre("coffre", this.monde);
        assertThat(this.coffre.estDeplacable(), is(false));
    }

    @Test
    public void testActivable() throws ITIAventureException {
        assertThat(this.coffre.getEtat(), is(Etat.FERME));
        this.coffre.activer();
        assertThat(this.coffre.getEtat(), is(Etat.OUVERT));
        this.coffre.activer();
        assertThat(this.coffre.getEtat(), is(Etat.FERME));        
    }

    @After
    public void tearDown() {
        this.monde = null;
        this.coffre = null;
    }
}
