package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;


public interface Type {
	public void accept(Visitor v);
}
