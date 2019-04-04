package com.finalproject.lexical;

public class Token {
	private String token;
	private String symbolName;
	private int tokenLine;
	private int position;
	
	public Token(String token, String symbolName, int tokenLine, int position) {
		this.token = token;
		this.symbolName = symbolName;
		this.tokenLine = tokenLine;
		this.position = position;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public int getTokenLine() {
		return tokenLine;
	}

	public void setTokenLine(int tokenLine) {
		this.tokenLine = tokenLine;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		int tokenEndposition = position+token.length()-1;
		return token + " " + symbolName + " " + tokenLine + " " + tokenLine + " " + position + " " + tokenEndposition;
	}
	
}
