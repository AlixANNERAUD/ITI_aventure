package fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;

public class TestClef {

    private Monde monde;
    Clef clef;

    @Before
    public void setUp() throws ITIAventureException {
        this.monde = new Monde("monde");
        this.clef = new Clef("clef", this.monde);
    }

    @Test
    public void testConstructeur() {
        assertThat(this.clef.estDeplacable(), is(true));
    }

    @After
    public void tearDown() {
        this.monde = null;
        this.clef = null;
    }
}
