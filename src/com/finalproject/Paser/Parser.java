package com.finalproject.Paser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.finalproject.AST.*;
import com.finalproject.lexical.*;

import com.finalproject.Visitor.*;

public class Parser extends Dictionary {

	private Lexical lex;
	private List<Token> listToken;
	private Token token;
	private Token errorToken;
	private int position;

	private int errors;

	private ArrayList<VarDecl> decelarations; // declarations symbol table
	private ArrayList<Identifier> identifiers; // identifiers symbol table
	private ArrayList<Assign> assigns; // assigns symbol table
	private ArrayList<Expression> conditions; // conditions symbol table

	// hash table for operator precedence levels
	private final static Map<String, Integer> binopLevels;

	static {
		binopLevels = new HashMap<String, Integer>();
		binopLevels.put("ANDnumber", 10);
		binopLevels.put("ORnumber", 10);
		binopLevels.put("LTnumber", 20);
		binopLevels.put("GTnumber", 20);
		binopLevels.put("LEnumber", 20);
		binopLevels.put("GEnumber", 20);
		binopLevels.put("EQnumber", 20);
		binopLevels.put("NEnumber", 20);
		binopLevels.put("PLUSnumber", 30);
		binopLevels.put("MINUSnumber", 30);
		binopLevels.put("TIMESnumber", 40);
		binopLevels.put("COLEQnumber", 40);
		binopLevels.put("DIVnumber", 40);
		binopLevels.put("MODnumber", 40);
		binopLevels.put("LBRACKETnumber", 50);
	}

	public Parser(String inputFile) {
		super();
		this.lex = new Lexical();
		this.listToken = lex.read(inputFile);
		this.token = listToken.get(0);
		this.position = 0;
		this.decelarations = new ArrayList<VarDecl>();
		this.identifiers = new ArrayList<Identifier>();
		this.assigns = new ArrayList<Assign>();
		this.conditions = new ArrayList<Expression>();
	}

	// verifies current token type and grabs next token or reports error
	private boolean eat(Token tk, int pos, String type) throws IOException {
		if (tk.getSymbolName() == type) {
			pos++;
			if (pos >= listToken.size()) {
				position = listToken.size() - 1;
			} else {
				position = pos;
				token = listToken.get(pos);
			}

			return true;
		} else {
			error(type);
			return false;
		}
	}

	// reports an error to the console
	private void error(String string) {
		// only report error once per erroneous token
		if (token == errorToken)
			return;

		// print error report
		System.err.print("ERROR: " + token.getSymbolName());
		System.err.print(
				" at line " + token.getTokenLine() + ", column " + (token.getPosition() + token.getToken().length()));
		System.err.println("; Expected " + string);

		errorToken = token; // set error token to prevent cascading
		errors++; // increment error counter
	}

	// number of reported syntax errors
	public int getErrors() {
		return errors;
	}

	public ArrayList<VarDecl> getDecelarations() {
		return decelarations;
	}

	public ArrayList<Identifier> getIdentifiers() {
		return identifiers;
	}

	public ArrayList<Assign> getAssigns() {
		return assigns;
	}

	public ArrayList<Expression> getConditions() {
		return conditions;
	}

