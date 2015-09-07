package com.digitalReasoning.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;

/*
	This class parse a file and return ArrayList<String> of the raw lines in the file.
*/
public class TextFileParser {

	public static ArrayList<String> parseFile(File file) {

		ArrayList<String> lines = new ArrayList<String>();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(reader);
			String lineString;
			while ((lineString = buffReader.readLine()) != null) {
				lines.add(lineString);
			}
		} catch (IOException e) {
			System.out.println(e);
			System.exit(0);
		}
		return lines;
	}
}
