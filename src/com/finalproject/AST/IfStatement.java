package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public class IfStatement implements Statement {

	

	private Expression condExp;
	private Statement trueStm, falseStm;

	public IfStatement(Expression condExp, Statement trueStm, Statement falseStm) {
		this.condExp = condExp;
		this.trueStm = trueStm;
		this.falseStm = falseStm;
	}

	public Expression getCondExp() {
		return condExp;
	}

	public Statement getTrueStm() {
		return trueStm;
	}

	public Statement getFalseStm() {
		return falseStm;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public String toString() {
		return "IfStatement [condExp=" + condExp + ", trueStm=" + trueStm + ", falseStm=" + falseStm + "]";
	}

}
