package commands;

import controller.Controller;
import encodingstrategies.Rot13Encoding;
import model.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TuneEncoding implements ActionListener {

    public TuneEncoding(Controller controller, ReplayManager replayManager,
                        JTextField authorField, JTextField titleField,
                        JMenuItem atBashEncoding, JMenuItem atBashEncodingLine,
                        JMenuItem rot13Encoding, JMenuItem rot13EncodingLine)
    {
        this.controller         = controller;
        this.replayManager      = replayManager;
        this.authorField        = authorField;
        this.titleField         = titleField;
        this.atBashEncoding     = atBashEncoding;
        this.atBashEncodingLine = atBashEncodingLine;
        this.rot13Encoding      = rot13Encoding;
        this.rot13EncodingLine  = rot13EncodingLine;
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
        Document doc = controller.getDocument(author, title);
        String option = "";

        if (actionEvent.getSource() == atBashEncoding) {
            option = "AtBashEncoding";
            controller.encodeAll(author, title, option);
            replayManager.addToList("AtBash", author, title, doc.getText());
        }
        else if (actionEvent.getSource() == rot13Encoding) {
            option = "Rot13Encoding";
            controller.encodeAll(author, title, option);
            replayManager.addToList("Rot13", author, title, doc.getText());
        }
        else if (actionEvent.getSource() == atBashEncodingLine) {
            option = "AtBashEncoding";
            int line = Integer.parseInt(JOptionPane.showInputDialog("Give me line"));
            controller.encodeLine(author, title, line-1, option);
            replayManager.addToList("AtBashLine", author, title, doc.getText(), line-1);
        }
        else if (actionEvent.getSource() == rot13EncodingLine) {
            option = "Rot13Encoding";
            int line = Integer.parseInt(JOptionPane.showInputDialog("Give me line"));
            controller.encodeLine(author, title, line-1, option);
            replayManager.addToList("Rot13Line", author, title, doc.getText(), line-1);
        }

    }

    private Controller    controller;
    private ReplayManager replayManager;
    private JTextField    authorField, titleField;
    private JMenuItem     atBashEncoding, atBashEncodingLine, rot13Encoding,
            rot13EncodingLine;
}
