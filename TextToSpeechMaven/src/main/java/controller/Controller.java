package controller;

import java.util.ArrayList;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import model.Line;

public class Controller {

	public Controller() {
		database = new ArrayList<Document>();
		strategiesFactory = new StrategiesFactory();
	}

	public Document getDocument(String author, String title)
	{
		for (Document document : database) {
			if (document.getTitle().equals(title) && document.getAuthor().equals(author)) {
				return document;
			}
		}
		System.out.println("No document with this Author and title");
		return null;
	}
	
	/**
	 * Checks if database contains a Document with this author 
	 * and title.
	 * @param author
	 * @param title
	 * @return
	 */
	public boolean containsDocument(String author, String title)
	{
		for (Document document : database) {
			if (document.getAuthor().equals(author) && document.getTitle().equals(title)) {
				return true;
			}
		}
		return false;
	}
 
	/**
	 * This is what will execute when the action Listener NewDocument button pressed.
	 * No need to test the class NewDocument, everything will implement in here.
	 * @param author
	 * @param title
	 * @param volume
	 * @param pitch
	 * @param rate
	 */
	public void newDocument(String author, String title, int volume, int pitch, int rate)
	{
		Document document = new Document(author, title);
		document.setModifiedDate("-");
		document.setVolumeDocument(volume);
		document.setPitchDocument(pitch);
		document.setRateDocument(rate);
		database.add(document);
	}
	
	/**
	 * Second newDocument means when you click the button open, it will check if database
	 * contains a document with this author and title. If not, it will read from the file
	 * the first 4 lines, take the data and create a new Document.
	 * @param author
	 * @param title
	 * @param date
	 * @param modifiedDate
	 * @param fullTextString
	 */
	public void secondNewDocument(String author, String title, String date,
								  String modifiedDate, String fullTextString)
	{
		Document document = new Document(author,title,date,modifiedDate);
		document.setListFromText(fullTextString);
		document.setVolumeDocument(50);
		document.setPitchDocument(100);
		document.setRateDocument(100);
		database.add(document);
	}
	
	/**
	 * Do all the work for edit Command. We can test this instead of EditCommand.
	 * choice 0 -> edit author.
	 * choice 1 -> edit title.
	 * choice 2 -> edit text.
	 * @param author
	 * @param title
	 * @param choice
	 * @param element
	 */
	public void editDocument(String author, String title, int choice, String element)
	{
		for (Document document : database) {
			if (document.getAuthor().equals(author) && document.getTitle().equals(title))
			{
				if (choice == 0) { 
					document.setAuthor(element);
				}
				else if (choice == 1) {
					document.setTitle(element);
				}
				else if (choice == 2) {
					document.replaceContents(element);
				}
				else {
					System.out.println("choice not good");
				}
			} 
		}
	}
	
	/**
	 * Same Content will tell us if the document we want to open and the document in our db
	 * has same text. if true, then we have to feed arrayList with info's so we can print it.
	 * @param author
	 * @param title
	 * @param text
	 * @param info
	 * @return
	 */
	public boolean sameContents(String author, String title, String text, ArrayList<String> info)
	{
		for (Document document : database) {
			if (document.getAuthor().equals(author) && document.getTitle().equals(title)) {
				if (document.getText().equals(text)) {
					info.add(document.getAuthor());
					info.add(document.getTitle());
					info.add(document.getText());
					info.add(Integer.toString(document.getVolume()));
					info.add(Integer.toString(document.getPitch()));
					info.add(Integer.toString(document.getRate()));
					return true;
				} else return false;
			}
		}
		System.out.println("shouldn't print that controller LINE 156");
		return false;
	}

	/**
	 * getInfoToOpen will open a file, if the content of the file is not equal to content of db
	 * will ask us to choose between keeping the content of db or original file.
	 * if we choose "yes" = 0, it will change the content of db to whatever original has.
	 * @param author
	 * @param title
	 * @param text
	 * @param option
	 * @return an ArrayList<String> for the info's to print.
	 */
	public ArrayList<String> getInfoToOpen(String author, String title, String text, int option)
	{
		for (Document document : database) {
			ArrayList<String> info = new ArrayList<String>();
			if (document.getAuthor().equals(author) && document.getTitle().equals(title)) {
				if (option == 0)
					document.replaceContents(text);
			}
			info.add(document.getAuthor());
			info.add(document.getTitle());
			info.add(document.getText());
			info.add(Integer.toString(document.getVolume()));
			info.add(Integer.toString(document.getPitch()));
			info.add(Integer.toString(document.getRate()));
			return info;
		}
		return null;
	}

	/**
	 * Take the author, title and content of textArea.
	 * searching for the document, update modified date and content.
	 * Its ready to save the file.
	 * @param author
	 * @param title
	 * @param content
	 * @return the info of the Document so we will write the info in first 4 lines.
	 * we will never see these details.
	 */
	public String saveToFile(String author, String title, String content)
	{
		Document document = null;
		for (Document doc : database) {
			if (doc.getAuthor().equals(author) && doc.getTitle().equals(title)) {
				document = doc;
				break;
			}
		}
		document.replaceContents(content);
		document.giveModifiedDate();
		return document.infoToWrite();
	}

	/**
	 * Take the Author, title and an option.
	 * Encode all text with atBash or Rot13
	 * @param author
	 * @param title
	 * @param option
	 */
	public void encodeAll(String author, String title, String option)
	{
		EncodingStrategy encodingStrategy = strategiesFactory.createStrategy(option);
		for (Document document : database) {
			if (document.getAuthor().equals(author) && document.getTitle().equals(title)) {
				document.tuneEncodingStrategy(encodingStrategy);
				document.playEncodedContents();
				break;
			}
		}
	}

	/**
	 * Take the Author, title and an option.
	 * Encode one Line with atBash or Rot13
	 * @param author
	 * @param title
	 * @param line
	 * @param option
	 */
	public void encodeLine(String author, String title, int line, String option)
	{
		EncodingStrategy encodingStrategy = strategiesFactory.createStrategy(option);

		for (Document document : database) {
			if (document.getAuthor().equals(author) && document.getTitle().equals(title)) {
				document.tuneEncodingStrategy(encodingStrategy);
				document.playEncodedLine(line);
				break;
			}
		}
	}

	private ArrayList<Document> database;
	private StrategiesFactory 	strategiesFactory;
}
