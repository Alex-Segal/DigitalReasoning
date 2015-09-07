package com.digitalReasoning.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.digitalReasoning.controllers.InputTokenizer;
import com.digitalReasoning.controllers.NamedEntityRecognition;
import com.digitalReasoning.controllers.ReadConfigurations;

public class ReadConfigTest {
	
	
	@Test
	public void readConfig1(){
		ReadConfigurations rc = new ReadConfigurations();
		File configFile = new File("config.txt");
		HashMap<String, String> properties = ReadConfigurations.readConfigFile(configFile);
		
		assertEquals("testSuccessful", properties.get("testValue").toString());
		
		
	}
	

}