	public void ParseFile(int currentIndex, String fileOut) throws IOException {

		String out = "resources/output/" + fileOut;

		switch (listToken.get(currentIndex).getToken()) {
		case "program": {
			Program prog = parseProgram(currentIndex);

			if (errors == 0) {
				PrintVisitor printer = new PrintVisitor(fileOut);
				printer.visit(prog);
				System.out.println();
			}
			break;
		}
		case "var": {
			Declaration stms = parseDeclarations(currentIndex);

			currentIndex = position;
			if (listToken.get(currentIndex).getSymbolName() == getSymbolNameSpecialWordToken(" ")) {
				Statement stm = parseStatement(currentIndex);
				System.out.print((IfStatement) stm);
				System.out.println((WhileStatement) stm);
			}

			PrintVisitor printer = new PrintVisitor(out);
			if (errors == 0) {
				if (stms.getList().size() > 1) {
					System.out.println("root: <mvar>\nchild: <var>\nchild: VARnumber [var]");
					printer.writeFile("root: <mvar>\r\nchild: <var>\r\nchild: VARnumber [var]\r\n");
				} else {
					System.out.println("root: <var>\nchild: VARnumber [var]");
					printer.writeFile("root: <var>\r\nchild: VARnumber [var]\r\n");
				}
				
				printer.visit(stms);
				System.out.println();
				printer.closeFile();
			} else {
				System.err.println("Khong The Tao Cay AST");
			}

			break;
		}
		case "if": {
			Statement stms = parseStatement(currentIndex);
			System.out.println(stms.toString());
			if (errors == 0) {
				System.out.println("root: <Statement>");
				PrintVisitor printer = new PrintVisitor(out);
				printer.writeFile("root: <Statement>\r\n");
				printer.visit((IfStatement) stms);
				System.out.println();
				printer.closeFile();
			} else {
				System.err.println("Khong The Tao AST");
			}

			break;
		}
		case "while": {

			Statement stms = parseStatement(currentIndex);

			if (errors == 0) {
				System.out.println("root: <Statement>");
				PrintVisitor printer = new PrintVisitor(out);
				printer.writeFile("root: <Statement>\r\n");
				printer.visit((WhileStatement) stms);
				System.out.println();
				printer.closeFile();
			} else {
				System.err.println("Khong The Tao AST");
			}

			break;
		}
		default: {
			// <exp>
			Statement stm = parseStatement(currentIndex);

			if (errors == 0) {
				System.out.println("root: <Statement>");
				System.out.println("child: <Expression>");
				PrintVisitor printer = new PrintVisitor(out);
				printer.writeFile("root: <Statement>\r\nchild: <Expression>\r\n");
				printer.visit((Assign) stm);
				System.out.println();
				printer.closeFile();
			} else {
				System.err.println("Khong the tao AST");
			}

		}

		}

		if (decelarations.isEmpty() == false) {
			System.out.println(decelarations.toString());
		}

		if (conditions.isEmpty() == false) {
			System.out.println(conditions.toString());
		}

		if (assigns.isEmpty() == false) {
			System.out.println(assigns.toString());
		}

	}

	private Program parseProgram(int currentPosition) throws IOException {
		eat(listToken.get(currentPosition), currentPosition, getSymbolNameSpecialWordToken("program"));
		currentPosition = position;
		Identifier progName = parseIdentifier(currentPosition);
		currentPosition = position;
		eat(listToken.get(currentPosition), currentPosition, getSymbolNameSpecialToken(";"));
		currentPosition = position;
		Declaration declarations = parseDeclarations(currentPosition);
		currentPosition = position;
		eat(listToken.get(currentPosition), currentPosition, getSymbolNameSpecialWordToken("begin"));
		currentPosition = position;
		StatementList statementList = parseStatementList(currentPosition);
		currentPosition = position;
		eat(listToken.get(currentPosition), currentPosition, getSymbolNameSpecialWordToken("end"));
		currentPosition = position;
		eat(listToken.get(currentPosition), currentPosition, getSymbolNameSpecialToken("."));
		currentPosition = position;
		return new Program(progName, declarations, statementList);
	}

	private StatementList parseStatementList(int currentPosition) throws IOException {
		currentPosition = position;
		StatementList statementList = new StatementList();
		while (isStatement())
			statementList.addElement(parseStatement(currentPosition));
		return statementList;
	}

