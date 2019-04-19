package com.finalproject.AST;

import com.finalproject.Visitor.Visitor;

public class Program {
	
	private Identifier name;
	private Declaration list;
	private StatementList stms;
	
	
	public Program(Identifier name, Declaration list, StatementList stms) {
		this.name = name;
		this.setList(list);
		this.stms = stms;
	}
	
	

	public Identifier getName() {
		return name;
	}

	public void setName(Identifier name) {
		this.name = name;
	}

	public StatementList getStm() {
		return stms;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}

	public Declaration getList() {
		return list;
	}

	public void setList(Declaration list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "Program [name=" + name + ", list=" + list + ", stms=" + stms + "]";
	}
}
