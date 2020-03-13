package model;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

import java.util.ArrayList;

public class Line {

    /**
     * Constructor of the class Line.
     * @param line from the document class.
     * we feed also the words arrayList.
     */
    public Line(String line)
    {
        this.line    = line;
        ttsFactory   = new TextToSpeechAPIFactory();
        audioManager = ttsFactory.createTTSAPI("FreeTTSAdapter");

        words = new ArrayList<String>();

        //split here the words.
        String[] myWords = line.split(" ");
        for (String word : myWords) {
            words.add(word);
        }
    }

    /**
     * playing the line (the whole arraylist(?))
     */
    public void playLine() {
        audioManager.play(line);
    }

    /**
     * playing the line but in reverse (the whole arrayList but from the end.)
     */
    public void playReverseLine()
    {
        String reversedString = "";
        for (int i = words.size() - 1; i >= 0; i--) {
            reversedString += words.get(i);
        }
        audioManager.play(reversedString);
    }

    /**
     * Playing the encoded line.
     */
    public void playEncodedLine() {
        //TODO fill your code HERE
    }

    /**
     * @param encodingStrategy
     * tune encoding Strategy (what strategy we will use for the encode(?))
     */
    public void tuneEncodingStrategy(EncodingStrategy encodingStrategy) {
        //TODO fill your code HERE
    }
    
    public String getLine()
    {
    	String wholeLineString = "";
    	for (String string : words) {
    		wholeLineString += string + " ";
    	}
    	return wholeLineString;
    }


    /** private Fields */
    private EncodingStrategy encodingStrategy;
    private TextToSpeechAPI audioManager;
    private TextToSpeechAPIFactory ttsFactory;
    private String line;
    ArrayList<String> words = new ArrayList<String>();

} // end of class Line
