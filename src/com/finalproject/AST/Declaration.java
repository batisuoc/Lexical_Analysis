package com.finalproject.AST;

import java.util.ArrayList;
import java.util.List;

import com.finalproject.Visitor.Visitor;

public class Declaration {
	private List<VarDeclList> list;

	public Declaration() {
		list = new ArrayList<VarDeclList>();
	}

	public void addElement(VarDeclList varDecl) {
		getList().add(varDecl);
	}

	public VarDeclList elementAt(int index) {
		return getList().get(index);
	}

	public int size() {
		return getList().size();
	}

	public List<VarDeclList> getList() {
		return list;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public String toString() {
		return "Declaration [list=" + list + "]";
	}
	
	

}
