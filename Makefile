# $Id$

JAVAC=javac
FILES=Form.java Sequent.java Main.java
CLASS=$(FILES:.java=.class)

all: $(CLASS)

clean:
	rm -f *~ .*~ *.class

.SUFFIXES: .java .class

%.class: %.java
	$(JAVAC) $<
