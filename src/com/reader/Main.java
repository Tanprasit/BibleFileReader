package com.reader;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		MyFileReader mfr = new MyFileReader("bible.txt");
		try {
			long start = System.currentTimeMillis();
			
//			mfr.formatBibleLines();
//			mfr.readFile(); mfr.printLine();
			mfr.readFile(); 
			mfr.lineReverse(); 
			mfr.printLine();
			
			long end = System.currentTimeMillis();
			long timeTaken = end - start;
			System.out.println("Indexing took: " + timeTaken);
			
			Scanner userInput = new Scanner(System.in);
			System.out.println("Please enter a verse");
			String query = userInput.next();
			
			System.out.println(mfr.getVerse(query));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
