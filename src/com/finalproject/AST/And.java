package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public class And implements Expression {
	private Expression lhs, rhs;

	public And(Expression lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression getLHS() {
		return lhs;
	}


	public Expression getRHS() {
		return rhs;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public String toString() {
		return "And [lhs=" + lhs + ", rhs=" + rhs + "]";
	}
}
