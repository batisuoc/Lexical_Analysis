package com.finalproject.AST;


import com.finalproject.Visitor.Visitor;

public class Assign implements Statement {
	

	private Identifier id;
	private Expression value;
	
	public Assign(Identifier id, Expression value) {
		this.id = id;
		this.value = value;
	}
	
	public Identifier getId() {
		return id;
	}
	
	public Expression getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "Assign [id=" + id + ", value=" + value + "]";
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
}
