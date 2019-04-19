package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public class CharLiteral implements Expression {
private char value;
	
	public CharLiteral(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
}
