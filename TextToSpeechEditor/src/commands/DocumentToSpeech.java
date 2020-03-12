package commands;

import controller.Database;
import model.Document;


import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class DocumentToSpeech implements ActionListener {

    /**
     * Constructor of the class DocumentToSpeech
     */
    public DocumentToSpeech(Database database, JTextField authorField,
                            JTextField titleField, JTextArea textArea)
    {
        this.textArea    = textArea;
        this.database    = database;
        this.authorField = authorField;
        this.titleField  = titleField;
    }

    /**
     * @param actionEvent
     * We implement here the method actionPerformed(ActionEvent actionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (database.containsDocument(authorField.getText(), titleField.getText())) {
            Document document = database.getDocument(authorField.getText(), titleField.getText());
            document.playContents();
        }
        else {
            JOptionPane.showMessageDialog(null, "No document in database.");
        }
    }

    /**
     * Private fields.
     */
    private JTextArea textArea;
    private JTextField authorField;
    private JTextField titleField;
    private Database database;
}
