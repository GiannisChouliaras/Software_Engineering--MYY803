package commands;

import controller.Controller;
import model.Document;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class DocumentToSpeech implements ActionListener {

    /**
     * @param controller
     * @param authorField
     * @param titleField
     * @param textArea
     * @param ttsReverseAllItem
     */
    public DocumentToSpeech(Controller controller, JTextField authorField,
                            JTextField titleField, JTextArea textArea, JMenuItem ttsReverseAllItem)
    {
        this.textArea          = textArea;
        this.controller        = controller;
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
        String author = authorField.getText();
        String title  = titleField.getText();
        if (controller.containsDocument(author, title)) {
            Document document = controller.getDocument(author, title);
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
    private JTextArea  textArea;
    private JTextField authorField;
    private JTextField titleField;
    private Controller controller;
    private JMenuItem  ttsReverseAllItem;
}
