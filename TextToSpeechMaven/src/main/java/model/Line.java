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

    /*
     * playing the Line.
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
    public void playEncodedLine()
    {
        String encode = "";
        String playEncode = "";
        for (String string : words) {
            encode += string + "\n";
        }
        encode = encode.substring(0, encode.length() -1);
        playEncode = encodingStrategy.encode(encode);
        audioManager.play(playEncode);
    }

    /**
     * @param encodingStrategy
     * tune encoding Strategy (what strategy we will use for the encode(?))
     */
    public void tuneEncodingStrategy(EncodingStrategy encodingStrategy) {
        this.encodingStrategy = encodingStrategy;
    }
    
    public String getLine()
    {
    	String wholeLineString = "";
    	for (String string : words) {
    		wholeLineString += string + " ";
    	}
    	wholeLineString = wholeLineString.substring(0, wholeLineString.length() -1);
    	return wholeLineString;
    }


    public void setVolumeLine(int value) {
        audioManager.setVolume(value);
    }

    public void setPitchLine(int value) {
        audioManager.setPitch(value);
    }

    public void setRateLine(int value) {
        audioManager.setRate(value);
    }

    /** private Fields */
    private EncodingStrategy        encodingStrategy;
    private TextToSpeechAPI         audioManager;
    private TextToSpeechAPIFactory  ttsFactory;
    private String                  line;
    private ArrayList<String>       words;

} // end of class Line
