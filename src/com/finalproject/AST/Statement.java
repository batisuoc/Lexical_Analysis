package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public interface Statement {
	public void accept(Visitor v);
}
