package com.digitalReasoning.executables;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.digitalReasoning.constants.ZipEntityContant;
import com.digitalReasoning.controllers.InputTokenizer;
import com.digitalReasoning.controllers.NamedEntityRecognition;
import com.digitalReasoning.controllers.ReadConfigurations;
import com.digitalReasoning.controllers.StreamToXML_MT;
import com.digitalReasoning.controllers.TextFileParser;
import com.digitalReasoning.controllers.ToXMLConverter;
import com.digitalReasoning.data.SentenceNERMetadata;

public class SentenceRecognition {

	public static void main(String[] args) {

		String configPath = "";
		
		if (args.length == 0){
			configPath = "config.txt";
		}else{
			configPath = args[0];
		}
		
		
		File configFile = new File(configPath);
		if (!configFile.exists()){
			System.out.println("Configuration file was not found!");
			System.out.println("Please place configuration file in the folder of the executable or specify path as parameter.");
			System.exit(1);
		}

		InputTokenizer it = new InputTokenizer();
		NamedEntityRecognition ner = new NamedEntityRecognition();
		ArrayList<String> parsedFile = new ArrayList<String>();
		ArrayList<String> tokenizerOutput = new ArrayList<String>();
		String delimiter = ".";
		
		// Get data files locations from config file
		HashMap<String, String> properties = ReadConfigurations.readConfigFile(configFile);

		File dataFile = new File(properties.get("inputFile").toString());
		File namedEntitiesFile = new File(properties.get("namedEntitiesFile").toString());
		File xmlOutFile = new File(properties.get("XMLOutDir").toString() +  properties.get("XMLOutFile1").toString());
		
		System.out.println("This program will execute all 3 tasks one after the other.");
		System.out.println("Output files are located in: " + properties.get("XMLOutDir").toString());
		
		// Task 1
		// This task will output text data file into tokenized XML file.
		parsedFile = TextFileParser.parseFile(dataFile);
		tokenizerOutput = it.sentenceTokenizer(parsedFile, delimiter);
		ToXMLConverter.convertToXML(tokenizerOutput, xmlOutFile);
		
		
		// Task 2
		// This task will match a file with NER records and output the result into XML file.
		File xmlOutFile2 = new File(properties.get("XMLOutDir").toString() +  properties.get("XMLOutFile2").toString());
		ArrayList<SentenceNERMetadata> senNerMetaList =  ner.getNamedEntitys(parsedFile, namedEntitiesFile);
		ToXMLConverter.NERMetadataToXML(senNerMetaList, xmlOutFile2);
		
		
		// Task 3
//		This task will read from zip file in a parallel manner and output it into XML file similarly to task 1.
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(properties.get("zipInputFile").toString());
			InputStream stream = null;
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while(entries.hasMoreElements()){
				ZipEntry entry = entries.nextElement();
				// Limit the files in the zip file to extract only windows formatted files.
				if (entry.getName().matches("^nlp_data...*$")){
					stream = zipFile.getInputStream(entry);
					if (stream != null){
						Runnable task = new StreamToXML_MT(stream, entry.getName(), properties.get("XMLOutDir").toString());
						Thread inZipFile = new Thread(task);
						inZipFile.setName(entry.getName());
						inZipFile.start();
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
