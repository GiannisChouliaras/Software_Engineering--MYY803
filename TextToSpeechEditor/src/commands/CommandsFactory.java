package commands;

import controller.Database;

import javax.swing.*;

public class CommandsFactory {

    /**
     * @param database
     * @param titleField
     * @param authorField
     * @param textArea
     * @param editAuthorItem
     * @param editTitleItem
     * @param ttsLineItem
     */
    public CommandsFactory(Database database, JTextField titleField, JTextField authorField,
                           JTextArea textArea,JMenuItem editAuthorItem, JMenuItem editTitleItem,
                           JMenuItem ttsLineItem)
    {
        this.authorField    = authorField;
        this.textArea       = textArea;
        this.database       = database;
        this.titleField     = titleField;
        this.editAuthorItem = editAuthorItem;
        this.editTitleItem  = editTitleItem;
        this.ttsLineItem    = ttsLineItem;
    }

    /**
     * @param command
     * method that we create a command giving a String and
     * @return an ActionListener
     */
    public ActionListener createCommand(String command) {
        if (command.equals("NewDocument")) {
            return new NewDocument(database,authorField,titleField);
        }
        else if (command.equals("EditDocument")) {
        	return new EditDocument(database, authorField, titleField, textArea,editAuthorItem,
                    editTitleItem);
        }
        else if (command.equals("ExitListener")) {
        	return new ExitListener();
        }
        else if (command.equals("OpenDocument")) {
        	return new OpenDocument(database, authorField, titleField, textArea);
        }
        else if (command.equals("SaveDocument")) {
        	return new SaveDocument(database, authorField, titleField, textArea);
        }
        else if (command.equals("DocumentToSpeech")) {
            return new DocumentToSpeech(database, authorField, titleField, textArea);
        }
        else if (command.equals("LineToSpeech")) {
            return new LineToSpeech(database,authorField,titleField,textArea, ttsLineItem);
        }
        else if (command.equals("InfoListener")) {
            return new InfoListener(database, authorField, titleField);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * private fields
     */
    private Database database;
    private JTextArea textArea;
    private JTextField titleField;
    private JTextField authorField;
    private JMenuItem editAuthorItem, editTitleItem, ttsLineItem;
}
