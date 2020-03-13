package commands;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Database;
import model.Document;
import view.FileTypeFilter;

public class SaveDocument implements ActionListener {

	/**
	 * Constructor
	 * @param database
	 * @param author
	 * @param title
	 * @param textArea
	 */
  public SaveDocument(Database database, JTextField author, JTextField title, JTextArea textArea)
  {
	  this.author   = author;
	  this.title    = title;
	  this.textArea = textArea;
	  this.database = database;
  }

	/**
	 * In this method, when we click the button save, a dialog is opened. we choose
	 * the destination we want. when we press ok, the method document.getInfo() will
	 * take the Author, title and dates and place them in top of the txt file.
	 * After we write whatever we have in JTextArea.
	 * @param actionEvent
	 */
  @Override
  public void actionPerformed(ActionEvent actionEvent)
  {
	  Document document = database.getDocument(author.getText(),title.getText());
	  
	  // Start to save the file. JFileChooser and using the class FileTypeFilter.
	  JFileChooser fs = new JFileChooser(new File("C:\\"));
	  fs.setDialogTitle("Save a File");
	  fs.setFileFilter(new FileTypeFilter(".tts", "TTS File"));
	  int result = fs.showSaveDialog(null);

	  if (result == JFileChooser.APPROVE_OPTION) {
		  document.giveModifiedDate();
		  String content = document.infoToWrite();
		  content += textArea.getText();
		  File fi = fs.getSelectedFile();
		  try {
			  FileWriter fw = new FileWriter(fi.getPath());
			  fw.write(content);
			  fw.flush();
			  fw.close();
		  }
		  catch (Exception e1) {
			  JOptionPane.showMessageDialog(null, e1.getMessage());
		  }
	  }//End of Saving the file.
  } // end of actionPerformed.

	/**
	 * private Fields for the class.
	 */
	private JTextField author;
	private JTextField title;
	private JTextArea textArea;
	private Database database;

} // end of the class.
