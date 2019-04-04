package com.finalproject.lexical;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Lexical2 extends Dictionary {
	private String inputFile;
	private String[] words;
	private List<Token> listToken;
	
	public Lexical2(String inputFile) {
		super();
		this.inputFile = inputFile;
		listToken = new ArrayList<Token>();
	}
	
	public void readFile() {
		int numLine = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(this.inputFile));
			String line;
			try {
				while((line = in.readLine()) != null)
				{
//					System.out.println("Line " + numLine + " : " + line);
					wordAnalysis(line, numLine);
					numLine++;
				}
				in.close();
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println("Khong doc duoc ky tu trong file.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Khong tim thay file.");
		}
	}
	
	public String toOutputString(String input, int stringLength, int position) {
		return input + " " + input.length() + " pos: " + position;
	}
	
	public void wordAnalysis(String line, int numLine) {
		this.words = line.split("\\s");
		int position = 1;
		int tokenIndex = 0;
		String charToken;
		for (String string : this.words) {
			if(string.matches(dicts.get("SPECIALCHAR_AND_WORDNUMBER"))) {
				String numberChar = null;
				char[] listChar = string.toCharArray();
				for (int i = 0; i < listChar.length; i++) {
					charToken = Character.toString(listChar[i]);
					if(charToken.matches(dicts.get("SPECIALCHAR"))) {
						listToken.add(tokenIndex, new Token(charToken, getSymbolNameSpecialToken(charToken), numLine, position));
						System.out.println(listToken.get(tokenIndex).toString());
						position = position + 1;
					}
					else if(charToken.matches(dicts.get("NUMBER"))) {
						numberChar += charToken;
					}
					else if(charToken.matches(dicts.get("WORD"))) {
						listToken.add(tokenIndex, new Token(charToken, "IDnumber", numLine, position));
						System.out.println(listToken.get(tokenIndex).toString());
						position = position + 1;
					}
				}
				position += 1;
			}
			else if(string.matches(dicts.get("WHITESPACE"))) {
				position += 1;
			}
			else {
				listToken.add(tokenIndex, new Token(string, getSymbolNameSpecialToken(string), numLine, position));
				System.out.println(listToken.get(tokenIndex).toString());
				position = position + string.length() + 1;
			}
			
		}
		
	}
}
