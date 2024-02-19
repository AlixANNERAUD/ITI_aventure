package fr.insarouen.iti.prog.itiaventure.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.iti.prog.itiaventure.elements.objets.AllTestObjets;
import fr.insarouen.iti.prog.itiaventure.elements.structure.AllTestStructure;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.AllTestVivants;

@RunWith(Suite.class)
@SuiteClasses({ TestEntite.class, AllTestObjets.class, AllTestStructure.class, AllTestVivants.class})

public class AllTestElements {

}
