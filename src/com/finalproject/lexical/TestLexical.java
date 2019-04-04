package com.finalproject.lexical;

import java.io.IOException;

public class TestLexical {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileInput = "resources/input/Test06.txt";		
//		Lexical test = new Lexical();
//		test.xuLyFile(fileInput);
		Lexical2 test2 = new Lexical2(fileInput);
		test2.readFile();
	}

}
