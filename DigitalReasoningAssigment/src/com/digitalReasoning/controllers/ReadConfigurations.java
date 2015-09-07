package com.digitalReasoning.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/*
	This class reads property file.
	Output: HashMap
*/
public class ReadConfigurations {

	public static HashMap<String, String> readConfigFile(File file){
		
		ArrayList<String> propertiesArray = TextFileParser.parseFile(file);		
		HashMap<String, String> properties = new HashMap<String, String>();
		for (String property : propertiesArray){
			if (property.length() > 0){
				properties.put(property.split("=")[0], property.split("=")[1]);
				property.split("=");
			}
		}
		return properties;
	}
	
}
