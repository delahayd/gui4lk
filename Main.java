// $Id$

public class Main {

    public static void main (String [] argv) {
	// Some basic formulas to build complex formulas
	Form a = new Var("A");
	Form b = new Var("B");
	Form c = new Var("C");
	Form t = new Top();
	Form f = new Bot();

	// Frege-Hilbert's axioms
	Form ax1 = new Imp(a,new Imp(b,a));
	Form ax2 = new Imp(new Imp(a,new Imp(b,c)),
			   new Imp(new Imp(a,b),new Imp(a,c)));
	Form ax3 = new Imp(new And(a,b),b);
	Form ax4 = new Imp (b,new Or(a,b));
	Form ax5 = new Imp (new Or(a,b),
			    new Imp(new Imp(a,c),new Imp(new Imp(b,c),c)));
	Form ax6 = new Imp(a,new Imp(f,new Not(a)));
	Form ax7 = new Imp(f,a);
	Form ax8 = new Imp(new Equiv(a,b),new Imp(a,b));
	Form ax9 = new Imp(new Equiv(a,b),new Imp(b,a));
	Form ax10 = new Imp(new Imp(a,b),new Imp(new Imp(b,a),new Equiv(a,b)));
	
	// Printing formulas
	System.out.println ("**** Formulas ****\n");
	System.out.println ("Axiom 1: " + ax1.toString ());
	System.out.println ("Axiom 2: " + ax2.toString ());
	System.out.println ("Axiom 3: " + ax3.toString ());
	System.out.println ("Axiom 4: " + ax4.toString ());
	System.out.println ("Axiom 5: " + ax5.toString ());
	System.out.println ("Axiom 6: " + ax6.toString ());
	System.out.println ("Axiom 7: " + ax7.toString ());
	System.out.println ("Axiom 8: " + ax8.toString ());
	System.out.println ("Axiom 9: " + ax9.toString ());
	System.out.println ("Axiom 10: " + ax10.toString ());

	// Sequents
	Sequent s1 = new Sequent();
	s1.addConcl (ax1);
	Sequent s2 = new Sequent();
	s2.addHyps (new Imp(a,new Imp(b,c)));
	s2.addHyps (new Imp(a,b));
	s2.addHyps (a);
	s2.addConcl (c);
	Sequent s3 = new Sequent();
	s3.addHyps (new Imp(a,new Imp(b,c)));
	s3.addHyps (a);
	s3.addConcl (c);
	s3.addConcl (new Not(new Imp(a,b)));
	
	System.out.println ("\n**** Sequents ****\n");
	System.out.println ("Sequent 1: " + s1.toString ());
	System.out.println ("Sequent 2: " + s2.toString ());
	System.out.println ("Sequent 3: " + s3.toString ());
    }//main

}//Main
