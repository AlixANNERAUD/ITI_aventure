package fr.insarouen.iti.prog.itiaventure;

public class TestConditionDeFinVivantMort {
    private Monde monde1;
    private Monde monde2;
    private Piece piece1, piece2;
    private Objet[] objets;
    private Porte porte;
    private Vivant vivant1;
    private Vivant vivant2;
    private EtatDuJeu etat;

    @Before 
    public void avantTest() {
        this.monde1 = new Monde("monde1");
        this.monde2 = new MOnde("monde2");
        this.piece1 = new Piece("piece1", monde);
        this.piece2 = new Piece("piece2", monde);
        this.porte = new Porte("porte", monde, piece1, piece2);
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
    public void TestConditionDeFinVivantMort(){
        ConditionDeFinVivantMort(this.etat,vivant1);
        assertThat(verifierEtatDuJeu().equal s(EtatDuJeu.ENCOURS), is(true));
    }

    @Test
    public void TestConditionDeFinVivantPossedeObjet() {
        ConditionDeFinVivantPossedeObjets(this.etat,vivant2,objets);
        assertThat(verifierEtatDuJeu().equals(EtatDuJeu.ENCOURS), is(true));
    }

    @Test
    public void TestConditionDeFinVivantDansPieceEtPOssedeObjets() {
       ConditionDeFinVivantDansPieceEtPossedeObjets(this.etat,) 
    }
}
