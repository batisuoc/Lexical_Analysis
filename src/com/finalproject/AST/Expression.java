package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public interface Expression {
	public void accept(Visitor v);
}
