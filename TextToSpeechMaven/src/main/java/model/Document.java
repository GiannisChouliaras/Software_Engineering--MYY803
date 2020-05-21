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
		fakeAudioManager = ttsFactory.createTTSAPI("FakeTextToSpeechAPI");
	}

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

		lines = new ArrayList<Line>();
		ttsFactory = new TextToSpeechAPIFactory();
		audioManager = ttsFactory.createTTSAPI("FreeTTSAdapter");
	}
	

	public void playContents()
	{
		String audioLines = "";
		for (Line line : lines) {
			audioLines += line.getLine() + "\n";
		}
		audioLines = audioLines.substring(0, audioLines.length() -1);
		audioManager.play(audioLines);
	}

	public String playContentsForTest()
	{
		String audioLines = "";
		for (Line line: lines) {
			audioLines += line.getLine() + "\n";
		}
		audioLines = audioLines.substring(0, audioLines.length() -1);
		fakeAudioManager.play(audioLines);
		return fakeAudioManager.getString();
	}

	public void playReverseContents()
	{
		for (int i = lines.size() - 1; i >= 0; i--) {
			lines.get(i).playReverseLine();
		}
	}

	public void playEncodedContents()
	{
		String encodeContent = "";
		for (Line line : lines) {
			encodeContent += line.getLine() + "\n";
		}
		encodeContent = encodeContent.substring(0, encodeContent.length() -1);
		String encoded = encodingStrategy.encode(encodeContent);
		audioManager.play(encoded);
	}

	public String playEncodedContentsTest() {
		String encodeContent = "";
		for (Line line : lines) {
			encodeContent += line.getLine() + "\n";
		}
		encodeContent = encodeContent.substring(0, encodeContent.length() -1);
		String encoded = encodingStrategy.encode(encodeContent);
		fakeAudioManager.play(encoded);
		return fakeAudioManager.getString();
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

	public void playLineString(String s)
	{
		audioManager.play(s);
	}

	public void playReversedLine(int line)
	{
		Line thisLine = lines.get(line);
		thisLine.playReverseLine();
	}

	public void playEncodedLine(int line)
	{
		Line myLine = lines.get(line);
		myLine.tuneEncodingStrategy(encodingStrategy);

		myLine.playEncodedLine();

	}

	public void tuneEncodingStrategy(EncodingStrategy encodedStrategy) {
		this.encodingStrategy = encodedStrategy;
	}

	/**
	 * setText.
	 * Takes a String from the textArea of GUI and separates every line
	 * then creates a Line object, and put it in the arrayList lines.
	 */
	public void setListFromText(String text)
	{
		String[] textLines = text.split("\n");
		for(String string : textLines) {
			Line line = new Line(string);
			lines.add(line);
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

	public void giveModifiedDate()
	{
		Date thisDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		lastModifiedString = sdf.format(thisDate);
	}

	public String getTitle() {return titleString; }

	public String getAuthor() { return authorString; }

	public void setAuthor(String authorString) { this.authorString = authorString;}

	public void setTitle(String titleString) { this.titleString = titleString;}

	public void setVolumeDocument(int value) {
		audioManager.setVolume(value);
		for (Line line : lines) {
			line.setVolumeLine(value);
		}
	}

	public void setRateDocument(int value) {
		audioManager.setRate(value);
		for (Line line : lines) {
			line.setRateLine(value);
		}
	}

	public void setPitchDocument(int value) {
		audioManager.setPitch(value);
		for (Line line : lines) {
			line.setPitchLine(value);
		}
	}

	public int getPitch() { return audioManager.getPitch();}

	public int getRate() { return audioManager.getRate();}

	public int getVolume() { return audioManager.getVolume();}

	public ArrayList<Line> getArrayList() {return lines;}

	public String getText()
	{
		String wholeText = "";
		for (Line line : lines)
			wholeText += line.getLine() + "\n";
		wholeText = wholeText.substring(0, wholeText.length()-1);
		return wholeText;
	}

	public void setModifiedDate(String mDate) {
		this.lastModifiedString = mDate;
	}

	public String infoToWrite()
	{
		return authorString + "\n" + titleString + "\n" + dateString + "\n" + lastModifiedString + "\n";
	}

	public String infoButton()
	{
		return "Title: " + titleString + "\nAuthor: " + authorString + "\nCreation Date: " + dateString
				+ "\nLast Modified Date: " + lastModifiedString;
	}

	private EncodingStrategy		encodingStrategy;
	private TextToSpeechAPI			audioManager, fakeAudioManager;
	private TextToSpeechAPIFactory	ttsFactory;
	private ArrayList<Line> 		lines;
	private String	authorString, titleString, dateString, lastModifiedString;

} //end of class Document
