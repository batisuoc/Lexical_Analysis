package com.finalproject.AST;


import com.finalproject.Visitor.Visitor;

public class Negative implements Expression {
	private Expression exp;

	public Negative(Expression exp) {
		this.exp = exp;
	}

	public Expression getExp() {
		return exp;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
