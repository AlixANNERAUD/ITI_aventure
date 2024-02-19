package fr.insarouen.iti.prog.itiaventure.elements.objets;

import static org.hamcrest.core.Is.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestPiedDeBiche {

    private PiedDeBiche piedDeBiche;
    private Monde monde;

    @Before
    public void setUp() throws ITIAventureException {
        this.monde = new Monde("monde");
        this.piedDeBiche = new PiedDeBiche(this.monde);
    }

    @Test
    public void testConstructeur() {
        assertThat(this.piedDeBiche.getNom(), is("Pied de biche"));
        assertThat(this.piedDeBiche.getMonde(), is(this.monde));
    }

    @Test
    public void testEstDeplacable() {
        assertThat(this.piedDeBiche.estDeplacable(), is(true));
    }

    @After
    public void tearDown() {
        this.piedDeBiche = null;
        this.monde = null;
    }
}
