package com.finalproject.AST;


import com.finalproject.Visitor.Visitor;

public class WhileStatement implements Statement {
	

	private Expression condExp;
	private Statement stm;
	
	public WhileStatement(Expression condExp, Statement stm) {
		this.condExp = condExp;
		this.stm = stm;
	}
	
	public Expression getCondExp() {
		return condExp;
	}
	
	public Statement getStm() {
		return stm;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public String toString() {
		return "WhileStatement [condExp=" + condExp + ", stm=" + stm + "]";
	}
}
