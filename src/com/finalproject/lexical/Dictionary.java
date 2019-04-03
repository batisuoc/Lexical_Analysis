package com.finalproject.lexical;

import java.util.*;
import java.util.regex.*;

public class Dictionary {
	private Map<String, String> dicts = new HashMap<String, String>();
	Pattern pattern = null;
	
	public Dictionary() {
		this.dicts.put("WHITESPACE", "\\s");
	}
	
	public boolean isWhiteSpace(String input) {
		pattern = Pattern.compile(this.dicts.get("WHITESPACE"));
		if(pattern.matcher(input).matches()) return true;
		return false;
	}
}
