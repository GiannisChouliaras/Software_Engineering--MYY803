package commands;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import controller.Database;
import model.Document;
import view.FileTypeFilter;

public class OpenDocument implements ActionListener {

	/**
	 * Constructor for the Class.
	 * @param database is the main database where we store all documents.
	 * @param authorString JTextField field.
	 * @param titleString  JTextField field.
	 * @param textArea	   JTextArea field
	 */
    public OpenDocument(Database database, JTextField authorString, JTextField titleString, JTextArea textArea)
	{
        this.authorString = authorString;
        this.titleString  = titleString;
        this.textArea = textArea;
        this.database = database;
        document = null;
    }

	/**
	 * In this method,
	 * 1. open a dialog file chooser.
	 * 2. im choosing one file (.txt).
	 * 3. i read the 4 first lines so i can see in my db if i have a doc with these fields.
	 * 4. If my db contains this doc, i should ask: you want to change the db text to whatever txt file has?
	 * 5. depends on the button he press. if Yes, we replace all the text and ArrayList of db's doc to txt.
	 * 6. If No, i keep the db doc and ask if he wants to change/save the txt content to the current db.doc.text
	 * @param actionEvent
	 */
	@Override
    public void actionPerformed(ActionEvent actionEvent)
	{
    	JFileChooser fs = new JFileChooser(new File("C:\\"));
    	fs.setDialogTitle("Open a File");
    	fs.setFileFilter(new FileTypeFilter(".tts", "TTS File"));
    	
    	int result = fs.showOpenDialog(null);
    	
    	if (result == JFileChooser.APPROVE_OPTION) {
    		try {
    			File file = fs.getSelectedFile();
    			BufferedReader bf = new BufferedReader(new FileReader(file.getPath()));
    			String line = "";
    			String fullTextString = "";

    			String author = bf.readLine(); // take author.
    			String title = bf.readLine();  // take title.
    			String date = bf.readLine();   // take original date.
    			String modDate = bf.readLine();// take modified date.
				//read all text.
    			while ((line = bf.readLine()) != null) {
    				fullTextString += line + "\n";
    			}

				// close the buffer
    			if (bf != null) {
    				bf.close();	
    			}

    			//here we implement 4,5,6.
				document = null;
				if (database.containsDocument(author,title)) {
					document = database.getDocument(author, title);
					if (document.getText().equals(fullTextString)) {
						placeInfo();
					}
					else {
						int option = JOptionPane.showConfirmDialog(null,"There are changes" +
								" in database of this document.\nDo you want to keep the txt file and delete the changes?");

						if (option == 0) // I want to replace the contents of database's doc to whatever txt has.
						{
							document.replaceContents(fullTextString); // replace the arrayList.
							placeInfo();
						}
						else if (option == 1) { // I want to display documents content
							placeInfo();
						}
					}
				}
				else {// database does not contain a document like this.

					document = new Document(author,title,date,modDate);
					document.setListFromText(fullTextString);
					database.addToDatabase(document);
					placeInfo();
				}
    			
    		} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
    	}
    }

    private void placeInfo()
	{
		authorString.setText(document.getAuthor());
		titleString.setText(document.getTitle());
		textArea.setText(document.getText());
	}

	/**
	 * Private fields for the class.
	 */
	private JTextField authorString;
	private JTextField titleString;
	private JTextArea textArea;
	private Database database;
	private Document document;
} // end of class
