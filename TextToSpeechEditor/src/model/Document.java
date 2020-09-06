package model;

import model.encodingStrategies.IEncodingStrategy;
import model.text2speechapis.Facade;
import model.text2speechapis.ITextToSpeechAPI;
import view.Text2SpeechEditorView;

import java.util.ArrayList;
import java.util.Date;

public class Document {

    private IEncodingStrategy encodingStrategy = null;
    private ITextToSpeechAPI audioManager = null;
    private String author;
    private String title;
    private String documentsCreationalDate;
    private String documentsLastSavedDate = "";
    private ArrayList<Line> lines = new ArrayList<Line>();

    public Document(String author, String title) {
        this.author = author;
        this.title = title;
        documentsCreationalDate = new Date().toString();
    }

    public void playContents() {
        try {
            for (Line line : lines)
                audioManager.play(line.getWholeLine());
        } catch (NullPointerException np) {
            System.out.println(np.getLocalizedMessage());
            System.out.println("use setAPI for your document in command");
        } catch (IndexOutOfBoundsException index) {
            System.out.println(index.getMessage());
            System.out.println("Maybe iteration is not correct!");
        }
    }


    public void playReverseContents() {
        try {
            for (int line = lines.size()-1; line >= 0; line --) {
                lines.get(line).setAudioManager(audioManager);
                lines.get(line).playReverseLine();
            }
        } catch (NullPointerException np) {
            System.out.println(np.getLocalizedMessage());
            System.out.println("use setAPI for your document in command");
        }
    }

    public void playEncodedContents() {
        try {
            for (Line line : lines) {
                line.tuneEncodingStrategy(encodingStrategy);
                line.setAudioManager(audioManager);
                line.playEncodedLine();
            }
        } catch (NullPointerException np) {
            System.out.println(np.getMessage());
        }
    }

    public void playLine(int line) {
        try {
            lines.get(line).setAudioManager(audioManager);
            lines.get(line).playLine();
        } catch(IndexOutOfBoundsException l) {
            System.out.println(l.getMessage());
        }
    }

    public void playReverseLine(int line) {

    }

    public void playEncodedLine(int line) {

    }

    public void tuneEncodingStrategy(IEncodingStrategy encodingStrategy) {
        this.encodingStrategy = encodingStrategy;
    }

    public void setAPI(ITextToSpeechAPI audioManager) {
        this.audioManager = audioManager;
        Facade facade = new Facade(this.audioManager);
        Text2SpeechEditorView INSTANCE = Text2SpeechEditorView.getSingletonView();
        facade.combineVRP(INSTANCE.getVolume(), INSTANCE.getRate(),INSTANCE.getPitch());
    }

    public String getDocumentsCreationalDate() {
        return documentsCreationalDate;
    }

    public void setBothDates(String creational, String lastSaved) {
        documentsCreationalDate = creational;
        documentsLastSavedDate = lastSaved;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void editDocument(String textArea) {
        lines.clear();
        String[] linesFromText = seperateLinesFromText(textArea);
        for (String line: linesFromText) {
            lines.add(new Line(line));
        }
    }

    public String getText() {
        if (lines.isEmpty()) return "-";
        String text = "";
        for (Line line: lines) {
            text += line.getWholeLine() + "\n";
        }
        return textWithoutLastIndex(text);
    }

    public String toString() {
        return author + "\n" + title + "\n" + documentsCreationalDate + "\n" +
                documentsLastSavedDate;
    }

    private String textWithoutLastIndex(String text) {
        if ((text != null) && (text.length() > 0)) {
            return text.substring(0, text.length() -1);
        }
        return null;
    }

    private String[] seperateLinesFromText(String text) {
        return text.split("\\n");
    }
}
