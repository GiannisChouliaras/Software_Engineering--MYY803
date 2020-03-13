package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

public class Document {

	/**
	 * constructor 1 for new
	 * We have to give an author and a title.
	 * We also need to generate the date
	 */
	public Document(String authorString, String titleString)
	{
		this.authorString = authorString;
		this.titleString  = titleString;

		//generate date.
		Date thisDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		dateString = sdf.format(thisDate);

		lines = new ArrayList<Line>();

		ttsFactory = new TextToSpeechAPIFactory();
		audioManager = ttsFactory.createTTSAPI("FreeTTSAdapter");
	} //end of Constructor

	/**
	 * constructor 2 for open
	 * @param authorString
	 * @param titleString
	 * @param dateString
	 * @param lastModifiedString
	 */
	public Document(String authorString, String titleString, String dateString, String lastModifiedString)
	{
		this.authorString 		= authorString;
		this.titleString  		= titleString;
		this.dateString   		= dateString;
		this.lastModifiedString = lastModifiedString;

		lines = new ArrayList<>();
		ttsFactory = new TextToSpeechAPIFactory();
		audioManager = ttsFactory.createTTSAPI("FreeTTSAdapter");
	} //end constructor 2
	
	/**
	 * playing the Contents of the document.
	 */
	public void playContents()
	{
		String audioLines = "";
		for (Line line : lines) {
			audioLines += line.getLine() + "\n";
		}
		audioManager.play(audioLines);
	}

	/**
	 * playing the contents of the document in Reverse
	 */
	public void playReverseContents()
	{
		for (int i = lines.size() - 1; i >= 0; i--) {
			lines.get(i).playReverseLine();
		}
	}

	/**
	 * playing the Encoded contents of a document.
	 */
	public void playEncodedContents() {
		//TODO fill your code HERE.
	}

	/**
	 * @param line
	 * play a single line of the contents.
	 */
	public void playLine(int line)
	{
		Line myLine = lines.get(line);
		myLine.playLine();
	}

	public void playLineString(String s) {
		audioManager.play(s);
	}

	/**
	 * @param line
	 * play a single line but in Reverse.
	 */
	public void playReversedLine(int line) {
		Line thisLine = lines.get(line);
		thisLine.playReverseLine();
	}

	/**
	 * @param line
	 * play an encoded single line of the contents
	 */
	public void playEncodedLine(int line) {
		//TODO fill your code HERE.
	}

	/**
	 * @param encodedStrategy
	 * tune encoding Strategy (?)
	 */
	public void tuneEncodingStrategy(EncodingStrategy encodedStrategy) {
		//TODO fill your code HERE.
	}

	/**
	 * setText.
	 * Takes a String from the textArea of GUI and separates every line
	 * then creates a Line object, and put it in the arrayList lines.
	 */
	public void setListFromText(String text)
	{
		String[] textLines = text.split("\n"); //holds all lines.
		for(String string : textLines) {
			Line line = new Line(string); //create the line.
			lines.add(line); //when you create a line, add it to list.
		}
	}

	public void replaceContents(String content)
	{
		String[] arrayContent = content.split("\n");
		if (arrayContent.length == lines.size()) // if same size
		{
			for (int counter = 0; counter < arrayContent.length; counter++) {
				if (arrayContent[counter].equals(lines.get(counter).getLine())) continue;
				else lines.set(counter, new Line(arrayContent[counter]));
			}
		}
		else if (arrayContent.length > lines.size()) // if JtextArea is bigger. we need to add
		{
			for (int counter = 0; counter < lines.size(); counter++) {
				if (arrayContent[counter].equals(lines.get(counter).getLine())) continue;
				else lines.set(counter, new Line(arrayContent[counter]));
			}
			for (int counter = lines.size(); counter < arrayContent.length; counter++) {
				lines.add(new Line(arrayContent[counter]));
			}
		}
		else // if arrayList is bigger than JText (we need to remove)
		{
			for (int counter = 0; counter < arrayContent.length; counter++) {
				if (arrayContent[counter].equals(lines.get(counter).getLine())) continue;
				else lines.set(counter, new Line(arrayContent[counter]));
			}
			for (int counter = lines.size() - 1; counter >= arrayContent.length; counter--) {
				lines.remove(counter);
			}
		}
	}
	
	/**
	 * Setting the modified date.
	 */
	public void giveModifiedDate()
	{
		Date thisDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		lastModifiedString = sdf.format(thisDate);
	}

	/**
	 * GETTERS SETTERS
	 * @return
	 */
	public String getTitle() {return titleString; }

	public String getAuthor() { return authorString; }

	public String getDate() { return dateString; }

	public String getMDate() { return lastModifiedString; }

	public void setAuthor(String authorString) { this.authorString = authorString;}

	public void setTitle(String titleString) { this.titleString = titleString;}

	/**
	 * @return a String with all the Lines.
	 */
	public String getText()
	{
		String wholeText = "";
		for (Line line : lines)
			wholeText += line.getLine() + "\n";

		return wholeText;
	}

	public void setModifiedDate(String mDate) {
		this.lastModifiedString = mDate;
	}

	/**
	 * @return a String so we can write it to txt file.
	 */
	public String infoToWrite() {
		return authorString + "\n" + titleString + "\n" + dateString + "\n" + lastModifiedString + "\n";
	}

	public String infoButton() {
		return "Title: " + titleString + "\nAuthor: " + authorString + "\nCreation Date: " + dateString
				+ "\nLast Modified Date: " + lastModifiedString;
	}

	

	/** private Fields */
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	private TextToSpeechAPIFactory ttsFactory;
	private String authorString;
	private String titleString;
	private String dateString;
	private String lastModifiedString;
	private ArrayList<Line> lines;

} //end of class Document
