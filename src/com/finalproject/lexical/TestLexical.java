package com.finalproject.lexical;

public class TestLexical {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lexical2 test2 = new Lexical2();
		int numTestcase = 7;
		for (int i = 1; i <= numTestcase; i++) {
			test2.readFile("resources/input/Test0" + i + ".txt");
			test2.writeFile("resources/output/Out0" + i + ".txt");
		}
		System.out.println("Done.");
	}
	
}
