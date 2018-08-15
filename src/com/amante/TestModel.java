package com.amante;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class TestModel {
	public static void main(String[] args) throws IOException{
    	//input string
    	
		String sentence = new String("What is the worst symptom of allergies ? The histamine then acts on  "
				+ "the eyes , nose , throat , lungs  skin , or gastrointestinal tract and causes the symptoms the allergic reaction ." + 
				"Future exposure to that same allergen will trigger this antibody response again.");
		
    	//converting input to array of string
    	String[] sentence_to_array = sentence.split(" ");
    	
    	System.out.println("\nYour sentence: \n\t" + sentence);
       
    	//System.out.println(Arrays.toString(sentence_to_array));
        
        // find person and location
        System.out.println("\nrecognized entity name");
        TestModel.findName(sentence_to_array);
        System.out.println();
        
    }// end of main method
	
	/**
     * method to find locations in the sentence
     * @throws IOException
     */
    public static void findName(String[] sentence) throws IOException {
        InputStream name_model = new FileInputStream("model/cl-model-sample.bin");
        // load the model from file
        TokenNameFinderModel model = new TokenNameFinderModel(name_model);
        name_model.close();
        // feed the model to name finder class
        NameFinderME nameFinder = new NameFinderME(model);
        Span nameSpans[] = nameFinder.find(sentence);
        // nameSpans contain all the possible entities detected
        
        System.out.println(nameSpans.length);
        
        for(Span s: nameSpans){
            System.out.print(s.toString());
            System.out.print("  :  ");
            // s.getStart() : contains the start index of possible name in the input string array
            // s.getEnd() : contains the end index of the possible name in the input string array
            for(int index=s.getStart();index<s.getEnd();index++){
                System.out.print(sentence[index]+" ");
            }
            System.out.println();
        }
    }

}
