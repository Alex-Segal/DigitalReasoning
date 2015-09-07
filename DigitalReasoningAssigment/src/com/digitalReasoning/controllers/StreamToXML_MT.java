package com.digitalReasoning.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.digitalReasoning.constants.ZipEntityContant;

/*
	This class converts from stream to XML file.
*/
public class StreamToXML_MT implements Runnable{
	
	private final InputStream stream;
	private final String fileName;
	private final String outputDir;
	
	public StreamToXML_MT (InputStream stream, String fileName, String outputDir){
		this.stream = stream;
		this.fileName = fileName;
		this.outputDir = outputDir;
	}
	
	
	public void run() {
		
		BufferedReader reader;
		InputStream stream = this.stream;
		ArrayList<String> parsedFile = new ArrayList<String>();
		try {
			reader = new BufferedReader(new InputStreamReader(stream, "UTF8"));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			parsedFile.add(out.toString());
			String delimiter = ".";
			
			
			InputTokenizer it = new InputTokenizer();
			ArrayList<String> tokenizerOutput = new ArrayList<String>();
			tokenizerOutput = it.sentenceTokenizer(parsedFile, delimiter);
			String [] inZipFilePath = fileName.split("/");
			String outputFileName = inZipFilePath[inZipFilePath.length-1];
			File xmlOutFile = new File(outputDir+outputFileName);
			ToXMLConverter.convertToXML(tokenizerOutput, xmlOutFile);

			String output = out.toString();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}



	
}
