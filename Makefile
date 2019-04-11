# $Id$

JAVAC=javac
FILES=Rules.java Form.java Sequent.java Proof.java Main.java
CLASS=$(FILES:.java=.class)

all: $(CLASS)

clean:
	rm -f *~ .*~ *.class

.SUFFIXES: .java .class

%.class: %.java
	$(JAVAC) $<
