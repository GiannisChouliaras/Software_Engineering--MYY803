package commands;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Database;
import model.Document;

public class NewDocument implements ActionListener {

	/**
	 * Constructor for the class.
	 * @param database , the database from package controller.
	 * @param titleField
	 * @param authorField
	 */
    public NewDocument(Database database, JTextField authorField, JTextField titleField)
	{
    	this.titleField = titleField;
    	this.authorField = authorField;
    	this.database = database;
    }

    /**
     * @param actionEvent
     * We implement here the method actionPerformed(ActionEvent actionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
	{
    	document = null;
		String authorString = JOptionPane.showInputDialog("Give me Author");
		String titleString  = JOptionPane.showInputDialog("Give me title");
		if (database.containsDocument(authorString,titleString)) {
			JOptionPane.showMessageDialog(null,"Document already exists in database");
		}
		else {
			document = new Document(authorString, titleString);
			document.setModifiedDate("-");
			database.addToDatabase(document);
			JOptionPane.showInternalMessageDialog(null, "Document Added to Database");
			titleField.setText(titleString);
			authorField.setText(authorString);
		}
    }


	/**
	 * Private fields.
	 */
	private JTextField titleField;
	private JTextField authorField;
	private Database database;
	private Document document;
}
