// $Id$

abstract class Form {}//Form

class Var extends Form {

    String name;

    Var (String name) {
	this.name = name;
    }//Var
    
    public String toString () {
	return name;
    }//toString

}//Var

class Top extends Form {

    public String toString () {
	return "⊤";
    }//toString

}//Top

class Bot extends Form {

    public String toString () {
	return "⊥";
    }//toString

}//Bot

class Not extends Form {

    Form f;

    Not (Form f) {
	this.f = f;
    }//Not

    public String toString () {
	return "¬(" + (f.toString ()) + ")";
    }//toString

}//Not

abstract class BinCon extends Form {

    Form f1, f2;

}//BinCon

class And extends BinCon {

    And (Form f1, Form f2) {
	this.f1 = f1;
	this.f2 = f2;
    }//And

    public String toString () {
	return "(" + (f1.toString ()) + " ∧ " + (f2.toString ()) + ")";
    }//toString

}//And

class Or extends BinCon {

    Or (Form f1, Form f2) {
	this.f1 = f1;
	this.f2 = f2;
    }//Or

    public String toString () {
	return "(" + (f1.toString ()) + " ∨ " + (f2.toString ()) + ")";
    }//toString

}//Or

class Imp extends BinCon {

    Imp (Form f1, Form f2) {
	this.f1 = f1;
	this.f2 = f2;
    }//Imp

    public String toString () {
	return "(" + (f1.toString ()) + " ⇒ " + (f2.toString ()) + ")";
    }//toString

}//Imp

class Equiv extends BinCon {

    Equiv (Form f1, Form f2) {
	this.f1 = f1;
	this.f2 = f2;
    }//Equiv

    public String toString () {
	return "(" + (f1.toString ()) + " ⇔ " + (f2.toString ()) + ")";
    }//toString

}//Equiv