	private Statement parseStatement(int index) throws IOException {
		index = position;

		// IfStatement ::= if Exp then Statement [else Statement]
		if (token.getSymbolName() == getSymbolNameSpecialWordToken("if")) {
			eat(listToken.get(index), index, getSymbolNameSpecialWordToken("if"));

			// parse conditional expression
			index = position;
			Expression condExp = parseExp(index);
			conditions.add(condExp);

			index = position;
			eat(listToken.get(index), index, getSymbolNameSpecialWordToken("then"));

			// parse true and false statements
			Statement trueStm;

			// BLock ::= 'begin' StatementList 'end'
			if (token.getSymbolName() == getSymbolNameSpecialWordToken("begin")) {
				index = position;
				eat(listToken.get(index), index, getSymbolNameSpecialWordToken("begin"));
				index = position;
				trueStm = parseBlock(index);
			} else
				// parse true statement
				trueStm = parseStatement(index);

			if (token.getSymbolName() == getSymbolNameSpecialWordToken("else")) {

				index = position;
				eat(listToken.get(index), index, getSymbolNameSpecialWordToken("else"));
				index = position;
				Statement falseStm;

				// BLock ::= 'begin' StatementList 'end'
				if (token.getSymbolName() == getSymbolNameSpecialWordToken("begin")) {
					eat(listToken.get(index), index, getSymbolNameSpecialWordToken("begin"));
					index = position;
					falseStm = parseBlock(index);
					index = position;
					eat(listToken.get(index), index, getSymbolNameSpecialWordToken("end"));
				} else {
					// parse false statement
					falseStm = parseStatement(index);
				}
				index = position;
				eat(listToken.get(index), index, getSymbolNameSpecialWordToken("endif"));
				return new IfStatement(condExp, trueStm, falseStm);
			} else {
				index = position;
				eat(listToken.get(index), index, getSymbolNameSpecialWordToken("endif"));
			}

			return new IfStatement(condExp, trueStm, null);
		}

		// WhileStatement ::= while Exp 'loop' Statement 'endloop'
		if (token.getSymbolName() == getSymbolNameSpecialWordToken("while")) {
			index = position;
			eat(listToken.get(index), index, getSymbolNameSpecialWordToken("while"));

			index = position;
			Expression condExp = parseExp(index);
			conditions.add(condExp);

			index = position;
			Statement loopStm;

			// BLock ::= 'loop' StatementList 'endloop'
			if (token.getSymbolName() == getSymbolNameSpecialWordToken("loop")) {
				loopStm = parseBlock(index);
				index = position;
				eat(listToken.get(index), index, getSymbolNameSpecialWordToken("endloop"));
			} else {
				// parse looping statement
				loopStm = parseStatement(index);
			}

			return new WhileStatement(condExp, loopStm);
		}

		// Identifier statement
		if (token.getSymbolName() == getSymbolNameSpecialWordToken("")) {

			Identifier id = new Identifier(token.getToken());
			identifiers.add(id);
			eat(listToken.get(index), index, getSymbolNameSpecialWordToken(""));

			index = position;

			// Assignment statement: id = Exp ;
			if (token.getSymbolName() == getSymbolNameSpecialToken(":=")) {
				eat(listToken.get(index), index, getSymbolNameSpecialToken(":="));
				index = position;
				Expression value = parseExp(index);

				index = position;
				eat(listToken.get(index), index, getSymbolNameSpecialToken(";"));
				index = position;
				Assign assign = new Assign(id, value);
				assigns.add(assign);
				return assign;
			}
		}

		// statement type unknown
		eat(listToken.get(index), index, "STATEMENT");
		index = position;
		token = listToken.get(index);
		return null;
	}

	private Block parseBlock(int index) throws IOException {

		// recursively call parseStatement() until closing brace
		StatementList stms = new StatementList();

		if (token.getSymbolName() == getSymbolNameSpecialWordToken("loop")) {
			eat(listToken.get(index), index, getSymbolNameSpecialWordToken("loop"));
			index = position;
			while (token.getSymbolName() != getSymbolNameSpecialWordToken("endloop")) {
				stms.addElement(parseStatement(index));
			}
		} else {
			while (token.getSymbolName() != getSymbolNameSpecialWordToken("end")) {
				stms.addElement(parseStatement(index));
			}
		}
		return new Block(stms);
	}

	private Expression parseExp(int index) throws IOException {
		Expression lhs = parsePrimaryExp(index);
		return parseBinopRHS(0, lhs, index);
	}

	private Expression parsePrimaryExp(int index) throws IOException {
		switch (token.getSymbolName()) {

		case "ICONSTnumber":
			int intValue = Integer.parseInt(token.getToken());
			eat(listToken.get(index), index, "ICONSTnumber");
			return new IntegerLiteral(intValue);

		case "FLOAT_CONST":
			float floatValue = Float.parseFloat(token.getToken());
			eat(listToken.get(index), index, "FLOAT_CONST");
			return new FloatLiteral(floatValue);

		case "CCONSTnumber":
			char charVal = token.getToken().charAt(0);
			eat(listToken.get(index), index, "CCONSTnumber");
			return new CharLiteral(charVal);

		case "IDnumber":
			Identifier id = parseIdentifier(index);
			identifiers.add(id);
			return new IdentifierExpression(id.getName());

		case "NOTnumber":
			eat(listToken.get(index), index, "NOTnumber");
			return new Not(parseExp(index));

		case "NEGATIVEnumber":
			eat(listToken.get(index), index, "NEGATIVEnumber");
			return new Negative(parseExp(index));

		case "LPARENnumber":
			eat(listToken.get(index), index, "LPARENnumber");
			Expression exp = parseExp(index);
			eat(listToken.get(index), index, "RPARENnumber");
			return exp;

		default:
			// unrecognizable expression
			eat(listToken.get(index), index, "EXPRESSION");
			token = listToken.get(index);
			return null;
		}
	}

