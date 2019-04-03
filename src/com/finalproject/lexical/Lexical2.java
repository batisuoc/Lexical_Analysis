package com.finalproject.lexical;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Lexical2 {
	private String inputFile;
	private String[] words;
	private Dictionary dicts;
	
	public Lexical2(String inputFile) {
		this.inputFile = inputFile;
		this.dicts = new Dictionary();
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
	
	public void wordAnalysis(String line, int numLine) {
		this.words = line.split("\\s");
		int position = 1;
		for (String string : words) {
			System.out.println(string + " " + string.length() + " pos: " + position);
			position = position + string.length() + 1;
		}
	}
}
