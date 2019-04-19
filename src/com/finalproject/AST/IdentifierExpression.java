package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public class IdentifierExpression implements Expression {

	private String name;

	public IdentifierExpression(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public String toString() {
		return "IdentifierExpression [name=" + name + "]";
	}
}
