package com.digitalReasoning.controllers;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.digitalReasoning.constants.CharTypes;
import com.digitalReasoning.data.SentenceNERMetadata;

import java.io.File;
import java.lang.reflect.Field;

/*
	This class will take a list of sentences and convert it to an specialized XML file.
*/
public class ToXMLConverter {

	public static void convertToXML(ArrayList<String> input, File xmlOutFile){
		
		SentenceSplitter sp = new SentenceSplitter();
		
		try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         // root element
	         Element rootElement = doc.createElement("sentences");
	         doc.appendChild(rootElement);
	         
	         
	 		for (String sentenceStr : input){

	 		      // create original sentence element
		        Element sentence = doc.createElement("sentence");
		        Attr attrType = doc.createAttribute("category");
		        attrType.setValue("original");
		        sentence.setAttributeNode(attrType);
		        sentence.appendChild(doc.createTextNode(sentenceStr));
		        rootElement.appendChild(sentence);
	 			
		        // Tokenize original sentence
	 			StringTokenizer splitTocken = sp.splitSentence(sentenceStr);
	 			Element token;

	 			while (splitTocken.hasMoreTokens()) {
	 				String currToken = splitTocken.nextToken();
	 				String tokenType = "";
	 				
	 				Pattern letterPattern = Pattern.compile("\\p{Alpha}");			// letters
	 				Matcher letterMatcher = letterPattern.matcher(currToken);
	 				Pattern numberPattern = Pattern.compile("\\p{Digit}");			// digits
		 			Matcher numberMatcher = numberPattern.matcher(currToken);
		 			Pattern spacePattern = Pattern.compile("\\p{Blank}");			// space and tab
		 			Matcher spaceMatcher = spacePattern.matcher(currToken);
		 			Pattern punctuationPattern = Pattern.compile("\\p{Punct}");		// punctuation
		 			Matcher punctuationMatcher = punctuationPattern.matcher(currToken);
	 				
	 				if (letterMatcher.find()){
	 					tokenType = CharTypes.WORD;
	 				}else if(numberMatcher.find()){
	 					tokenType = CharTypes.NUMBER;
	 				}else if(spaceMatcher.find()){
	 					tokenType = CharTypes.SPACE;
	 				}else if(punctuationMatcher.find()){
	 					tokenType = CharTypes.PUNCTUATION;
	 				}else{
	 					tokenType = CharTypes.OTHER;
	 				}
	 				
	 				token = doc.createElement("token");
	 				attrType = doc.createAttribute("category");
	 				attrType.setValue(tokenType);
	 				token.setAttributeNode(attrType);
	 				token.appendChild(doc.createTextNode(currToken));
	 				sentence.appendChild(token);
	 	        }
			}
	         // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(xmlOutFile);
	         transformer.transform(source, result);
	        
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	public static void NERMetadataToXML (ArrayList<SentenceNERMetadata> senNerMetaList, File xmlOutFile){
		
		try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         // root element
	         Element rootElement = doc.createElement("sentences");
	         doc.appendChild(rootElement);
	         
	         
	         // Retrieves elements names from SentenceNERMetadata to build matching tagNames
	         String fieldValue;
	         Element XMLfield;
	         for (SentenceNERMetadata sen : senNerMetaList){
	        	 
	        	 Element sentence = doc.createElement("sentence");
				 rootElement.appendChild(sentence);
	        	 
	        	 fieldValue = sen.getOriginal();
	        	 XMLfield = doc.createElement("original");
				 XMLfield.appendChild(doc.createTextNode(fieldValue));
				 sentence.appendChild(XMLfield);
				 
	        	 fieldValue = sen.getNer();
	        	 XMLfield = doc.createElement("ner");
				 XMLfield.appendChild(doc.createTextNode(fieldValue));
				 sentence.appendChild(XMLfield);
				 
	        	 fieldValue = Integer.toString(sen.getStartLoc());
	        	 XMLfield = doc.createElement("startLoc");
				 XMLfield.appendChild(doc.createTextNode(fieldValue));
				 sentence.appendChild(XMLfield);
				 
	        	 fieldValue = Integer.toString(sen.getEndLoc());
	        	 XMLfield = doc.createElement("endLoc");
				 XMLfield.appendChild(doc.createTextNode(fieldValue));
				 sentence.appendChild(XMLfield);
	         }
	         
	         // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(xmlOutFile);
	         transformer.transform(source, result);
	        	 
		} catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}
}
