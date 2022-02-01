# Mohamed SABI
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin


all: Forex ValoCB Test

Forex:
		javac -d ./bin -sourcepath ./src -classpath ./bin src/Forex.java

ValoCB:
		javac -d ./bin -sourcepath ./src -classpath ./bin src/ValoCB.java

Test:
		javac -d ./bin -sourcepath ./src -classpath ./bin src/Test.java


doc:
		javadoc ./src/Forex.java ./src/ValoCB.java ./src/Test.java -d documentation


# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin Test
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeTest

exeTest:
		java -classpath bin:bin/ Test

clean:
		rm -rf bin/*.class src/main/resultats/*.csv
