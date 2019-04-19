package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public class IntegerLiteral implements Expression {

	

	private int value;

	public IntegerLiteral(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public String toString() {
		return "IntegerLiteral [value=" + getValue() + "]";
	}
}
