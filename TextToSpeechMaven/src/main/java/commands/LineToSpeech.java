package commands;

import controller.Controller;
import model.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LineToSpeech implements ActionListener {

    /**
     * Constructor
     * @param controller
     * @param authorField
     * @param titleField
     * @param textArea
     */
    public LineToSpeech(Controller controller, ReplayManager replayManager, JTextField authorField,
                        JTextField titleField, JTextArea textArea, JMenuItem ttsLineItem,
                        JMenuItem ttsReverseLineItem)
    {
        this.replayManager      = replayManager;
        this.controller         = controller;
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
        String author = authorField.getText();
        String title = titleField.getText();

        if (controller.containsDocument(author, title)) {
            Document document = controller.getDocument(author, title);
            String text = document.getText();
            if (actionEvent.getSource() == ttsLineItem) {
                String line = JOptionPane.showInputDialog("Give me the line you want: ");
                int lineNumber = Integer.parseInt(line);
                document.playLine(lineNumber - 1);
                replayManager.addToList("LineToSpeech", author, title, text, lineNumber-1);
            }
            else if (actionEvent.getSource() == ttsReverseLineItem) {
                String line = JOptionPane.showInputDialog("Give me the line you want: ");
                int lineNumber = Integer.parseInt(line);
                document.playReversedLine(lineNumber - 1);
                replayManager.addToList("RevLineToSpeech", author, title, text, lineNumber-1);
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
    private ReplayManager   replayManager;
    private JTextArea       textArea;
    private JTextField      authorField;
    private JTextField      titleField;
    private Controller      controller;
    private JMenuItem       ttsLineItem, ttsReverseLineItem;
}
