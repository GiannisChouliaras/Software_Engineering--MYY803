package commands;

import controller.Database;
import model.Document;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class DocumentToSpeech implements ActionListener {

    /**
     * Constructor of the class DocumentToSpeech
     */
    public DocumentToSpeech(Database database, JTextField authorField,
                            JTextField titleField, JTextArea textArea, JMenuItem ttsReverseAllItem)
    {
        this.textArea          = textArea;
        this.database          = database;
        this.authorField       = authorField;
        this.titleField        = titleField;
        this.ttsReverseAllItem = ttsReverseAllItem;
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
            if (actionEvent.getSource() == ttsReverseAllItem)
                document.playReverseContents();
            else
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
    private JMenuItem ttsReverseAllItem;
}
