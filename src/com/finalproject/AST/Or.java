package com.finalproject.AST;


import com.finalproject.Visitor.Visitor;

public class Or implements Expression{
	private Expression lhs, rhs;

	public Or(Expression lhs, Expression rhs) {
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
}
