// $Id$

import java.io.*;
import java.util.*;

class InputException extends RuntimeException {}//InputException

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

    void help () {
	System.out.println("Actions are either:");
	System.out.println("* h = display this help message");
	System.out.println("* ax = apply axiom rule");
	System.out.println("* s:i:cnt =");
    }//help
    
    void loop () {
	BufferedReader buffer =
	    new BufferedReader (new InputStreamReader (System.in));
	String [] res;
	while (!complete) {
	    try {
		printIndex ();
		System.out.print ("Action: ");
		res = buffer.readLine ().split (":");
		if (res.length == 1)
		    if (res[0].equals ("h"))
			help ();
		    else if (res[0].equals ("ax"))
			applyAxiom ();
		    else
			throw new InputException ();
		else if (res.length == 3) {
		    Side s;
		    if (res[0].equals("l"))
			s = Side.Left;
		    else if (res[0].equals("r"))
			s = Side.Right;
		    else
			throw new InputException ();
		    int index = Integer.parseInt (res[1]) - 1;
		    switch (res[2]) {
		    case "imp":
			if (s == Side.Left)
			    applyRule (index, s, Rule.ImpLeft);
			else
			    applyRule (index, s, Rule.ImpRight);
			break;
		    default:
			throw new InputException ();
		    }//switch
		}//if
		System.out.println();
	    }//try
	    catch (Exception e) {
		System.out.println ("Input not well formed");
		System.out.println();
	    }//catch
	}//while
	System.out.println ("Proof complete !");	
    }//loop

}//ProofEngine
