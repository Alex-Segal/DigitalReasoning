package com.digitalReasoning.controllers;

import java.io.File;
import java.util.ArrayList;

import com.digitalReasoning.data.SentenceNERMetadata;

/*
	This class will take a list of sentences and cross reference it with named entity records.
	Output: ArrayList<SentenceNERMetadata>
*/
public class NamedEntityRecognition {
	
	public ArrayList<SentenceNERMetadata> getNamedEntitys(ArrayList<String> parsedFile, File nerFile){
		
		ArrayList<String> ner = new ArrayList<String>();
		SentenceSplitter sp = new SentenceSplitter();
		SentenceNERMetadata nerMetadata = new SentenceNERMetadata();
		ArrayList<SentenceNERMetadata> nerMetadataList = new ArrayList<SentenceNERMetadata>();
		ner = TextFileParser.parseFile(nerFile);
		for (String line : parsedFile){
			
			for (String singleNER : ner){
				int index = line.indexOf(singleNER);
				while (index >= 0 && !singleNER.equals("")) {
					nerMetadata.setOriginal(line);
					nerMetadata.setNer(singleNER);
					nerMetadata.setStartLoc(index);
					nerMetadata.setEndLoc(index + singleNER.length());
					nerMetadataList.add(nerMetadata);
				    index = line.indexOf(singleNER, index + 1);
				}
			}
		}
		return nerMetadataList;
	}

}
