package com.digitalReasoning.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.digitalReasoning.controllers.NamedEntityRecognition;
import com.digitalReasoning.controllers.ReadConfigurations;
import com.digitalReasoning.data.SentenceNERMetadata;

public class NamedEntityRecognitionTest {
	
	

	File configFile = new File("config.txt");
	HashMap<String, String> properties = ReadConfigurations.readConfigFile(configFile);
	File namedEntitiesFile = new File(properties.get("namedEntitiesFile").toString());
	
	@Test
	public void ner1(){
    	ArrayList<String> input = new ArrayList<String>();
    	ArrayList<SentenceNERMetadata> output = new ArrayList<SentenceNERMetadata>();
    	NamedEntityRecognition ner = new NamedEntityRecognition();
    	input.add("First sentence.");
    	input.add("Here we will match the NER: BFGS.");
    	
    	output = ner.getNamedEntitys(input, namedEntitiesFile);
    	assertEquals("BFGS", output.get(0).getNer());
    	assertEquals(28, output.get(0).getStartLoc());
    	assertEquals(32, output.get(0).getEndLoc());
	}
	
	@Test
	public void ner2(){
    	ArrayList<String> input = new ArrayList<String>();
    	ArrayList<SentenceNERMetadata> output = new ArrayList<SentenceNERMetadata>();
    	NamedEntityRecognition ner = new NamedEntityRecognition();
    	input.add("First sentence.");
    	input.add("Here we will match the NER: BFGS.");
    	input.add("Second NER: Elements");
    	
    	output = ner.getNamedEntitys(input, namedEntitiesFile);
    	assertEquals("Elements", output.get(1).getNer());
    	assertEquals(12, output.get(1).getStartLoc());
    	assertEquals(20, output.get(1).getEndLoc());
	}
	
	@Test
	public void ner3(){
    	ArrayList<String> input = new ArrayList<String>();
    	ArrayList<SentenceNERMetadata> output = new ArrayList<SentenceNERMetadata>();
    	NamedEntityRecognition ner = new NamedEntityRecognition();
    	input.add("First sentence.");
    	input.add("Here we will match the NER: BFGS.");
    	input.add("Second NER: Elements and another one Apollo 11");
    	
    	output = ner.getNamedEntitys(input, namedEntitiesFile);
    	assertEquals("Apollo 11", output.get(2).getNer());
    	assertEquals(37, output.get(2).getStartLoc());
    	assertEquals(46, output.get(2).getEndLoc());
	}
	
	
	
}
