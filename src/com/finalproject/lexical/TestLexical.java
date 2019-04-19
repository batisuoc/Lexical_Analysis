package com.finalproject.lexical;

public class TestLexical {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numTestcase = 7;
		for (int i = 1; i <= numTestcase; i++) {
			Lexical test2 = new Lexical();
			test2.readFile("resources/input/Test0" + i + ".txt");
			test2.writeFile("resources/output/Out0" + i + ".txt");
		}
		System.out.println("Done.");
	}
	
}
