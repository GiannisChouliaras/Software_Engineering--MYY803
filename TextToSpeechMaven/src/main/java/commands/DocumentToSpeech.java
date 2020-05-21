package commands;

import controller.Controller;
import model.Document;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DocumentToSpeech implements ActionListener {

    public DocumentToSpeech(Controller controller, ReplayManager replayManager,
                            JTextField authorField, JTextField titleField,
                            JMenuItem ttsReverseAllItem)
    {
        this.controller        = controller;
        this.authorField       = authorField;
        this.titleField        = titleField;
        this.ttsReverseAllItem = ttsReverseAllItem;
        this.replayManager     = replayManager;
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
            if (actionEvent.getSource() == ttsReverseAllItem) {
                document.playReverseContents();
                replayManager.addToList("ReverseToSpeech", author, title, document.getText());
            }
            else {
                replayManager.addToList("DocumentToSpeech", author, title, document.getText());
                document.playContents();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No document in database.");
        }
    }


    private JTextField    authorField;
    private JTextField    titleField;
    private Controller    controller;
    private JMenuItem     ttsReverseAllItem;
    private ReplayManager replayManager;
}
