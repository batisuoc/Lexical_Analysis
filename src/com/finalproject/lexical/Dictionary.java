package com.finalproject.lexical;

import java.util.*;
import java.util.regex.*;

public class Dictionary {
	protected Map<String, String> dicts = new HashMap<String, String>();
	
	public Dictionary() {
		//Dictionary of regex
		this.dicts.put("WHITESPACE", "\\s");
		this.dicts.put("SPECIALCHAR_AND_WORDNUMBER", "(?=.*[;,:=\\+])(?=.*[a-zA-Z0-9]).{1,20}");
		this.dicts.put("SPECIALCHAR", "[;,:=\\+]+");
		this.dicts.put("NUMBER", "^[-+]?\\d+(\\.\\d+)?$");
		this.dicts.put("WORD","[a-zA-Z]+");
	}
	
	
	public String getSymbolNameSpecialWordToken(String token) {
		switch (token) {
		case "var":
			return "VARnumber";
		case "and":
			return "ANDnumber";
		case "array":
			return "ARRAYnumber";
		case "begin":
			return "BEGINnumber";
		case "constant":
			return "CONSTnumber";
		case "div":
			return "DIVnumber";
		case "downto":
			return "DOWNTOnumber";
		case "else":
			return "ELSEnumber";
		case "elsif":
			return "ELSIFnumber";
		case "end":
			return "ENDnumber";
		case "endif":
			return "ENDIFnumber";
		case "endloop":
			return "ENDLOOPnumber";
		case "endrec":
			return "ENDRECnumber";
		case "exit":
			return "EXITnumber";
		case "for":
			return "FORnumber";
		case "forward":
			return "FORWARDnumber";
		case "function":
			return "FUNCTIONnumber";
		case "if":
			return "IFnumber";
		case "is":
			return "ISnumber";
		case "loop":
			return "LOOPnumber";
		case "not":
			return "NOTnumber";
		case "of":
			return "OFnumber";
		case "or":
			return "ORnumber";
		case "procedure":
			return "PROCEDUREnumber";
		case "program":
			return "PROGRAMnumber";
		case "record":
			return "RECORDnumber";
		case "repeat":
			return "REPEATnumber";
		case "return":
			return "RETURNnumber";
		case "then":
			return "THENnumber";
		case "to":
			return "TOnumber";
		case "type":
			return "TYPEnumber";
		case "until":
			return "UNTILnumber";
		case "while":
			return "WHILEnumber";
		default:
			return "IDnumber";
		}
	}
	
	public String getSymbolNameSpecialToken(String token) {
		switch (token) {
		case ",":
			return "COMMMAnumber";
		case ";":
			return "SEMInumber";
		case ":=":
			return "COLEQnumber";
		case "+":
			return "PLUSnumber";
		case ":":
			return "COLONnumber";
		case ".":
			return "DOTnumber";
		case "(":
			return "LPARENnumber";
		case ")":
			return "RPARENnumber";
		case "<":
			return "LTnumber";
		case ">":
			return "GTnumber";
		case "==":
			return "EQnumber";
		case "-":
			return "MINUSnumber";
		case "*":
			return "TIMESnumber";
		case "..":
			return "DOTDOTnumber";
		case "<=":
			return "LEnumber";
		case ">=":
			return "GEnumber";
		case "<>":
			return "NEnumber";
		default:
			return "Error";
		}
	}
	
	
		
}
