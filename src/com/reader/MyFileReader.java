package com.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyFileReader {
	private String filePath;
	private String regex = "(?:\\d\\s*)?[A-Z]?[a-z]+\\s*\\d+(?:[:-]\\d+)?(?:\\s*-\\s*\\d+)?(?::\\d+|(?:\\s*[A-Z]?[a-z]+\\s*\\d+:\\d+))?";

	private Map<String, String> sortedLines = new HashMap<String, String>();

	public MyFileReader(String filePath) {
		this.filePath = filePath;
	}

	public Map<String, String> getSortedLines() {
		return sortedLines;
	}

	public void setSortedLines(Map<String, String> sortedLines) {
		this.sortedLines = sortedLines;
	}

	public void readFile() throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(this.filePath);
			sc = new Scanner(inputStream, "UTF-8");
			int lineNum = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				sortedLines.put(String.valueOf(lineNum++), line);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}
	}

	public void formatBibleLines() throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(this.filePath);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String lineCopy = line;
				String index = Arrays.toString(lineCopy.split(regex));
				System.out.println("key: "  + index);
				line = line.replace(index, "");
				System.out.println("line: "  + line);
				sortedLines.put(index, line);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	public String getVerse(String query) {
		String result = this.getSortedLines().get(query);
		if (null == result) {
			result = "No verse match";
		}
		return result;
	}
	
	public void printLine() {
		int size = this.sortedLines.size();
		for(int i = 0; i <size -1; i++) {
			System.out.println(this.sortedLines.get(String.valueOf(i)));
		}
	}
	
	public void lineReverse() {	    
	    // work out how many lines to reverse
	    // reverse half the number of lines (div by 2)
		int index = 1;
	    for (int i=0; i < this.getSortedLines().size(); i++) {
	    	String line = this.getSortedLines().get(String.valueOf(index));
	    	String reversedLine = "";
	    	if (null != line) {
	    		reversedLine = new StringBuilder(line).reverse().toString();
	    	}
	    	this.getSortedLines().put(String.valueOf(index), reversedLine);
	    	index = index+2;
	    }
	}
}
