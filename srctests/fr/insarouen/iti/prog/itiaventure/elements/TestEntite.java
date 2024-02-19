package fr.insarouen.iti.prog.itiaventure.elements;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;

import org.hamcrest.core.IsEqual;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEntite {

    class EntiteTest extends Entite {

        public EntiteTest(String nom, Monde monde) throws ITIAventureException {
            super(nom, monde);
        }
    }

    private Entite entite;

    @Before
    public void setUp() throws ITIAventureException {
        Monde monde = new Monde("monde");
        entite = new EntiteTest("entite", monde);
    }

    @Test
    public void testGetNom() {
        assertThat(entite.getNom(), is("entite"));
    }

    @After
    public void tearDown() {
        entite = null;
    }
}

