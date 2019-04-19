package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public class VarType implements Type {
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public String toString() {
		return "VarType [Var]";
	}
}
