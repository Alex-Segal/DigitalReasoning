package com.digitalReasoning.data;

public class SentenceNERMetadata {
	
	private String original;
	private String ner;
	private int startLoc;
	private int endLoc;
	
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getNer() {
		return ner;
	}
	public void setNer(String ner) {
		this.ner = ner;
	}
	public int getStartLoc() {
		return startLoc;
	}
	public void setStartLoc(int startLoc) {
		this.startLoc = startLoc;
	}
	public int getEndLoc() {
		return endLoc;
	}
	public void setEndLoc(int endLoc) {
		this.endLoc = endLoc;
	}
}
