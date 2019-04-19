package com.finalproject.Paser;

import java.io.IOException;

public class TestParser {
	public static void main(String[] args) throws IOException {

		String input;
		String output;

		int numTestcase = 5;
		for (int i = 1; i <= numTestcase; i++) {
			input = "resources/input/Test0" + i + ".txt";
			output = "TestParser0" + i + ".txt";
			Parser test = new Parser(input);
			test.ParseFile(0, output);

		}
		System.out.println("Parsing Done.");
	}

}
