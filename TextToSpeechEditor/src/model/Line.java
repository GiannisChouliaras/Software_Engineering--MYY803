package model;

import model.encodingStrategies.IEncodingStrategy;
import model.text2speechapis.ITextToSpeechAPI;

import java.util.ArrayList;
import java.util.ListIterator;

public class Line {

    private ArrayList<String> words = new ArrayList<>();
    private IEncodingStrategy encodingStrategy;
    private ITextToSpeechAPI audioManager;

    public Line(String line) {
        separateWordsFromLine(line);
    }

    public void playLine() {
        try {
            String text = "";
            for (String word : words)
                text += word + " ";
            audioManager.play(text);
        } catch (NullPointerException np) {
            System.out.println(np.getMessage());
            System.out.println("Use the method setAPI on your command before calling" +
                    " playLine");
        }
    }

    public void playReverseLine() {
        try {
            for (int word = words.size()-1; word >= 0; word --) {
                audioManager.play(words.get(word));
            }
        } catch (NullPointerException np) {
            System.out.println(np.getMessage());
            System.out.println("Use the method setAPI on your command before calling" +
                    " playLine");
        } catch (IndexOutOfBoundsException index) {
            System.out.println(index.getMessage());
            System.out.println("Maybe iteration is not correct!");
        }
    }

    public void playEncodedLine() {
        try {
            for (String word : words) {
                audioManager.play(encodingStrategy.encode(word));
            }
        } catch (NullPointerException np) {
            System.out.println(np.getMessage());
        }
    }

    public void setAudioManager(ITextToSpeechAPI audioManager) {
        this.audioManager = audioManager;
    }

    public void tuneEncodingStrategy(IEncodingStrategy encodingStrategy) {
        this.encodingStrategy = encodingStrategy;
    }

    private void separateWordsFromLine(String line) {
        String[] wordsFromLine = line.split(" ");
        for (String word : wordsFromLine) {
            words.add(word);
        }
    }

    public String getWholeLine() {
        if (words.isEmpty()) return "";
        String line = "";
        for (String word: words) {
            line += word + " ";
        }
        return lineWithoutLastIndex(line);
    }

    private String lineWithoutLastIndex(String line) {
        if ((line != null) && (line.length() > 0)) {
            return line.substring(0, line.length() -1);
        }
        return null;
    }
}
