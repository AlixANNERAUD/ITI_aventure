package fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;

public class TestSerrure {

    private Monde monde;
    private Serrure serrure;

    @Before
    public void setUp() throws ITIAventureException {
        this.monde = new Monde("monde");
        this.serrure = new Serrure(monde);
    }

    @Test
    void testCreerClef() {
        assertThat(this.serrure.creerClef(), is(Clef.class));
        assertThat(this.serrure.creerClef(), is(null));
    }

    

    @After
    public void tearDown() {
        this.monde = null;
        this.serrure = null;
    }

}
