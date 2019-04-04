package com.finalproject.lexical;

import java.util.*;
import java.util.regex.*;

public class Dictionary {
	protected Map<String, String> dicts = new HashMap<String, String>();
	
	public Dictionary() {
		//Dictionary of regex
		this.dicts.put("WHITESPACE", "\\s");
		this.dicts.put("SPECIALCHAR_AND_WORDNUMBER", "(?=.*[;,:=])(?=.*[a-zA-Z0-9]).{1,20}");
		this.dicts.put("SPECIALCHAR", "[;,:=]+");
		this.dicts.put("NUMBER", "\\d+");
		this.dicts.put("WORD","[a-zA-Z]+");
	}
	
	public String getSymbolNameSpecialToken(String token) {
		switch (token) {
		case "var":
			return "VARnumber";
		case ",":
			return "COMMMAnumber";
		case ";":
			return "SEMInumber";
		default:
			return "";
		}
	}
	
	
		
}
