package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThrows;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestMonde {

    class EntiteTest extends Entite {
        public EntiteTest(String nom, Monde monde) throws ITIAventureException {
            super(nom, monde);
        }
    }

    private Monde monde;

    @Before
    public void setUp() {
        this.monde = new Monde("monde");
    }

    @Test
    public void testGetNom() {
        assertThat(this.monde.getNom(), is("monde"));
    }

    @Test
    public void testGetEntite() throws ITIAventureException {
        assertThat(this.monde.getEntite("entite"), is((Entite) null));
        Entite entite = new EntiteTest("entite", this.monde);
        assertThat(this.monde.getEntite("entite"), is(entite));
    }

    @Test
    public void testAjouter() throws ITIAventureException {
        Entite entite = new EntiteTest("entite", this.monde);
        assertThat(this.monde.getEntite("entite"), is(entite));

        assertThrows(NomDEntiteDejaUtiliseDansLeMondeException.class, () -> {
            Entite entite2 = new EntiteTest("entite", this.monde);
            this.monde.ajouter(entite2);
        });
        
        for (int i = 0; i < 1000; i++) {
            Entite entite2 = new EntiteTest("entite" + i, this.monde);
            assertThat(this.monde.getEntite("entite" + i), is(entite2));
        }
    }

    @After
    public void tearDown() {
        this.monde = null;
    }
}
