package com.digitalReasoning.controllers;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
	This class converts from raw file lines into sentences. each line will have one sentence.
*/
public class InputTokenizer {

	public ArrayList<String> sentenceTokenizer(ArrayList<String> lines, String delimiter){
		
		ArrayList<String> sentences = new ArrayList<String>();
		for (String st : lines) {
			// The string will be parsed with the "." delimiter.
			StringTokenizer tokens = new StringTokenizer(st, delimiter, true);
			while (tokens.hasMoreTokens()) {
				String token = tokens.nextToken().trim();
				if (token.length() > 0){
					if (sentences.size() > 0 && (Character.isLowerCase(token.charAt(0)) || !Character.isLetterOrDigit(token.charAt(0)) || token.equals(delimiter))){
						sentences.set(sentences.size()-1, sentences.get(sentences.size()-1) + token);
					}else{
						sentences.add(token);
					}
				}
			}
		}
		return sentences;
	}
	

	
}
