package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.TestObjet.ObjetTestDeplacable;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.JoueurHumain;

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
    private Objet objet1;
    private Objet objet2;

    @Before
    public void setUp() throws ITIAventureException {
        this.monde = new Monde("monde");
        this.objet1 = new ObjetTestDeplacable("objet1", this.monde);
        this.objet2 = new ObjetTestDeplacable("objet2", this.monde);
    }

    @Test
    public void testGetNom() {
        assertThat(this.monde.getNom(), is("monde"));
    }

    @Test
    public void testGetEntite() {
        assertThat(this.monde.getEntite("objet1"), is(this.objet1));
        assertThat(this.monde.getEntite("objet2"), is(this.objet2));
        assertThat(this.monde.getEntite("objet3") == null, is(true));
    }

    @Test
    public void testAjouter() throws ITIAventureException {
        Entite entite = new EntiteTest("entite", this.monde);
        assertThat(this.monde.getEntite("entite"), is(entite));

        assertThrows(NomDEntiteDejaUtiliseDansLeMondeException.class, () -> {
            Entite entite2 = new EntiteTest("entite", this.monde);
            this.monde.ajouter(entite2);
        });

        for (int i = 0; i < 10; i++) {
            Entite entite2 = new EntiteTest("entite" + i, this.monde);
            assertThat(this.monde.getEntite("entite" + i), is(entite2));
        }
    }

    @Test
    public void testAjouterDejaPresent() throws ITIAventureException {
        Entite entite = new EntiteTest("entite", this.monde);
        assertThat(this.monde.getEntite("entite"), is(entite));

        assertThrows(NomDEntiteDejaUtiliseDansLeMondeException.class, () -> {
            Entite entite2 = new EntiteTest("entite", this.monde);
            this.monde.ajouter(entite2);
        });
    }

    @Test
    public void testAjouterMondeDifferent() throws ITIAventureException {
        Monde monde2 = new Monde("monde2");

        assertThrows(EntiteDejaDansUnAutreMondeException.class, () -> {
            monde2.ajouter(this.objet1);
        });

    }

    @Test
    public void testGetExecutables() throws ITIAventureException {
        Piece piece = new Piece("piece", this.monde);
        JoueurHumain joueur = new JoueurHumain("joueur", this.monde, 0, 0, piece);
        assertThat(this.monde.getExecutables().size(), is(1));
        assertThat(this.monde.getExecutables().contains(joueur), is(true));

    }

    @After
    public void tearDown() {
        this.monde = null;
        this.objet1 = null;
        this.objet2 = null;
    }
}
