package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public class FloatLiteral implements Expression {
	private float value;

	public FloatLiteral(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
