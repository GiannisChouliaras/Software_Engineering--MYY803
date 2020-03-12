package commands;

import controller.Database;
import model.Document;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
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
                        JTextArea textArea)
    {
        this.database    = database;
        this.authorField = authorField;
        this.textArea    = textArea;
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
            String line = JOptionPane.showInputDialog("Give me the line you want: ");
            int lineNumber = Integer.parseInt(line);
            document.playLine(lineNumber-1);
            //String thisLine = textArea.getSelectedText();
            //document.playLineString(thisLine);
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
}
