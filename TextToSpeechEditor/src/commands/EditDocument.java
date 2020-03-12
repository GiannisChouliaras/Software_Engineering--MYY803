package commands;

import java.awt.event.ActionEvent;

import javax.swing.*;

import controller.Database;
import model.Document;

public class EditDocument implements ActionListener {

    /**
     * @param database
     * @param author
     * @param title
     * @param textArea
     * @param editAuthorItem
     * @param editTitleItem
     */
    public EditDocument(Database database, JTextField author, JTextField title, JTextArea textArea,
                        JMenuItem editAuthorItem, JMenuItem editTitleItem)
    {
        this.author = author;
        this.title  = title;
        this.database = database;
        this.textArea = textArea;
        this.editAuthorItem = editAuthorItem;
        this.editTitleItem = editTitleItem;
    }

    /**
     * @param actionEvent
     * We implement here the method actionPerformed(ActionEvent actionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (database.containsDocument(author.getText(), title.getText())) {
        	Document document = database.getDocument(author.getText(), title.getText());
        	if (actionEvent.getSource() == editAuthorItem) {
                String myAuthor = JOptionPane.showInputDialog(null, "give new name");
                document.setAuthor(myAuthor);
                author.setText(myAuthor);
            }
        	else if (actionEvent.getSource() == editTitleItem) {
                String myTitle = JOptionPane.showInputDialog(null, "give new title");
                document.setAuthor(myTitle);
                title.setText(myTitle);
            }
        	else {
                document.replaceContents(textArea.getText());
            }

        }
        else {
        	System.out.println("There is no doc with this author and title");
        }
    }

    /**
     * Private Fields
     */
    private Database database;
    private JTextField author;
    private JTextField title;
    private JTextArea textArea;
    private JMenuItem editAuthorItem, editTitleItem;

}
