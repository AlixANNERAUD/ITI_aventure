package fr.insarouen.iti.prog.itiaventure.elements.objets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.AllTestSerrurerie;

@RunWith(Suite.class)
@SuiteClasses({ TestObjet.class, TestPiedDeBiche.class, TestCoffre.class, AllTestSerrurerie.class })

public class AllTestObjets {
}
