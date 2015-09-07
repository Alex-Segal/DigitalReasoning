package com.digitalReasoning.controllers;

import java.util.StringTokenizer;

/*
	This class splits file into single word or character tokens.
*/
public class SentenceSplitter {
	
	public StringTokenizer splitSentence(String line){
		
        StringTokenizer st = new StringTokenizer(line, "!#$%&()*+,-.:;<=>?@[]^_{|}~ \"\'\\", true);
		return st;
	}

}
