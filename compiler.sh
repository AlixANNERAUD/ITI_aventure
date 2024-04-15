#!/bin/bash
javac -classpath ./classes -sourcepath ./src -d ./classes $(find ./src -name "*.java")