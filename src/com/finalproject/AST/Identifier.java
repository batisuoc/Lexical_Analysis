package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;


public class Identifier {
private String name;
	
	public Identifier(String name) {
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
		return "Identifier [name=" + name + "]";
	}
}