	private Expression parseBinopRHS(int level, Expression lhs, int index) throws IOException {
		// continuously parse exp until a lower order operator comes up
		while (true) {
			// grab operator precedence (-1 for non-operator token)
			Integer val = binopLevels.get(token.getSymbolName());
			int tokenLevel = (val != null) ? val.intValue() : -1;

			// either op precedence is lower than prev op or token is not an op
			if (tokenLevel < level)
				return lhs;

			// save binop before parsing rhs of exp
			Token binop = token;
			index = position;
			eat(listToken.get(index), index, binop.getSymbolName());

			index = position;
			Expression rhs = parsePrimaryExp(index); // parse rhs of exp

			// grab operator precedence (-1 for non-operator token)
			val = binopLevels.get(token.getSymbolName());
			int nextLevel = (val != null) ? val.intValue() : -1;

			// if next op has higher precedence than prev op, make recursive call
			if (tokenLevel < nextLevel)
				index = position;
			rhs = parseBinopRHS(tokenLevel + 1, rhs, index);

			// build AST for exp
			switch (binop.getSymbolName()) {
			case "ANDnumber":
				lhs = new And(lhs, rhs);
				break;
			case "ORnumber":
				lhs = new Or(lhs, rhs);
				break;
			case "EQnumber":
				lhs = new Equal(lhs, rhs);
				break;
			case "NEnumber":
				lhs = new NotEqual(lhs, rhs);
				break;
			case "LTnumber":
				lhs = new LessThan(lhs, rhs);
				break;
			case "GTnumber":
				lhs = new MoreThan(lhs, rhs);
				break;
			case "LEnumber":
				lhs = new LessThanEqual(lhs, rhs);
				break;
			case "GEnumber":
				lhs = new MoreThanEqual(lhs, rhs);
				break;
			case "PLUSnumber":
				lhs = new Plus(lhs, rhs);
				break;
			case "MINUSnumber":
				lhs = new Minus(lhs, rhs);
				break;
			case "TIMESnumber":
				lhs = new Times(lhs, rhs);
				break;
			case "DIVnumber":
				lhs = new Divide(lhs, rhs);
				break;
			case "MODnumber":
				lhs = new Modules(lhs, rhs);
				break;

			default:
				eat(listToken.get(index), index, "OPERATOR");
				break;
			}
		}
	}

	private boolean isStatement() {
		switch (token.getSymbolName()) {
		case "SEMInumber":
		case "IFnumber":
		case "WHILEnumber":
		case "LPARENnumber":
		case "LBRACEnumber":
		case "IDnumber":
			return true;
		default:
			return false;
		}
	}

	private Declaration parseDeclarations(int currentPosition) throws IOException {
		Declaration declarations = new Declaration();

		currentPosition = position;
		while (token.getSymbolName() == getSymbolNameSpecialWordToken("var")) {
			declarations.addElement(parseVarDecList(currentPosition));
		}

		return declarations;
	}

	private VarDeclList parseVarDecList(int currentPosition) throws IOException {
		currentPosition = position;
		VarDeclList varDeclList = new VarDeclList();
		VarDecl varDecl = parseVarDecl(currentPosition);
		varDeclList.addElement(varDecl);
		getDecelarations().add(varDecl);

		// check for additional varDecl
		while (token.getSymbolName() == getSymbolNameSpecialToken(",")) {
			currentPosition = position;
			eat(listToken.get(currentPosition), currentPosition, getSymbolNameSpecialToken(","));
			VarDecl newVarDecl = new VarDecl(varDecl.getType(), parseIdentifier(currentPosition));
			varDeclList.addElement(newVarDecl);
			getDecelarations().add(newVarDecl);
		}
		currentPosition = position;
		eat(listToken.get(currentPosition), currentPosition, getSymbolNameSpecialToken(";"));

		return varDeclList;
	}

	private Identifier parseIdentifier(int currentPosition) throws IOException {
		Identifier identifier = null;
		currentPosition = position;
		// grab ID value if token type is ID
		if (listToken.get(currentPosition).getSymbolName() == getSymbolNameSpecialWordToken(""))
			identifier = new Identifier(token.getToken());

		eat(listToken.get(currentPosition), currentPosition, getSymbolNameSpecialWordToken(""));

		return identifier;
	}

	private VarDecl parseVarDecl(int currentPosition) throws IOException {
		currentPosition = position;
		Type type = parseType(currentPosition);
		currentPosition += 1;
		Identifier id = parseIdentifier(currentPosition);
		return new VarDecl(type, id);
	}

	private Type parseType(int index) throws IOException {
		index = position;
		eat(listToken.get(index), index, getSymbolNameSpecialWordToken("var"));
		return new VarType();
	}

}
