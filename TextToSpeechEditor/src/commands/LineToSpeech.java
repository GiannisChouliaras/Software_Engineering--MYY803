package commands;

import controller.Database;
import model.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LineToSpeech implements ActionListener {

    /**
     * Constructor
     * @param database
     * @param authorField
     * @param titleField
     * @param textArea
     */
    public LineToSpeech(Database database, JTextField authorField, JTextField titleField,
                        JTextArea textArea, JMenuItem ttsLineItem, JMenuItem ttsReverseLineItem)
    {
        this.database           = database;
        this.authorField        = authorField;
        this.textArea           = textArea;
        this.titleField         = titleField;
        this.ttsLineItem        = ttsLineItem;
        this.ttsReverseLineItem = ttsReverseLineItem;
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
            if (actionEvent.getSource() == ttsLineItem) {
                String line = JOptionPane.showInputDialog("Give me the line you want: ");
                int lineNumber = Integer.parseInt(line);
                document.playLine(lineNumber-1);
            }
            else if (actionEvent.getSource() == ttsReverseLineItem) {
                String line = JOptionPane.showInputDialog("Give me the line you want: ");
                int lineNumber = Integer.parseInt(line);
                document.playReversedLine(lineNumber - 1);
            }
            else {
                String thisLine = textArea.getSelectedText();
                document.playLineString(thisLine);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No document in database.");
        }
    }


    /**
     * Private Fields
     */
    private JTextArea textArea;
    private JTextField authorField;
    private JTextField titleField;
    private Database database;
    private JMenuItem ttsLineItem, ttsReverseLineItem;
}
