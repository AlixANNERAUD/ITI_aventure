package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestObjet {
    public static class ObjetTestNonDeplacable extends Objet {

        public ObjetTestNonDeplacable(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
            super(nom, monde);
        }

        @Override
        public boolean estDeplacable() {
            return false;
        }
    }

    public static class ObjetTestDeplacable extends Objet {

        public ObjetTestDeplacable(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
            super(nom, monde);
        }

        @Override
        public boolean estDeplacable() {
            return true;
        }
    }


    private ObjetTestDeplacable objet_deplacable;
    private ObjetTestNonDeplacable objet_non_deplacable;

    private Monde monde;

    @Before
    public void setUp() throws ITIAventureException {
        this.monde = new Monde("monde");
        this.objet_deplacable = new ObjetTestDeplacable("objet 1", monde);
        this.objet_non_deplacable = new ObjetTestNonDeplacable("objet 2", monde);
    }

    @Test 
    public void test() {
        assertThat(this.objet_deplacable.getNom(), is("objet 1"));
        assertThat(this.objet_deplacable.getMonde(), is(this.monde));
        assertThat(this.objet_non_deplacable.getNom(), is("objet 2"));
        assertThat(this.objet_non_deplacable.getMonde(), is(this.monde));

        assertThat(this.objet_deplacable.estDeplacable(), is(true));
        assertThat(this.objet_non_deplacable.estDeplacable(), is(false));
       
        assertThat(this.objet_deplacable.equals(this.objet_deplacable), is(true));
        assertThat(this.objet_deplacable.equals(this.objet_non_deplacable), is(false));

    }

    @After 
    public void tearDown() {
        this.objet_deplacable = null;
        this.objet_non_deplacable = null;
        this.monde = null;
    }
}
