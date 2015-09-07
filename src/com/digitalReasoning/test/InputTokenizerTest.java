package com.digitalReasoning.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.digitalReasoning.controllers.InputTokenizer;

public class InputTokenizerTest {
	
    @Test
    public void testConcatenate1() {
    	InputTokenizer it = new InputTokenizer();
    	ArrayList<String> output = new ArrayList<String>();
    	ArrayList<String> input = new ArrayList<String>();
    	input.add("one two ");
    	output = it.sentenceTokenizer(input, ".");
        assertEquals(1, output.size());

    }
    
    @Test
    public void testConcatenate2() {
    	InputTokenizer it = new InputTokenizer();
    	ArrayList<String> output = new ArrayList<String>();
    	ArrayList<String> input = new ArrayList<String>();
    	input.add("one two ");
    	input.add("five 6");
    	output = it.sentenceTokenizer(input, ".");
        assertEquals(1, output.size());
    }
    
    @Test
    public void testConcatenate3() {
    	InputTokenizer it = new InputTokenizer();
    	ArrayList<String> output = new ArrayList<String>();
    	ArrayList<String> input = new ArrayList<String>();
    	input.add("one. Two. Bob go. ");
    	input.add("Five 6");
    	output = it.sentenceTokenizer(input, ".");
        assertEquals("one.", output.get(0));
        assertEquals("Two.", output.get(1));
        assertEquals("Bob go.", output.get(2));
        assertEquals("Five 6", output.get(3));
    }

}
