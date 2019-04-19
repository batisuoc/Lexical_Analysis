package com.finalproject.Visitor;

import com.finalproject.AST.*;

public interface Visitor {

	public void visit(Identifier identifier);

	public void visit(VarDecl varDecl);

	public void visit(Assign assign);

	public void visit(VarDeclList varDeclList);

	public void visit(Declaration declaration);

	public void visit(Program program);

	public void visit(VarType varType);

	public void visit(IfStatement ifStatement);

	public void visit(WhileStatement whileStatement);

	public void visit(IntegerLiteral integerLiteral);

	public void visit(FloatLiteral floatLiteral);

	public void visit(CharLiteral charLiteral);

	public void visit(IdentifierExpression identifierExpression);

	public void visit(Not not);

	public void visit(Negative negative);

	public void visit(And and);

	public void visit(Or or);

	public void visit(Equal equal);

	public void visit(LessThan lessThan);

	public void visit(LessThanEqual lessThanEqual);

	public void visit(Minus minus);

	public void visit(Modules modules);

	public void visit(MoreThan moreThan);

	public void visit(MoreThanEqual moreThanEqual);

	public void visit(NotEqual notEqual);

	public void visit(Plus plus);

	public void visit(Times times);

	public void visit(Divide divide);

	public void visit(Block block);

}
