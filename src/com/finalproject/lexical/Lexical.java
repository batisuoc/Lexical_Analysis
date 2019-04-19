package com.finalproject.lexical;

import java.io.*;
import java.util.*;
import java.util.regex.*;



public class Lexical extends Dictionary {
	private String inputFile;
	private String[] words;
	private List<Token> listToken;
	private int tokenIndex = 0;
	
	public Lexical() {
		super();
		listToken = new ArrayList<Token>();
	}
	
	public void readFile(String inputFile) {
		int numLine = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			String line;
			try {
				while((line = in.readLine()) != null)
				{
					wordAnalysis(line, numLine);
					numLine++;
				}
				in.close();
			} catch (IOException e) {
				System.out.println("Khong doc duoc ky tu trong file.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Khong tim thay file.");
		}
		
//		for (Token token : listToken) {
//			System.out.println(token.toString());
//		}
		
		
	}
	
	public void writeFile(String outputFile) {
		try {
			try {
				PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
				for (Token token : listToken) {
					writer.println(token.toString());
				}
				writer.close();
			} catch (UnsupportedEncodingException e) {
				// TODO: handle exception
				System.out.println("Khong dung ma code");
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Khong tao duoc file");
		}
		
	}
	
	public String toOutputString(String input, int stringLength, int position) {
		return input + " " + input.length() + " pos: " + position;
	}
	
	public void wordAnalysis(String line, int numLine) {
		//Ä�áº¿m sá»‘ lÆ°á»£ng tab cÃ³ trong hÃ ng Ä‘Ã³ 
		char[] listLineChar = line.toCharArray();
		int countTab = 0;
		for (char c : listLineChar) {
			if(c == '\t') countTab++;
		}
		this.words = line.split("\\s");
		//Náº¿u cÃ³ tab thÃ¬ láº¥y sá»‘ lÆ°á»£ng tab x4 Ä‘á»ƒ cáº­p nháº­t vá»‹ trÃ­ báº¯t Ä‘áº§u
		int position = 1 + countTab * 4;
		String charToken;
		for (String string : this.words) {
			if(string.matches(dicts.get("SPECIALCHAR_AND_WORDNUMBER"))) {
				int posNumChar = 0;
				char[] listChar = string.toCharArray();
				String numberChar = "";
				for (int i = 0; i < listChar.length; i++) {
					charToken = Character.toString(listChar[i]);
					if(charToken.matches(dicts.get("SPECIALCHAR"))) {
						numberChar = "";
						listToken.add(tokenIndex, new Token(charToken, getSymbolNameSpecialToken(charToken), numLine, position));
						tokenIndex++;
						position = position + 1;
					}
					else if(charToken.matches(dicts.get("NUMBER"))) {
						int j = i;
						while(Character.toString(listChar[j]).matches(dicts.get("NUMBER"))) {
							if(j == 0) posNumChar = position;
							numberChar += charToken;
							position = position + 1;
							if((j+1) < listChar.length) {
								charToken = Character.toString(listChar[j+1]);
								if(charToken.matches(dicts.get("SPECIALCHAR"))) {
									posNumChar = position - 1;
									j = j + 1;
									break;
								}
								j = j + 1;
							}
							else {
								posNumChar = position - 1;
								j = j + 1;
								break;
							}
						}
						i = j;
						listToken.add(tokenIndex, new Token(numberChar, "ICONSTnumber", numLine, posNumChar));
						tokenIndex++;
						i -= 1;//LÃ¹i láº¡i má»™t index vÃ¬ trong vÃ²ng láº·p tÄƒng thá»«a 1 index 
					}
					else if(charToken.equals("-")) {
						numberChar = numberChar + "-";
					}
					else if(charToken.matches(dicts.get("WORD"))) {
						numberChar = "";
						listToken.add(tokenIndex, new Token(charToken, "IDnumber", numLine, position));
						position = position + 1;
						tokenIndex++;
					}
				}
				position += 1;
			}
			else if(string.equals("")) {
				//Náº¿u hÃ ng Ä‘Ã³ cÃ³ tab thÃ¬ cho qua 
				//VÃ¬ khi split string sáº½ khÃ´ng Ä‘á»�c Ä‘Æ°á»£c kÃ­ tá»± "\t" mÃ  chá»‰ Ä‘á»�c Ä‘Æ°á»£c kÃ­ tá»± "" vÃ  sáº½ nháº§m láº«n vá»›i trÆ°á»�ng há»£p cÃ³ dáº¥u cÃ¡ch
				if(countTab > 0) {
					continue;
				}
				else {
					position = position + 1;
				}
			}
			else if(string.matches(dicts.get("NUMBER"))) {
				listToken.add(tokenIndex, new Token(string, "ICONSTnumber", numLine, position));
				position = position + string.length() + 1;
				tokenIndex++;
			}
			else if(string.matches(dicts.get("WORD"))) {
				listToken.add(tokenIndex, new Token(string, getSymbolNameSpecialWordToken(string), numLine, position));
				position = position + string.length() + 1;
				tokenIndex++;
			}
			else {
				if(getSymbolNameSpecialToken(string).equals("Error")) {
					listToken.add(tokenIndex, new Token(string, "Error", numLine, position));
					break;
				}
				else {
					listToken.add(tokenIndex, new Token(string, getSymbolNameSpecialToken(string), numLine, position));
					position = position + string.length() + 1;
					tokenIndex++;
				}
				
			}	
		}
		
	}

	
	public List<Token> read(String inputFile) {
		int numLine = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			String line;
			try {
				while((line = in.readLine()) != null)
				{
					wordAnalysis(line, numLine);
					numLine++;
				}
				in.close();
			} catch (IOException e) {
				System.out.println("Khong doc duoc ky tu trong file.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Khong tim thay file.");
		}
		
//		for (Token token : listToken) {
//			System.out.println(token.toString());
//		}
		
	    return listToken;
		
	}

}
