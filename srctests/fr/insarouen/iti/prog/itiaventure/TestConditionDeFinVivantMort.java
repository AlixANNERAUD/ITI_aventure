package fr.insarouen.iti.prog.itiaventure;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

import 
public class TestConditionDeFinVivantMort {
    private Monde monde1;
    private Monde monde2;
    private Piece piece1, piece2;
    private Objet[] objets;
    private Porte porte;
    private Vivant vivant1;
    private Vivant vivant2;
    private EtatDuJeu etat;
    private ConditionDeFin[] conditions;
    @Before 
    public void avantTest() {
        this.monde1 = new Monde("monde1");
        this.monde2 = new Monde("monde2");
        this.piece1 = new Piece("piece1", monde1);
        this.piece2 = new Piece("piece2", monde2);
        this.porte = new Porte("porte", monde1, piece1, piece2);
        this.porte.activer();
        this.vivant1 = new Vivant("Taoba",monde1,0,0,piece1,objets);
        this.vivant2 = new Vivant("Alix", monde2,3,2,piece2,objets);
        this.etat = EtatDuJeu.ENCOURS;
        
    }
    @Test 
    public void TestConditionDeFin() {
        EtatDuJeu etat = new ConditionDeFin(EtatDuJeu.ENCOURS);
        assertThat(getEtatConditionVerifiee(etat).equals(EtatDuJeu.ENCOURS), is(true));
    }

    @Test 
    public void TestConditionDeFinVivantMortInitial(){
        ConditionDeFinVivantMort(this.etat,vivant1);
        assertThat(verifierEtatDuJeu().equals(EtatDuJeu..ENCOURS), is(true));
    }

    @Test
    public void TestConditionDeFinVivantPossedeObjet() {
        ConditionDeFinVivantPossedeObjets(this.etat,vivant2,objets);
        assertThat(verifierEtatDuJeu().equals(EtatDuJeu.ENCOURS), is(true));
    }

    @Test
    public void TestConditionDeFinVivantDansPieceEtPossedeObjets() {
       ConditionDeFinVivantDansPieceEtPossedeObjets(this.etat,vivant2,piece1,objets);
        
    }

    @Test 
    public void TestConditionDeFinConjonctionDeConditionDeFin() {
        ConditionDeFinConjonctionDeConditionDeFin(this.etat,conditions);
        assertThat(verifierEtatDuJeu().equals(this.etat), is(true));
    }
}
