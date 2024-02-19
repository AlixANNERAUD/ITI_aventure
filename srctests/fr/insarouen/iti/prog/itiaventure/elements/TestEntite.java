package fr.insarouen.iti.prog.itiaventure.elements;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEntite {

    class EntiteTest extends Entite {

        public EntiteTest(String nom, Monde monde) throws ITIAventureException {
            super(nom, monde);
        }
    }

    private Entite entite;
    private Monde monde;

    @Before
    public void setUp() throws ITIAventureException {
        this.monde = new Monde("monde");
        this.entite = new EntiteTest("entite", monde);
    }

    @Test
    public void testGetNom() {
        assertThat(entite.getNom(), is("entite"));
    }

    @Test
    public void testGetMonde() {
        assertThat(entite.getMonde(), is(monde));
    }

    @After
    public void tearDown() {
        this.entite = null;
        this.monde = null;
    }
}

