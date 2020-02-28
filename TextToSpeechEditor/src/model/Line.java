package model;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

import java.util.ArrayList;

public class Line {

    /** private Fields */
    private EncodingStrategy encodingStrategy;
    private TextToSpeechAPI audioManager;

    ArrayList<String> words = new ArrayList<String>();

    /**
     * Constructor of the class Line
     */
    public Line(){
        //TODO fill your code HERE
    }

    /**
     * playing the line (the whole arraylist(?))
     */
    public void playLine(){
        //TODO fill your code HERE
    }

    /**
     * playing the line but in reverse (the whole arrayList but from the end.)
     */
    public void playReverseLine() {
        //TODO fill your code HERE
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
} // end of class Line
