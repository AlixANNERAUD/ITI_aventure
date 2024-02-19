#!/bin/bash

sh compiler.sh && \
javac -classpath ./classes:./classestests:/usr/share/java/* -sourcepath ./srctests -d ./classestests ./srctests/fr/insarouen/iti/prog/itiaventure/AllTests.java && \
java -classpath ./classes:./classestests:/usr/share/java/* org.junit.runner.JUnitCore fr.insarouen.iti.prog.itiaventure.AllTests
