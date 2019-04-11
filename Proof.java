// $Id$

import java.util.*;

class ProofTree {

    Sequent concl;
    ArrayList<ProofTree> prem;
    Rule rule;

    ProofTree (Sequent concl) {
	this.concl = concl;
    }//ProofTree

    Sequent getSequent () {
	return concl;
    }//getSequent

    void setRule (Rule r) {
	rule = r;
    }//setRule

    void setPrem (ArrayList<ProofTree> p) {
	prem = p;
    }//setPrem

    public String toString () {
	return concl.toString ();
    }//toString

}//ProofTree

class ProofEngine {

    ProofTree pft, current;
    ArrayList<ProofTree> toBeProved = new ArrayList<ProofTree> ();
    boolean complete = false;

    ProofEngine (Sequent s) {
	pft = new ProofTree (s);
	toBeProved.add (pft);
	current = pft;
    }//ProofEngine

    void applyRule (int i, Side c, Rule r) {
	ArrayList<Sequent> res;
	ArrayList<ProofTree> resp = new ArrayList<ProofTree> ();
	ArrayList<Form> lf = null;
	switch (c) {
	case Left: lf = current.getSequent ().getHyps (); break;
	case Right: lf = current.getSequent ().getConcl ();
	}//switch
	res = lf.get (i).applyRule (current.getSequent (), i, c, r);
	toBeProved.remove(current);
	current.setRule (r);
	for (Sequent s : res)
	    resp.add (new ProofTree (s));
	current.setPrem (resp);
	current = resp.get(0);
	for (ProofTree e : resp)
	    toBeProved.add (e);
    }//applyRule

    void applyAxiom () {
	// Looking for a formula on both sides
	Sequent s = current.getSequent ();
	boolean res = false;
	int i = 0;
	while (!res && i < s.getHyps ().size ()) {
	    res = s.getConcl ().contains (s.getHyps ().get (i));
	    i++;
	}//while
	if (!res)
	    throw new ApplyRuleException ();
	else {
	    ArrayList<ProofTree> resp = new ArrayList<ProofTree> ();
	    current.setRule (Rule.Ax);
	    current.setPrem (resp);
	    toBeProved.remove(current);
	    if (toBeProved.size () == 0) {
		current = null;
		complete = true;
	    }//if
	    else
		current = toBeProved.get (0);
	}//else
    }//applyAxiom

    void print () {
	System.out.println ("Current sequent: " + current.toString ());
	System.out.println ("Sequents to be proved:");
	for (ProofTree e : toBeProved)
	    System.out.println (e.toString ());
    }//print

    void printIndex () {
	System.out.println ("Current sequent: " +
			    current.getSequent ().toStringIndex ());
	System.out.println ("Sequents to be proved:");
	for (int i = 0; i < toBeProved.size (); i++)
	    System.out.println ("(" + (i + 1) + ") " +
				toBeProved.get (i).toString ());
    }//print

    void loop () {
	printIndex ();
	applyRule (0, Side.Right, Rule.ImpRight);
	printIndex ();
	applyRule (0, Side.Right, Rule.ImpRight);
	printIndex ();
	applyAxiom ();
    }//loop

}//ProofEngine
