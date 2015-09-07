package com.digitalReasoning.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.digitalReasoning.controllers.ReadConfigurations;
import com.digitalReasoning.controllers.TextFileParser;

public class TextFileParserTest {

	File configFile = new File("config.txt");
	HashMap<String, String> properties = ReadConfigurations.readConfigFile(configFile);
	File dataFile = new File(properties.get("inputFile").toString());
	
	@Test
	public void parser1(){
		ArrayList<String> test = TextFileParser.parseFile(dataFile);
		assertEquals(3, test.size());
	}
	
	@Test
	public void parser2(){
		ArrayList<String> test = TextFileParser.parseFile(dataFile);
		assertEquals("The", test.get(0).substring(0, 3));
	}
	
	@Test
	public void parser3(){
		ArrayList<String> test = TextFileParser.parseFile(dataFile);
		assertEquals("trigger", test.get(2).substring(37, 44));
	}
}
