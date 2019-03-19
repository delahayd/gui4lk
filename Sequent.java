// $Id$

import java.util.*;

class Sequent {

    ArrayList<Form> hyps, concl;

    Sequent () {
	hyps = new ArrayList<Form>();
	concl = new ArrayList<Form>();
    }//Sequent

    Sequent (ArrayList<Form> hyps, ArrayList<Form> concl) {
	this.hyps = hyps;
	this.concl = concl;
    }//Sequent

    void addHyps (Form f) {
	hyps.add(f);
    }//addHyps

    void addConcl (Form f) {
	concl.add(f);
    }//addConcl
    
    public String toString () {
	// Printing hypotheses
	String res = "";
	for (int i = 0; i < hyps.size (); i++) {
	    res += hyps.get(i).toString ();
	    if (i != (hyps.size () - 1))
		res += ", ";
	}//for
	res = (hyps.size () == 0)?res + "⊢ ":res + " ⊢ ";
	for (int i = 0; i < concl.size (); i++) {
	    res += concl.get(i).toString ();
	    if (i != (concl.size () - 1))
		res += ", ";
	}//for
	return res;
    }//toString

}//Sequent
